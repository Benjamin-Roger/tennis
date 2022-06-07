package com.tennis.back.driver.repository;

import com.tennis.back.domain.entity.Player;
import com.tennis.back.domain.entity.PlayerSex;
import com.tennis.back.driver.repository.PlayerAtelierRepository.PlayerApiHandler;
import com.tennis.back.driver.repository.PlayerAtelierRepository.PlayerLocalFileHandler;
import com.tennis.back.driver.repository.PlayerWtaAtpRepository.PlayerWtaAtpRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;

import java.net.URI;
import java.net.URISyntaxException;

import static com.tennis.back.driver.repository.TestUtils.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withNoContent;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;


@RunWith(SpringRunner.class)
@SpringBootTest
@EnableConfigurationProperties
public class LocalMemoryPlayerRepositoryTest {

    @Autowired
    private PlayerRepositoryProperties properties;

    @Autowired
    private LocalMemoryPlayerRepository playerRepository;

    @Autowired
    private PlayerApiHandler playerApiHandler;

    @Autowired
    private PlayerLocalFileHandler playerLocalFileHandler;
    @Autowired
    private PlayerWtaAtpRepository playerWtaAtpRepository;


    @Test
    public void checkPropertiesAreSet() {
        assertThat(properties.getFallbackFileLocation()).isNotNull();
        assertThat(properties.getPlayersApi()).isNotNull();
    }

    @Before
    public void setup() {
        playerRepository.clearCache();
    }


    @Test
    public void validApiResponse_WillBeStoredInCache() throws URISyntaxException, NoSuchFieldException, IllegalAccessException {

        // Mock server API call
        MockRestServiceServer server = mockPlayerDataAPI("restTemplate", playerApiHandler);
        server.expect(ExpectedCount.once(), requestTo(new URI(properties.getPlayersApi())))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(getStringifiedJsonDTO("static/players/api/headtohead.json"), MediaType.APPLICATION_JSON));

        playerRepository = new LocalMemoryPlayerRepository(playerApiHandler, playerLocalFileHandler, playerWtaAtpRepository);

        playerRepository.getPlayers(); // should not be called twice as its stored in cache
        playerRepository.getPlayers(); // should not be called twice as its stored in cache
        playerRepository.getPlayers(); // should not be called twice as its stored in cache

        // Verify all expectations met
        server.verify();

        assertThat(playerRepository.getPlayers().size()).isEqualTo(3);
    }

    @Test
    public void apiCallFailure_WillFallbackOnLocalFile() throws URISyntaxException, NoSuchFieldException, IllegalAccessException {

        // Mock server API call
        createMockServer("restTemplate", playerApiHandler, properties.getPlayersApi(), withNoContent());
        playerRepository.init();

        assertThat(playerRepository.getPlayers().size()).isEqualTo(5);

    }

    @Test
    public void playerDTOFromApi_WillBeConvertedToPlayerDAOFormat() throws URISyntaxException, NoSuchFieldException, IllegalAccessException {

        // Mock server API call
        createMockServer("restTemplate", playerApiHandler, properties.getPlayersApi(), withSuccess(getStringifiedJsonDTO("static/players/api/headtohead.json"), MediaType.APPLICATION_JSON));

        playerRepository = new LocalMemoryPlayerRepository(playerApiHandler, playerLocalFileHandler, playerWtaAtpRepository);

        Player player = playerRepository.getPlayerById("52").get();

        assertThat(player.getFirstName()).isEqualTo("Novak");
        assertThat(player.getLastName()).isEqualTo("Djokovic");
        assertThat(player.getId()).isEqualTo("52");
        assertThat(player.getPicture()).isEqualTo("https://data.latelier.co/training/tennis_stats/resources/Djokovic.png");
        assertThat(player.getShortName()).isEqualTo("N.DJO");
        assertThat(player.getSex()).isEqualTo(PlayerSex.MALE);
        assertThat(player.getCountry().getCode()).isEqualTo("SRB");
        assertThat(player.getCountry().getPicture()).isEqualTo("https://data.latelier.co/training/tennis_stats/resources/Serbie.png");
        assertThat(player.getStats().getAge()).isEqualTo(31);
        assertThat(player.getStats().getHeight()).isEqualTo(188);
        assertThat(player.getStats().getWeight()).isEqualTo(80000);
        assertThat(player.getStats().getPoints()).isEqualTo(2542);
        assertThat(player.getStats().getRank()).isEqualTo(2);
        assertThat(player.getStats().getLastResults().size()).isEqualTo(5);
    }

    @Test
    public void birthdayPresentInWtaAtpRepository_shouldUpdatePlayer() throws URISyntaxException, NoSuchFieldException, IllegalAccessException {
        // Mock servers API responses
        // - from JSON API
        createMockServer("restTemplate", playerApiHandler, properties.getPlayersApi(), withSuccess(getStringifiedJsonDTO("static/players/api/headtohead.json"), MediaType.APPLICATION_JSON));
        // - from CSV WTA/ATP API
        createMockServer("restTemplate", playerWtaAtpRepository, properties.getMalePlayersAtpApi(), withSuccess(getMalePlayerStringifiedDTO(), MediaType.TEXT_PLAIN))
                .expect(requestTo(properties.getFemalePlayersWtaApi())).andRespond(withSuccess(getFemalePlayerStringifiedDTO(), MediaType.TEXT_PLAIN));

        Player malePlayer = playerRepository.getPlayerById("52").get();
        assertThat(malePlayer.getStats().getAge()).isEqualTo(35);
        assertThat(malePlayer.getStats().getBirthday()).isEqualTo("1987-05-22");

        Player femalePlayer = playerRepository.getPlayerById("95").get();
        assertThat(femalePlayer.getStats().getAge()).isEqualTo(42);
        assertThat(femalePlayer.getStats().getBirthday()).isEqualTo("1980-06-17");
    }

    @Test
    public void birthdayMissingFromWtaAtpRepository_shouldKeepInitialValue() throws URISyntaxException, NoSuchFieldException, IllegalAccessException {
        // Mock servers API responses
        // - from JSON API
        createMockServer("restTemplate", playerApiHandler, properties.getPlayersApi(), withSuccess(getStringifiedJsonDTO("static/players/api/headtohead.json"), MediaType.APPLICATION_JSON));
        // - from CSV WTA/ATP API
        createMockServer("restTemplate", playerWtaAtpRepository, properties.getMalePlayersAtpApi(), withSuccess(getMalePlayerStringifiedDTO(), MediaType.TEXT_PLAIN))
                .expect(requestTo(properties.getFemalePlayersWtaApi())).andRespond(withSuccess(getFemalePlayerStringifiedDTO(), MediaType.TEXT_PLAIN));

        Player player = playerRepository.getPlayerById("102").get();
        assertThat(player.getStats().getAge()).isEqualTo(37);
        assertThat(player.getStats().getBirthday()).isNull();
    }
}
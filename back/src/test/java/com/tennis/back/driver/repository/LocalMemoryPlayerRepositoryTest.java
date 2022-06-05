package com.tennis.back.driver.repository;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tennis.back.domain.entity.Player;
import com.tennis.back.domain.entity.PlayerSex;
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
import org.springframework.web.client.RestTemplate;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URI;
import java.net.URISyntaxException;

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
        MockRestServiceServer server = mockPlayerDataAPI();
        server.expect(ExpectedCount.once(), requestTo(new URI(properties.getPlayersApi())))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(getStringifiedDTO(), MediaType.APPLICATION_JSON));

        playerRepository = new LocalMemoryPlayerRepository(playerApiHandler, playerLocalFileHandler);

        playerRepository.findAllPlayers(); // should not be called twice as its stored in cache
        playerRepository.findAllPlayers(); // should not be called twice as its stored in cache
        playerRepository.findAllPlayers(); // should not be called twice as its stored in cache

        // Verify all expectations met
        server.verify();

        assertThat(playerRepository.findAllPlayers().size()).isEqualTo(2);
    }

    @Test
    public void apiCallFailure_WillFallbackOnLocalFile() throws URISyntaxException, NoSuchFieldException, IllegalAccessException {

        // Mock server API call
        MockRestServiceServer server = mockPlayerDataAPI();
        server.expect(ExpectedCount.once(), requestTo(new URI(properties.getPlayersApi())))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withNoContent());
        playerRepository.init();

        assertThat(playerRepository.findAllPlayers().size()).isEqualTo(5);

    }

    private MockRestServiceServer mockPlayerDataAPI() throws NoSuchFieldException, IllegalAccessException, URISyntaxException {
        Field reader = PlayerApiHandler.class.getDeclaredField("restTemplate");
        reader.setAccessible(true);
        RestTemplate restTemplate = (RestTemplate) reader.get(playerApiHandler);
        MockRestServiceServer server = MockRestServiceServer.bindTo(restTemplate).build();
        return server;
    }

    private String getStringifiedDTO() {
        try (InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("static/players/api/headtohead.json")) {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readValue(in, JsonNode.class);
            return mapper.writeValueAsString(jsonNode);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void playerDTOFromApi_WillBeConvertedToPlayerDAOFormat() throws URISyntaxException, NoSuchFieldException, IllegalAccessException {

        // Mock server API call
        MockRestServiceServer server = mockPlayerDataAPI();
        server.expect(ExpectedCount.once(), requestTo(new URI(properties.getPlayersApi())))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(getStringifiedDTO(), MediaType.APPLICATION_JSON));

        playerRepository = new LocalMemoryPlayerRepository(playerApiHandler, playerLocalFileHandler);

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
    public void missingBirthday_shouldBeFetchedFromBirthdayRepository() {
        // assertThat(player.getStats().getBirthday()).isEqualTo("SER");
    }
}
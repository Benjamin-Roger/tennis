package com.tennis.back.driver.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import static com.tennis.back.driver.repository.TestUtils.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableConfigurationProperties
public class PlayerWikiRepositoryTest {

    @Autowired
    PlayerWikiRepository playerWikiRepository;

    @Autowired
    PlayerRepositoryProperties properties;

    @Test
    public void onStartUp_willLoadCsvFileFetched() throws URISyntaxException, NoSuchFieldException, IllegalAccessException {
        // Mock server API call
        MockRestServiceServer server = createMockServer("restTemplate", playerWikiRepository, properties.getMalePlayersWikiApi(), withSuccess(getMalePlayerStringifiedDTO(), MediaType.TEXT_PLAIN));
        server
                .expect(requestTo(properties.getFemalePlayersWikiApi()))
                .andRespond(withSuccess(getFemalePlayerStringifiedDTO(), MediaType.TEXT_PLAIN));

        List<WikiPlayer> players = playerWikiRepository.getPlayers();

        assertThat(players.size()).isEqualTo(4);
    }

    @Test
    public void getPlayerByFirstLastNameCountry_willReturnWikiPlayer() throws URISyntaxException, NoSuchFieldException, IllegalAccessException {
        // Mock server API call
        MockRestServiceServer server = createMockServer("restTemplate", playerWikiRepository, properties.getMalePlayersWikiApi(), withSuccess(getMalePlayerStringifiedDTO(), MediaType.TEXT_PLAIN));
        server
                .expect(requestTo(properties.getFemalePlayersWikiApi()))
                .andRespond(withSuccess(getFemalePlayerStringifiedDTO(), MediaType.TEXT_PLAIN));

        Optional<WikiPlayer> malePlayer = playerWikiRepository.getPlayer("Novak", "Djokovic", "SRB");
        assertThat(malePlayer.isPresent()).isTrue();
        assertThat(malePlayer.get().dob).isEqualTo("19870522");

        Optional<WikiPlayer> femalePlayer = playerWikiRepository.getPlayer("Venus", "Williams", "USA");
        assertThat(femalePlayer.isPresent()).isTrue();
        assertThat(femalePlayer.get().dob).isEqualTo("19800617");
    }

}
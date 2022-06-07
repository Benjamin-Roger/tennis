package com.tennis.back.driver.repository;

import com.tennis.back.driver.repository.PlayerWtaAtpRepository.PlayerWtaAtp;
import com.tennis.back.driver.repository.PlayerWtaAtpRepository.PlayerWtaAtpRepository;
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
public class PlayerWtaAtpRepositoryTest {

    @Autowired
    PlayerWtaAtpRepository playerWtaAtpRepository;

    @Autowired
    PlayerRepositoryProperties properties;

    @Test
    public void onStartUp_willLoadCsvFileFetched() throws URISyntaxException, NoSuchFieldException, IllegalAccessException {
        // Mock server API call
        MockRestServiceServer server = createMockServer("restTemplate", playerWtaAtpRepository, properties.getMalePlayersAtpApi(), withSuccess(getMalePlayerStringifiedDTO(), MediaType.TEXT_PLAIN));
        server
                .expect(requestTo(properties.getFemalePlayersWtaApi()))
                .andRespond(withSuccess(getFemalePlayerStringifiedDTO(), MediaType.TEXT_PLAIN));

        List<PlayerWtaAtp> players = playerWtaAtpRepository.getPlayers();

        assertThat(players.size()).isEqualTo(4);
    }

    @Test
    public void getPlayerByFirstLastNameCountry_willReturnPlayer() throws URISyntaxException, NoSuchFieldException, IllegalAccessException {
        // Mock server API call
        MockRestServiceServer server = createMockServer("restTemplate", playerWtaAtpRepository, properties.getMalePlayersAtpApi(), withSuccess(getMalePlayerStringifiedDTO(), MediaType.TEXT_PLAIN));
        server
                .expect(requestTo(properties.getFemalePlayersWtaApi()))
                .andRespond(withSuccess(getFemalePlayerStringifiedDTO(), MediaType.TEXT_PLAIN));

        Optional<PlayerWtaAtp> malePlayer = playerWtaAtpRepository.getPlayer("Novak", "Djokovic", "SRB");
        assertThat(malePlayer.isPresent()).isTrue();
        assertThat(malePlayer.get().dob).isEqualTo("19870522");

        Optional<PlayerWtaAtp> femalePlayer = playerWtaAtpRepository.getPlayer("Venus", "Williams", "USA");
        assertThat(femalePlayer.isPresent()).isTrue();
        assertThat(femalePlayer.get().dob).isEqualTo("19800617");
    }

}
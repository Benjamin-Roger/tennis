package com.tennis.back.driver.repository.PlayerWtaAtpRepository;

import com.tennis.back.driver.repository.PlayerRepositoryProperties;
import com.tennis.back.driver.web.utils.csvParser.CsvHttpMessageConverter;
import com.tennis.back.driver.web.utils.csvParser.CsvParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class PlayerWtaAtpRepository {

    private final PlayerRepositoryProperties properties;
    private final RestTemplate restTemplate = restTemplate();
    private final Logger LOGGER = LoggerFactory.getLogger(PlayerWtaAtpRepository.class);
    private List<PlayerWtaAtp> players = Collections.emptyList();

    public PlayerWtaAtpRepository(PlayerRepositoryProperties properties) {
        this.properties = properties;
        init();
    }

    public CsvParser<PlayerWtaAtp> csvParser() {
        return new PlayerWtaAtpCsvParser();
    }

    public RestTemplate restTemplate() {
        RestTemplate result = new RestTemplate();
        CsvHttpMessageConverter<PlayerWtaAtp> messageConverter = new CsvHttpMessageConverter<>(
                this.csvParser(),
                MediaType.ALL
        );
        result.getMessageConverters().add(messageConverter);
        return result;
    }

    public Optional<PlayerWtaAtp> getPlayer(String firstName, String lastName, String countryCode) {
        LOGGER.info("Looking for player {} {} {}", firstName, lastName, countryCode);
        return getPlayers()
                .stream()
                .filter(player -> firstName.equalsIgnoreCase(player.nameFirst) && lastName.equalsIgnoreCase(player.nameLast) && countryCode.equalsIgnoreCase(player.ioc))
                .findFirst();
    }

    private void fetchAndLoadPlayers() {
        try {

            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.TEXT_PLAIN));
            HttpEntity<Void> request = new HttpEntity<>(headers);

            ResponseEntity<List<PlayerWtaAtp>> malePlayers = restTemplate.exchange(
                    properties.getMalePlayersAtpApi(),
                    HttpMethod.GET,
                    request,
                    new ParameterizedTypeReference<>() {
                    }
            );

            ResponseEntity<List<PlayerWtaAtp>> femalePlayers = restTemplate.exchange(
                    properties.getFemalePlayersWtaApi(),
                    HttpMethod.GET,
                    request,
                    new ParameterizedTypeReference<>() {
                    }
            );

            List<PlayerWtaAtp> players = new ArrayList<>();
            players.addAll(malePlayers.getBody());
            players.addAll(femalePlayers.getBody());

            this.players = players;
        } catch (Exception exception) {
            this.players = Collections.emptyList();
        }
    }

    public List<PlayerWtaAtp> getPlayers() {
        if (this.players.isEmpty()) {
            fetchAndLoadPlayers();
        }

        return this.players;
    }

    private void init() {
        fetchAndLoadPlayers();
        LOGGER.info("[PlayerWtaAtpRepository] Finished DB initialization");
    }
}

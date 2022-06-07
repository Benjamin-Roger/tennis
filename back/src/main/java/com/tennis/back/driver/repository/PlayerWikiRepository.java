package com.tennis.back.driver.repository;

import com.opencsv.bean.CsvBindByPosition;
import com.tennis.back.utils.csvParser.CsvHttpMessageConverter;
import com.tennis.back.utils.csvParser.CsvParser;
import com.tennis.back.utils.csvParser.DefaultCsvParser;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class PlayerWikiRepository {

    private final PlayerRepositoryProperties properties;
    private final RestTemplate restTemplate = restTemplate();
    private List<WikiPlayer> players = Collections.emptyList();

    public PlayerWikiRepository(PlayerRepositoryProperties properties) {
        this.properties = properties;
    }

    public CsvParser<WikiPlayer> csvParser() {
        return new WikiPlayerCsvParser();
    }

    public RestTemplate restTemplate() {
        RestTemplate result = new RestTemplate();
        CsvHttpMessageConverter<WikiPlayer> messageConverter = new CsvHttpMessageConverter<>(
                this.csvParser(),
                MediaType.ALL
        );
        result.getMessageConverters().add(messageConverter);
        return result;
    }

    public Optional<WikiPlayer> getPlayer(String firstName, String lastName, String countryCode) {
        return getPlayers()
                .stream()
                .filter(player -> firstName.matches(player.nameFirst) && lastName.matches(player.nameLast) && countryCode.matches(player.ioc))
                .findFirst();
    }

    private void fetchAndLoadPlayers() {
        try {

            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.TEXT_PLAIN));
            HttpEntity<Void> request = new HttpEntity<>(headers);

            ResponseEntity<List<WikiPlayer>> malePlayers = restTemplate.exchange(
                    properties.getMalePlayersWikiApi(),
                    HttpMethod.GET,
                    request,
                    new ParameterizedTypeReference<>() {
                    }
            );

            ResponseEntity<List<WikiPlayer>> femalePlayers = restTemplate.exchange(
                    properties.getFemalePlayersWikiApi(),
                    HttpMethod.GET,
                    request,
                    new ParameterizedTypeReference<>() {
                    }
            );

            List<WikiPlayer> players = new ArrayList<>();
            players.addAll(malePlayers.getBody());
            players.addAll(femalePlayers.getBody());

            this.players = players;
        } catch (Exception exception) {
            this.players = Collections.emptyList();
        }
    }

    public List<WikiPlayer> getPlayers() {
        if (this.players.isEmpty()) {
            fetchAndLoadPlayers();
        }

        return this.players;
    }
}

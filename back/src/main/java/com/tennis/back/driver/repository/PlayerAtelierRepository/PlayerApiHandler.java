package com.tennis.back.driver.repository.PlayerAtelierRepository;

import com.tennis.back.driver.repository.PlayerRepositoryProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Component
public class PlayerApiHandler implements GetPlayerSourceInterface {

    private final PlayerRepositoryProperties properties;
    private RestTemplate restTemplate = new RestTemplate();

    public PlayerApiHandler(PlayerRepositoryProperties properties) {
        this.properties = properties;
    }

    public List<PlayerResponseDTO.Player> getPlayers() throws URISyntaxException {
        return restTemplate.getForObject(new URI(properties.getPlayersApi()), PlayerResponseDTO.class).getPlayers();
    }
}

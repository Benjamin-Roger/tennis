package com.tennis.back.driver.repository.PlayerAtelierRepository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tennis.back.driver.repository.PlayerRepositoryProperties;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

@Component
public class PlayerLocalFileHandler implements GetPlayerSourceInterface {

    private final PlayerRepositoryProperties properties;
    private final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public PlayerLocalFileHandler(PlayerRepositoryProperties properties) {
        this.properties = properties;
    }

    @Override
    public List<PlayerResponseDTO.Player> getPlayers() {
        try (InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(properties.getFallbackFileLocation())) {
            PlayerResponseDTO playerFile = OBJECT_MAPPER.readValue(in, PlayerResponseDTO.class);
            return playerFile.getPlayers();
        } catch (Exception e) {
            throw new InvalidFileException(e);
        }
    }
}

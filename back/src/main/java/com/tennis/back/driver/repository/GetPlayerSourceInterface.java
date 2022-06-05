package com.tennis.back.driver.repository;

import java.net.URISyntaxException;
import java.util.List;

public interface GetPlayerSourceInterface {
    List<PlayerResponseDTO.Player> getPlayers() throws URISyntaxException;
}

package com.tennis.back.interfaceAdapter.gateway;

import com.tennis.back.domain.entity.Player;

import java.util.Optional;

public interface GetPlayerRepositoryInterface {
    Optional<Player> getPlayerById(String id);
}

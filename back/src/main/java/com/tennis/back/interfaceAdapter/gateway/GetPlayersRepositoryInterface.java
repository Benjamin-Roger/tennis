package com.tennis.back.interfaceAdapter.gateway;

import com.tennis.back.domain.entity.Player;

import java.util.List;

public interface GetPlayersRepositoryInterface {
    List<Player> getPlayers();
}

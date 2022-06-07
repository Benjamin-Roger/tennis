package com.tennis.back.domain.useCase.GetPlayersUseCase;

import com.tennis.back.domain.entity.Player;

import java.util.List;

public interface GetPlayersGatewayInterface {
    List<Player> getPlayers();
}

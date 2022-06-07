package com.tennis.back.interfaceAdapter.gateway;

import com.tennis.back.domain.entity.Player;
import com.tennis.back.domain.useCase.GetPlayerUseCase.GetPlayerRequest;
import com.tennis.back.domain.useCase.GetPlayerUseCase.GetPlayerGatewayInterface;
import com.tennis.back.domain.useCase.GetPlayersUseCase.GetPlayersGatewayInterface;

import java.util.List;
import java.util.Optional;

public class PlayerGateway implements GetPlayerGatewayInterface, GetPlayersGatewayInterface {
    PlayerRepositoryInterface repository;

    public PlayerGateway(PlayerRepositoryInterface repository) {
        this.repository = repository;
    }

    @Override
    public Player getPlayer(GetPlayerRequest request) {
        Optional<Player> player =  repository.getPlayerById(request.getId());
        return player.orElse(null);
    }

    @Override
    public List<Player> getPlayers() {
        return repository.getPlayers();
    }
}

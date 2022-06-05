package com.tennis.back.interfaceAdapter.gateway;

import com.tennis.back.domain.entity.Player;
import com.tennis.back.domain.useCase.GetPlayerRequest;
import com.tennis.back.domain.useCase.PlayerGatewayInterface;

import java.util.Optional;

public class PlayerGateway implements PlayerGatewayInterface {

    GetPlayerRepositoryInterface repository;

    public PlayerGateway(GetPlayerRepositoryInterface repository) {
        this.repository = repository;
    }

    @Override
    public Player getPlayer(GetPlayerRequest request) {
        Optional<Player> player =  repository.getPlayerById(request.getId());

        return player.orElse(null);
    }
}

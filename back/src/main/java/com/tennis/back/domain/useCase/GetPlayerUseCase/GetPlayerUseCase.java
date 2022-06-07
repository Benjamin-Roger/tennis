package com.tennis.back.domain.useCase.GetPlayerUseCase;

import com.tennis.back.domain.entity.Player;
import com.tennis.back.domain.useCase.NoPlayerFoundException;

public class GetPlayerUseCase {
    GetPlayerGatewayInterface gateway;

    public GetPlayerUseCase(GetPlayerGatewayInterface gateway) {
        this.gateway = gateway;
    }

    public void execute(GetPlayerRequest request, PlayerPresenterInterface presenter) throws NoPlayerFoundException {
        Player player = gateway.getPlayer(request);

        if(player == null) {
            throw new NoPlayerFoundException(String.format("Player with id %s has not been found", request.getId()));
        }

        GetPlayerResponse response = new GetPlayerResponse();
        response.setPlayer(player);

        presenter.updatePlayer(response);
    }
}

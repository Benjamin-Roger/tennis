package com.tennis.back.domain.useCase.GetPlayersUseCase;

import com.tennis.back.domain.entity.Player;
import com.tennis.back.domain.useCase.NoPlayerFoundException;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class GetPlayersUseCase {
    GetPlayersGatewayInterface gateway;

    public GetPlayersUseCase(GetPlayersGatewayInterface gateway) {
        this.gateway = gateway;
    }

    public void execute(PlayersPresenterInterface presenter) throws NoPlayerFoundException {
        List<Player> players = gateway.getPlayers()
                .stream()
                .sorted(Comparator.comparingInt(p -> p.getStats().getRank()))
                .collect(Collectors.toList());

        GetPlayersResponse response = new GetPlayersResponse();
        response.setPlayers(players);

        presenter.updatePlayers(response);
    }
}

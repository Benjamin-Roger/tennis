package com.tennis.back.domain.useCase.GetPlayersUseCase;

import com.tennis.back.domain.entity.Player;

import java.util.List;

public class GetPlayersResponse {
    private List<Player> players;

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}

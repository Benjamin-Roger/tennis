package com.tennis.back.domain.useCase;

import com.tennis.back.domain.entity.Player;

public class GetPlayerResponse {
    private Player player;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}

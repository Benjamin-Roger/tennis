package com.tennis.back.domain.useCase;

import com.tennis.back.domain.entity.Player;

public interface PlayerGatewayInterface {
    Player getPlayer(GetPlayerRequest request);
}

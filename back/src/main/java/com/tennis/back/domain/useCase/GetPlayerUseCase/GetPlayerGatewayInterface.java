package com.tennis.back.domain.useCase.GetPlayerUseCase;

import com.tennis.back.domain.entity.Player;
import com.tennis.back.domain.useCase.GetPlayerUseCase.GetPlayerRequest;

public interface GetPlayerGatewayInterface {
    Player getPlayer(GetPlayerRequest request);
}

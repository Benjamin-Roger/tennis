package com.tennis.back.interfaceAdapter.presenter;

import com.tennis.back.domain.useCase.GetPlayerResponse;
import com.tennis.back.domain.useCase.PlayerPresenterInterface;

public class PlayerPresenter implements PlayerPresenterInterface {
    private GetPlayerDetailResponse playerDetailResponse;

    @Override
    public void updatePlayer(GetPlayerResponse response) {
        this.playerDetailResponse = GetPlayerDetailResponse.of(response.getPlayer());
    }

    public GetPlayerDetailResponse getPlayerDetailResponse() {
        return playerDetailResponse;
    }
}

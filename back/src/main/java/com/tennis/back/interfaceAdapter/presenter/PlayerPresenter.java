package com.tennis.back.interfaceAdapter.presenter;

import com.tennis.back.domain.useCase.GetPlayerUseCase.GetPlayerResponse;
import com.tennis.back.domain.useCase.GetPlayerUseCase.PlayerPresenterInterface;
import com.tennis.back.domain.useCase.GetPlayersUseCase.GetPlayersResponse;
import com.tennis.back.domain.useCase.GetPlayersUseCase.PlayersPresenterInterface;

public class PlayerPresenter implements PlayerPresenterInterface, PlayersPresenterInterface {
    private GetPlayerDetailResponse playerDetailResponse;
    private GetPlayersSummariesResponse playerSummariesResponse;

    @Override
    public void updatePlayer(GetPlayerResponse response) {
        this.playerDetailResponse = GetPlayerDetailResponse.of(response.getPlayer());
    }

    public GetPlayerDetailResponse getPlayerDetailResponse() {
        return this.playerDetailResponse;
    }

    @Override
    public void updatePlayers(GetPlayersResponse response) {
        this.playerSummariesResponse = GetPlayersSummariesResponse.of(response.getPlayers());
    }

    public GetPlayersSummariesResponse getPlayerSummariesResponse() {
        return this.playerSummariesResponse;
    }
}

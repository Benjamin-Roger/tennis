package com.tennis.back.interfaceAdapter.controller;

import com.tennis.back.domain.useCase.GetAveragePlayerBmiUseCase.GetAveragePlayerBmiUseCase;
import com.tennis.back.domain.useCase.GetHighestCountryWinRatioUseCase.GetHighestCountryWinRatioUseCase;
import com.tennis.back.domain.useCase.GetPlayersMedianHeightUseCase.GetPlayersMedianHeightUseCase;
import com.tennis.back.interfaceAdapter.gateway.PlayerGateway;
import com.tennis.back.interfaceAdapter.presenter.StatsPresenter;

public class StatsController {

    private final PlayerGateway gateway;
    private final GetAveragePlayerBmiUseCase getAveragePlayerBmiUseCase;
    private final GetPlayersMedianHeightUseCase getPlayersMedianHeightUseCase;
    private final GetHighestCountryWinRatioUseCase getHighestCountryWinRatioUseCase;


    public StatsController(PlayerGateway gateway) {
        this.gateway = gateway;
        this.getAveragePlayerBmiUseCase = new GetAveragePlayerBmiUseCase(gateway);
        this.getPlayersMedianHeightUseCase = new GetPlayersMedianHeightUseCase(gateway);
        this.getHighestCountryWinRatioUseCase = new GetHighestCountryWinRatioUseCase(gateway);
    }

    public void getGeneralStats(StatsPresenter presenter) {
        getAveragePlayerBmiUseCase.execute(presenter);
        getPlayersMedianHeightUseCase.execute(presenter);
        getHighestCountryWinRatioUseCase.execute(presenter);
    }

}

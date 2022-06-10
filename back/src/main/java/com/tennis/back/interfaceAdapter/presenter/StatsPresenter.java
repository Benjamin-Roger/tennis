package com.tennis.back.interfaceAdapter.presenter;

import com.tennis.back.domain.useCase.GetAveragePlayerBmiUseCase.AveragePlayerBmiPresenterInterface;
import com.tennis.back.domain.useCase.GetAveragePlayerBmiUseCase.GetAveragePlayerBmiStatResponse;
import com.tennis.back.domain.useCase.GetHighestCountryWinRatioUseCase.GetHighestCountryWinRatioStatResponse;
import com.tennis.back.domain.useCase.GetHighestCountryWinRatioUseCase.HighestCountryWinRatioPresenterInterface;
import com.tennis.back.domain.useCase.GetPlayersMedianHeightUseCase.GetPlayerMedianHeightResponse;
import com.tennis.back.domain.useCase.GetPlayersMedianHeightUseCase.PlayerMedianHeightPresenterInterface;

public class StatsPresenter implements PlayerMedianHeightPresenterInterface, HighestCountryWinRatioPresenterInterface, AveragePlayerBmiPresenterInterface {

    GetAveragePlayerBmiStatResponse bmi;
    GetHighestCountryWinRatioStatResponse winRatio;
    GetPlayerMedianHeightResponse medianHeight;

    @Override
    public void updateAveragePlayerBmiStat(GetAveragePlayerBmiStatResponse response) {
        this.bmi = response;
    }

    @Override
    public void updateHighestCountryWinRatioStat(GetHighestCountryWinRatioStatResponse response) {
        this.winRatio = response;
    }

    @Override
    public void updateMedianHeightStat(GetPlayerMedianHeightResponse response) {
        this.medianHeight = response;
    }

    public GeneralStatsDTOModel getDTOModel() {
        return new GeneralStatsDTOModel(this.bmi, this.winRatio, this.medianHeight);
    }

}

package com.tennis.back.interfaceAdapter.presenter;

import com.tennis.back.domain.useCase.GetAveragePlayerBmiUseCase.GetAveragePlayerBmiStatResponse;
import com.tennis.back.domain.useCase.GetHighestCountryWinRatioUseCase.GetHighestCountryWinRatioStatResponse;
import com.tennis.back.domain.useCase.GetPlayersMedianHeightUseCase.GetPlayerMedianHeightResponse;

public class GeneralStatsDTOModel {
    private GetAveragePlayerBmiStatResponse bmi;
    private GetHighestCountryWinRatioStatResponse winRatio;
    private GetPlayerMedianHeightResponse medianHeight;

    public GeneralStatsDTOModel(GetAveragePlayerBmiStatResponse bmi, GetHighestCountryWinRatioStatResponse winRatio, GetPlayerMedianHeightResponse medianHeight) {
        this.bmi = bmi;
        this.winRatio = winRatio;
        this.medianHeight = medianHeight;
    }

    public GetAveragePlayerBmiStatResponse getBmi() {
        return bmi;
    }

    public GetHighestCountryWinRatioStatResponse getWinRatio() {
        return winRatio;
    }

    public GetPlayerMedianHeightResponse getMedianHeight() {
        return medianHeight;
    }
}
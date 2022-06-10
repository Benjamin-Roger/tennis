package com.tennis.back.interfaceAdapter.gateway;

import com.tennis.back.domain.entity.Player;
import com.tennis.back.domain.useCase.GetAveragePlayerBmiUseCase.BMIData;
import com.tennis.back.domain.useCase.GetHighestCountryWinRatioUseCase.WinData;

import java.util.List;

public interface GetStatsRepositoryInterface {
    List<BMIData> getPlayersBmiData();

    List<WinData> getPlayersLastResults();

    List<Integer> getPlayersHeight();
}

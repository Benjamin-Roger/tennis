package com.tennis.back.domain.useCase.GetPlayersMedianHeightUseCase;

import com.tennis.back.domain.useCase.NoPlayerFoundException;

import java.util.List;

public class GetPlayersMedianHeightUseCase {

    private final GetPlayersHeightGatewayInterface gateway;

    public GetPlayersMedianHeightUseCase(GetPlayersHeightGatewayInterface gateway) {
        this.gateway = gateway;
    }

    public void execute(PlayerMedianHeightPresenterInterface presenter) throws NoPlayerFoundException {
        List<Integer> playersHeight = gateway.getPlayersHeight();

        MedianCalculator medianCalculator = new MedianCalculator();
        playersHeight.forEach(medianCalculator::add);

        GetPlayerMedianHeightResponse response = new GetPlayerMedianHeightResponse();
        response.setPlayersMedianHeight(medianCalculator.getMedian());

        presenter.updateMedianHeightStat(response);
    }

}

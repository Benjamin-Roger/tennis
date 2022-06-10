package com.tennis.back.domain.useCase.GetAveragePlayerBmiUseCase;

import com.tennis.back.domain.useCase.NoPlayerFoundException;

import java.util.List;

public class GetAveragePlayerBmiUseCase {

    private final GetPlayersBmiDataGatewayInterface gateway;

    public GetAveragePlayerBmiUseCase(GetPlayersBmiDataGatewayInterface gateway) {
        this.gateway = gateway;
    }

    public void execute(AveragePlayerBmiPresenterInterface presenter) throws NoPlayerFoundException {
        List<BMIData> playersWeightAndHeight = gateway.getPlayersBmiData();
        Double averageBmi = playersWeightAndHeight
                .stream()
                .mapToDouble(data-> calculateBmi(data))
                .average()
                .getAsDouble();

        GetAveragePlayerBmiStatResponse response = new GetAveragePlayerBmiStatResponse();
        response.setAveragePlayerBmi(averageBmi);

        presenter.updateAveragePlayerBmiStat(response);
    }

    // BMI= M/T^2
    private Double calculateBmi(BMIData data) {
        return data.weight/1000d / Math.pow(data.height/100d, 2);
    }
}

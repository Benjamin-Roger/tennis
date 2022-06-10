package com.tennis.back.domain.useCase.GetHighestCountryWinRatioUseCase;

import com.tennis.back.domain.useCase.NoPlayerFoundException;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GetHighestCountryWinRatioUseCase {

    private final GetPlayersWinDataGatewayInterface gateway;

    public GetHighestCountryWinRatioUseCase(GetPlayersWinDataGatewayInterface gateway) {
        this.gateway = gateway;
    }

    public void execute(HighestCountryWinRatioPresenterInterface presenter) throws NoPlayerFoundException {
        List<WinData> playersLastResults = gateway.getPlayersLastResults();

        Map<String, List<Integer>> resultsPerCountry = playersLastResults
                .stream()
                .collect(Collectors.groupingBy(
                        WinData::getCountryCode,
                        Collectors.flatMapping(d -> d.getLastResults().stream(), Collectors.toList())
                ));


        Map<String, Double> winRatioPerCountry = resultsPerCountry
                .keySet()
                .stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        country -> resultsPerCountry.get(country).stream().mapToDouble(a -> a).average().getAsDouble()
                ));

        String countryWithHighestWinRatio = winRatioPerCountry.keySet()
                .stream()
                .max(Comparator.comparing(winRatioPerCountry::get))
                .get();

        GetHighestCountryWinRatioStatResponse response = new GetHighestCountryWinRatioStatResponse();
        response.setCountryCode(countryWithHighestWinRatio);
        response.setWinRatio(winRatioPerCountry.get(countryWithHighestWinRatio));

        presenter.updateHighestCountryWinRatioStat(response);
    }

}

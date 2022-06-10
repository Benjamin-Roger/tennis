package com.tennis.back.domain.useCase.GetHighestCountryWinRatioUseCase;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class GetHighestCountryWinRatioUseCaseTest {

    @Test
    public void singleCountry_willReturnCountryNameAndRatio() {
        WinData winData = new WinData();
        winData.setCountryCode("SRB");
        winData.setLastResults(Arrays.asList(1,1,1,1,1));

        GetHighestCountryWinRatioUseCase useCase = new GetHighestCountryWinRatioUseCase(new MockPlayerWinDataGateway(Collections.singletonList(winData)));

        MockCountryRatioPresenter presenter = new MockCountryRatioPresenter();
        useCase.execute(presenter);

        assertThat(presenter.res.getCountryCode()).isEqualTo(winData.getCountryCode());
        assertThat(presenter.res.getWinRatio()).isEqualTo(1d);
    }


    @Test
    public void multipleCountries_willReturnCountryWithHighestWinRatio() {
        WinData winDataHighestRatio = new WinData();
        winDataHighestRatio.setCountryCode("SRB");
        winDataHighestRatio.setLastResults(Arrays.asList(1,1,1,1,1));

        WinData winDataLowestRatio = new WinData();
        winDataLowestRatio.setCountryCode("USA");
        winDataLowestRatio.setLastResults(Arrays.asList(0,0,0,0,0));

        GetHighestCountryWinRatioUseCase useCase = new GetHighestCountryWinRatioUseCase(new MockPlayerWinDataGateway(List.of(winDataHighestRatio, winDataLowestRatio)));

        MockCountryRatioPresenter presenter = new MockCountryRatioPresenter();
        useCase.execute(presenter);

        assertThat(presenter.res.getCountryCode()).isEqualTo(winDataHighestRatio.getCountryCode());
        assertThat(presenter.res.getWinRatio()).isEqualTo(1d);
    }


    @Test
    public void multipleCountriesWithMultiplePlayers_willReturnCountryWithHighestWinRatioAveraged() {
        // First country, expected highest win ratio
        String HIGHEST_WIN_RATIO_COUNTRY = "SRB";
        WinData strongPlayerFromHighWinRatioCountry = new WinData();
        strongPlayerFromHighWinRatioCountry.setCountryCode(HIGHEST_WIN_RATIO_COUNTRY);
        strongPlayerFromHighWinRatioCountry.setLastResults(Arrays.asList(1,1,0));

        WinData weakPlayerFromHighWinRatioCountry = new WinData();
        weakPlayerFromHighWinRatioCountry.setCountryCode(HIGHEST_WIN_RATIO_COUNTRY);
        weakPlayerFromHighWinRatioCountry.setLastResults(Arrays.asList(0,0,1,1,1,1,1));

        // Second country - lower average win ratio
        WinData weakPlayerFromLowWinRatioCountry = new WinData();
        weakPlayerFromLowWinRatioCountry.setCountryCode("USA");
        weakPlayerFromLowWinRatioCountry.setLastResults(Arrays.asList(1,1,1,0,0));

        GetHighestCountryWinRatioUseCase useCase = new GetHighestCountryWinRatioUseCase(new MockPlayerWinDataGateway(List.of(strongPlayerFromHighWinRatioCountry, weakPlayerFromHighWinRatioCountry, weakPlayerFromLowWinRatioCountry)));

        MockCountryRatioPresenter presenter = new MockCountryRatioPresenter();
        useCase.execute(presenter);

        assertThat(presenter.res.getCountryCode()).isEqualTo(HIGHEST_WIN_RATIO_COUNTRY);
        assertThat(presenter.res.getWinRatio()).isEqualTo(0.7d);
    }

    private static class MockPlayerWinDataGateway implements GetPlayersWinDataGatewayInterface {
        private final List<WinData> placeholder;

        public MockPlayerWinDataGateway(List<WinData> placeholder) {
            this.placeholder = placeholder;
        }

        @Override
        public List<WinData> getPlayersLastResults() {
            return this.placeholder;
        }
    }

    private static class MockCountryRatioPresenter implements HighestCountryWinRatioPresenterInterface {
        public GetHighestCountryWinRatioStatResponse res;

        @Override
        public void updateHighestCountryWinRatioStat(GetHighestCountryWinRatioStatResponse response) {
            this.res = response;
        }
    }
}
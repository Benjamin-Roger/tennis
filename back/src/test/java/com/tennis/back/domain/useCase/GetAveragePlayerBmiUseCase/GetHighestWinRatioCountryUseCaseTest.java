package com.tennis.back.domain.useCase.GetAveragePlayerBmiUseCase;

import org.assertj.core.data.Offset;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class GetHighestWinRatioCountryUseCaseTest {

    @Test
    public void bmiData_isCorrectlyCalculated() {
        BMIData bmiData = new BMIData();
        bmiData.weight = 100000;
        bmiData.height = 180;

        GetAveragePlayerBmiUseCase useCase = new GetAveragePlayerBmiUseCase(new BmiGatewayMock(Collections.singletonList(bmiData)));
        MockAveragePlayerBmiPresenter presenter = new MockAveragePlayerBmiPresenter();
        useCase.execute(presenter);

        assertThat(Math.round(presenter.res.getAveragePlayerBmi()*100)/100d).isEqualTo(30.86d);
    }

    @Test
    public void bmiDataAverage_isCorrectlyCalculated() {
        BMIData bmiData0 = new BMIData();
        bmiData0.weight = 75000;
        bmiData0.height = 160;

        BMIData bmiData1 = new BMIData();
        bmiData1.weight = 100000;
        bmiData1.height = 180;

        GetAveragePlayerBmiUseCase useCase = new GetAveragePlayerBmiUseCase(new BmiGatewayMock(List.of(bmiData0, bmiData1)));

        MockAveragePlayerBmiPresenter presenter = new MockAveragePlayerBmiPresenter();
        useCase.execute(presenter);

        assertThat(Math.round(presenter.res.getAveragePlayerBmi()*100)/100d).isEqualTo(30.08d);
    }

    private class BmiGatewayMock implements GetPlayersBmiDataGatewayInterface {

        private final List<BMIData> placeholder;

        public BmiGatewayMock(List<BMIData> placeholder) {
            this.placeholder = placeholder;
        }

        @Override
        public List<BMIData> getPlayersBmiData() {
            return placeholder;
        }
    }

    private class MockAveragePlayerBmiPresenter implements AveragePlayerBmiPresenterInterface {
        GetAveragePlayerBmiStatResponse res;

        @Override
        public void updateAveragePlayerBmiStat(GetAveragePlayerBmiStatResponse response) {
            this.res = response;
        }
    }
}
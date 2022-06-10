package com.tennis.back.domain.useCase.GetPlayersMedianHeightUseCase;

import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class GetPlayersMedianHeightUseCaseTest {


    @Test
    public void singlePlayer_willReturnOwnHeight() {
        GetPlayersMedianHeightUseCase useCase = new GetPlayersMedianHeightUseCase(new MockPlayerHeightGateway(List.of(180)));
        MockPlayerMedianHeightPresenter presenter = new MockPlayerMedianHeightPresenter();
        useCase.execute(presenter);

        assertThat(presenter.response.getPlayersMedianHeight()).isEqualTo(180);
    }

    @Test
    public void oddNumberOfPlayer_willReturnMedianHeight() {
        GetPlayersMedianHeightUseCase useCase = new GetPlayersMedianHeightUseCase(new MockPlayerHeightGateway(List.of(180, 160, 140)));
        MockPlayerMedianHeightPresenter presenter = new MockPlayerMedianHeightPresenter();
        useCase.execute(presenter);

        assertThat(presenter.response.getPlayersMedianHeight()).isEqualTo(160);
    }

    @Test
    public void evenNumberOfPlayer_willReturnAverageHeight() {
        GetPlayersMedianHeightUseCase useCase = new GetPlayersMedianHeightUseCase(new MockPlayerHeightGateway(List.of(140, 140, 160, 180)));
        MockPlayerMedianHeightPresenter presenter = new MockPlayerMedianHeightPresenter();
        useCase.execute(presenter);

        assertThat(presenter.response.getPlayersMedianHeight()).isEqualTo(150);
    }

    @Test
    public void oddNumberOfPlayer_willReturnFirstLowestHeight() {
        GetPlayersMedianHeightUseCase useCase = new GetPlayersMedianHeightUseCase(new MockPlayerHeightGateway(List.of(140, 140, 160)));
        MockPlayerMedianHeightPresenter presenter = new MockPlayerMedianHeightPresenter();
        useCase.execute(presenter);

        assertThat(presenter.response.getPlayersMedianHeight()).isEqualTo(140);
    }

    private class MockPlayerHeightGateway implements GetPlayersHeightGatewayInterface {

        private final List<Integer> mockedHeights;

        public MockPlayerHeightGateway(List<Integer> mockedHeights) {
            this.mockedHeights = mockedHeights;
        }

        @Override
        public List<Integer> getPlayersHeight() {
            return this.mockedHeights;
        }
    }

    private class MockPlayerMedianHeightPresenter implements PlayerMedianHeightPresenterInterface {
        private GetPlayerMedianHeightResponse response;

        @Override
        public void updateMedianHeightStat(GetPlayerMedianHeightResponse response) {
            this.response = response;
        }
    }
}
package com.tennis.back.domain.useCase;

import com.tennis.back.domain.entity.Player;
import org.assertj.core.api.AbstractThrowableAssert;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class GetPlayerUseCaseTest {

    private String EXISTING_PLAYER_ID = "1";
    private Player EXISTING_PLAYER = new Player();
    private GetPlayerUseCase getPlayerUseCase;
    private MockPlayerPresenter presenter;


    @Before
    public void setup() {
        getPlayerUseCase = new GetPlayerUseCase(new MockPlayerGateway());
        presenter = new MockPlayerPresenter();
    }

    @Test
    public void getSinglePlayer_willReturnPlayerByIdIfExists() {
        GetPlayerRequest request = new GetPlayerRequest();
        request.setId(EXISTING_PLAYER_ID);
        getPlayerUseCase.execute(request, presenter);
        
        assertThat(presenter.response.getPlayer()).isEqualTo(EXISTING_PLAYER);
    }

    @Test
    public void getSinglePlayer_willReturnExceptionIfExistsNot() {
        GetPlayerRequest request = new GetPlayerRequest();
        request.setId("unknownPlayerId");

        assertThatThrownBy(() -> getPlayerUseCase.execute(request, presenter))
                .isInstanceOf(NoPlayerFoundException.class);
    }

    private class MockPlayerGateway implements PlayerGatewayInterface {
        @Override
        public Player getPlayer(GetPlayerRequest request) {
            if(request.getId().equals(EXISTING_PLAYER_ID)) {
                return EXISTING_PLAYER;
            }

            return null;
        }
    }

    private class MockPlayerPresenter implements PlayerPresenterInterface{
        GetPlayerResponse response;

        @Override
        public void updatePlayer(GetPlayerResponse response) {
            this.response = response;            
        }
    }
}
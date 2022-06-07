package com.tennis.back.domain.useCase;

import com.tennis.back.domain.entity.Player;
import com.tennis.back.domain.useCase.GetPlayerUseCase.*;
import com.tennis.back.domain.useCase.GetPlayersUseCase.GetPlayersGatewayInterface;
import com.tennis.back.domain.useCase.GetPlayersUseCase.GetPlayersResponse;
import com.tennis.back.domain.useCase.GetPlayersUseCase.GetPlayersUseCase;
import com.tennis.back.domain.useCase.GetPlayersUseCase.PlayersPresenterInterface;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class GetPlayerUseCaseTest {

    private String EXISTING_PLAYER_ID = "1";
    private Player EXISTING_PLAYER = new Player();
    private GetPlayerUseCase getPlayerUseCase;
    private GetPlayersUseCase getPlayersUseCase;
    private MockPlayerPresenter presenter;


    @Before
    public void setup() {
        getPlayerUseCase = new GetPlayerUseCase(new MockGetPlayerGateway());
        getPlayersUseCase = new GetPlayersUseCase(new MockGetPlayerGateway());
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

    @Test
    public void getMultiplePlayers_willReturnSortedByRank() {
        getPlayersUseCase.execute(presenter);
        GetPlayersResponse response = presenter.playersResponse;
        List<Player> players = response.getPlayers();

        assertThat(players.size()).isEqualTo(4);

        for (int i = 1; i < players.size(); i++) {
            assertThat(players.get(i - 1).getStats().getRank()).isLessThan(players.get(i).getStats().getRank());
        }
        ;
    }

    private class MockGetPlayerGateway implements GetPlayerGatewayInterface, GetPlayersGatewayInterface {
        @Override
        public Player getPlayer(GetPlayerRequest request) {
            if (request.getId().equals(EXISTING_PLAYER_ID)) {
                return EXISTING_PLAYER;
            }
            return null;
        }

        @Override
        public List<Player> getPlayers() {
            return Arrays.asList(5, 2, 1, 10)
                    .stream()
                    .map(index -> {
                        Player player = new Player();
                        player.setId(String.valueOf(UUID.randomUUID()));
                        player.getStats().setRank(index);
                        return player;
                    })
                    .collect(Collectors.toList());
        }
    }

    private class MockPlayerPresenter implements PlayerPresenterInterface, PlayersPresenterInterface {
        GetPlayerResponse response;
        GetPlayersResponse playersResponse;

        @Override
        public void updatePlayer(GetPlayerResponse response) {
            this.response = response;
        }

        @Override
        public void updatePlayers(GetPlayersResponse response) {
            this.playersResponse = response;
        }
    }
}
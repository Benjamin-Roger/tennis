package com.tennis.back.interfaceAdapter.controller;

import com.tennis.back.domain.useCase.GetPlayerUseCase.GetPlayerRequest;
import com.tennis.back.domain.useCase.GetPlayerUseCase.GetPlayerUseCase;
import com.tennis.back.domain.useCase.GetPlayersUseCase.GetPlayersUseCase;
import com.tennis.back.interfaceAdapter.gateway.PlayerGateway;
import com.tennis.back.interfaceAdapter.presenter.PlayerPresenter;

public class PlayerController {

    private final PlayerGateway gateway;

    private final PlayerPresenter presenter;
    private final GetPlayerUseCase getPlayerUseCase;
    private final GetPlayersUseCase getPlayersUseCase;

    public PlayerController(PlayerGateway gateway, PlayerPresenter presenter) {
        this.gateway = gateway;
        this.presenter = presenter;
        this.getPlayerUseCase = new GetPlayerUseCase(gateway);
        this.getPlayersUseCase = new GetPlayersUseCase(gateway);

    }

    public void getPlayerDetail(String id) {
        GetPlayerRequest request = new GetPlayerRequest();
        request.setId(id);
        getPlayerUseCase.execute(request, presenter);
    }


    public PlayerPresenter getPresenter() {
        return presenter;
    }

    public void getPlayerSummaries() {
        getPlayersUseCase.execute(presenter);
    }
}

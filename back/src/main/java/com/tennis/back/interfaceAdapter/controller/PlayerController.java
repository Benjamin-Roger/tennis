package com.tennis.back.interfaceAdapter.controller;

import com.tennis.back.domain.useCase.GetPlayerRequest;
import com.tennis.back.domain.useCase.GetPlayerUseCase;
import com.tennis.back.interfaceAdapter.gateway.PlayerGateway;
import com.tennis.back.interfaceAdapter.presenter.PlayerPresenter;

public class PlayerController {

    private PlayerGateway gateway;

    private PlayerPresenter presenter;
    private GetPlayerUseCase getPlayerUseCase;

    public PlayerController(PlayerGateway gateway, PlayerPresenter presenter) {
        this.gateway = gateway;
        this.presenter = presenter;
        this.getPlayerUseCase = new GetPlayerUseCase(gateway);
    }

    public void getPlayerDetail(String id) {
        GetPlayerRequest request = new GetPlayerRequest();
        request.setId(id);
        getPlayerUseCase.execute(request, presenter);
    }


    public PlayerPresenter getPresenter() {
        return presenter;
    }

}

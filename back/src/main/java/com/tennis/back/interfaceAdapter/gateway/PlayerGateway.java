package com.tennis.back.interfaceAdapter.gateway;

import com.tennis.back.domain.entity.Player;
import com.tennis.back.domain.useCase.GetAveragePlayerBmiUseCase.BMIData;
import com.tennis.back.domain.useCase.GetAveragePlayerBmiUseCase.GetPlayersBmiDataGatewayInterface;
import com.tennis.back.domain.useCase.GetHighestCountryWinRatioUseCase.GetPlayersWinDataGatewayInterface;
import com.tennis.back.domain.useCase.GetHighestCountryWinRatioUseCase.WinData;
import com.tennis.back.domain.useCase.GetPlayerUseCase.GetPlayerRequest;
import com.tennis.back.domain.useCase.GetPlayerUseCase.GetPlayerGatewayInterface;
import com.tennis.back.domain.useCase.GetPlayersMedianHeightUseCase.GetPlayersHeightGatewayInterface;
import com.tennis.back.domain.useCase.GetPlayersUseCase.GetPlayersGatewayInterface;

import java.util.List;
import java.util.Optional;

public class PlayerGateway implements GetPlayerGatewayInterface, GetPlayersGatewayInterface, GetPlayersBmiDataGatewayInterface, GetPlayersHeightGatewayInterface, GetPlayersWinDataGatewayInterface {
    PlayerRepositoryInterface repository;

    public PlayerGateway(PlayerRepositoryInterface repository) {
        this.repository = repository;
    }

    @Override
    public Player getPlayer(GetPlayerRequest request) {
        Optional<Player> player =  repository.getPlayerById(request.getId());
        return player.orElse(null);
    }

    @Override
    public List<Player> getPlayers() {
        return repository.getPlayers();
    }

    @Override
    public List<BMIData> getPlayersBmiData() {
        return repository.getPlayersBmiData();
    }

    @Override
    public List<WinData> getPlayersLastResults() {
        return repository.getPlayersLastResults();
    }

    @Override
    public List<Integer> getPlayersHeight() {
        return repository.getPlayersHeight();
    }
}

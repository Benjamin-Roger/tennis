package com.tennis.back.driver.repository;

import com.tennis.back.domain.entity.Country;
import com.tennis.back.domain.entity.Player;
import com.tennis.back.domain.entity.PlayerSex;
import com.tennis.back.domain.entity.PlayerStats;
import com.tennis.back.interfaceAdapter.gateway.GetPlayerRepositoryInterface;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class LocalMemoryPlayerRepository implements GetPlayerRepositoryInterface {

    private Map<String, Player> playersCache = new HashMap<>();
    private PlayerApiHandler playerApiHandler;
    private PlayerLocalFileHandler playerLocalFileHandler;

    public LocalMemoryPlayerRepository(PlayerApiHandler playerApiHandler, PlayerLocalFileHandler playerLocalFileHandler) {
        this.playerApiHandler = playerApiHandler;
        this.playerLocalFileHandler = playerLocalFileHandler;
        init();
    }

    @Override
    public Optional<Player> getPlayerById(String id) {
        return Optional.ofNullable(getPlayers().get(id));
    }


    public Map<String, Player> getPlayers() {
        if (playersCache.isEmpty()) {
            fetchPlayersData();
        }
        return playersCache;
    }

    public List<Player> findAllPlayers() {
        return new ArrayList<>(getPlayers().values());
    }

    private void fetchPlayersData() {
        List<PlayerResponseDTO.Player> players;

        try {
            players = playerApiHandler.getPlayers();
        } catch (Exception e) {
            players = playerLocalFileHandler.getPlayers();
        }

        if (players != null) {
            playersCache.putAll(
                    players
                            .stream()
                            .collect(
                                    Collectors.toMap(
                                            PlayerResponseDTO.Player::getId,
                                            this::computePlayer
                                    )
                            )
            );
        }

    }

    private Player computePlayer(PlayerResponseDTO.Player dto) {
        Player player = new Player();
        player.setId(dto.getId());
        player.setFirstName(dto.firstname);
        player.setLastName(dto.lastname);
        player.setShortName(dto.shortname);
        player.setPicture(dto.picture);
        player.setSex(computePlayerSex(dto.sex));
        player.setCountry(computeCountry(dto.country));
        player.setStats(computePlayerStats(dto.data));
        return player;
    }

    private Country computeCountry(PlayerResponseDTO.Player.Country country) {
        Country playerCountry = new Country();
        playerCountry.setCode(country.code);
        playerCountry.setPicture(country.picture);
        return playerCountry;
    }

    private PlayerSex computePlayerSex(String sex) {
        switch (sex) {
            case "M":
                return PlayerSex.MALE;
            case "F":
                return PlayerSex.FEMALE;
            default:
                return PlayerSex.UNKNOWN;
        }
    }


    private PlayerStats computePlayerStats(PlayerResponseDTO.Player.Data data) {
        PlayerStats stats = new PlayerStats();
        stats.setAge(data.age);
        stats.setHeight(data.height);
        stats.setWeight(data.weight);
        stats.setLastResults(data.last);
        stats.setRank(data.rank);
        stats.setPoints(data.points);


        //TODO : add birthday from another repository

        return stats;
    }

    public void init() {
        fetchPlayersData();
    }

    protected void clearCache() {
        this.playersCache = new HashMap<>();
    }
}

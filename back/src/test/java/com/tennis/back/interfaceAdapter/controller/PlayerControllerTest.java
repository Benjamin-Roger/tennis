package com.tennis.back.interfaceAdapter.controller;


import com.tennis.back.domain.entity.Country;
import com.tennis.back.domain.entity.Player;
import com.tennis.back.domain.entity.PlayerSex;
import com.tennis.back.domain.entity.PlayerStats;
import com.tennis.back.interfaceAdapter.gateway.GetPlayerRepositoryInterface;
import com.tennis.back.interfaceAdapter.gateway.PlayerGateway;
import com.tennis.back.interfaceAdapter.presenter.GetPlayerDetailResponse;
import com.tennis.back.interfaceAdapter.presenter.PlayerPresenter;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PlayerControllerTest {

    private Player player;
    PlayerController playerController;

    @Before
    public void setup() {
        player = new Player();
        player.setId("1");
        player.setFirstName("Novak");
        player.setLastName("Djokovic");
        player.setPicture("https://data.latelier.co/training/tennis_stats/resources/Djokovic.png");
        player.setSex(PlayerSex.MALE);
        player.setShortName("N.DJO");

        Country country = new Country();
        country.setCode("SER");
        country.setPicture("https://data.latelier.co/training/tennis_stats/resources/Serbie.png");

        PlayerStats stats = new PlayerStats();
        stats.setAge(34);
        stats.setRank(1);
        stats.setPoints(11015);
        stats.setHeight(188);
        stats.setWeight(77000);
        stats.setLastResults(List.of(1, 1, 1, 1, 1));

        player.setCountry(country);
        player.setStats(stats);
    }

    @Test
    public void getPlayerDetailById_updatesPresenterWithPlayerDetail() {
        PlayerPresenter presenter = new PlayerPresenter();
        PlayerController playerController = new PlayerController(new PlayerGateway(new MockGetPlayerRepository()), presenter);

        playerController.getPlayerDetail(player.getId());

        GetPlayerDetailResponse res = presenter.getPlayerDetailResponse();
        assertThat(res).isNotNull();
        assertThat(res.getId()).isEqualTo(player.getId());
        assertThat(res.getFirstName()).isEqualTo(player.getFirstName());
        assertThat(res.getLastName()).isEqualTo(player.getLastName());
        assertThat(res.getPicture()).isEqualTo(player.getPicture());
        assertThat(res.getCountry().getCode()).isEqualTo(player.getCountry().getCode());
        assertThat(res.getCountry().getPicture()).isEqualTo(player.getCountry().getPicture());
        assertThat(res.getStats().getAge()).isEqualTo(player.getStats().getAge());
        assertThat(res.getStats().getBirthday()).isEqualTo(player.getStats().getBirthday());
        assertThat(res.getStats().getHeight()).isEqualTo(player.getStats().getHeight());
        assertThat(res.getStats().getWeight()).isEqualTo(player.getStats().getWeight());
        assertThat(res.getStats().getPoints()).isEqualTo(player.getStats().getPoints());
        assertThat(res.getStats().getRank()).isEqualTo(player.getStats().getRank());

    }


    private class MockGetPlayerRepository implements GetPlayerRepositoryInterface {
        @Override
        public Optional<Player> getPlayerById(String id) {
            if (player.getId().equals(id)) {
                return Optional.of(player);
            }
            return Optional.empty();
        }
    }

}
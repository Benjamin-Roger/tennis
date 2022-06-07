package com.tennis.back.driver.repository;

import com.tennis.back.domain.entity.Country;
import com.tennis.back.domain.entity.Player;
import com.tennis.back.domain.entity.PlayerSex;
import com.tennis.back.domain.entity.PlayerStats;
import com.tennis.back.driver.repository.PlayerAtelierRepository.PlayerApiHandler;
import com.tennis.back.driver.repository.PlayerAtelierRepository.PlayerLocalFileHandler;
import com.tennis.back.driver.repository.PlayerAtelierRepository.PlayerResponseDTO;
import com.tennis.back.driver.repository.PlayerWtaAtpRepository.PlayerWtaAtp;
import com.tennis.back.driver.repository.PlayerWtaAtpRepository.PlayerWtaAtpRepository;
import com.tennis.back.interfaceAdapter.gateway.GetPlayerRepositoryInterface;
import com.tennis.back.interfaceAdapter.gateway.GetPlayersRepositoryInterface;
import com.tennis.back.interfaceAdapter.gateway.PlayerRepositoryInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class LocalMemoryPlayerRepository implements PlayerRepositoryInterface {

    private Map<String, Player> playersCache = new HashMap<>();
    private PlayerApiHandler playerApiHandler;
    private PlayerLocalFileHandler playerLocalFileHandler;
    private PlayerWtaAtpRepository playerWtaAtpRepository;

    private final Logger LOGGER = LoggerFactory.getLogger(LocalMemoryPlayerRepository.class);

    public LocalMemoryPlayerRepository(PlayerApiHandler playerApiHandler, PlayerLocalFileHandler playerLocalFileHandler, PlayerWtaAtpRepository playerWtaAtpRepository) {
        this.playerApiHandler = playerApiHandler;
        this.playerLocalFileHandler = playerLocalFileHandler;
        this.playerWtaAtpRepository = playerWtaAtpRepository;
        init();
    }

    @Override
    public Optional<Player> getPlayerById(String id) {
        return Optional.ofNullable(getPlayersMap().get(id));
    }

    public List<Player> getPlayers() {
        return new ArrayList<>(getPlayersMap().values());
    }

    public Map<String, Player> getPlayersMap() {
        if (playersCache.isEmpty()) {
            fetchPlayersData();
        }
        return playersCache;
    }

    private void fetchPlayersData() {
        List<PlayerResponseDTO.Player> players;

        try {
            players = playerApiHandler.getPlayers();
        } catch (Exception e) {
            LOGGER.error("[PlayerRepository] The data could not be retrieved fron API, fallback on local file", e);
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
        player.setFirstName(dto.getFirstname());
        player.setLastName(dto.getLastname());
        player.setShortName(dto.getShortname());
        player.setPicture(dto.getPicture());
        player.setSex(computePlayerSex(dto.getSex()));
        player.setCountry(computeCountry(dto.getCountry()));
        player.setStats(computePlayerStats(dto));
        return player;
    }

    private Country computeCountry(PlayerResponseDTO.Player.Country country) {
        Country playerCountry = new Country();
        playerCountry.setCode(country.getCode());
        playerCountry.setPicture(country.getPicture());
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


    private PlayerStats computePlayerStats(PlayerResponseDTO.Player player) {
        PlayerStats stats = new PlayerStats();
        stats.setAge(player.getData().getAge());
        stats.setHeight(player.getData().getHeight());
        stats.setWeight(player.getData().getWeight());
        stats.setLastResults(player.getData().getLast());
        stats.setRank(player.getData().getRank());
        stats.setPoints(player.getData().getPoints());

        Optional<PlayerWtaAtp> extaInfoPlayer = playerWtaAtpRepository.getPlayer(player.getFirstname(), player.getLastname(), player.getCountry().getCode());

        extaInfoPlayer.ifPresentOrElse(p -> {
                    LOGGER.info("[PlayerRepository] Player {} {} found !", player.getFirstname(), player.getLastname());
                    LocalDate birthday = LocalDate.parse(p.dob, DateTimeFormatter.BASIC_ISO_DATE);
                    Integer age = LocalDate.now().compareTo(birthday);
                    stats.setBirthday(birthday);
                    stats.setAge(age);
                },
                () -> LOGGER.info("[PlayerRepository] Player {} {} not found !", player.getFirstname(), player.getLastname())
        );

        return stats;
    }

    public void init() {
        fetchPlayersData();
        LOGGER.info("[PlayerRepository] Finished DB initialization");
    }

    protected void clearCache() {
        this.playersCache = new HashMap<>();
    }
}

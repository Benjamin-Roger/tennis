package com.tennis.back.driver.repository;

import com.tennis.back.domain.entity.Country;
import com.tennis.back.domain.entity.Player;
import com.tennis.back.domain.entity.PlayerSex;
import com.tennis.back.domain.entity.PlayerStats;
import com.tennis.back.interfaceAdapter.gateway.GetPlayerRepositoryInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class LocalMemoryPlayerRepository implements GetPlayerRepositoryInterface {

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
        player.setFirstName(dto.firstname);
        player.setLastName(dto.lastname);
        player.setShortName(dto.shortname);
        player.setPicture(dto.picture);
        player.setSex(computePlayerSex(dto.sex));
        player.setCountry(computeCountry(dto.country));
        player.setStats(computePlayerStats(dto));
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


    private PlayerStats computePlayerStats(PlayerResponseDTO.Player player) {
        PlayerStats stats = new PlayerStats();
        stats.setAge(player.data.age);
        stats.setHeight(player.data.height);
        stats.setWeight(player.data.weight);
        stats.setLastResults(player.data.last);
        stats.setRank(player.data.rank);
        stats.setPoints(player.data.points);

        Optional<PlayerWtaAtp> extaInfoPlayer = playerWtaAtpRepository.getPlayer(player.firstname, player.lastname, player.country.code);

        extaInfoPlayer.ifPresentOrElse(p -> {
                    LOGGER.info("[PlayerRepository] Player {} {} found !", player.firstname, player.lastname);
                    LocalDate birthday = LocalDate.parse(p.dob, DateTimeFormatter.BASIC_ISO_DATE);
                    Integer age = LocalDate.now().compareTo(birthday);
                    stats.setBirthday(birthday);
                    stats.setAge(age);
                },
                () -> LOGGER.info("[PlayerRepository] Player {} {} not found !", player.firstname, player.lastname)
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

package com.tennis.back.interfaceAdapter.presenter;

import com.tennis.back.domain.entity.Player;
import com.tennis.back.domain.entity.PlayerStats;

import java.util.ArrayList;
import java.util.List;

public class GetPlayersSummariesResponse {

    private List<PlayerSummary> players = new ArrayList<PlayerSummary>();

    public List<PlayerSummary> getPlayers() {
        return players;
    }

    public static GetPlayersSummariesResponse of(List<Player> players) {
        GetPlayersSummariesResponse response = new GetPlayersSummariesResponse();
        players.forEach(player -> {
            PlayerSummary summary = new PlayerSummary();
            summary.setId(player.getId());
            summary.setFirstName(player.getFirstName());
            summary.setLastName(player.getLastName());
            summary.setPicture(player.getPicture());
            summary.setCountryCode(player.getCountry().getCode());
            summary.setStats(PlayerSummary.PlayerStats.of(player.getStats()));
            response.getPlayers().add(summary);
        });
        return response;
    }

    public static class PlayerSummary {
        private String id;
        private String firstName;
        private String lastName;
        private String picture;
        private String countryCode;
        private PlayerStats stats = new PlayerStats();

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public String getCountryCode() {
            return countryCode;
        }

        public void setCountryCode(String countryCode) {
            this.countryCode = countryCode;
        }

        public PlayerStats getStats() {
            return stats;
        }

        public void setStats(PlayerStats stats) {
            this.stats = stats;
        }

        public static class PlayerStats {
            public static PlayerStats of(com.tennis.back.domain.entity.PlayerStats stats) {
                PlayerStats playerStats = new PlayerStats();
                playerStats.setPoints(stats.getPoints());
                playerStats.setRank(stats.getRank());
                return playerStats;
            }

            public Integer getRank() {
                return rank;
            }

            public void setRank(Integer rank) {
                this.rank = rank;
            }

            public Integer getPoints() {
                return points;
            }

            public void setPoints(Integer points) {
                this.points = points;
            }

            private Integer rank;
            private Integer points;
        }
    }
}

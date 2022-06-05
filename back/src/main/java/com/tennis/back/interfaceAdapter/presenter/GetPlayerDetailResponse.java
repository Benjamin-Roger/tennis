package com.tennis.back.interfaceAdapter.presenter;

import com.tennis.back.domain.entity.Player;
import com.tennis.back.domain.entity.PlayerStats;

import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class GetPlayerDetailResponse {
    String id;
    String firstName;
    String lastName;
    String picture;
    Stats stats;
    Country country;

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPicture() {
        return picture;
    }

    public Stats getStats() {
        return stats;
    }

    public Country getCountry() {
        return country;
    }

    public static class Country {
        private String name;
        private String code;
        private String picture;

        public String getName() {
            return name;
        }

        public String getCode() {
            return code;
        }

        public String getPicture() {
            return picture;
        }

        public static Country of(Player player) {
            Country resCountry = new Country();
            resCountry.code = player.getCountry().getCode();
            resCountry.picture = player.getCountry().getPicture();
            return resCountry;
        }
    }

    public static class Stats {
        Integer rank;
        Integer points;
        Integer weight;
        Integer height;
        Integer age;
        String birthday;

        public Integer getRank() {
            return rank;
        }

        public Integer getPoints() {
            return points;
        }

        public Integer getWeight() {
            return weight;
        }

        public Integer getHeight() {
            return height;
        }

        public Integer getAge() {
            return age;
        }

        public String getBirthday() {
            return birthday;
        }

        static Stats of(Player player) {
            PlayerStats playerStats = player.getStats();
            Stats resStats = new Stats();
            resStats.age = playerStats.getAge();
            resStats.height = playerStats.getHeight();
            resStats.weight = playerStats.getWeight();
            resStats.points = playerStats.getPoints();
            resStats.rank = playerStats.getRank();

            if (playerStats.getBirthday() != null) {
                resStats.birthday = playerStats.getBirthday().atOffset(ZoneOffset.UTC).format(DateTimeFormatter.ISO_LOCAL_DATE);
            }

            return resStats;
        }
    }

    static GetPlayerDetailResponse of(Player player) {
        GetPlayerDetailResponse response = new GetPlayerDetailResponse();
        response.id = player.getId();
        response.firstName = player.getFirstName();
        response.lastName = player.getLastName();
        response.picture = player.getPicture();
        response.country = Country.of(player);
        response.stats = Stats.of(player);
        return response;
    }
}

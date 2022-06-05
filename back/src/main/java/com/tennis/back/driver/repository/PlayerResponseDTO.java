package com.tennis.back.driver.repository;

import java.util.List;

public class PlayerResponseDTO {
    List<Player> players;

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public static class Player {
        String id;
        String firstname;
        String lastname;
        String shortname;
        String picture;
        String sex;
        Country country;
        Data data;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setFirstname(String firstname) {
            this.firstname = firstname;
        }

        public void setLastname(String lastname) {
            this.lastname = lastname;
        }

        public void setShortname(String shortname) {
            this.shortname = shortname;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public void setCountry(Country country) {
            this.country = country;
        }

        public void setData(Data data) {
            this.data = data;
        }

        public class Country {
            String picture;
            String code;

            public void setPicture(String picture) {
                this.picture = picture;
            }

            public void setCode(String code) {
                this.code = code;
            }
        }

        public class Data {
            Integer rank;
            Integer points;
            Integer weight;
            Integer height;
            Integer age;
            List<Integer> last;

            public void setRank(Integer rank) {
                this.rank = rank;
            }

            public void setPoints(Integer points) {
                this.points = points;
            }

            public void setWeight(Integer weight) {
                this.weight = weight;
            }

            public void setHeight(Integer height) {
                this.height = height;
            }

            public void setAge(Integer age) {
                this.age = age;
            }

            public void setLast(List<Integer> last) {
                this.last = last;
            }
        }
    }
}

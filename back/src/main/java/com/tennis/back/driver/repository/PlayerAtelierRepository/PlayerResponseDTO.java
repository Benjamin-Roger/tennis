package com.tennis.back.driver.repository.PlayerAtelierRepository;

import java.util.List;

public class PlayerResponseDTO {
    private List<Player> players;

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public static class Player {
        private String id;
        private String firstname;
        private String lastname;
        private String shortname;
        private String picture;
        private String sex;
        private Country country;
        private Data data;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getFirstname() {
            return firstname;
        }

        public void setFirstname(String firstname) {
            this.firstname = firstname;
        }

        public String getLastname() {
            return lastname;
        }

        public void setLastname(String lastname) {
            this.lastname = lastname;
        }

        public String getShortname() {
            return shortname;
        }

        public void setShortname(String shortname) {
            this.shortname = shortname;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public Country getCountry() {
            return country;
        }

        public void setCountry(Country country) {
            this.country = country;
        }

        public Data getData() {
            return data;
        }

        public void setData(Data data) {
            this.data = data;
        }

        public class Country {
            private String picture;
            private String code;

            public String getPicture() {
                return picture;
            }

            public void setPicture(String picture) {
                this.picture = picture;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }
        }

        public class Data {
            private Integer rank;
            private Integer points;
            private Integer weight;
            private Integer height;
            private Integer age;
            private List<Integer> last;

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

            public Integer getWeight() {
                return weight;
            }

            public void setWeight(Integer weight) {
                this.weight = weight;
            }

            public Integer getHeight() {
                return height;
            }

            public void setHeight(Integer height) {
                this.height = height;
            }

            public Integer getAge() {
                return age;
            }

            public void setAge(Integer age) {
                this.age = age;
            }

            public List<Integer> getLast() {
                return last;
            }

            public void setLast(List<Integer> last) {
                this.last = last;
            }
        }
    }
}

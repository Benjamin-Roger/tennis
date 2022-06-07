package com.tennis.back.domain.entity;

public class Player {
    String id;
    String firstName;
    String lastName;
    String shortName;
    PlayerSex sex;
    String picture;
    Country country = new Country();
    PlayerStats playerStats = new PlayerStats();

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

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public PlayerSex getSex() {
        return sex;
    }

    public void setSex(PlayerSex sex) {
        this.sex = sex;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public PlayerStats getStats() {
        return playerStats;
    }

    public void setStats(PlayerStats playerStats) {
        this.playerStats = playerStats;
    }

}

package com.tennis.back.domain.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PlayerStats {
    private Integer rank;
    private Integer points;
    private Integer weight; // in grams
    private Integer height; // centimeters
    private Integer age;
    private LocalDate birthday;
    private List<Integer> lastResults = new ArrayList<>();

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

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public List<Integer> getLastResults() {
        return lastResults;
    }

    public void setLastResults(List<Integer> lastResults) {
        this.lastResults = lastResults;
    }
}

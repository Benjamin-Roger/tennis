package com.tennis.back.driver.repository;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "player-repository")

public class PlayerRepositoryProperties {
    private String fallbackFileLocation;
    private String playersApi;

    private String malePlayersAtpApi;
    private String femalePlayersWtaApi;

    public String getFallbackFileLocation() {
        return fallbackFileLocation;
    }

    public void setFallbackFileLocation(String fallbackFileLocation) {
        this.fallbackFileLocation = fallbackFileLocation;
    }

    public String getPlayersApi() {
        return playersApi;
    }

    public void setPlayersApi(String playersApi) {
        this.playersApi = playersApi;
    }

    public String getMalePlayersAtpApi() {
        return malePlayersAtpApi;
    }

    public void setMalePlayersAtpApi(String malePlayersAtpApi) {
        this.malePlayersAtpApi = malePlayersAtpApi;
    }

    public String getFemalePlayersWtaApi() {
        return femalePlayersWtaApi;
    }

    public void setFemalePlayersWtaApi(String femalePlayersWtaApi) {
        this.femalePlayersWtaApi = femalePlayersWtaApi;
    }
}

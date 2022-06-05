package com.tennis.back.driver.repository;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "player-repository")

public class PlayerRepositoryProperties {
    private String fallbackFileLocation;
    private String playersApi;

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
}

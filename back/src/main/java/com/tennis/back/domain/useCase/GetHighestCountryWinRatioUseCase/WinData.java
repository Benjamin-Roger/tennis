package com.tennis.back.domain.useCase.GetHighestCountryWinRatioUseCase;

import java.util.List;

public class WinData {
    List<Integer> lastResults;
    String countryCode;

    public List<Integer> getLastResults() {
        return lastResults;
    }

    public void setLastResults(List<Integer> lastResults) {
        this.lastResults = lastResults;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}

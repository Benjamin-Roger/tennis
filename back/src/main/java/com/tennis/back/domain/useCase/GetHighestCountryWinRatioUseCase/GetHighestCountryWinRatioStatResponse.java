package com.tennis.back.domain.useCase.GetHighestCountryWinRatioUseCase;

public class GetHighestCountryWinRatioStatResponse {
    private String countryCode;
    private double winRatio;

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public double getWinRatio() {
        return winRatio;
    }

    public void setWinRatio(double winRatio) {
        this.winRatio = winRatio;
    }
}

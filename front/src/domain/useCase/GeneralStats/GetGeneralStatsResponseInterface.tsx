export interface GetGeneralStatsResponseInterface {
    winRatio: {
        country: string,
        winRatio: number,
    },
    medianHeight: {
        playersMedianHeight: number,
    },
    bmi: {
        averagePlayerBmi: number,
    }
}

import {
    GetGeneralStatsAPIResponseInterface
} from "../../interface-adapter/gateway/GeneralStats/GetGeneralStatsAPIResponseInterface";

export class GetGeneralStatsApiResponse implements GetGeneralStatsAPIResponseInterface {
    winRatio: {
        countryCode: string,
        winRatio: number,
    };
    medianHeight: {
        playersMedianHeight: number,
    };
    bmi: {
        averagePlayerBmi: number,
    }

    constructor(winRatio: { countryCode: string; winRatio: number }, medianHeight: { playersMedianHeight: number }, bmi: { averagePlayerBmi: number }) {
        this.winRatio = winRatio;
        this.medianHeight = medianHeight;
        this.bmi = bmi;
    }
}

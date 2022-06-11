import {GetGeneralStatsResponseInterface} from "../../../domain/useCase/GeneralStats/GetGeneralStatsResponseInterface";


export class GeneralStatsViewModel {
    title: string = "Statistics";
    winRatioLabel: string = "Country with highest win ratio";
    winRatioSubtitle: string= "Results based on players' last 5 matches"
    winRatioValue?: string;
    medianHeightLabel: string = "Median height of all players";
    medianHeightValue?: string;
    averageBmiLabel: string = "Average BMI among players";
    averageBmiValue?: string;


    static of(stats: GetGeneralStatsResponseInterface): GeneralStatsViewModel {
        const viewModel = new GeneralStatsViewModel();
        viewModel.winRatioValue = stats.winRatio !== undefined ? `${stats.winRatio.country} | ${Math.round(stats.winRatio.winRatio * 10000) / 100}%` : undefined;
        viewModel.averageBmiValue = stats.bmi.averagePlayerBmi !== undefined ? `${Math.round(stats.bmi.averagePlayerBmi * 100) / 100}` : undefined;
        viewModel.medianHeightValue = stats.medianHeight.playersMedianHeight !== undefined ? `${stats.medianHeight.playersMedianHeight}cm` : undefined;
        return viewModel;
    }
}

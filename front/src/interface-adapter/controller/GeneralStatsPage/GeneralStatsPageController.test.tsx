import {GetGeneralStatsAPIResponseInterface} from "../../gateway/GeneralStats/GetGeneralStatsAPIResponseInterface";
import {GetGeneralStatsResponseInterface} from "../../../domain/useCase/GeneralStats/GetGeneralStatsResponseInterface";
import {GeneralStatsPageController} from "./GeneralStatsPageController";
import {GeneralStatsPresenter} from "../../presenter/GeneralStats/GeneralStatsPresenter";
import {GeneralStatsApiInterface} from "../../gateway/GeneralStats/GeneralStatsApiInterface";

const mockResponse: GetGeneralStatsResponseInterface = {
    bmi: {averagePlayerBmi: 23.357838995505837},
    winRatio: {country: "Serbie", winRatio: 1.0},
    medianHeight: {playersMedianHeight: 185.0}
};

export class GeneralStatsRepositoryMock implements GeneralStatsApiInterface {
    mockResponse: GetGeneralStatsResponseInterface;

    constructor(mockResponse: GetGeneralStatsResponseInterface) {
        this.mockResponse = mockResponse;
    }

    getGeneralStats(): Promise<GetGeneralStatsResponseInterface> {
        return Promise.resolve(this.mockResponse);
    }

    fetchGeneralStats(): Promise<GetGeneralStatsAPIResponseInterface> {
        return Promise.resolve({});
    }


}


test("General stats information are correctly mapped to view model", async () => {
    const controller = new GeneralStatsPageController(new GeneralStatsPresenter(), new GeneralStatsRepositoryMock(mockResponse));

    await controller.getStats();

    const viewModel = controller.viewModel;
    expect(viewModel.title).toMatch("Statistics");
    expect(viewModel.averageBmiLabel).toEqual("Players average BMI");
    expect(viewModel.averageBmiValue).toEqual("23.36");
    expect(viewModel.winRatioLabel).toEqual("Country with highest win ratio");
    expect(viewModel.winRatioValue).toEqual("Serbie | 100%");
    expect(viewModel.winRatioSubtitle).toEqual("Results based off players' last 5 matches");
    expect(viewModel.medianHeightLabel).toEqual("Players median height");
    expect(viewModel.medianHeightValue).toEqual("185cm");
});


export {};

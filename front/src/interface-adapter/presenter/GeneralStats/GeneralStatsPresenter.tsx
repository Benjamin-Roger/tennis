import {GeneralStatsPresenterInterface} from "../../../domain/useCase/GeneralStats/GeneralStatsPresenterInterface";
import {GeneralStatsViewModel} from "../../viewModel/GeneralStatsPage/GeneralStatsViewModel";
import {GetGeneralStatsResponseInterface} from "../../../domain/useCase/GeneralStats/GetGeneralStatsResponseInterface";

export class GeneralStatsPresenter implements GeneralStatsPresenterInterface {
    private _viewModel:GeneralStatsViewModel = new GeneralStatsViewModel();

    updateGeneralStats(generalStats: GetGeneralStatsResponseInterface): void {
        this._viewModel = GeneralStatsViewModel.of(generalStats);
    }

    get viewModel(): GeneralStatsViewModel {
        return this._viewModel;
    }
}

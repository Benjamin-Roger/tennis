import usePlayerSummaryViewModel, {HomePageViewModel} from "../../viewModel/HomePage/HomeViewModel";
import {
    PlayerSummariesPresenterInterface
} from "../../../domain/useCase/PlayerSummaries/PlayerSummariesPresenterInterface";
import {
    UpdatePlayerSummariesResponseInterface
} from "../../../domain/useCase/PlayerSummaries/UpdatePlayerSummariesResponseInterface";

export class PlayerSummariesPresenter implements PlayerSummariesPresenterInterface {

    private _viewModel: HomePageViewModel = new HomePageViewModel();

    get viewModel(): HomePageViewModel {
        return this._viewModel;
    }

    updatePlayerSummaries(response: UpdatePlayerSummariesResponseInterface): void {
        this.viewModel.players = response.players.map(usePlayerSummaryViewModel);
    }

}

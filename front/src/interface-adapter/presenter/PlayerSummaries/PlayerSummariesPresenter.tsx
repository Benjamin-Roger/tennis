import usePlayerSummaryViewModel, {PlayerSummariesViewModel} from "../../viewModel/HomePage/HomeViewModel";
import {
    PlayerSummariesPresenterInterface
} from "../../../domain/useCase/PlayerSummaries/PlayerSummariesPresenterInterface";
import {
    UpdatePlayerSummariesResponseInterface
} from "../../../domain/useCase/PlayerSummaries/UpdatePlayerSummariesResponseInterface";

export class PlayerSummariesPresenter implements PlayerSummariesPresenterInterface {

    private _viewModel: PlayerSummariesViewModel = new PlayerSummariesViewModel();

    get viewModel(): PlayerSummariesViewModel {
        return this._viewModel;
    }

    updatePlayerSummaries(response: UpdatePlayerSummariesResponseInterface): void {
        this.viewModel.players = response.players.map(usePlayerSummaryViewModel);
    }

}

import {PlayerDetailPresenterInterface} from "../../../domain/useCase/PlayerDetail/PlayerDetailPresenterInterface";
import {PlayerDetailViewModel, makeViewablePlayerDetail} from "../../viewModel/PlayerDetailPage/PlayerDetailViewModel";
import {GetPlayerDetailResponseInterface} from "../../../domain/useCase/PlayerDetail/GetPlayerDetailResponseInterface";

export class PlayerDetailPresenter implements PlayerDetailPresenterInterface {
    private _viewModel:PlayerDetailViewModel = new PlayerDetailViewModel();

    updatePlayerDetail(response: GetPlayerDetailResponseInterface): void {
        this._viewModel = makeViewablePlayerDetail(response.player)
    }

    get viewModel(): PlayerDetailViewModel {
        return this._viewModel;
    }
}

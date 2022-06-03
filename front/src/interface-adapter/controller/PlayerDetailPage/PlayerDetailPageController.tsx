import {PlayerDetailPresenter} from "../../presenter/PlayerDetail/PlayerDetailPresenter";
import {PlayerDetailApiInterface} from "../../gateway/PlayerDetail/PlayerDetailApiInterface";
import {PlayerDetailInteractor} from "../../interactors/PlayerDetail/PlayerDetailInteractor";
import {PlayerDetailViewModel} from "../../viewModel/PlayerDetailPage/PlayerDetailViewModel";


export class PlayerDetailPageController {
    private _presenter: PlayerDetailPresenter;
    private _repository: PlayerDetailApiInterface;
    private _playerDetailInteractor: PlayerDetailInteractor;

    constructor(presenter: PlayerDetailPresenter, repository: PlayerDetailApiInterface) {
        this._presenter = presenter;
        this._repository = repository;
        this._playerDetailInteractor = new PlayerDetailInteractor(presenter, repository)
    }

    get presenter():PlayerDetailPresenter {
        return this._presenter;
    }

    get viewModel():PlayerDetailViewModel {
        return this.presenter.viewModel;
    }

    get playerDetailInteractor(): PlayerDetailInteractor {
        return this._playerDetailInteractor;
    }
}

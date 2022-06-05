import {PlayerSummariesPresenter} from "../../presenter/PlayerSummaries/PlayerSummariesPresenter";
import {PlayerSummariesApiInterface} from "../../gateway/PlayerSummaries/PlayerSummariesApiInterface";
import {HomePageViewModel} from "../../viewModel/HomePage/HomeViewModel";
import {PlayerSummariesInteractor} from "../../interactors/PlayerSummaries/PlayerSummariesInteractor";

export class HomePageController {
    private _presenter: PlayerSummariesPresenter;
    private _repository: PlayerSummariesApiInterface;
    private _playerSummariesInteractor: PlayerSummariesInteractor;

    constructor(presenter: PlayerSummariesPresenter, repository: PlayerSummariesApiInterface) {
        this._presenter = presenter;
        this._repository = repository;
        this._playerSummariesInteractor = new PlayerSummariesInteractor(presenter, repository);
    }

    get playerSummariesInteractor(): PlayerSummariesInteractor {
        return this._playerSummariesInteractor;
    }

    async init(): Promise<void> {
        await this.playerSummariesInteractor.findAllPlayerSummaries();
    }

    get presenter():PlayerSummariesPresenter {
        return this._presenter;
    }

    get viewModel():HomePageViewModel {
        return this.presenter.viewModel;
    }
}

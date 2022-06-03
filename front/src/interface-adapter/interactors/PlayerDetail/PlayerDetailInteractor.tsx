import {PlayerDetailPresenter} from "../../presenter/PlayerDetail/PlayerDetailPresenter";
import {PlayerDetailUseCase} from "../../../domain/useCase/PlayerDetail/PlayerDetailUseCase";
import {PlayerDetailRequestInterface} from "../../../domain/useCase/PlayerDetail/PlayerDetailRequestInterface";
import {PlayerDetailApiInterface} from "../../gateway/PlayerDetail/PlayerDetailApiInterface";

export class PlayerDetailInteractor {
    private _presenter: PlayerDetailPresenter;
    private _gateway: PlayerDetailApiInterface;
    private _useCase: PlayerDetailUseCase;

    constructor(presenter: PlayerDetailPresenter, gateway: PlayerDetailApiInterface) {
        this._presenter = presenter;
        this._gateway = gateway;
        this._useCase = new PlayerDetailUseCase(this.gateway);
    }

    get presenter(): PlayerDetailPresenter {
        return this._presenter;
    }

    get gateway(): PlayerDetailApiInterface {
        return this._gateway;
    }

    get useCase(): PlayerDetailUseCase {
        return this._useCase;
    }

    async getPlayerDetail(request: PlayerDetailRequestInterface): Promise<void> {
        return await this.useCase.getPlayerDetail(request, this.presenter);
    }

}

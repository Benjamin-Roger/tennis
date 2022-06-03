import {PlayerSummariesPresenter} from "../../presenter/PlayerSummaries/PlayerSummariesPresenter";
import {PlayerSummariesUseCase} from "../../../domain/useCase/PlayerSummaries/PlayerSummariesUseCase";
import {PlayerSummariesApiInterface} from "../../gateway/PlayerSummaries/PlayerSummariesApiInterface";
import {
    FilterKeyword,
} from "../../../domain/useCase/PlayerSummaries/FilterPlayerSummariesRequestInterface";
import {PlayerSummaryFilterRequest} from "./PlayerSummaryFilterRequest";

export class PlayerSummariesInteractor {
    private _presenter: PlayerSummariesPresenter;
    private gateway: PlayerSummariesApiInterface;
    private useCase: PlayerSummariesUseCase;

    constructor(presenter: PlayerSummariesPresenter, gateway: PlayerSummariesApiInterface) {
        this._presenter = presenter;
        this.gateway = gateway;
        this.useCase = new PlayerSummariesUseCase(this.gateway);
    }

    get presenter(): PlayerSummariesPresenter {
        return this._presenter;
    }

    async findAllPlayerSummaries(): Promise<void> {
        return await this.useCase.findAllPlayerSummaries(this.presenter);
    }

    async getPlayerSummaries(keyword: FilterKeyword): Promise<void> {
        const filterRequest = new PlayerSummaryFilterRequest(keyword);
        return await this.useCase.getPlayerSummaries(filterRequest, this.presenter);
    }

}

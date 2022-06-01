import {PlayerSummariesPresenter} from "../../presenter/PlayerSummaries/PlayerSummariesPresenter";
import {PlayerSummariesUseCase} from "../../../domain/useCase/PlayerSummaries/PlayerSummariesUseCase";
import {PlayerSummariesApiInterface} from "../../gateway/PlayerSummaries/PlayerSummariesApiInterface";
import {
    FilterKeyword,
    FilterValue
} from "../../../domain/useCase/PlayerSummaries/FilterPlayerSummariesRequestInterface";

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
        const filterRequestDTO = {
            keyword,
            fields: [FilterValue.FIRST_NAME, FilterValue.LAST_NAME, FilterValue.COUNTRY]
        };

        return await this.useCase.getPlayerSummaries(filterRequestDTO, this.presenter);
    }

}

import {GeneralStatsPresenter} from "../../presenter/GeneralStats/GeneralStatsPresenter";
import {GeneralStatsApiInterface} from "../../gateway/GeneralStats/GeneralStatsApiInterface";
import {GeneralStatsViewModel} from "../../viewModel/GeneralStatsPage/GeneralStatsViewModel";
import {GetGeneralStatsUseCase} from "../../../domain/useCase/GeneralStats/GetGeneralStatsUseCase";


export class GeneralStatsPageController {
    private _presenter: GeneralStatsPresenter;
    private _repository: GeneralStatsApiInterface;
    private _useCase: GetGeneralStatsUseCase;

    constructor(presenter: GeneralStatsPresenter, repository: GeneralStatsApiInterface) {
        this._presenter = presenter;
        this._repository = repository;
        this._useCase = new GetGeneralStatsUseCase(repository);
    }

    async getStats(): Promise<void> {
        return await this.useCase.getGeneralStats(this.presenter);
    }

    get presenter(): GeneralStatsPresenter {
        return this._presenter;
    }

    get viewModel(): GeneralStatsViewModel {
        return this.presenter.viewModel;
    }

    get useCase(): GetGeneralStatsUseCase {
        return this._useCase;
    }

    async init(): Promise<void> {
        return this.getStats();
    }
}

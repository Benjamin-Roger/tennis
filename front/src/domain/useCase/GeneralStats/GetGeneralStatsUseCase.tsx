import {GeneralStatsGatewayInterface} from "./GeneralStatsGatewayInterface";
import {GeneralStatsPresenterInterface} from "./GeneralStatsPresenterInterface";

export class GetGeneralStatsUseCase {
    gateway: GeneralStatsGatewayInterface;

    constructor(gateway: GeneralStatsGatewayInterface) {
        this.gateway = gateway;
    }

    async getGeneralStats(presenter: GeneralStatsPresenterInterface): Promise<void> {
        const generalStats = await this.gateway.getGeneralStats();
        return presenter.updateGeneralStats(generalStats);
    }
}

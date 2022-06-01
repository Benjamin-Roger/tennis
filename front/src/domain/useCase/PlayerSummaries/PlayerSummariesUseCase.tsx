import {PlayerSummariesPresenterInterface} from "./PlayerSummariesPresenterInterface";
import {FilterPlayerSummariesRequestInterface} from "./FilterPlayerSummariesRequestInterface";
import {PlayerSummariesGatewayInterface} from "./PlayerSummariesGatewayInterface";

export class PlayerSummariesUseCase {
    gateway: PlayerSummariesGatewayInterface;

    constructor(gateway: PlayerSummariesGatewayInterface) {
        this.gateway = gateway;
    }

    async findAllPlayerSummaries(presenter: PlayerSummariesPresenterInterface,): Promise<void> {
        return this.getPlayerSummaries(null, presenter);
    }

    async getPlayerSummaries(request : FilterPlayerSummariesRequestInterface | null, presenter: PlayerSummariesPresenterInterface): Promise<void> {
        const players = await this.gateway.getPlayerSummaries(request);
        return presenter.updatePlayerSummaries(players);
    }
}

import {PlayerDetailRequestInterface} from "./PlayerDetailRequestInterface";
import {PlayerDetailGatewayInterface} from "./PlayerDetailGatewayInterface";
import {PlayerDetailPresenterInterface} from "./PlayerDetailPresenterInterface";

export class PlayerDetailUseCase {
    gateway: PlayerDetailGatewayInterface;

    constructor(gateway: PlayerDetailGatewayInterface) {
        this.gateway = gateway;
    }

    async getPlayerDetail(playerDetailRequest : PlayerDetailRequestInterface, presenter: PlayerDetailPresenterInterface): Promise<void> {
        const player = await this.gateway.getPlayerDetail(playerDetailRequest);
        return presenter.updatePlayerDetail(player);
    }
}

import {GetPlayerDetailResponseInterface} from "./GetPlayerDetailResponseInterface";
import {PlayerDetailRequestInterface} from "./PlayerDetailRequestInterface";

export interface PlayerDetailGatewayInterface {
    getPlayerDetail(request: PlayerDetailRequestInterface): Promise<GetPlayerDetailResponseInterface>;
}

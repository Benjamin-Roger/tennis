import {PlayerDetailGatewayInterface} from "../../../domain/useCase/PlayerDetail/PlayerDetailGatewayInterface";
import {PlayerDetailRequestInterface} from "../../../domain/useCase/PlayerDetail/PlayerDetailRequestInterface";
import {GetPlayerDetailAPIResponseInterface} from "./GetPlayerDetailAPIResponseInterface";

export interface PlayerDetailApiInterface extends PlayerDetailGatewayInterface {
    fetchPlayerDetail(request: PlayerDetailRequestInterface): Promise<GetPlayerDetailAPIResponseInterface>
}

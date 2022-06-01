import {PlayerSummariesGatewayInterface} from "../../../domain/useCase/PlayerSummaries/PlayerSummariesGatewayInterface";
import {GetPlayerSummariesAPIResponseInterface} from "./GetPlayerSummariesAPIResponseInterface";

export interface PlayerSummariesApiInterface extends PlayerSummariesGatewayInterface {
    fetchAllPlayersSummaries():Promise<GetPlayerSummariesAPIResponseInterface>;
}

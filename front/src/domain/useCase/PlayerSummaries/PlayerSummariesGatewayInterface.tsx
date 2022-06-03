import {FilterPlayerSummariesRequestInterface} from "./FilterPlayerSummariesRequestInterface";
import {GetPlayerSummariesResponseInterface} from "./GetPlayerSummariesResponseInterface";

export interface PlayerSummariesGatewayInterface {
    getPlayerSummaries(filter: FilterPlayerSummariesRequestInterface | null): Promise<GetPlayerSummariesResponseInterface>;
}

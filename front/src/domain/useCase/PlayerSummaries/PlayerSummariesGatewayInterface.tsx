import {UpdatePlayerSummariesResponseInterface} from './UpdatePlayerSummariesResponseInterface';
import {FilterPlayerSummariesRequestInterface} from './FilterPlayerSummariesRequestInterface';

export interface PlayerSummariesGatewayInterface {
    getPlayerSummaries(filter: FilterPlayerSummariesRequestInterface | null): Promise<UpdatePlayerSummariesResponseInterface>;
}

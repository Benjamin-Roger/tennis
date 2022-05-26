import {UpdatePlayerSummariesResponseInterface} from './UpdatePlayerSummariesResponseInterface';

export interface PlayerSummariesPresenterInterface {
    updatePlayerSummaries(response: UpdatePlayerSummariesResponseInterface): void;
}

import {
    GetPlayerSummariesAPIResponseInterface
} from "../../interface-adapter/gateway/PlayerSummaries/GetPlayerSummariesAPIResponseInterface";

interface PlayerApiResponse {
    id: number,
    firstName: string,
    lastName: string,
    picture?: string,
    data: {
        rank: number,
        points: number
    },
    country: string
}


export class GetPlayerSummariesApiResponse implements GetPlayerSummariesAPIResponseInterface {
    players: PlayerApiResponse[] = [];
}

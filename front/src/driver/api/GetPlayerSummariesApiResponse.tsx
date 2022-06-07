import {
    GetPlayerSummariesAPIResponseInterface
} from "../../interface-adapter/gateway/PlayerSummaries/GetPlayerSummariesAPIResponseInterface";

interface PlayerApiResponse {
    id: string,
    firstName: string,
    lastName: string,
    picture?: string,
    stats: {
        rank: number,
        points: number
    },
    countryCode: string
}


export class GetPlayerSummariesApiResponse implements GetPlayerSummariesAPIResponseInterface {
    players: PlayerApiResponse[] = [];
}

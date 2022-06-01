import {PlayerSummariesApiInterface} from "../../interface-adapter/gateway/PlayerSummaries/PlayerSummariesApiInterface";
import {
    UpdatePlayerSummariesResponseInterface
} from "../../domain/useCase/PlayerSummaries/UpdatePlayerSummariesResponseInterface";
import {
    GetPlayerSummariesAPIResponseInterface
} from "../../interface-adapter/gateway/PlayerSummaries/GetPlayerSummariesAPIResponseInterface";
import {
    FilterPlayerSummariesRequestInterface
} from "../../domain/useCase/PlayerSummaries/FilterPlayerSummariesRequestInterface";


export class PlayerSummariesApi implements PlayerSummariesApiInterface {
    private url: string;
    private cache: GetPlayerSummariesAPIResponseInterface | null = null;

    constructor(url: string) {
        this.url = url;
    }

    async findAllPlayerSummaries(): Promise<GetPlayerSummariesAPIResponseInterface> {
        if (this.cache !== null) {
            return Promise.resolve(this.cache);
        } else {
            return await this.fetchAllPlayersSummaries();
        }
    }

    async getPlayerSummaries(filter: FilterPlayerSummariesRequestInterface | null): Promise<UpdatePlayerSummariesResponseInterface> {
        const playerSummariesResponse = await this.findAllPlayerSummaries()
            .then(this.convertToPlayerAdapter);

        if (filter === null) {
            return playerSummariesResponse;
        }

        return {
            ...playerSummariesResponse,
            players: playerSummariesResponse.players.filter(filter.callback)
        };
    }

    convertToPlayerAdapter(playerDTO: GetPlayerSummariesAPIResponseInterface): UpdatePlayerSummariesResponseInterface {
        try {
            return {
                players: playerDTO.players.map(player => ({
                    id: player.id,
                    firstName: player.firstName,
                    lastName: player.lastName,
                    picture: player.picture,
                    data: {
                        rank: player.data.rank,
                        points: player.data.points,
                    },
                    country: {
                        name: player.country
                    }
                }))
            }
        } catch (e) {
            throw new TypeError(`Error met when converting player data to player format ${e}`);
        }

    }

    async fetchAllPlayersSummaries(): Promise<GetPlayerSummariesAPIResponseInterface> {
        return new Promise<GetPlayerSummariesAPIResponseInterface>((resolve, reject) => {
            try {
                fetch(this.url)
                    .then(res => res.json())
                    .then(resolve)
            } catch (e) {
                reject("Error met when fetching data");
            }
        })
    }
}

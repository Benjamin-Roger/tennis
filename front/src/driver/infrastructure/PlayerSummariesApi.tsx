import {PlayerSummariesApiInterface} from "../../interface-adapter/gateway/PlayerSummaries/PlayerSummariesApiInterface";
import {
    UpdatePlayerSummariesResponseInterface
} from "../../domain/useCase/PlayerSummaries/UpdatePlayerSummariesResponseInterface";
import {
    GetPlayerSummariesAPIResponseInterface
} from "../../interface-adapter/gateway/PlayerSummaries/GetPlayerSummariesAPIResponseInterface";
import {
    FilterPlayerSummariesRequestInterface,
    FilterValue
} from "../../domain/useCase/PlayerSummaries/FilterPlayerSummariesRequestInterface";
import {Player} from "../../domain/entity/Player";


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

    filterByFields(player: Player, filter:FilterPlayerSummariesRequestInterface):Boolean {
        const {fields, keyword} = filter;

        return fields.some(field => {
            if([FilterValue.FIRST_NAME, FilterValue.LAST_NAME].includes(field)) {
                return player[field].toString().toLowerCase().includes(keyword.toLowerCase());
            }
            if(FilterValue.COUNTRY == field) {
                return player.country.name.toString().toLowerCase().includes(keyword.toLowerCase());
            }
            return false;
        });
    }

    async getPlayerSummaries(filter: FilterPlayerSummariesRequestInterface | null): Promise<UpdatePlayerSummariesResponseInterface> {
        const playerSummariesResponse = await this.findAllPlayerSummaries()
            .then(this.convertToPlayerAdapter);

        if (filter === null) {
            return playerSummariesResponse;
        }

        return {
            ...playerSummariesResponse,
            players: playerSummariesResponse.players.filter((player) => this.filterByFields(player, filter))
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

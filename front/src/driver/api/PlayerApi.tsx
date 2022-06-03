import {PlayerSummariesApiInterface} from "../../interface-adapter/gateway/PlayerSummaries/PlayerSummariesApiInterface";
import {Player} from "../../domain/entity/Player";
import {
    FilterPlayerSummariesRequestInterface,
    FilterValue
} from "../../domain/useCase/PlayerSummaries/FilterPlayerSummariesRequestInterface";
import {PlayerDetailApiInterface} from "../../interface-adapter/gateway/PlayerDetail/PlayerDetailApiInterface";
import {PlayerDetailRequestInterface} from "../../domain/useCase/PlayerDetail/PlayerDetailRequestInterface";
import {GetPlayerDetailResponseInterface} from "../../domain/useCase/PlayerDetail/GetPlayerDetailResponseInterface";
import {GetPlayerDetailApiResponse} from "./GetPlayerDetailApiResponse";
import {
    GetPlayerSummariesResponseInterface
} from "../../domain/useCase/PlayerSummaries/GetPlayerSummariesResponseInterface";
import {GetPlayerSummariesApiResponse} from "./GetPlayerSummariesApiResponse";

export class PlayerApi implements PlayerSummariesApiInterface, PlayerDetailApiInterface {
    private readonly url: string;
    private playerSummariesUrl: string;
    private playerDetailUrl: string;

    private playerSummariesCache: GetPlayerSummariesApiResponse | null = null;

    constructor(url: string) {
        this.url = url;
        this.playerSummariesUrl = `${this.url}${process.env.REACT_APP_PLAYER_SUMMARIES_PATH}`;
        this.playerDetailUrl = `${this.url}${process.env.REACT_APP_PLAYER_DETAIL_PATH}`;
    }

    async findAllPlayerSummaries(): Promise<GetPlayerSummariesApiResponse> {
        if (this.playerSummariesCache !== null) {
            return Promise.resolve(this.playerSummariesCache);
        } else {
            this.playerSummariesCache = await this.fetchAllPlayersSummaries();
            return this.playerSummariesCache;
        }
    }

    filterByFields(player: Player, filter: FilterPlayerSummariesRequestInterface): Boolean {
        const {fields, keyword} = filter;

        return fields.some(field => {
            if ([FilterValue.FIRST_NAME, FilterValue.LAST_NAME].includes(field)) {
                return player[field].toString().toLowerCase().includes(keyword.toLowerCase());
            }
            if (FilterValue.COUNTRY == field) {
                return player.country.name.toString().toLowerCase().includes(keyword.toLowerCase());
            }
            return false;
        });
    }

    async getPlayerSummaries(filter: FilterPlayerSummariesRequestInterface | null): Promise<GetPlayerSummariesResponseInterface> {
        const playerSummariesResponse = await this.findAllPlayerSummaries()
            .then(this.convertPlayerSummariesDTOToPlayerAdapter);

        if (filter === null) {
            return playerSummariesResponse;
        }

        return {
            ...playerSummariesResponse,
            players: playerSummariesResponse.players.filter((player) => this.filterByFields(player, filter))
        };
    }

    convertPlayerSummariesDTOToPlayerAdapter(playerDTO: GetPlayerSummariesApiResponse): GetPlayerSummariesResponseInterface {
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

    async fetchAllPlayersSummaries(): Promise<GetPlayerSummariesApiResponse> {
        return new Promise<GetPlayerSummariesApiResponse>((resolve, reject) => {
            fetch(this.playerSummariesUrl)
                .then(res => res.json())
                .then(resolve)
                .catch(e => reject(`Error met when fetching data ${e}`));
        });
    }

    async getPlayerDetail(request: PlayerDetailRequestInterface): Promise<GetPlayerDetailResponseInterface> {
        const playerDetailResponse = await this.fetchPlayerDetail(request)
            .then(this.convertPlayerDetailDTOToPlayerAdapter);

        return playerDetailResponse;
    }

    convertPlayerDetailDTOToPlayerAdapter(playerDTO: GetPlayerDetailApiResponse): GetPlayerDetailResponseInterface {
        try {
            return {
                player: {
                    id: playerDTO.id,
                    firstName: playerDTO.firstName,
                    lastName: playerDTO.lastName,
                    picture: playerDTO.picture,
                    data: {
                        age: playerDTO.data.age,
                        birthday: playerDTO.data.birthday,
                        height: playerDTO.data.height,
                        weight: playerDTO.data.weight,
                        rank: playerDTO.data.rank,
                        points: playerDTO.data.points,
                    },
                    country: {
                        name: playerDTO.country.name,
                        code: playerDTO.country.code,
                        picture: playerDTO.country.picture,
                    }
                }
            }
        } catch (e) {
            throw new TypeError(`Error met when converting player data to player format ${e}`);
        }

    }

    async fetchPlayerDetail(request: PlayerDetailRequestInterface): Promise<GetPlayerDetailApiResponse> {
        const builtUrl = this.playerDetailUrl.replace("%id%", request.id.toString());
        return new Promise<GetPlayerDetailApiResponse>((resolve, reject) => {
            fetch(builtUrl)
                .then(res => res.json())
                .then(resolve)
                .catch(e => {
                    reject(`Error met when fetching data ${e}`);
                })
        })
    }
}

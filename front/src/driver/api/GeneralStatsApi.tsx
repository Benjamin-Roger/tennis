import {GetPlayerSummariesApiResponse} from "./GetPlayerSummariesApiResponse";
import {CountryNameUtils} from "../service/CountryNameUtils";
import {GeneralStatsApiInterface} from "../../interface-adapter/gateway/GeneralStats/GeneralStatsApiInterface";
import {GetGeneralStatsResponseInterface} from "../../domain/useCase/GeneralStats/GetGeneralStatsResponseInterface";
import {GetGeneralStatsApiResponse} from "./GetGeneralStatsApiResponse";

export class GeneralStatsApi implements GeneralStatsApiInterface {
    private readonly url: string;
    private generalStatsUrl: string;

    private playerSummariesCache: GetPlayerSummariesApiResponse | null = null;

    constructor(url: string) {
        this.url = url;
        this.generalStatsUrl = `${this.url}${process.env.REACT_APP_GENERAL_STATS_PATH}`;
    }

    async fetchGeneralStats(): Promise<GetGeneralStatsApiResponse> {
        return new Promise<GetGeneralStatsApiResponse>((resolve, reject) => {
            fetch(this.generalStatsUrl)
                .then(res => res.json())
                .then(resolve)
                .catch(e => {
                    reject(`Error met when fetching stats data ${e}`);
                })
        });
    }

    async getGeneralStats(): Promise<GetGeneralStatsResponseInterface> {
        return this.fetchGeneralStats()
            .then(this.convertStatsDTOtoStatsAdapterFormat);
    }

    convertStatsDTOtoStatsAdapterFormat(statsDTO: GetGeneralStatsApiResponse): GetGeneralStatsResponseInterface {
        try {
            return {
                medianHeight: statsDTO.medianHeight,
                bmi: statsDTO.bmi,
                winRatio: {
                    winRatio: statsDTO.winRatio.winRatio,
                    country: CountryNameUtils.find(statsDTO.winRatio.countryCode)
                }
            }
        } catch (e) {
            throw new TypeError(`Error met when converting player data to player format ${e}`);
        }

    }

}


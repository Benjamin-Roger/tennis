import {GetGeneralStatsResponseInterface} from "./GetGeneralStatsResponseInterface";

export interface GeneralStatsGatewayInterface {
    getGeneralStats(): Promise<GetGeneralStatsResponseInterface>;
}

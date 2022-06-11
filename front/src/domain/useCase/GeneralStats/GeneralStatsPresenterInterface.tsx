import {GetGeneralStatsResponseInterface} from "./GetGeneralStatsResponseInterface";

export interface GeneralStatsPresenterInterface {

    updateGeneralStats(generalStats: GetGeneralStatsResponseInterface ): void;
}

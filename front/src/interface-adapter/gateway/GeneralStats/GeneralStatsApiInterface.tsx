import {GetGeneralStatsAPIResponseInterface} from "./GetGeneralStatsAPIResponseInterface";
import {GeneralStatsGatewayInterface} from "../../../domain/useCase/GeneralStats/GeneralStatsGatewayInterface";

export interface GeneralStatsApiInterface extends GeneralStatsGatewayInterface {
    fetchGeneralStats():Promise<GetGeneralStatsAPIResponseInterface>;
}

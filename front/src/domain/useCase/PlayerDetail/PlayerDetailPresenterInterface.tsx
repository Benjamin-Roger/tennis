import {GetPlayerDetailResponseInterface} from "./GetPlayerDetailResponseInterface";

export interface PlayerDetailPresenterInterface {
    updatePlayerDetail(response: GetPlayerDetailResponseInterface): void;
}

import {Player} from '../../entity/Player';

export type FilterKeyword = string;

export interface FilterPlayerSummariesRequestInterface {
    callback: (p: Player) => boolean
}

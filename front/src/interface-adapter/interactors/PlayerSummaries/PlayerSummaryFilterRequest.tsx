import {
    FilterPlayerSummariesRequestInterface,
    FilterValue
} from "../../../domain/useCase/PlayerSummaries/FilterPlayerSummariesRequestInterface";

export class PlayerSummaryFilterRequest implements FilterPlayerSummariesRequestInterface{
    readonly fields: FilterValue[] = [FilterValue.FIRST_NAME, FilterValue.LAST_NAME, FilterValue.COUNTRY];
    private _keyword: string;

    constructor(keyword: string) {
        this._keyword = keyword;
    }
    get keyword(): string {
        return this._keyword;
    }
}

export type FilterKeyword = string;

export enum FilterValue {
    FIRST_NAME = "firstName",
    LAST_NAME = "lastName",
    COUNTRY = "country",
}

export interface FilterPlayerSummariesRequestInterface {
    fields: FilterValue[],
    keyword: string
}

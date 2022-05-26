export interface PlayerSummaryInterface {
    id: number,
    firstName: string,
    lastName: string,
    picture?: string,
    data: {
        rank: number,
        points: number
    },
    country: string
}

export interface GetPlayerSummariesAPIResponseInterface {
    players: PlayerSummaryInterface[]
}

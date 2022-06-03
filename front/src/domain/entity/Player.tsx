export interface PlayerStats {
    rank?: number,
    points?: number,
    weight?: number,
    height?: number,
    age?: number,
    birthday?: string,
    last?: [number]
}
export interface Player {
    id: number,
    firstName: string;
    lastName: string;
    shortName?: string;
    sex?: string;
    picture?: string,
    country: {
        name: string
        code?: string,
        picture?: string,
    };
    data?: PlayerStats
}

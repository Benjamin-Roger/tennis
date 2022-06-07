import {
    GetPlayerDetailAPIResponseInterface
} from "../../interface-adapter/gateway/PlayerDetail/GetPlayerDetailAPIResponseInterface";

export class GetPlayerDetailApiResponse implements GetPlayerDetailAPIResponseInterface {
    id: string;
    firstName: string;
    lastName: string;
    picture?: string;
    stats: {
        rank: number,
        points: number,
        age: number,
        birthday: string,
        height: number,
        weight: number
    };
    country: {
        name: string,
        code: string,
        picture: string
    };

    constructor(id: string, firstName: string, lastName: string, picture: string, stats: { rank: number; points: number; age: number; birthday: string; height: number; weight: number }, country: { name: string; code: string; picture: string }) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.picture = picture;
        this.stats = stats;
        this.country = country;
    }
}

import {
    GetPlayerDetailAPIResponseInterface
} from "../../interface-adapter/gateway/PlayerDetail/GetPlayerDetailAPIResponseInterface";

export class GetPlayerDetailApiResponse implements GetPlayerDetailAPIResponseInterface {
    id: number;
    firstName: string;
    lastName: string;
    picture?: string;
    data: {
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

    constructor(id: number, firstName: string, lastName: string, picture: string, data: { rank: number; points: number; age: number; birthday: string; height: number; weight: number }, country: { name: string; code: string; picture: string }) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.picture = picture;
        this.data = data;
        this.country = country;
    }
}

import {GetPlayerDetailApiResponse} from "./GetPlayerDetailApiResponse";
import {MockServer} from "jest-mock-server";
import {PlayerApi} from "./PlayerApi";
import {PlayerDetailRequestInterface} from "../../domain/useCase/PlayerDetail/PlayerDetailRequestInterface";

const mockAPIResponse = new GetPlayerDetailApiResponse(
    "1",
    "Novak",
    "Djokovic",
    "https://data.latelier.co/training/tennis_stats/resources/Djokovic.png",
    {
        rank: 1,
        points: 11015,
        age: 34,
        birthday: "22/05/1987",
        weight: 77000,
        height: 188
    },
    {
        name: "Serbia",
        code: "SER",
        picture: "https://data.latelier.co/training/tennis_stats/resources/Serbie.png"
    },
);

describe("Testing player detail API", () => {
    const server = new MockServer();
    beforeAll(() => server.start());
    afterAll(() => server.stop());
    beforeEach(() => server.reset());

    it("PlayerDetailApi correctly converts DTO to adapter format", async () => {
        const route = server
            .get(`/player/${mockAPIResponse.id}`)
            .mockImplementationOnce((ctx) => {
                ctx.status = 200;
                ctx.set("Access-Control-Allow-Origin", "*");
                ctx.body = mockAPIResponse;
            })

        const url = server.getURL().toString();
        const playerDetailApi = new PlayerApi(url);
        const request: PlayerDetailRequestInterface = {
            id: "1",
        }
        const response = await playerDetailApi.getPlayerDetail(request);

        expect(response.player.firstName).toEqual(mockAPIResponse.firstName);
        expect(response.player.lastName).toEqual(mockAPIResponse.lastName);
        expect(response.player.id).toEqual(mockAPIResponse.id);
        expect(response.player.picture).toEqual(mockAPIResponse.picture);
        expect(response.player.country).toEqual(mockAPIResponse.country);
        expect(response.player.data).toEqual(mockAPIResponse.stats);

        expect(route).toHaveBeenCalledTimes(1);
    });

})


export {};

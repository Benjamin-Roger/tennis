import {MockServer} from "jest-mock-server";
import {PlayerApi} from "./PlayerApi";
import {PlayerSummariesPresenter} from "../../interface-adapter/presenter/PlayerSummaries/PlayerSummariesPresenter";
import {PlayerSummariesInteractor} from "../../interface-adapter/interactors/PlayerSummaries/PlayerSummariesInteractor";
import {GetPlayerSummariesApiResponse} from "./GetPlayerSummariesApiResponse";
import {
    PlayerSummaryFilterRequest
} from "../../interface-adapter/interactors/PlayerSummaries/PlayerSummaryFilterRequest";

const mockAPIResponse: GetPlayerSummariesApiResponse = {
    players: [0, 1, 2, 3].map(i => ({
        id: i.toString(),
        firstName: "firstName " + i,
        lastName: "lastName " + i,
        stats: {
            rank: i + 1,
            points: 1200,
        },
        countryCode: "SRB",
    }))
};

describe("Testing player summaries API", () => {
    const server = new MockServer();
    beforeAll(() => server.start());
    afterAll(() => server.stop());
    beforeEach(() => server.reset());

    it("PlayerDetailApi correctly converts DTO to adapter format", async () => {
        server
            .get("/playerSummaries")
            .mockImplementationOnce((ctx) => {
                ctx.status = 200;
                ctx.set("Access-Control-Allow-Origin", "*");
                ctx.body = mockAPIResponse;
            })

        const url = server.getURL().toString();
        const playerDetailApi = new PlayerApi(url);

        const response = await playerDetailApi.getPlayerSummaries(null);

        expect(response.players.length).toEqual(4);

        response.players.forEach((player, index) => {
            expect(player.lastName).toEqual(mockAPIResponse.players[index].lastName);
            expect(player.id).toEqual(mockAPIResponse.players[index].id);
            expect(player.picture).toEqual(mockAPIResponse.players[index].picture);
            expect(player.country.name).toEqual(mockAPIResponse.players[index].countryCode);
            expect(player.data).toEqual(mockAPIResponse.players[index].stats);
        })
    });

})


const mockPlayerSummariesApiResponse: GetPlayerSummariesApiResponse = {
    players: [
        {
            id: "1",
            firstName: "Novak",
            lastName: "Djokovic",
            countryCode: "SRB",
            stats: {
                rank: 1,
                points: 2,
            }
        },
        {
            id: "2",
            firstName: "S??r??na",
            lastName: "Williams",
            countryCode: "USA",
            stats: {
                rank: 1,
                points: 2,
            }
        }
    ]
}

describe("Cache prevents to send several requests", () => {
    const server = new MockServer();
    beforeAll(() => server.start());
    afterAll(() => server.stop());
    beforeEach(() => server.reset());

    it("Cache stores summaries after first request", async () => {
        const route = server
            .get("/playerSummaries")
            .mockImplementation((ctx) => {
                ctx.status = 200;
                ctx.set("Access-Control-Allow-Origin", "*");
                ctx.body = mockPlayerSummariesApiResponse;
            })

        const url = server.getURL().toString();
        const repository = new PlayerApi(url);

        const presenter = new PlayerSummariesPresenter();
        const interactor = new PlayerSummariesInteractor(presenter, repository);
        await interactor.findAllPlayerSummaries();
        await interactor.getPlayerSummaries("ak");
        await interactor.getPlayerSummaries("djoko");
        await interactor.getPlayerSummaries("unknownName");

        expect(route).toHaveBeenCalledTimes(1);

    });
});

describe("Search filter by name and country", () => {
    const server = new MockServer();
    beforeAll(() => server.start());
    afterAll(() => server.stop());
    beforeEach(() => server.reset());

    it("Searchbar input correctly filters by name", async () => {
        server
            .get("/playerSummaries")
            .mockImplementation((ctx) => {
                ctx.status = 200;
                ctx.set("Access-Control-Allow-Origin", "*");
                ctx.body = mockPlayerSummariesApiResponse;
            })

        const url = server.getURL().toString();
        const repository = new PlayerApi(url);

        let res = await repository.getPlayerSummaries(null);
        expect(res.players.length).toEqual(2);

        res = await repository.getPlayerSummaries(new PlayerSummaryFilterRequest("ak"));
        expect(res.players.length).toEqual(1);
        expect(res.players[0].firstName).toMatch(mockPlayerSummariesApiResponse.players[0].firstName);

        res = await repository.getPlayerSummaries(new PlayerSummaryFilterRequest("djoko"));
        expect(res.players.length).toEqual(1);
        expect(res.players[0].firstName).toMatch(mockPlayerSummariesApiResponse.players[0].firstName);

        res = await repository.getPlayerSummaries(new PlayerSummaryFilterRequest("unknownName"));
        expect(res.players.length).toEqual(0);
    });

    it("Searchbar input correctly filters by countryCode", async () => {
        server
            .get("/playerSummaries")
            .mockImplementation((ctx) => {
                ctx.status = 200;
                ctx.set("Access-Control-Allow-Origin", "*");
                ctx.body = mockPlayerSummariesApiResponse;
            })

        const repository = new PlayerApi(server.getURL().toString());

        let res = await repository.getPlayerSummaries(null);
        expect(res.players.length).toEqual(2);

        res = await repository.getPlayerSummaries(new PlayerSummaryFilterRequest("Ser"));
        expect(res.players.length).toEqual(1);
        expect(res.players[0].firstName).toMatch(mockPlayerSummariesApiResponse.players[0].firstName);

        res = await repository.getPlayerSummaries(new PlayerSummaryFilterRequest("usa"));
        expect(res.players.length).toEqual(1);
        expect(res.players[0].firstName).toMatch(mockPlayerSummariesApiResponse.players[1].firstName);

        res = await repository.getPlayerSummaries(new PlayerSummaryFilterRequest("unknownName"));
        expect(res.players.length).toEqual(0);
    });

})


export {};

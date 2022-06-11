import {MockServer} from "jest-mock-server";
import {GetGeneralStatsApiResponse} from "./GetGeneralStatsApiResponse";
import {GeneralStatsApi} from "./GeneralStatsApi";

const mockAPIResponse: GetGeneralStatsApiResponse = {
    "bmi": {"averagePlayerBmi": 23.357838995505837},
    "winRatio": {"countryCode": "SRB", "winRatio": 1.0},
    "medianHeight": {"playersMedianHeight": 185.0}
};

describe("Testing general stats API", () => {
    const server = new MockServer();
    beforeAll(() => server.start());
    afterAll(() => server.stop());
    beforeEach(() => server.reset());

    it("GeneralStatsApi correctly converts DTO to adapter format", async () => {
        server
            .get("/stats")
            .mockImplementationOnce((ctx) => {
                ctx.status = 200;
                ctx.set("Access-Control-Allow-Origin", "*");
                ctx.body = mockAPIResponse;
            })

        const url = server.getURL().toString();
        const api = new GeneralStatsApi(url);

        const response = await api.getGeneralStats();

        expect(response).not.toBeNull();
        expect(response.medianHeight).toEqual(mockAPIResponse.medianHeight);
        expect(response.bmi).toEqual(mockAPIResponse.bmi);
        expect(response.winRatio?.country).toEqual("Serbie");
        expect(response.winRatio?.winRatio).toEqual(mockAPIResponse.winRatio.winRatio);
    });

})



export {};

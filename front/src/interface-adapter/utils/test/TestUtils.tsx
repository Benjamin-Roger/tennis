import {GetPlayerSummariesAPIResponseInterface} from "../../gateway/PlayerSummaries/GetPlayerSummariesAPIResponseInterface";
import {PlayerApi} from "../../../driver/infrastructure/PlayerApi";


export class PlayerSummariesRepositoryMock extends PlayerApi {

    mockResponse: GetPlayerSummariesAPIResponseInterface;

    constructor(mockResponse: GetPlayerSummariesAPIResponseInterface) {
        super("fakeUrl");
        this.mockResponse = mockResponse;
    }

    async fetchAllPlayersSummaries(): Promise<GetPlayerSummariesAPIResponseInterface> {
        return Promise.resolve(this.mockResponse);
    }
}

import {PlayerSummariesApi} from "../../../driver/infrastructure/PlayerSummariesApi";
import {GetPlayerSummariesAPIResponseInterface} from "../../gateway/PlayerSummaries/GetPlayerSummariesAPIResponseInterface";


export class PlayerSummariesRepositoryMock extends PlayerSummariesApi {

    mockResponse: GetPlayerSummariesAPIResponseInterface;

    constructor(mockResponse: GetPlayerSummariesAPIResponseInterface) {
        super("fakeUrl");
        this.mockResponse = mockResponse;
    }

    async fetchAllPlayersSummaries(): Promise<GetPlayerSummariesAPIResponseInterface> {
        return Promise.resolve(this.mockResponse);
    }
}

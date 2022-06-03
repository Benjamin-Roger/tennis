import {PlayerDetailPageController} from "./PlayerDetailPageController";
import {GetPlayerDetailResponseInterface} from "../../../domain/useCase/PlayerDetail/GetPlayerDetailResponseInterface";
import {PlayerDetailPresenter} from "../../presenter/PlayerDetail/PlayerDetailPresenter";
import {PlayerDetailApiInterface} from "../../gateway/PlayerDetail/PlayerDetailApiInterface";
import {GetPlayerDetailAPIResponseInterface} from "../../gateway/PlayerDetail/GetPlayerDetailAPIResponseInterface";

const mockResponse = {
    player: {
        id: 1,
        firstName: "Novak",
        lastName: "Djokovic",
        picture: "https://data.latelier.co/training/tennis_stats/resources/Djokovic.png",
        data: {
            rank: 1,
            points: 11015,
            age: 34,
            birthday: "22/05/1987",
            weight: 77000,
            height: 188
        },
        country: {
            name: "Serbia",
            code: "SER",
            picture: "https://data.latelier.co/training/tennis_stats/resources/Serbie.png"
        },
    }
};

export class PlayerDetailRepositoryMock implements PlayerDetailApiInterface {
    mockResponse: GetPlayerDetailResponseInterface;

    constructor(mockResponse: GetPlayerDetailResponseInterface) {
        this.mockResponse = mockResponse;
    }

    fetchPlayerDetail(): Promise<GetPlayerDetailAPIResponseInterface> {
        return Promise.resolve({});
    }


    getPlayerDetail(): Promise<GetPlayerDetailResponseInterface> {
        return Promise.resolve(this.mockResponse);
    }


}


test("Player detail page correctly shows information of the player", async () => {
    const playerDetailPageController = new PlayerDetailPageController(new PlayerDetailPresenter(), new PlayerDetailRepositoryMock(mockResponse));

    await playerDetailPageController.playerDetailInteractor.getPlayerDetail({
        id: mockResponse.player.id
    })

    const viewModel = playerDetailPageController.viewModel;
    expect(viewModel.title).toMatch(mockResponse.player.firstName);
    expect(viewModel.title).toMatch(mockResponse.player.lastName);
    expect(viewModel.playerPicture).toMatch(mockResponse.player.picture);
    expect(viewModel.countryCode).toMatch(mockResponse.player.country.code);
    expect(viewModel.countryName).toMatch(mockResponse.player.country.name);
    expect(viewModel.countryPicture).toMatch(mockResponse.player.country.picture);
});


export {};

import {PlayerDetailPageController} from "./PlayerDetailPageController";
import {GetPlayerDetailResponseInterface} from "../../../domain/useCase/PlayerDetail/GetPlayerDetailResponseInterface";
import {PlayerDetailPresenter} from "../../presenter/PlayerDetail/PlayerDetailPresenter";
import {PlayerDetailApiInterface} from "../../gateway/PlayerDetail/PlayerDetailApiInterface";
import {GetPlayerDetailAPIResponseInterface} from "../../gateway/PlayerDetail/GetPlayerDetailAPIResponseInterface";

const mockResponse = {
    player: {
        id: "1",
        firstName: "Novak",
        lastName: "Djokovic",
        picture: "https://data.latelier.co/training/tennis_stats/resources/Djokovic.png",
        data: {
            rank: 1,
            points: 11015,
            age: 34,
            birthday: "1987-05-22",
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

    const {player} = mockResponse;

    await playerDetailPageController.playerDetailInteractor.getPlayerDetail({
        id: player.id
    })

    const viewModel = playerDetailPageController.viewModel;
    expect(viewModel.title).toMatch(player.firstName);
    expect(viewModel.title).toMatch(player.lastName);
    expect(viewModel.playerPicture).toEqual(player.picture);
    expect(viewModel.countryCode).toEqual(player.country.code);
    expect(viewModel.countryName).toEqual(player.country.name);
    expect(viewModel.countryPicture).toEqual(player.country.picture);

    expect(viewModel.firstName).toEqual(player.firstName);
    expect(viewModel.lastName).toEqual(player.lastName);
    expect(viewModel.fullName).toEqual(player.firstName + " " + player.lastName);

    const getStat:any = (label:string) => viewModel.stats?.find(s => s.label === label)?.data;
    expect(getStat("Rank")).toEqual(`#${player.data.rank}`);
    expect(getStat("Weight")).toEqual(`${player.data.weight/1000} kg`);
    expect(getStat("Height")).toEqual(`${player.data.height} cm`);
    expect(getStat("Birthday")).toEqual(new Date(player.data.birthday).toLocaleDateString());
    expect(getStat("Age")).toEqual(player.data.age);
    expect(getStat("Country")).toEqual(player.country.name);

});


export {};

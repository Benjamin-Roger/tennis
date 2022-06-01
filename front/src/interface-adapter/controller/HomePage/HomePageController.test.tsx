import {HomePageController} from "./HomePageController";
import {PlayerSummariesPresenter} from "../../presenter/PlayerSummaries/PlayerSummariesPresenter";
import {PlayerSummariesRepositoryMock} from "../../utils/test/TestUtils";

const mockResponse = {
    players: [0, 1, 2, 3].map(i => ({
        id: i,
        firstName: "firstName "+ i,
        lastName: "lastName "+ i,
        data: {
            rank: i + 1,
            points: 1200,
        },
        country: "Serbia",
    }))
};

test("Home controller correctly shows title", async () => {
    const homePageController = new HomePageController(new PlayerSummariesPresenter(), new PlayerSummariesRepositoryMock(mockResponse));
    expect(homePageController.viewModel.title).toEqual("Bienvenue dans l'annuaire du tennis");
});

test("Home controller correctly shows button label", async () => {
    const homePageController = new HomePageController(new PlayerSummariesPresenter(), new PlayerSummariesRepositoryMock(mockResponse));
    expect(homePageController.viewModel.buttonLabel).toEqual("Rechercher un joueur");
});

test("Home controller correctly shows players data from API", async () => {
    const homePageController = new HomePageController(new PlayerSummariesPresenter(), new PlayerSummariesRepositoryMock(mockResponse));
    await homePageController.init();
    expect(homePageController.viewModel.players.length).toEqual(4);

    const mockResponseFirstPlayer = mockResponse.players[0];
    const viewModelFirstPlayer = homePageController.viewModel.players[0];
    expect(viewModelFirstPlayer.name).toEqual(`${mockResponseFirstPlayer.firstName} ${mockResponseFirstPlayer.lastName}`);
    expect(viewModelFirstPlayer.stats.length).toEqual(3);
    expect(viewModelFirstPlayer.stats.find(s => s.label === "Rank")?.data).toEqual("#" + mockResponseFirstPlayer.data.rank);
    expect(viewModelFirstPlayer.stats.find(s => s.label === "Points")?.data).toEqual(mockResponseFirstPlayer.data.points);
    expect(viewModelFirstPlayer.stats.find(s => s.label === "Country")?.data).toEqual(mockResponseFirstPlayer.country);
});

export {};

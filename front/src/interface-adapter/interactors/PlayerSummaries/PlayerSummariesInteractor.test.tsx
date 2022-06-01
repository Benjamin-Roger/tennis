import {PlayerSummariesInteractor} from "./PlayerSummariesInteractor";
import {PlayerSummariesRepositoryMock} from "../../utils/test/TestUtils";
import {PlayerSummariesPresenter} from "../../presenter/PlayerSummaries/PlayerSummariesPresenter";
import {GetPlayerSummariesAPIResponseInterface} from "../../gateway/PlayerSummaries/GetPlayerSummariesAPIResponseInterface";

const mockPlayersResponse: GetPlayerSummariesAPIResponseInterface = {
    players: [
        {
            id: 1,
            firstName: "Novak",
            lastName: "Djokovic",
            country: "Serbia",
            data: {
                rank: 1,
                points: 2,
            }
        },
        {
            id: 2,
            firstName: "Séréna",
            lastName: "Williams",
            country: "USA",
            data: {
                rank: 1,
                points: 2,
            }
        }
    ]
}

test("Searchbar input correctly filters by name", async () => {

    const presenter = new PlayerSummariesPresenter();
    const repository = new PlayerSummariesRepositoryMock(mockPlayersResponse)
    const interactor = new PlayerSummariesInteractor(presenter, repository);
    await interactor.findAllPlayerSummaries();
    expect(presenter.viewModel.players.length).toEqual(2);

    await interactor.getPlayerSummaries("ak");
    expect(presenter.viewModel.players.length).toEqual(1);
    expect(presenter.viewModel.players[0].name).toMatch(mockPlayersResponse.players[0].firstName);

    await interactor.getPlayerSummaries("djoko");
    expect(presenter.viewModel.players.length).toEqual(1);
    expect(presenter.viewModel.players[0].name).toMatch(mockPlayersResponse.players[0].firstName);

    await interactor.getPlayerSummaries("unknownName");
    expect(presenter.viewModel.players.length).toEqual(0);

});

test("Searchbar input correctly filters by countryCode", async () => {
    const presenter = new PlayerSummariesPresenter();
    const repository = new PlayerSummariesRepositoryMock(mockPlayersResponse)
    const interactor = new PlayerSummariesInteractor(presenter, repository);

    await interactor.findAllPlayerSummaries();
    expect(presenter.viewModel.players.length).toEqual(2);

    await interactor.getPlayerSummaries("Ser");
    expect(presenter.viewModel.players.length).toEqual(1);
    expect(presenter.viewModel.players[0].name).toMatch(mockPlayersResponse.players[0].firstName);

    await interactor.getPlayerSummaries("usa");
    expect(presenter.viewModel.players.length).toEqual(1);
    expect(presenter.viewModel.players[0].name).toMatch(mockPlayersResponse.players[1].firstName);

});

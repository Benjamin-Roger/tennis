import React, {useCallback, useEffect, useState} from "react";
import {PlayerSummariesPresenter} from "../../../interface-adapter/presenter/PlayerSummaries/PlayerSummariesPresenter";
import {SinglePlayerSummary} from "../../../interface-adapter/viewModel/HomePage/HomeViewModel";
import {HomePageController} from "../../../interface-adapter/controller/HomePage/HomePageController";
import {SearchInputFilter} from "../home/SearchFilterInput";
import {Helmet} from "react-helmet";
import {PlayerApi} from "../../api/PlayerApi";
import {PlayerCards} from "../home/PlayerCards";
import {StatLinkWidget} from "../home/StatLinkWidget";
import Container from "../common/Layout/Container";


const HomePage: React.FC = () => {
    const playerSummariesController = new HomePageController(
        new PlayerSummariesPresenter(),
        new PlayerApi(process.env.REACT_APP_BASE_API_URL || "http://localhost:8000/")
    );
    const {viewModel, playerSummariesInteractor} = playerSummariesController;

    const [keyword, setKeyword] = useState<string>("");
    const [players, setPlayers] = useState<Array<SinglePlayerSummary>>([]);

    const fetchPlayers = useCallback(async () => {
        await playerSummariesController.init();
        setPlayers(viewModel.players);
    }, []);

    useEffect(() => {
        fetchPlayers()
        // eslint-disable-next-line no-console
            .catch(console.error);
    }, [fetchPlayers]);

    const filterPlayers = useCallback(async () => {
        await playerSummariesInteractor.getPlayerSummaries(keyword);
        setPlayers(viewModel.players);
    }, [keyword])

    useEffect(() => {
        filterPlayers()
        // eslint-disable-next-line no-console
            .catch(console.error);
    }, [filterPlayers])

    function handleKeyword(e: { target: { value: React.SetStateAction<string>; }; }): void {
        setKeyword(e.target.value)
    }

    return (
        <>
            <Helmet><title>{viewModel.title}</title></Helmet>
            <Container>
                <div className="relative">
                    <StatLinkWidget/>
                    <div className="md:mt-12 w-full md:w-1/2 lg:w-1/3">
                        <SearchInputFilter value={keyword} onChange={handleKeyword}
                            placeholder={viewModel.buttonLabel}/>
                        {players && players.length > 0 && <PlayerCards players={players}/>}
                    </div>
                </div>
            </Container>

        </>
    )
}
;


export default HomePage;

import React, {useCallback, useEffect, useState} from "react";
import {PlayerSummariesPresenter} from "../../../interface-adapter/presenter/PlayerSummaries/PlayerSummariesPresenter";
import {SinglePlayerSummary} from "../../../interface-adapter/viewModel/HomePage/HomeViewModel";
import {HomePageController} from "../../../interface-adapter/controller/HomePage/HomePageController";
import {PlayerSummaryCard} from "../home/PlayerSummaryCard";
import {SearchInputFilter} from "../home/SearchFilterInput";
import {Helmet} from "react-helmet";
import {PlayerApi} from "../../api/PlayerApi";
import {Link} from "react-router-dom";

const containerStyles = {
    width: "24rem",
    maxWidth: "100%",
    marginTop: "3rem"
};


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

            <div style={containerStyles}>

                <SearchInputFilter value={keyword} onChange={handleKeyword} placeholder={viewModel.buttonLabel}/>
                <div>
                    {players.map(player => <Link
                        key={player.id}
                        to={`/player/${player.id}`}
                        style={{"textDecoration": "none"}}
                    >
                        <PlayerSummaryCard key={player.id} name={player.name}
                            picture={player.picture}
                            stats={player.stats}/>
                    </Link>
                    )}
                </div>
            </div>
        </>
    )
}
;


export default HomePage;

import {useParams} from "react-router-dom";
import React, {useCallback, useEffect, useState} from "react";
import {PlayerApi} from "../../api/PlayerApi";
import {
    PlayerDetailPageController
} from "../../../interface-adapter/controller/PlayerDetailPage/PlayerDetailPageController";
import {PlayerDetailPresenter} from "../../../interface-adapter/presenter/PlayerDetail/PlayerDetailPresenter";
import {PlayerDetailViewModel} from "../../../interface-adapter/viewModel/PlayerDetailPage/PlayerDetailViewModel";
import {PlayerModal} from "../player/PlayerModal";
import {Helmet} from "react-helmet";

const PlayerPage: React.FC = () => {
    const {id} = useParams();

    const controller = new PlayerDetailPageController(
        new PlayerDetailPresenter(),
        new PlayerApi(process.env.REACT_APP_BASE_API_URL || "http://localhost:8000/")
    );
    const {playerDetailInteractor} = controller;

    const [unkownPlayer, setUnknownPlayer] = useState<Boolean>(false);

    const [player, setPlayer] = useState<PlayerDetailViewModel | undefined>();

    const fetchPlayer = useCallback(async () => {
        if (id) {
            try {
                await playerDetailInteractor.getPlayerDetail({id});
                setPlayer(controller.viewModel);
            } catch (e) {
                console.error(e); // eslint-disable-line no-console
                setUnknownPlayer(true);
            }
        }
    }, []);

    useEffect(() => {
        fetchPlayer()
            // eslint-disable-next-line no-console
            .catch(console.error);
    }, [fetchPlayer]);


    return <>

        {
            unkownPlayer ?
                "Ce joueur n'est pas connu !" :
                (player && <>
                    <Helmet>
                        <title>{player.title}</title>
                    </Helmet>
                    <PlayerModal
                        firstName={player.firstName}
                        lastName={player.lastName}
                        fullName={player.fullName}
                        playerPicture={player.playerPicture}
                        countryName={player.countryName}
                        countryCode={player.countryCode}
                        countryPicture={player.countryPicture}
                        stats={player.stats}/>
                </>)
        }

    </>
};

export default PlayerPage;

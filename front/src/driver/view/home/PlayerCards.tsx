import {SinglePlayerSummary} from "../../../interface-adapter/viewModel/HomePage/HomeViewModel";
import {Link} from "react-router-dom";
import {PlayerSummaryCard} from "./PlayerSummaryCard";
import React from "react";
import {motion} from "framer-motion";


const cardContainerAnimation = {
    hidden: {},
    visible: {
        transition: {
            staggerChildren: .05
        }
    }
};
const cardAnimation = {
    hidden: {
        opacity: 0,
        y: 10,
    },
    visible: {
        opacity: 1,
        y: 0
    },
};


export const PlayerCards:React.FC<{ players: Array<SinglePlayerSummary> }> = (props) => {
    const {players} = props;
    return <motion.div variants={cardContainerAnimation} initial="hidden" animate="visible">
        {players.map(player => player.id && <motion.div
            key={player.id + player.name}
            variants={cardAnimation}
        >
            <Link
                to={`/player/${player.id}`}
                style={{"textDecoration": "none"}}
            >
                <PlayerSummaryCard key={player.id} name={player.name}
                    picture={player.picture}
                    stats={player.stats}/>
            </Link>
        </motion.div>
        )}
    </motion.div>;
};

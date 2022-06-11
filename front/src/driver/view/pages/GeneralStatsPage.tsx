import React, {useCallback, useEffect, useState} from "react";
import {Helmet} from "react-helmet";
import {
    GeneralStatsPageController
} from "../../../interface-adapter/controller/GeneralStatsPage/GeneralStatsPageController";
import {GeneralStatsPresenter} from "../../../interface-adapter/presenter/GeneralStats/GeneralStatsPresenter";
import {GeneralStatsApi} from "../../api/GeneralStatsApi";
import {GeneralStatsViewModel} from "../../../interface-adapter/viewModel/GeneralStatsPage/GeneralStatsViewModel";
import {GeneralStatsCard} from "../stats/GeneralStatsCard";
import {theme} from "../theme";
import {Modal} from "../common/Modal/Modal";
import {motion} from "framer-motion";


const titleStyles = {
    WebkitTextStroke: "3px " + theme.color.primary,
};

const cardStyles = {
    flex: 1,
    minWidth: "300px",
    maxWidth: "100%",
}


const titleAnimation = {
    hidden: {
        opacity: 0,
        y: 10,
    },
    visible: {
        opacity: 1,
        y: 0,
        transition: {
            ease: "easeOut"
        }
    },
}


const statContainerAnimation = {
    hidden: {},
    visible: {
        transition: {
            delayChildren: 0.2,
            staggerChildren: 0.1
        }
    },
}

const statAnimation = {
    hidden: {
        opacity: 0,
        y: 10,
    },
    visible: {
        opacity: 1,
        y: 0
    },
}

const GeneralStatsPage: React.FC = () => {
    const controller = new GeneralStatsPageController(
        new GeneralStatsPresenter(),
        new GeneralStatsApi(process.env.REACT_APP_BASE_API_URL || "http://localhost:8000/")
    );

    const {viewModel} = controller;

    const [stats, setStats] = useState<GeneralStatsViewModel | undefined>();

    const fetchStats = useCallback(async () => {
        await controller.init();
        setStats(controller.viewModel);
    }, []);

    useEffect(() => {
        fetchStats()
        // eslint-disable-next-line no-console
            .catch(console.error);
    }, [fetchStats]);

    return (
        <>
            <Helmet><title>{viewModel.title}</title></Helmet>
            <Modal>
                <div className="py-6 p-4 md:p-12 lg:p-20">
                    <motion.h1
                        className="text-white text-5xl md:text-7xl text-center md:text-left"
                        style={titleStyles}
                        variants={titleAnimation}
                        initial="hidden"
                        animate="visible"
                    >
                        {viewModel.title}
                    </motion.h1>
                    <div className="min-h-[15rem]">
                        {stats && (<motion.div className="flex flex-wrap gap-4" variants={statContainerAnimation}
                            initial="hidden"
                            animate="visible">
                            {stats.medianHeightValue &&
                                    <motion.div style={cardStyles} variants={statAnimation}>
                                        <GeneralStatsCard
                                            label={stats.medianHeightLabel}
                                            value={stats.medianHeightValue}
                                            icon="/assets/images/height.svg"
                                        />
                                    </motion.div>}
                            {stats.averageBmiValue &&
                                    <motion.div style={cardStyles} variants={statAnimation}>
                                        <GeneralStatsCard
                                            label={stats.averageBmiLabel}
                                            value={stats.averageBmiValue}
                                            icon="/assets/images/bmi.svg"
                                        />
                                    </motion.div>}
                            {stats.winRatioValue &&
                                    <motion.div style={cardStyles} variants={statAnimation}>
                                        <GeneralStatsCard
                                            label={stats.winRatioLabel}
                                            value={stats.winRatioValue}
                                            subtitle={stats.winRatioSubtitle}
                                            icon="/assets/images/ratio.svg"
                                        />
                                    </motion.div>}

                        </motion.div>)}
                    </div>
                </div>
            </Modal>
        </>
    )
}
;


export default GeneralStatsPage;

import {PlayerStat} from "../../../interface-adapter/viewModel/PlayerDetailPage/PlayerDetailViewModel";
import React from "react";
import {motion} from "framer-motion"
import {PlayerStatCard} from "../home/PlayerStatCard";
import {theme} from "../theme";
import {Modal} from "../common/Modal/Modal";
import "./modal-grid.css";

type PlayerModalProps = {
    firstName: string,
    lastName: string,
    fullName: string,
    playerPicture?: string,
    countryName?: string,
    countryCode?: string,
    countryPicture?: string,
    stats?: PlayerStat[]
}


const containerStyle = {
    minHeight: "85vh",
} as React.CSSProperties


const firstNameStyle = {
    WebkitTextStroke: "3px " + theme.color.primary,
};

const pictureStyle = {
    maxWidth: "300%",
    objectPosition: "bottom",
    filter: `drop-shadow(.5rem .5rem .5rem ${theme.color.black})`,
    WebkitFilter: `drop-shadow(.5rem .5rem .5rem ${theme.color.black})`
} as React.CSSProperties


const countryCodeStyle = {
    color: theme.color.black,
    fontSize: theme.fontSizes.lg,
    fontWeight: "lighter",
    textTransform: "uppercase",
    textAlign: "center",
    letterSpacing: "1.5rem",
    textIndent: "1.5rem"
} as React.CSSProperties

const animations = {
    title: {
        hidden: {
            opacity: 0,
            y: 10,
        },
        visible: {
            opacity: 1,
            y: 0,
            transition: {
                delay: 0,
                duration: .5
            }
        }
    },
    statsContainer: {
        hidden: {},
        visible: {
            transition: {
                delayChildren: .7,
                staggerChildren: .05
            }
        }
    },
    stats: {
        hidden: {
            opacity: 0,
            y: 10,
        },
        visible: {
            opacity: 1,
            y: 0
        }
    },
    picture: {
        hidden: {
            opacity: 0,
            y: 30,
        },
        visible: {
            opacity: 1,
            y: 0,
            transition: {
                delay: 0.35,
                duration: .5,
                ease: "easeOut",
            }
        }
    },
}

export const PlayerModal: React.FC<PlayerModalProps> = ({
    firstName,
    lastName,
    fullName,
    playerPicture,
    countryName,
    countryCode,
    countryPicture,
    stats,
}) => {

    return (
        <Modal alignment="items-end">
            <div style={containerStyle} className="flex items-end w-full justify-center">
                <section className="modal-grid grid w-full h-full relative">
                    <div className="relative flex justify-center items-center max-h-56 lg:max-h-full lg:h-full lg:items-end" style={{gridArea:"picture"}}>
                        <motion.img style={pictureStyle} className="h-full object-cover lg:object-contain" src={playerPicture}
                            alt={lastName} title={fullName}
                            variants={animations.picture} initial="hidden" animate="visible"/>
                    </div>
                    <div className="hide-scrollbar lg:overflow-y-scroll mx-4 pt-12  mb-12 md:pt-20" style={{gridArea:"main"}}>
                        <motion.h1 variants={animations.title} initial="hidden" animate="visible" className="text-5xl md:text-7xl text-primary">
                            <span style={firstNameStyle} className="text-white">{firstName}</span>
                            <br/>
                            <span className="text-6xl md:text-9xl">{lastName}</span>
                        </motion.h1>
                        {stats && (
                            <motion.div
                                className="mt-12 ml-12 md:ml-20 grid gap-8 grid-cols-2 md:grid-cols-4"
                                variants={animations.statsContainer}
                                initial="hidden"
                                animate="visible">
                                {
                                    stats.map(stat => stat.data &&
                                        <motion.div key={stat.label + stat.data} variants={animations.stats}>
                                            <PlayerStatCard label={stat.label} data={stat.data}/>
                                        </motion.div>
                                    )
                                }
                            </motion.div>)
                        }
                    </div>
                    <div className="mt-10 mr-8 flex" style={{gridArea:"extraInfo"}}>
                        <div className="mx-auto">
                            <img
                                className="w-full shadow-custom mb-4 max-h-32"
                                src={countryPicture}
                                title={countryName}
                                alt={countryName}/>
                            <p style={countryCodeStyle}>{countryCode}</p>
                        </div>
                    </div>
                </section>
            </div>
        </Modal>
    )
}


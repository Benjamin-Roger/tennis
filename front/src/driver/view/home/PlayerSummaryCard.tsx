import {PlayerStat} from "../../../interface-adapter/viewModel/HomePage/HomeViewModel";
import {PlayerStatCard} from "./PlayerStatCard";
import React from "react";
import {theme} from "../theme";

const containerStyles = {
    margin: "1rem 0",
    background: "#fff",
    overflow: "hidden",
    borderRadius: ".2rem",
    boxShadow: ".3rem .3rem .3rem var(--black)"
}

const wrapperStyles = {
    margin: ".5rem .5rem 0 0",
    width: "100%",
    height: "8rem",
    display: "flex",
    alignItems: "center",
}

const pictureContainerStyles = {
    height: "100%",
    flex: 1,
}

const pictureStyles = {
    minHeight: "100%",
    maxWidth: "100%",
    objectFit: "cover"
} as React.CSSProperties;

const textContainerStyles = {
    flex: 2,
}

const nameStyles = {
    fontSize: theme.fontSizes.md,
    color: theme.color.primary
}

const statContainerStyles = {
    display: "flex",
    gap: "1rem"
}
type PlayerSummaryCardProps = {
    name: string;
    picture?: string;
    stats?: PlayerStat[];
}

export const PlayerSummaryCard: React.FC<PlayerSummaryCardProps> = (props) => {
    const {name, picture, stats} = props;
    return (
        <div style={containerStyles}>
            <div style={wrapperStyles}>
                <div style={pictureContainerStyles}>
                    {picture && <img style={pictureStyles} title={name} alt={name} src={picture}/>}
                </div>
                <div style={textContainerStyles}>
                    <h2 style={nameStyles}>{name}</h2>
                    {stats && <div style={statContainerStyles}>
                        {stats.map((stat) => <PlayerStatCard key={stat.label} label={stat.label} data={stat.data}/>)}
                    </div>}
                </div>
            </div>
        </div>
    )
}

import {PlayerStat} from "../../../interface-adapter/viewModel/PlayerDetailPage/PlayerDetailViewModel";
import {Link} from "react-router-dom";
import React from "react";
import {PlayerStatCard} from "../home/PlayerStatCard";
import Container from "../common/Layout/Container";
import {theme} from "../theme";
import {PlayerModalExit} from "./PlayerModalExit";

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


const backgroundStyle = {
    position: "absolute",
    height: "100%",
    width: "100%",
    overflow: "hidden",
    top: 0,
    left: 0,
    backgroundColor: theme.color.black,
    display: "flex",
    alignItems: "flex-end",
} as React.CSSProperties;

const containerStyle = {
    background: "#fff",
    display: "grid",
    gridTemplateColumns: "14% 66% 20%",
    margin: "0 5rem",
    position: "relative",
    height: "85vh",
    maxHeight: "1200px",
} as React.CSSProperties

const textContainerStyle = {
    paddingTop: "3rem",
    height: containerStyle.height,
    overflow: "scroll",
}

const firstNameStyle = {
    fontSize: theme.fontSizes["2xl"],
    color: theme.color.white,
    WebkitTextStroke: "3px " + theme.color.primary,
};
const lastNameStyle = {
    fontSize: theme.fontSizes["3xl"],
    fontWeight: "900",
    color: theme.color.primary,
    marginTop: "10%",
    lineHeight: "4rem"
};

const pictureContainerStyle = {
    position: "relative",
    height: "100%",
} as React.CSSProperties

const pictureStyle = {
    position: "absolute",
    height: "100%",
    right: "-50%",
    filter: `drop-shadow(.5rem .5rem .5rem ${theme.color.black})`,
    WebkitFilter: `drop-shadow(.5rem .5rem .5rem ${theme.color.black})`
} as React.CSSProperties

const descriptionContainer = {
    margin: "5rem 5rem 0 20%",
    display: "grid",
    gridTemplateColumns: "repeat(4, 1fr)",
    gap: "2.4rem"
} as React.CSSProperties

const extraInfoContainerStyle = {
    margin: "3rem",
    display: "flex",
}

const countryInfoStyle = {
    //
}

const countryPictureStyle = {
    width: "100%",
    boxShadow: `0 0 .3rem ${theme.color.black}`,
    marginBottom: "1rem"

}
const countryCodeStyle = {
    color: theme.color.black,
    fontSize: theme.fontSizes.lg,
    fontWeight: "lighter",
    textTransform: "uppercase",
    textAlign: "center",
    letterSpacing: "1.5rem",
    textIndent: "1.5rem"
} as React.CSSProperties

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
        <div style={backgroundStyle}>
            <Container>
                <div style={{textAlign: "center"}}>
                    <div style={{width:"2rem", margin:"1rem auto"}}><Link to={"/"}><PlayerModalExit/></Link></div>
                </div>
                <section style={containerStyle}>
                    <div style={pictureContainerStyle}>
                        <img style={pictureStyle} src={playerPicture} alt={lastName} title={fullName}/>
                    </div>
                    <div className="hide-scrollbar" style={textContainerStyle}>
                        <h1>
                            <span style={firstNameStyle}>{firstName}</span>
                            <br/>
                            <span style={lastNameStyle}>{lastName}</span>
                        </h1>
                        <div style={descriptionContainer}>
                            {stats?.map(stat => stat.data && <div>
                                <PlayerStatCard key={stat.label + stat.data} label={stat.label} data={stat.data}/>
                            </div>)}
                        </div>
                    </div>
                    <div style={extraInfoContainerStyle}>
                        <div style={countryInfoStyle}>
                            <img style={countryPictureStyle} src={countryPicture} title={countryName}
                                alt={countryName}/>
                            <p style={countryCodeStyle}>{countryCode}</p>
                        </div>
                    </div>
                </section>
            </Container>
        </div>
    )
}


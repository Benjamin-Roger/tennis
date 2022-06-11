import {Link} from "react-router-dom";
import React from "react";
import {theme} from "../theme";

const widgetStyle = {
    position: "absolute",
    top: "3rem",
    right: "3rem"
} as React.CSSProperties;

const wrapper = {
    background: theme.color.white,
    borderRadius: "10rem",
    padding: "1rem",
    height: "2rem",
    width: "2rem",
}

const iconStyle = {
    objectFit: "contain",
    width: "100%",
    height: "100%",
} as React.CSSProperties;

export const StatLinkWidget: React.FC = () => {
    return <div style={widgetStyle}>
        <Link to={"/stats"} title={"Statistics"}>
            <div style={wrapper}>
                <img style={iconStyle} src="/assets/images/trophy.svg" alt="Statistics"/>
            </div>
        </Link>
    </div>;
};

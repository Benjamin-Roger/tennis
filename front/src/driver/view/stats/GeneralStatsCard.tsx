import React from "react";
import {theme} from "../theme";

const containerStyles = {
    marginTop:"2rem"
}

const wrapperStyles = {
    padding: "1rem",
    height: "8rem",
}

const iconContainerStyles = {
    textAlign: "center"
} as React.CSSProperties;

const iconStyles = {
    height: "4rem",
    margin: "0 auto"
}

const textContainerStyles = {
    textAlign: "center",
    margin: "1rem auto"
} as React.CSSProperties

const labelStyles = {
    fontSize: theme.fontSizes.sm,
    color: theme.color.black,
    fontWeight: "bold",
    textTransform: "uppercase",
} as React.CSSProperties;

const valueStyles = {
    fontSize: theme.fontSizes.sm,
    color: theme.color.primary,
    fontWeight: "bold",
}

const subtitleStyles = {
    fontSize: theme.fontSizes.sm,
    color: theme.color.black,
    marginTop: "1rem"
}

type GeneralStatCardProps = {
    label: string;
    value: string;
    subtitle?: string;
    icon: string;
}

export const GeneralStatsCard: React.FC<GeneralStatCardProps> = (props) => {
    const {label, value, subtitle, icon} = props;
    return (
        <div style={containerStyles}>
            <div style={wrapperStyles}>
                <div style={iconContainerStyles}>
                    <img alt={label} src={icon} style={iconStyles} />
                </div>
                <div style={textContainerStyles}>
                    <p style={labelStyles}>{label}</p>
                    <p style={valueStyles}>{value}</p>
                    {subtitle && <p style={subtitleStyles}>{subtitle}</p>}
                </div>
            </div>
        </div>
    )
}

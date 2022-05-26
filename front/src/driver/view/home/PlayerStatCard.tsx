import React from 'react';
import {theme} from '../theme';

const labelStyles = {
    fontSize: theme.fontSizes.sm,
    color: theme.color.black,
    fontWeight: 'bold',
    textTransform: 'uppercase',
    marginBottom: '.2rem',
} as React.CSSProperties;

const dataStyles = {
    fontSize: theme.fontSizes.sm,
    color: theme.color.secondary,
    fontWeight: 'bold',
}

interface PlayerStatCardProps {
    label: string;
    data: string | number | undefined;
}

export const PlayerStatCard:React.FC<PlayerStatCardProps> = (props) => {
    const {label, data} = props;
    return (<div>
        <p style={labelStyles}>{label}</p>
        <p style={dataStyles}>{data}</p>
    </div>);
}

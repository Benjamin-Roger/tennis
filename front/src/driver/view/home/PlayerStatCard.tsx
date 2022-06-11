import React from "react";

interface PlayerStatCardProps {
    label: string;
    data: string | number | undefined;
}

export const PlayerStatCard:React.FC<PlayerStatCardProps> = (props) => {
    const {label, data} = props;
    return (<div>
        <p className="text-sm text-black font-bold uppercase">{label}</p>
        <p className="text-sm text-primary font-bold">{data}</p>
    </div>);
}

import {PlayerStat} from "../../../interface-adapter/viewModel/HomePage/HomeViewModel";
import {PlayerStatCard} from "./PlayerStatCard";
import React from "react";


type PlayerSummaryCardProps = {
    name: string;
    picture?: string;
    stats?: PlayerStat[];
}

export const PlayerSummaryCard: React.FC<PlayerSummaryCardProps> = (props) => {
    const {name, picture, stats} = props;
    return (
        <div className="shadow-custom rounded-sm bg-white overflow-hidden my-6">
            <div className="flex items-center h-28">
                <div className="flex-1 h-full">
                    {picture && <img className="min-h-full max-w-full object-cover" title={name} alt={name} src={picture}/>}
                </div>
                <div style={{flex:2}}>
                    <h2 className="text-primary text-lg mb-4">{name}</h2>
                    {stats && <div className="flex gap-4">
                        {stats.map((stat) =>stat.data && <PlayerStatCard key={stat.label} label={stat.label} data={stat.data}/>)}
                    </div>}
                </div>
            </div>
        </div>
    )
}

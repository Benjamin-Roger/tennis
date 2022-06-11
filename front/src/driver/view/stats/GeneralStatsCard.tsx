import React from "react";


type GeneralStatCardProps = {
    label: string;
    value: string;
    subtitle?: string;
    icon: string;
}

export const GeneralStatsCard: React.FC<GeneralStatCardProps> = (props) => {
    const {label, value, subtitle, icon} = props;
    return (
        <div className="mt-12">
            <div className="min-h-24 p-4">
                <div className="text-center">
                    <img alt={label} src={icon} className="h-16 w-16 mx-auto" />
                </div>
                <div className="mt-6 mx-auto text-center">
                    <p className="text-sm text-black uppercase">{label}</p>
                    <p className="text-sm text-primary font-bold mb-4">{value}</p>
                    {subtitle && <p className="text-sm text-black">{subtitle}</p>}
                </div>
            </div>
        </div>
    )
}

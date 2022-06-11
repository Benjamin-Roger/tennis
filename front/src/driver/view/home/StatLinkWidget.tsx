import {Link} from "react-router-dom";
import React from "react";


export const StatLinkWidget: React.FC = () => {
    return <div className="my-12 md:absolute md:my-0 top-0 right-0 flex justify-end">
        <Link to={"/stats"} title={"Statistics"}>
            <div className="bg-white rounded-full p-4 h-16 w-16">
                <img className="object-contain" src="/assets/images/trophy.svg" alt="Statistics"/>
            </div>
        </Link>
    </div>;
};

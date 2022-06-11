import React from "react";


const containerStyles = {
    maxWidth: "1600px",
}

type LayoutProps = {
    children: React.ReactNode,
}

const Container: React.FC<LayoutProps> = ({children}) => (
    <div className="my-0 mx-4 md:mx-12 lg:mx-24 w-auto">
        <div style={containerStyles} className="w-full my-0 mx-auto">
            {children}
        </div>
    </div>);

export default Container;

import React from "react";

const layoutStyles = {
    margin: "0 3rem",
    flex: 1,
}

const containerStyles = {
    margin: "0 auto",
    maxWidth: "1600px",
}

type LayoutProps = {
    children: React.ReactNode,
    style?: React.CSSProperties
}

const Layout: React.FC<LayoutProps> = ({children, style}) => (
    <div style={{...layoutStyles, ...style}}>
        <div style={containerStyles}>
            {children}
        </div>
    </div>);

export default Layout;

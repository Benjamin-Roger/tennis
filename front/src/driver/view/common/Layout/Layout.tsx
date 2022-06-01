import {Outlet} from "react-router-dom";
import React from "react";

const layoutStyles = {
    margin: "3rem",
    flex: 1,
}

const containerStyles = {
    margin: "0 auto",
    maxWidth: "1600px",
}

const Layout: React.FC = () => (
    <div style={layoutStyles}>
        <div style={containerStyles}>
            <main>
                <Outlet/>
            </main>
        </div>
    </div>);

export default Layout;

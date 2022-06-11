import {Outlet} from "react-router-dom";
import React from "react";

const Layout: React.FC = () => (
    <main>
        <Outlet/>
    </main>
);

export default Layout;

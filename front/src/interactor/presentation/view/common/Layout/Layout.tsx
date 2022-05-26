import {Outlet} from "react-router-dom";
import React from "react";

const Layout: React.FC = () => <div>
    <main>
        <Outlet/>
    </main>
</div>;

export default Layout;

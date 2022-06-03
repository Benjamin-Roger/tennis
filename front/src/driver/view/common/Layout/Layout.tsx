import {Outlet} from "react-router-dom";
import React from "react";
import Container from "./Container";

const Layout: React.FC = () => (
    <Container>
        <main>
            <Outlet/>
        </main>
    </Container>
);

export default Layout;

import {BrowserRouter, Route, Routes} from "react-router-dom";
import HomePage from "../view/pages/HomePage";
import Page404 from "../view/pages/Page404";
import Layout from "../view/common/Layout/Layout";
import React from "react";
import PlayerPage from "../view/pages/PlayerPage";
import GeneralStatsPage from "../view/pages/GeneralStatsPage";

const BrowserRouterComponent: React.FC = () => (
    <BrowserRouter>
        <Routes>
            <Route path="/" element={<Layout/>}>
                <Route index element={<HomePage/>}/>
                <Route path="player/:id" element={<PlayerPage/>}/>
                <Route path="stats" element={<GeneralStatsPage/>}/>
                <Route path="*" element={<Page404/>}/>
            </Route>
        </Routes>
    </BrowserRouter>
)
export default BrowserRouterComponent;

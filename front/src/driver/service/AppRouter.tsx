import {BrowserRouter, Route, Routes} from "react-router-dom";
import HomePage from "../view/pages/HomePage";
import DetailPage from "../view/pages/DetailPage";
import Page404 from "../view/pages/Page404";
import Layout from "../view/common/Layout/Layout";
import React from "react";

const BrowserRouterComponent: React.FC = () => (
    <BrowserRouter>
        <Routes>
            <Route path="/" element={<Layout/>}>
                <Route index element={<HomePage/>}/>
                <Route path="detail/:id" element={<DetailPage/>}/>
                <Route path="*" element={<Page404/>}/>
            </Route>
        </Routes>
    </BrowserRouter>
)
export default BrowserRouterComponent;

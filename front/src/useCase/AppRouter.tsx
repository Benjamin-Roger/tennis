
import {BrowserRouter, Route, Routes} from 'react-router-dom';
import React from 'react';
import Layout from '../interactor/presentation/view/common/Layout/Layout';
import HomePage from '../interactor/presentation/presenters/pages/HomePage';
import DetailPage from '../interactor/presentation/presenters/pages/DetailPage';
import Page404 from '../interactor/presentation/presenters/pages/Page404';

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

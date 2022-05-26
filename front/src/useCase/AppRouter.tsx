import {BrowserRouter, Route, Routes} from "react-router-dom";
import HomePage from "../interactor/presentation/presenters/pages/HomePage";
import DetailPage from "../interactor/presentation/presenters/pages/DetailPage";
import Page404 from "../interactor/presentation/presenters/pages/Page404";
import Layout from "../interactor/presentation/view/common/Layout/Layout";


export default function AppRouter() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Layout />}>
                    <Route index element={<HomePage />} />
                    <Route path="detail/:id" element={<DetailPage />} />
                    <Route path="*" element={<Page404 />} />
                </Route>
            </Routes>
        </BrowserRouter>
    );
}

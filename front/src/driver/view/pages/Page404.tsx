import React from "react";
import {Link} from "react-router-dom";
import {Modal} from "../common/Modal/Modal";

const Page404: React.FC = () => <>
    <Modal exit={false}>
        <div className="text-center py-12">
            <h1 className="text-6xl mb-12">Page not found!</h1>
            <Link className="text-primary font-bold" to={"/"}>Return to homepage</Link>
        </div>
    </Modal>
</>;

export default Page404;

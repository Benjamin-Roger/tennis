import {theme} from "../../theme";
import React from "react";
import Container from "../Layout/Container";
import {ModalExit} from "./ModalExit";
import {Link} from "react-router-dom";

type ModalProps = {
    children: React.ReactNode,
    alignment?: string,
    exit?: boolean
}

const backgroundStyle = {
    zIndex: -1,
    backgroundColor: theme.color.black,
} as React.CSSProperties;

export const Modal: React.FC<ModalProps> = ({children, alignment = "items-center", exit = true}) => (
    <div className="relative">
        <div style={backgroundStyle} className="h-full w-full fixed"></div>
        <Container>
            <div className={`flex min-h-screen w-full ${alignment}`}>
                <div className="w-full">
                    {exit && <div className="text-center">
                        <div className="w-10 h-10 my-6 mx-auto"><Link to={"/"}><ModalExit/></Link></div>
                    </div>}
                    <section className="bg-white">
                        {children}
                    </section>
                </div>
            </div>
        </Container>
    </div>
);


import {theme} from "../../theme";
import React from "react";
import Container from "../Layout/Container";
import {ModalExit} from "./ModalExit";
import {Link} from "react-router-dom";

type ModalProps = {
    children: React.ReactNode,
}

const backgroundStyle = {
    position: "absolute",
    height: "100%",
    width: "100%",
    overflow: "hidden",
    top: 0,
    left: 0,
    backgroundColor: theme.color.black,
    display: "flex",
    alignItems: "flex-end",
} as React.CSSProperties;

const containerStyle = {
    background: "#fff",
    margin: "0 5%",
    height: "85vh",
    maxHeight: "1200px",
} as React.CSSProperties

export const Modal: React.FC<ModalProps> = ({
    children
}) => {

    return (
        <div style={backgroundStyle}>
            <Container>
                <div style={{textAlign: "center"}}>
                    <div style={{width: "2rem", margin: "1rem auto"}}><Link to={"/"}><ModalExit/></Link></div>
                </div>
                <section style={containerStyle}>
                    {children}
                </section>
            </Container>
        </div>
    )
}


import React, {SetStateAction} from "react";
import {theme} from "../theme";

type SearchFilterInputProps = {
    value:string;
    onChange:(e: { target: { value: SetStateAction<string>; }; }) => void;
    placeholder:string;
}

const inputStyles = {
    width: "calc(100% - 1rem)",
    background: theme.color.black,
    color: theme.color.secondary,
    padding: "1rem 0.5rem",
    marginBottom: "3rem",
    borderRadius: ".5rem",
    fontWeight: "bold",
    border: "none",
    boxShadow: "none"

}

export const SearchInputFilter:React.FC<SearchFilterInputProps> = ({value, onChange, placeholder}) => (
    <input
        style={inputStyles}
        value={value}
        onChange={onChange}
        placeholder={placeholder}
    />
)

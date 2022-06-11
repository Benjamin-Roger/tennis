import React, {SetStateAction} from "react";
import "./search-input.css";

type SearchFilterInputProps = {
    value:string;
    onChange:(e: { target: { value: SetStateAction<string>; }; }) => void;
    placeholder:string;
}

export const SearchInputFilter:React.FC<SearchFilterInputProps> = ({value, onChange, placeholder}) => (
    <input
        className="search-input w-full py-4 px-6 mb-12 rounded-md font-bold bg-black text-white"
        value={value}
        onChange={onChange}
        placeholder={placeholder}
    />
)

import {useParams} from 'react-router-dom';
import React from 'react';

const DetailPage:React.FC = () => {
    const {id} = useParams();
    return (<div>{id}</div>)
};

export default DetailPage;

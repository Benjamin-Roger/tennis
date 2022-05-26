import {useParams} from "react-router-dom";

const DetailPage = () => {
    const {id} = useParams();
    return (<div>{id}</div>)
};

export default DetailPage;

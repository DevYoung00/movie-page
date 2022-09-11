import "./css/Header.css";
import {Link} from 'react-router-dom';

export default function Header() {


    return(
        <div className="header">
            <Link to="/" style={{ textDecoration: 'none' }}>  <h1
            >Movie Page</h1></Link>
            <div id="gnb">
            <Link to="/myList"> <button type="button" class="btn btn-primary">영화 리스트 보기</button></Link>
            <Link to="/"> <button type="button" class="btn btn-primary">영화 검색하기</button></Link>
           
            </div>
            <hr></hr>
        </div>
    )
}
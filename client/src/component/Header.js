import "./css/Header.css";
import axios from 'axios';
import {Link ,useNavigate,useLocation} from 'react-router-dom';
import React, { useState,useEffect } from "react";
import { useCookies } from 'react-cookie'; // useCookies import


export default function Header() {
    const navigate = useNavigate();
const location = useLocation();
const [cookies, setCookie, removeCookie] = useCookies(['token'])
const [auth, setAuth] = useState(false)
const goReset = () => {

    if(location.pathname === "/"){
        window.location.reload();
    }
    else {
        navigate("/")
    }
    
}
    return(
        <div className="header">
            <div className="header_logo">
            <Link to="/" style={{ textDecoration: 'none' }}>
                <img onClick={goReset} 
                src="about_movie_logo.png"/>
            </Link>
            </div>
            <div id="gnb">
            <Link to="/"> <button type="button" class="btn btn-danger">영화 검색</button> </Link>
            <Link to="/join"> <button type="button" class="btn btn-danger">회원가입</button></Link> 
            <Link to="/login"> <button type="button" class="btn btn-danger">로그인</button></Link> 
 
            </div>
           
        </div>
    )
}
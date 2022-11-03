import "./css/Header.css";
import axios from 'axios';
import {Link ,useNavigate,useLocation} from 'react-router-dom';
import React, { useState,useEffect } from "react";
import { useCookies } from 'react-cookie'; // useCookies import

export default function HeaderUser() {
    const [cookies, setCookie, removeCookie] = useCookies(['token'])
    const navigate = useNavigate();
    const location = useLocation();
    const goReset = () => {

        if(location.pathname === "/"){
            window.location.reload();
        }
        else {
            navigate("/")
        }
    }

    const logout = () => {
        removeCookie('token');
        navigate("/")

        window.location.reload();
  
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
    <Link to="/myList"> <button type="button" class="btn btn-danger">내 영화 리스트 보기</button></Link> 
    <Link to="/"> <button onClick={logout}
    type="button" class="btn btn-danger">로그아웃</button></Link> 
    </div>
</div>
)

}
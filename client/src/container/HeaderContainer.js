import React, { useState,useEffect } from "react";
import axios from 'axios';
import { useCookies } from 'react-cookie'; // useCookies import

import Header from "../component/Header"
import HeaderUser from "../component/HeaderUser"
import Join from './Join';

export default function HeaderContainer() {
    const [cookies, setCookie, removeCookie] = useCookies(['token'])
    const [auth, setAuth] = useState(false)

    const isAuth = () => {
        axios.get('http://localhost:9000/api/user/isAuth',
        {params: {token: cookies.token}}, 
        { withCredentials: true },
        )  .then((res) => {
            setAuth(true)
            console.log(auth)
          })
          .catch((error) => {
            setAuth(false)
       
          })
    }
    useEffect(() => {
        isAuth()
      }, [] ,
    )
    if(auth) {
       return (  <HeaderUser/> )
    }
    else {
        return ( <Header/>)
    }
   

}
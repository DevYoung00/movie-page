import axios from 'axios';
import "./css/login.css"
import React, { useState,useEffect } from "react";
import {Link ,useNavigate,useLocation} from 'react-router-dom';
import { useCookies } from 'react-cookie'; // useCookies import

export default function Login() {
    const navigate = useNavigate();
    const [email, setEmail] = useState("")
    const [password, setPassword] = useState("")
    const [cookies, setCookie, removeCookie] = useCookies(['token'])
    const loginButton = () => {
        axios.post('http://localhost:9000/api/user/login', {
        email : email,
        password : password,
        withCredentials: true,
    }).then((res) => {
        if(res.data.code === 2003) {
            window.alert("비밀번호가 틀렸습니다.")
        }
        else if(res.data.code === 2002) {
            window.alert("이메일에 해당하는 유저가 없습니다.")
        }
        else{
            setCookie('token',res.data.result);
            window.alert("로그인 완료")
            navigate("/")
            window.location.reload();
        }
      
    })
    .catch((error)=> {
        window.alert(error)
    }
    )
    }

return (
    <div className="login">
        <img src="login.png"/>
        <div className="loginBox">
        <div class="emailForm">
        <div class="form-group">
            <div className="form-input">
      <input type="email" 
       value={email}
       onChange={e => setEmail(e.target.value)} 
       
      class="form-control" id="inputEmail3" placeholder="이메일을 입력하세요."></input>
      </div>

  </div>
  
</div>
<div className="pwdForm">
<div class="form-group">
            <div className="form-input">
      <input type="password" 
       value={password}
       onChange={e => setPassword(e.target.value)} 
      class="form-control"  placeholder="비밀번호를 입력하세요."></input>
      </div>
</div>
        </div>
        <div className="submitButton">
            <button type="button" class="btn btn-danger"
      onClick={e => loginButton()}
            >로그인</button> 
        </div> 

        </div>
    </div>
)
}

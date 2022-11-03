import axios from 'axios';
import React, { useState,useEffect } from "react";
import {useNavigate,useLocation} from 'react-router-dom';
import './css/Join.css';

export default function Join() {
  const [name, setName] = useState("")
  const [email, setEmail] = useState("")
  const [password, setPassword] = useState("")
  const [exist, setExist] = useState("")
  const history = useNavigate();
  const location = useLocation();
    const onEnterEmail = (e) => {
        if (e.key === "Enter") {
          //키를 눌렀을 때 동작할 코드
          isExist()
        }
      }

    const isExist = () => {

        axios.get('http://localhost:9000/api/user/isExist',
        {params: {email: email}}, 
    { withCredentials: true },
    ).then((res) => {
     if(res.data === 1){
      setExist(res.data)
        window.alert("이미 존재하는 이메일입니다.")

     }
     else {
      setExist(res.data)
      window.alert("사용가능한 이메일입니다.")
     }
    })
    }
    const joinButton = () => {
        axios.post('http://localhost:9000/api/user/join',{
        email : email,
        name : name,
        password : password,
        withCredentials: true,
    }).then((res) => {
  
      window.alert("회원가입 완료")
      window.location.reload();
      
    })
    .catch(error => {
      window.alert("에러발생, 이메일 중복체크를 재시도 해보세요")
    });
    }

    return (
    
    <div className="join">
        <img src='join_img.png'/>
        <div className ="joinBox">
            <div className="nameForm">
            <div class="form-group">
            <div className="form-input">
            <input type="text"
              value={name}
              onChange={e => setName(e.target.value)} 
            class="form-control"  placeholder="이름을 입력하세요."></input>
            </div>
            </div>
            </div>
        <div class="emailForm">
        <div class="form-group">
            <div className="form-input">
      <input type="email" 
       value={email}
       onChange={e => setEmail(e.target.value)} 
       onKeyPress={onEnterEmail}
      class="form-control" id="inputEmail3" placeholder="이메일을 입력하세요. (엔터 시 중복 체크)"></input>
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
            onClick={e => joinButton()}
            >회원가입</button> 
        </div> 
</div>
    </div>

    
    )
}
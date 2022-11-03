import { BrowserRouter, Routes, Route} from 'react-router-dom';


import React, {useEffect } from "react";
import 'antd/dist/antd.css'
import './App.css';
import Search from './container/Search';
import MyMovieList from './container/MyMovieList';
import Join from './container/Join';
import Login from './container/Login'
import HeaderContainer from "./container/HeaderContainer"

let currentPath = "";

function App() {


  return (
    <div className='App'>
    <BrowserRouter>
      <HeaderContainer />
      <Routes>
        <Route path="/" element={<Search />}></Route>
        <Route path="/myList" element={<MyMovieList />}></Route>
        <Route path= "/join" element={<Join />}></Route>
        <Route path= "/login" element={<Login />}></Route>
      </Routes>
    </BrowserRouter>
  </div>
  );
}

export default App;

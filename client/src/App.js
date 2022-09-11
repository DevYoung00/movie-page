import { BrowserRouter, Routes, Route} from 'react-router-dom';


import React, {useEffect } from "react";
import 'antd/dist/antd.css'
import './App.css';
import Header from './component/Header';
import Search from './container/Search';
import MyMovieList from './container/MyMovieList';

let currentPath = "";

function App() {


  return (
    <div className='App'>
    <BrowserRouter>
      <Header />
      <Routes>
        <Route path="/" element={<Search />}></Route>
        <Route path="/myList" element={<MyMovieList />}></Route>
      </Routes>
    </BrowserRouter>
  </div>
  );
}

export default App;

import axios from 'axios';
import React, { useState,useEffect } from "react";
import "./css/MyMovieList.css"
import MovieListCard  from '../component/MovieListCard';
import { useCookies } from 'react-cookie'; // useCookies import

export default function MyMovieList() {
    const [movieList, setMovieList] = useState([]) //영화 리스트
    const [cookies, setCookie, removeCookie] = useCookies(['token'])
    const getMovieList = () => { // 비동기로 json 정보를 가져온다.
      const headers = {
        'Content-Type' : 'application/json',
        'Authorization' : cookies.token ,
    }
        axios.get('http://localhost:9000/api/movies/get',{headers:headers})
          .then((res) => {
            setMovieList(res.data)
            console.log(res.data)
          })
      }
    
      useEffect(() => {
        getMovieList()
      }, [] ,
    )
    return (
        <div className="myMovieList">
<img src="movie_list.png"/>
            <div className="movieList">
      
        {movieList.map((item) => {
          return (
        <MovieListCard item={item}></MovieListCard>
          )
        })}
    </div>
        </div>
    )

}
import axios from 'axios';
import React, { useState,useEffect } from "react";
import "./css/MyMovieList.css"
import MovieListCard  from '../component/MovieListCard';


export default function MyMovieList() {
    const [movieList, setMovieList] = useState([]) //영화 리스트

    const getMovieList = () => { // 비동기로 json 정보를 가져온다.
        axios.get('http://localhost:9000/api/movies/get')
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
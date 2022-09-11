import "./css/search.css";
import axios from 'axios';
import React, { useState,useEffect } from "react";
import MovieCard from "../component/MovieCard";

export default function Search() {
    const [movies, setMovies] = useState([]) //영화 리스트
    const [search, setSearch] = useState("")
    //const [isLoading, setLoading] = useState(true)

  const getMovies = () => { // 비동기로 json 정보를 가져온다.
    axios.get('http://localhost:9000/api/movies/search',
    {params: {query: search}}, 
    { withCredentials: true },
    )
      .then((res) => {
        setMovies(res.data.items)
        console.log(movies)
        //setLoading(false)
  
      })
  }

  useEffect(() => {
    getMovies()
  }, [] ,
)


    return(
        <div className="search">
            <div className="searchBox">
        <input type="text" class="form-control" placeholder="영화를 검색해보세요" 
                   value={search}
                   onChange={e => setSearch(e.target.value)} 
                  ></input>
    <button type="button" class="btn btn-primary" 
    onClick={e => getMovies()} >영화 검색</button>
    </div>
    <div className="movieList">
        {movies.map((item) => {
          return (
        <MovieCard item={item} ></MovieCard>
          )
        })}
    </div>
        </div>
    )
}
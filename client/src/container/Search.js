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
  const onEnterSearch = (e) => {
    if (e.key === "Enter") {
      //키를 눌렀을 때 동작할 코드
      getMovies()
    }
  };

  useEffect(() => {
    getMovies()
  }, [] ,
)
    return(
        <div className="search">
          <img src="search_img.png"/>
            <div className="searchBox">
        <input type="text" class="form-control" placeholder="영화를 검색해보세요 (검색 후 엔터)" 
                   value={search}
                   onChange={e => setSearch(e.target.value)} 
                   onKeyPress={onEnterSearch}
                  ></input>
    </div>
    <p>검색 후 이미지 클릭 시 리뷰가 나옵니다.</p>
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
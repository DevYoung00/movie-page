import "./css/movieCard.css"
import axios from 'axios';
import React, { useState,useEffect } from "react";
import { useCookies } from 'react-cookie'; // useCookies import
import SearchMovieReview from "./SearchMovieReview"


export default function MovieCard({ item }) {
    const title = item.title.replace(/<[^>]*>?/g, '');
    const [isInsert, setIsInsert] = useState(false);
    const [myList, setMyList] = useState([])
    const [cookies, setCookie, removeCookie] = useCookies(['token'])
    const [auth, setAuth] = useState(false)
    const [open, setOpen] = useState(false)

    const handleOpen = () => {
      setOpen(true); }

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
    const insertMovie = () => {
      const headers = {
        'Content-Type' : 'application/json',
        'Authorization' : cookies.token ,
    }
        axios.post('http://localhost:9000/api/movies/insert',{
        title : title,
        img : item.image,
        withCredentials: true,
        },{headers:headers}
        )
      .then((res) => {
      
        setMyList(res)
        setIsInsert(true)
        console.log(myList)
        alert("추가 되었습니다.")
      })
      .catch(function(error) {
        if(error.response){
          alert("이미 추가된 영화입니다.")
        }
      })

  }

    return (
        <div className="movieCard">
          <div className="movieCard_container">
            <div className="movie-image">
            <img src={item.image}  onClick={handleOpen}/>
            {open && <SearchMovieReview item={item} setOpen={setOpen}/>}
       
          </div>
          <div className="movie-text">
            <h5>{title}</h5>
          </div>
          <div className="insertButton">
                <button type="button" class="btn btn-default" 
                 style = { auth  ? { visibility: "unset" } : { visibility: "hidden"} }
                    onClick={e => insertMovie() }>추가</button>
          </div>
          </div>
        </div>
      );
}
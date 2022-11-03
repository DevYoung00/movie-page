import "./css/movieListCard.css"
import axios from 'axios';
import React, { useState } from "react";
import MovieImgModal from "./MovieImgModal";
import { useCookies } from 'react-cookie'; // useCookies import
export default function MovieListCard({ item }) {

    //const [modalOpen, setModalOpen] = useState(false);
    const [cookies, setCookie, removeCookie] = useCookies(['token'])
    const [auth, setAuth] = useState(false)
    const [show, setShow] = useState(false);
    const handleShow = () => 
    {
      setShow(true); 
    }
    const deleteMovie = () => { 
      
      const headers = {
        'Content-Type' : 'application/json',
        'Authorization' : cookies.token ,
    }
  
      axios.delete('http://localhost:9000/api/movies/delete',
      {
        params: {title: item.title} ,headers:headers}
      )
        .then((res) => {
          window.location.replace("/myList")
          alert("삭제 되었습니다.");
        })
        .catch((error) => {
          
        })
    }
    return (
        <div className="movieListCard">
          <div className="movieListCard_container">
              <div className="movie-image">
      
            <img src={item.img} onClick={handleShow} />
            {show && <MovieImgModal item={item} setShow={setShow}/>}
          </div>
          <div className="movie-text">
            <h5>{item.title}</h5>
          </div>
          <div className="deleteButton">
          <button type="button" class="btn btn-default" 
    onClick={e =>  deleteMovie()} >삭제</button>
            </div>    
            </div>
        </div>
      );
}
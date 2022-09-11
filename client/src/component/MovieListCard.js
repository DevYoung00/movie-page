import "./css/movieListCard.css"
import axios from 'axios';
import React, { useState } from "react";
import MovieImgModal from "./MovieImgModal";

export default function MovieListCard({ item }) {

    //const [modalOpen, setModalOpen] = useState(false);

    const [show, setShow] = useState(false);
    const handleShow = () => 
    {
      setShow(true);
    }
    const deleteMovie = () => { // 비동기로 json 정보를 가져온다.
      axios.delete('http://localhost:9000/api/movies/delete',
      {params: {title: item.title}}, 

      )
        .then((res) => {
          window.location.replace("/myList")
          alert("삭제 되었습니다.");

    
        })
    }

  
    return (
        <div className="movieListCard">
              <div className="movie-image">
            <img src={item.img} onClick={handleShow} />
            {show && <MovieImgModal item={item} setShow={setShow}/>}
          </div>
          <div className="movie-text">
            <h5>{item.title}</h5>
          </div>
          <div clssName="deleteButton">
          <button type="button" class="btn btn-primary" 
    onClick={e =>  deleteMovie()} >삭제</button>
            </div>    
        </div>
      );
}
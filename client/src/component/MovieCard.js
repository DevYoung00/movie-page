import "./css/movieCard.css"
import axios from 'axios';
import React, { useState } from "react";

export default function MovieCard({ item }) {
    const title = item.title.replace(/<[^>]*>?/g, '');
    const [isInsert, setIsInsert] = useState(false);
    const [myList, setMyList] = useState([])

    const insertMovie = () => {
        axios.post('http://localhost:9000/api/movies/insert',{
        title : title,
        img : item.image,
        withCredentials: true,
        }
        )
      .then((res) => {
        //console.log(res)
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
          <div className="movie-image">
            <img src={item.image} />
          </div>
          <div className="movie-text">
            <h5>{title}</h5>
          </div>
          <div className="insertButton">
                <button type="button" class="btn btn-default" 
                 style = { isInsert  ? { visibility: "hidden" } : { visibility: "unset"} }
                    onClick={e => insertMovie() }>추가</button>
          </div>
        </div>
      );
}
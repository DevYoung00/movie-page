import "./css/movieListCard.css"
import axios from 'axios';
import React, { useState } from "react";


export default function MovieListCard({ item }) {


    //const [movie, setMovie] = useState([])

    /*
    const insertStar = () => {
        axios.post('http://localhost:9000/api/movies/star',{
        title : title,
        co: coment,
        withCredentials: true,
        }
        )
      .then((res) => {
        //console.log(res)
        setMyList(res)
        console.log(myList)
        alert("추가 되었습니다.")
      })

  }*/
  
    return (
        <div className="movieListCard">
              <div className="movie-image">
            <img src={item.img} />
          </div>
          <div className="movie-text">
            <h5>{item.title}</h5>
          </div>
       
        </div>
      );
}
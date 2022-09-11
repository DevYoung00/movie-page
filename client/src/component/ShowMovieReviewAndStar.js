import React, { useState,useEffect }from 'react';
import axios from 'axios';

export default function ShowMovieReviewAndStar({  item }) {
    //const handleAlwaysExist = () => {
        //setIsExist(true);
      //}


    const [movieContent , setMovieContent] = useState([]);

    const getMovieContent = () => { // 비동기로 json 정보를 가져온다.
        axios.get('http://localhost:9000/api/movies/getMovieEntity',
        {params: {title: item.title}}, 
        { withCredentials: true },
        )
          .then((res) => {
            setMovieContent(res.data)
            console.log(movieContent)
            //setLoading(false)
      
          })
      }
      useEffect(() => {
        getMovieContent()
      }, [] ,
     // handleAlwaysExist()
    )
   return (
    <div className='showMovieReviewAndStar'>
            <div className="star">
          <h5>별점 : {movieContent.star}</h5>
          </div>
          <div className="review">
          <h5>리뷰 : {movieContent.review}</h5>
          </div>
    </div>
   )
 
    
}
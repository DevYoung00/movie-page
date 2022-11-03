import React, { useState,useEffect }from 'react';
import axios from 'axios';
import { useCookies } from 'react-cookie'; // useCookies import


export default function ShowMovieReviewAndStar({ item}) {
      const [cookies, setCookie, removeCookie] = useCookies(['token'])

    const [movieContent , setMovieContent] = useState([]);

    const getMovieContent = () => { // 비동기로 json 정보를 가져온다.
      const headers = {
        'Content-Type' : 'application/json',
        'Authorization' : cookies.token ,
    }
    
        axios.get('http://localhost:9000/api/movies/getMovieEntity',
        {
          params: {title: item.title} ,headers:headers}
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
          <h7>별점 : {movieContent.star} / 5 점</h7>
          </div>
          <div className="review">
          <h7>리뷰 : {movieContent.review}</h7>
          </div>
          <div className="updateButton">
        
                     </div>
    </div>
   )
 
    
}
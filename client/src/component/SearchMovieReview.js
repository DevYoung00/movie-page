import axios from 'axios';
import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';
import React, { useState,useEffect }from 'react';
import "./css/searchMovieReview.css"
export default function SearchMovieReview({setOpen,item})  {
    const title = item.title.replace(/<[^>]*>?/g, '');
    const [review, setReview] = useState([]) //영화 리스트
    const [reviewExist, setReviewExist] = useState(false) //영화 리스트
    const handleClose = () => {
        setOpen(false);
    }
    const getReview = () => {
        axios.get('http://localhost:9000/api/movies/getReview',
        {params: {title: title}}, 
        { withCredentials: true },
        )  .then((res) => {
            setReview(res.data)
            if(res.data.length === 0) {
                setReviewExist(false)
            }
            else setReviewExist(true)
            console.log(res)
          })
          .catch((error) => {
            window.alert(error)
       
          })
    }
    useEffect(() => {
        getReview()
      }, [] ,
    )

    return (
        <div className="searchMovieReview">
      <Modal 
        show={setOpen}
        onHide={handleClose}
        
        keyboard={false} >
        <Modal.Header closeButton>
          <Modal.Title>{title}</Modal.Title>
        </Modal.Header>
        <Modal.Body>
        {reviewExist && review.map((item) => {
          return (
           <p>{item.review} ( {item.star} / 5 ) - {item.name}</p>
          )
        })}
        <p style={ reviewExist ? { visibility:'hidden'} : {visibility:'none'} }
        >리뷰가 없습니다.</p>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="defalut" onClick={handleClose}>
            닫기
          </Button>
        </Modal.Footer>
      </Modal>

        </div>
    );
    
    
}
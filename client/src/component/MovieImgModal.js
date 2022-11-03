import React, { useState,useEffect }from 'react';
import axios from 'axios';
import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';
import MovieReviewAndStar from './MovieReviewAndStar';
import ShowMovieReviewAndStar from './ShowMovieReviewAndStar';
import { useCookies } from 'react-cookie'; // useCookies import
import "./css/movieImgModal.css"

export default function MovieImgModal({ setShow, item }) {
  const [cookies, setCookie, removeCookie] = useCookies(['token'])
  const handleClose = () => {
    setShow(false);
  }
  const [isExist, setIsExist] = useState(false)
  const [notPatch, setNotPatch] = useState(true)

  const getIsExist = () => { 
    const headers = {
      'Content-Type' : 'application/json',
      'Authorization' : cookies.token ,
  }
    axios.get('http://localhost:9000/api/movies/isExist',{
      params: {title: item.title} ,headers:headers}
    )
      .then((res) => {
        if(res.data === true){
          setIsExist(res.data)
          console.log(isExist)
        }
     
      })
      .catch((error) => {
      
      })
  }
  
  const handlePatch = () => {
    setNotPatch(false)
  }


  useEffect(() => {
    getIsExist()
  }, []);

  return (

    <div className="movieImgModal">
      <Modal
        show={setShow}
        onHide={handleClose}
        backdrop="static"
        keyboard={false}
      >
        <Modal.Header closeButton>
          <Modal.Title><h3>{item.title}</h3></Modal.Title>
        </Modal.Header>
        <Modal.Body>
        { isExist&&notPatch ? ( <ShowMovieReviewAndStar item={item}/> ) : ( < MovieReviewAndStar isExist={isExist} setNotPatch={setNotPatch} item={item} setIsExist={setIsExist}/> ) }
      </Modal.Body>
        <Modal.Footer>
          <Button variant="defalut" onClick={handleClose}>
            닫기
          </Button>
          <Button variant="defalut" onClick={handlePatch}>
            수정
          </Button>
        </Modal.Footer>
      </Modal>
      </div>
    
  );
  
}
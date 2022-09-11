import React, { useState,useEffect }from 'react';
import axios from 'axios';
import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';
import MovieReviewAndStar from './MovieReviewAndStar';
import ShowMovieReviewAndStar from './ShowMovieReviewAndStar';
import "./css/movieImgModal.css"

export default function MovieImgModal({ setShow, item }) {
  const handleClose = () => {
    setShow(false);
  }
  const [isExist, setIsExist] = useState(false)

    //const handleIsExist = () => 
    //{
      //setIsExist(true);
    //}
 

  const getIsExist = () => { 
    axios.get('http://localhost:9000/api/movies/isExist',
    {params: {title: item.title}}, 
    { withCredentials: true },
    )
      .then((res) => {
        setIsExist(res.data)
        console.log(isExist)
      })
  }


useEffect(() => {
  getIsExist()
});

  return (

    <div className="movieImgModal">
      <Modal
        show={setShow}
        onHide={handleClose}
        backdrop="static"
        keyboard={false}
      >
        <Modal.Header closeButton>
          <Modal.Title>{item.title}</Modal.Title>
        </Modal.Header>
        <Modal.Body>
        { isExist ? ( <ShowMovieReviewAndStar item={item} /> ) : ( < MovieReviewAndStar   item={item} setIsExist={setIsExist}/> ) }

        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose}>
            닫기
          </Button>
        </Modal.Footer>
      </Modal>
      </div>
    
  );
  
}
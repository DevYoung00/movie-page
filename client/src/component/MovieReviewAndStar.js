import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import React, { useState,useEffect }from 'react';
import axios from 'axios';


export default function MovieReviewAndStar({ setIsExist, item }) {
    const [star, setStar] = useState()
    const [coment, setComent] = useState("")

    const handleInput = () => {
        setIsExist(true);
      }

    const InputReviewAndStar = () => {
        axios.post('http://localhost:9000/api/movies/starAndReview',{
            title : item.title,
            star: star,
            coment : coment 
            }
            )
          .then((res) => {
            console.log(res)
            alert("별점과 리뷰가 추가 되었습니다.")
          })
    
    }

      return (
        <div className="movieReviewAndStar">
            <Form>
      <Form.Group className="mb-3" controlId="formBasicStar">
        <Form.Label>별점</Form.Label>
        <Form.Control type="text" placeholder="별점을 작성해주세요! (1 부터 5까지의 숫자)" 
         value={star}
         onChange={e => setStar(e.target.value)} />
      </Form.Group>

      <Form.Group className="mb-3" controlId="formBasicReview">
        <Form.Label>리뷰</Form.Label>
        <Form.Control type="text" placeholder="한줄 리뷰를 작성해주세요!"
          value={coment}
          onChange={e => setComent(e.target.value)} />
      </Form.Group>
      <Button variant="primary" type="submit" onClick={ () => {
     handleInput()
     InputReviewAndStar()} }>
        완료
      </Button>
    </Form>
            </div>
      )

}
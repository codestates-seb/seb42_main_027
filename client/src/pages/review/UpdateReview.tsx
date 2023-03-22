/* eslint-disable react/no-array-index-key */
/* eslint-disable jsx-a11y/label-has-associated-control */
import GlobalStyle from 'GlobalStyles';
import { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router';
import styled from 'styled-components';
import axios from 'axios';
import { FlexContainer } from './ReviewPage';
import { UploadButton } from './CreateTeacher';

const defaultData = {
  title: '리뷰글 제목',
  starPoint: 5,
  content: '리뷰글 내용',
  lectureId: 64,
  createdAt: new Date(),
};

function UpdateReview() {
  const [title, setTitle] = useState<string>('');
  const [content, setContent] = useState<string>('');
  const [starPoint, setStarPoint] = useState<number>(0);
  const { lectureReviewId, lectureId } = useParams();

  const navigate = useNavigate();
  const Authorization = localStorage.getItem('token');

  const starArr = [1, 2, 3, 4, 5];

  useEffect(() => {
    axios
      .get(
        `${process.env.REACT_APP_API_URL}/boards/reviews/lectures/${lectureReviewId}`,
        {
          headers: {
            Authorization,
            'ngrok-skip-browser-warning': '69420',
          },
        },
      )
      .then((res: any) => {
        console.log(res.data.data);
        return res.data.data;
      })
      .then(data => {
        setTitle(data.title);
        setContent(data.content);
      });
  }, []);

  const updateHandler = () => {
    if (!title || !content || !starPoint) {
      alert('빈 곳을 채워주세요!');
    } else {
      const data = {
        title,
        content,
        starPoint: Number(starPoint),
        lectureId: Number(lectureId),
        modifiedAt: new Date(),
      };

      axios
        .patch(
          `${process.env.REACT_APP_API_URL}/boards/reviews/lectures/${lectureReviewId}`,
          data,
          {
            headers: {
              Authorization,
              'ngrok-skip-browser-warning': 'asdasdas',
            },
          },
        )
        .then(() => {
          navigate(-1);
        });
    }
  };

  return (
    <Container>
      <FlexContainer dir="col" width="30rem" gap="2rem">
        <FlexContainer dir="col" align="start" gap="0" width="100%">
          <label htmlFor="title">Title</label>
          <Input
            id="title"
            placeholder="제목"
            value={title}
            onChange={e => {
              setTitle(e.target.value);
            }}
          />
        </FlexContainer>
        <FlexContainer dir="col" align="start" gap="0" width="100%">
          <label htmlFor="content">Content</label>
          <TextArea
            id="content"
            placeholder="내용"
            value={content}
            onChange={e => {
              setContent(e.target.value);
            }}
          />
        </FlexContainer>
        <FlexContainer gap="0.6rem" width="100%">
          별점 :
          {starArr.map((el, index) => {
            return (
              <FlexContainer gap="0.3rem" key={index}>
                <input
                  id="star"
                  name="grade"
                  type="radio"
                  value={el}
                  onClick={(e: any) => {
                    setStarPoint(e.target.value);
                  }}
                />
                ⭐️
                <label htmlFor="star">{el}</label>
              </FlexContainer>
            );
          })}
        </FlexContainer>
        <FlexContainer>
          <UploadButton onClick={updateHandler}>후기 수정</UploadButton>
          <UploadButton
            onClick={() => {
              navigate(-1);
            }}
          >
            수정 취소
          </UploadButton>
        </FlexContainer>
      </FlexContainer>
    </Container>
  );
}

export default UpdateReview;

const Container = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
`;

const Input = styled.input`
  width: 100%;
  padding: 0.3rem 0.5rem;
  border: 1px solid black;
`;

const TextArea = styled.textarea`
  width: 100%;
  height: 15rem;
  padding: 0.5rem;
`;

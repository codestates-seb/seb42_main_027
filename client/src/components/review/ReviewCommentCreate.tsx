/* eslint-disable react/button-has-type */
/* eslint-disable react/require-default-props */
import GlobalStyle from 'GlobalStyles';
import styled from 'styled-components';
import { FlexContainer } from 'pages/review/ReviewPage';
import { useNavigate } from 'react-router-dom';
import { SmallFont } from 'pages/review/TeacherDetail/Information';
import { useState } from 'react';
import axios from 'axios';
import isLogin from 'utils/isLogin';

type Props = {
  lectureReviewId: number;
};
function ReviewCommentCreate({ lectureReviewId }: Props) {
  const [content, setContent] = useState<string>('');
  const Authorization = localStorage.getItem('token');

  const createHandler = () => {
    if (!content) {
      alert('빈 곳을 채워주세요!');
    } else {
      const data = {
        content,
        createdAt: new Date(),
        lectureReviewId,
      };

      axios
        .post(
          `${process.env.REACT_APP_API_URL}/comments/reviews/lectures`,
          data,
          {
            headers: { Authorization, 'ngrok-skip-browser-warning': '69420' },
          },
        )
        .then(() => {
          window.location.reload();
        });
    }
  };

  return (
    <Container>
      <FlexContainer width="100%" align="start" dir="col" gap="0.2rem">
        <Textarea
          value={content}
          onChange={e => {
            setContent(e.target.value);
          }}
        />
        <FlexContainer width="100%" justify="right">
          <Ubutton onClick={createHandler}>등록</Ubutton>
        </FlexContainer>
      </FlexContainer>
    </Container>
  );
}

export default ReviewCommentCreate;

type Container = {
  first?: boolean;
};

const Container = styled.div<Container>`
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 0.5rem;
  padding: 1rem;
  border: 1px solid black;
  background-color: #b9b9b9;
`;

const Textarea = styled.textarea`
  width: 100%;
  height: 6rem;
  padding: 1rem;
`;

const Ubutton = styled.button`
  padding: 0.4rem;
  background-color: gray;
  color: white;
  border-radius: 0.4rem;
`;

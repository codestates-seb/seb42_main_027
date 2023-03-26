/* eslint-disable react/no-array-index-key */
/* eslint-disable jsx-a11y/label-has-associated-control */
import GlobalStyle from 'GlobalStyles';
import { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router';
import styled from 'styled-components';
import axios from 'axios';
import theme from 'theme';
import GoBackMenu from 'components/board/post/goBackMenu';
import StarCounter from 'components/review/StarCounter';
import { FlexContainer } from '../TeacherList/ReviewPage';
import { UploadButton } from '../TeacherList/CreateTeacher';

const defaultData = {
  title: '리뷰글 제목',
  starPoint: 5,
  content: '리뷰글 내용',
  lectureId: 64,
  createdAt: new Date(),
};

function CreateReview() {
  const [title, setTitle] = useState<string>('');
  const [content, setContent] = useState<string>('');
  const [starPoint, setStarPoint] = useState<number>(0);
  const { lectureId } = useParams();

  const navigate = useNavigate();
  const Authorization = localStorage.getItem('token');

  useEffect(() => {
    window.scrollTo(0, 0);
    console.log('Rerendering');
  }, []);

  const createHandler = () => {
    if (!title || !content || !starPoint) {
      alert('빈 곳을 채워주세요!');
    } else {
      const data = {
        title,
        content,
        starPoint: Number(starPoint),
        lectureId: Number(lectureId),
        createdAt: new Date(),
      };

      axios
        .post(
          `${process.env.REACT_APP_API_URL}/boards/reviews/lectures`,
          data,
          {
            headers: {
              Authorization,
              'ngrok-skip-browser-warning': 'asdasdas',
            },
          },
        )
        .then(res => {
          navigate(-1);
        });
    }
  };

  return (
    <Container>
      <FlexContainer dir="col" width="50rem" gap="2rem">
        <Title>
          <H2>리뷰 작성</H2>
          <p>리뷰를 작성 해주세요.</p>
        </Title>
        <GoBackMenu />
        {/* 리뷰 제목 */}
        <FlexContainer dir="col" align="start" gap="0.5rem" width="95%">
          <Label htmlFor="title">제목</Label>
          <Input
            id="title"
            value={title}
            onChange={e => {
              setTitle(e.target.value);
            }}
          />
        </FlexContainer>
        {/* 리뷰 별점 */}
        <FlexContainer dir="col" gap="0.6rem" width="95%" align="start">
          <Label>별점</Label>
          <StarCounter starPoint={starPoint} setStarPoint={setStarPoint} />
        </FlexContainer>
        {/* 리뷰 내용 */}
        <FlexContainer dir="col" align="start" gap="0.5rem" width="95%">
          <Label htmlFor="content">내용</Label>
          <TextArea
            id="content"
            value={content}
            onChange={e => {
              setContent(e.target.value);
            }}
          />
        </FlexContainer>

        <FlexContainer>
          <UploadButton onClick={createHandler}>후기 등록</UploadButton>
          <UploadButton
            onClick={() => {
              navigate(-1);
            }}
          >
            등록 취소
          </UploadButton>
        </FlexContainer>
      </FlexContainer>
    </Container>
  );
}

export default CreateReview;

const Container = styled.div`
  width: 100%;
  background-color: white;

  display: flex;
  flex-direction: column;
  justify-content: start;
  align-items: center;
  gap: 0.5rem;
  margin-bottom: 2rem;
`;

const Title = styled.div`
  box-sizing: border-box;
  width: 100%;
  height: 150px;
  padding: 45px 42px;
  border-radius: 25px;
  background-color: ${theme.colors.palePurple};
`;

const H2 = styled.h2`
  font-weight: bold;
  font-size: ${theme.fontSizes.subTitle};
  margin-bottom: ${theme.gap.px10};
`;

const Input = styled.input`
  width: 100%;
  padding: 0.6rem 0.5rem;
  border: 0.5px solid gray;
  :focus {
    border: 1.2px solid black;
  }
`;

const TextArea = styled.textarea`
  width: 100%;
  height: 15rem;
  padding: 0.5rem;
  border: 0.5px solid gray;
  :focus {
    border: 1.2px solid black;
  }
`;

const Label = styled.label`
  font-size: large;
`;

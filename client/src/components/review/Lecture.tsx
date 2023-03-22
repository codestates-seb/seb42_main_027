/* eslint-disable react/button-has-type */
/* eslint-disable react/require-default-props */
import GlobalStyle from 'GlobalStyles';
import styled from 'styled-components';
import { FlexContainer } from 'pages/review/ReviewPage';
import { Link, useParams } from 'react-router-dom';
import isLogin from 'utils/isLogin';
import axios from 'axios';

type Props = {
  first?: boolean;
  lecture: {
    lectureId: number;
    title: string;
    introduction: string;
    status: string;
    starPointAverage: number;
    totalReviewCount: number;
    gradeTags: { gradeTag: string }[];
    subjectTags: {
      subjectTag: string;
    }[];
    teacher: {
      teacherId: number;
      name: string;
      starPointAverage: number;
    };
  };
};

function Lecture({ lecture, first }: Props) {
  const { teacherId } = useParams();
  return (
    <Container first={first}>
      <FlexContainer width="5rem">
        <MiddleFont>{lecture.teacher.name}</MiddleFont>
      </FlexContainer>
      <FlexContainer width="25rem" dir="col" align="start" gap="0.3rem">
        <StatusBox>{lecture.status}</StatusBox>
        <IntroSpan>{`${lecture.introduction}`}</IntroSpan>
        <Link to={`/lecturereviewlist/${lecture.lectureId}`}>
          <TitleSpan>{`${lecture.title}`}</TitleSpan>
        </Link>
        <SmallFont>+ 자세히 보기</SmallFont>
      </FlexContainer>
      <FlexContainer width="3rem">
        ⭐️ {lecture.starPointAverage.toFixed(1)}
      </FlexContainer>
      <FlexContainer width="5rem">
        {lecture.totalReviewCount} Reviews
      </FlexContainer>
      <FlexContainer
        dir="col"
        display={isLogin() && teacherId ? 'flex' : 'none'}
      >
        <Link to={`updateLecture/${lecture.lectureId}`}>
          <button>수정</button>
        </Link>
        <button
          onClick={() => {
            axios
              .delete(
                `${process.env.REACT_APP_API_URL}/lectures/${lecture.lectureId}`,
                {
                  headers: { 'ngrok-skip-browser-warning': '69420' },
                },
              )
              .then(() => {
                window.location.reload();
              });
          }}
        >
          삭제
        </button>
      </FlexContainer>
    </Container>
  );
}

export default Lecture;

type Container = {
  first?: boolean;
};

const Container = styled.div<Container>`
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 1rem;
  padding: 1rem;
  border-top: ${props => (props.first ? '2px solid black' : null)};
  border-bottom: 0.5px solid black;
`;

const MiddleFont = styled.div`
  font-size: 0.9rem;
`;

const SmallFont = styled.div`
  font-size: 0.6rem;
  color: gray;
`;

const StatusBox = styled.div`
  text-align: center;
  font-size: 0.4rem;
  background-color: #4f4f4f;
  color: white;
  padding: 0.1rem 0.3rem;
`;

const TitleSpan = styled.span`
  font-size: 1rem;
  font-weight: bold;
  :hover {
    color: red;
  }
`;

const IntroSpan = styled.span`
  font-size: 0.8rem;
  color: gray;
`;

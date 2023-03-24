/* eslint-disable react/button-has-type */
/* eslint-disable react/no-array-index-key */
import styled from 'styled-components';
import { Link } from 'react-router-dom';
import { FlexContainer } from 'pages/review/ReviewPage';
import isLogin from 'utils/isLogin';
import axios from 'axios';
import PButton from 'components/member/login/PButton';

type Props = {
  teachers: {
    gradeTags: string[];
    imageUrl: string;
    introduction: string;
    name: string; // 강사명
    platformTags: { platformTag: string }[];
    starPointAverage: number;
    subjectTags: { subjectTag: string }[];
    teacherId: number;
    totalReviewCount: number;
  }[];
};

function CharacterCard({ teachers }: Props) {
  return (
    <Container>
      {teachers.map((el, index) => {
        return (
          <FlexContainer dir="col" key={index}>
            <Link to={`/ReviewPageDetail/${el.teacherId}`}>
              <CardContainer>
                <Img src="http://placehold.it/200X200" alt="dummyImage" />
                <Span>{el.name}</Span>
                <Span>{el.platformTags[0].platformTag}</Span>
                <Span>{el.subjectTags[0].subjectTag}</Span>
                <LargeSpan>
                  {el.introduction.length > 10
                    ? `${el.introduction.slice(0, 10)}...`
                    : el.introduction}
                </LargeSpan>
                <Span>⭐️ {el.starPointAverage.toFixed(1)}</Span>
              </CardContainer>
            </Link>
            <FlexContainer display={isLogin() ? 'flex' : 'none'}>
              <Link to={`updateTeacher/${el.teacherId}`}>
                <button>수정</button>
              </Link>
              <button
                onClick={() => {
                  axios
                    .delete(`http://13.125.1.215:8080/teachers/${el.teacherId}`)
                    .then(() => {
                      window.location.reload();
                    });
                }}
              >
                삭제
              </button>
            </FlexContainer>
          </FlexContainer>
        );
      })}
    </Container>
  );
}

export default CharacterCard;

const Container = styled.div`
  margin: 2rem 0 4rem 0;
  width: 50rem;
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  align-items: center;
  gap: 2rem;
`;

const CardContainer = styled.div`
  width: 12rem;
  padding: 0.5rem;
  padding-bottom: 1rem;
  background-color: white;
  border: 0.3rem solid #6667ab;
  border-radius: 1.2rem;

  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  gap: 1rem;
`;

const Img = styled.img`
  width: 10.5rem;
  height: 6rem;
  border-radius: 0.5rem;
  background-color: #b8b8b8;
`;

const Span = styled.span`
  font-weight: bold;
`;

const LargeSpan = styled.span`
  margin: 1rem 0;
  font-size: large;
  font-weight: bold;
`;

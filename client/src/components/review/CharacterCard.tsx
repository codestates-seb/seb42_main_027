/* eslint-disable react/button-has-type */
/* eslint-disable react/no-array-index-key */
import styled from 'styled-components';
import { Link } from 'react-router-dom';
import { FlexContainer } from 'pages/review/TeacherList/ReviewPage';
import axios from 'axios';
import useUserInfoStore from 'stores/userInfoStore';

type Props = {
  teachers: {
    gradeTags: string[];
    imageUrl: string;
    introduction: string;
    profileImageUrl: string;
    realImageUrl: string;
    name: string; // 강사명
    platformTags: { platformTag: string }[];
    starPointAverage: number;
    subjectTags: { subjectTag: string }[];
    teacherId: number;
    totalReviewCount: number;
  }[];
  setPlatform: React.Dispatch<React.SetStateAction<string>>;
  setSubject: React.Dispatch<React.SetStateAction<string>>;
  setCurPage: React.Dispatch<React.SetStateAction<number>>;
};

function CharacterCard({
  teachers,
  setPlatform,
  setSubject,
  setCurPage,
}: Props) {
  const { userInfo } = useUserInfoStore(state => state);

  return (
    <Container>
      {teachers.map((el, index) => {
        return (
          <FlexContainer dir="col" key={index}>
            <CardContainer>
              <Link to={`/ReviewPageDetail/${el.teacherId}`}>
                <Img
                  src={el.realImageUrl || 'http://placehold.it/170X175'}
                  alt="thumbnail"
                />
              </Link>
              <NomalSpan>{el.name}</NomalSpan>
              <Span
                onClick={() => {
                  setPlatform(el.platformTags[0].platformTag);
                  setSubject('전체');
                  setCurPage(1);
                }}
              >
                {el.platformTags[0].platformTag}
              </Span>
              <Span
                onClick={() => {
                  setSubject(el.subjectTags[0].subjectTag);
                  setPlatform('전체');
                  setCurPage(1);
                }}
              >
                {el.subjectTags[0].subjectTag}
              </Span>
              <NomalSpan>⭐️ {el.starPointAverage.toFixed(1)}</NomalSpan>
            </CardContainer>
            <FlexContainer
              display={userInfo.state === 'ADMIN' ? 'flex' : 'none'}
            >
              <Link to={`updateTeacher/${el.teacherId}`}>
                <button>수정</button>
              </Link>
              <button
                onClick={() => {
                  axios
                    .delete(
                      `${process.env.REACT_APP_API_URL}/boards/teachers/${el.teacherId}`,
                      {
                        headers: {
                          'ngrok-skip-browser-warning': 'asdasdas',
                        },
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
  width: 170px;
  height: 175px;
  border-radius: 0.5rem;
  background-color: #b8b8b8;
`;

const Span = styled.span`
  font-weight: bold;
  cursor: pointer;
  :hover {
    color: red;
  }
`;

const NomalSpan = styled.span`
  font-size: large;
  font-weight: bold;
  cursor: default;
`;

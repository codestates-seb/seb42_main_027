/* eslint-disable no-restricted-globals */
/* eslint-disable react/button-has-type */
/* eslint-disable react/no-array-index-key */
import styled from 'styled-components';
import { Link } from 'react-router-dom';
import { FlexContainer } from 'pages/review/TeacherList/ReviewPage';
import axios from 'axios';
import useUserInfoStore from 'stores/userInfoStore';
import { BsStarFill } from 'react-icons/bs';
import Swal from 'sweetalert2';

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
                  src={el.profileImageUrl || 'http://placehold.it/170X175'}
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
              <NomalSpan>
                <BsStarFill color="gold" /> {el.starPointAverage.toFixed(1)}
              </NomalSpan>
            </CardContainer>
            <FlexContainer
              display={userInfo.state === 'ADMIN' ? 'flex' : 'none'}
            >
              <Link to={`updateTeacher/${el.teacherId}`}>
                <button>수정</button>
              </Link>
              <button
                onClick={() => {
                  Swal.fire({
                    title: '강사를 삭제하시겠습니까?',
                    text: '다시 되돌릴 수 없습니다. 신중하세요.',
                    icon: 'warning',

                    showCancelButton: true, // cancel버튼 보이기. 기본은 원래 없음
                    confirmButtonColor: '#3085d6', // confrim 버튼 색깔 지정
                    cancelButtonColor: '#d33', // cancel 버튼 색깔 지정
                    confirmButtonText: '승인', // confirm 버튼 텍스트 지정
                    cancelButtonText: '취소', // cancel 버튼 텍스트 지정

                    reverseButtons: true, // 버튼 순서 거꾸로
                  }).then(result => {
                    // 만약 Promise리턴을 받으면,
                    if (result.isConfirmed) {
                      // 만약 모달창에서 confirm 버튼을 눌렀다면
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
                    }
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
  @media screen and (max-width: 600px) {
    width: 30rem;
  }
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
  box-shadow: 2px 2px 2px 1px rgba(0, 0, 0, 0.2);
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
  text-align: center;
`;

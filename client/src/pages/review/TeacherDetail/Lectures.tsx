/* eslint-disable react/no-array-index-key */
import { useEffect, useState } from 'react';
import axios from 'axios';
import { useParams } from 'react-router';
import styled from 'styled-components';
import Lecture from 'components/review/Lecture';
import { Link } from 'react-router-dom';
import isLogin from 'utils/isLogin';
import Button from 'components/common/Button';

import { FlexContainer } from '../ReviewPage';

const defaultData = {
  lectureId: 1,
  title: '강의 타이틀명!',
  introduction: '강의 간단 소개',
  status: '진행중',
  starPointAverage: 4.7,
  totalReviewCount: 9,
  gradeTags: [
    {
      gradeTag: '고1',
    },
    {
      gradeTag: '고2',
    },
    {
      gradeTag: '고3',
    },
    {
      gradeTag: '예비고1',
    },
    {
      gradeTag: '예비고2',
    },
    {
      gradeTag: '예비고3',
    },
  ],
  subjectTags: [
    {
      subjectTag: '국어',
    },
    {
      subjectTag: '한국사',
    },
  ],
  platformTags: [
    {
      platformTag: '이투스',
    },
    {
      platformTag: 'EBS',
    },
  ],
  teacher: {
    teacherId: 1,
    name: '홍길동',
    starPointAverage: 0.0,
  },
};

const defaultData2 = {
  gradeTags: ['1'],
  imageUrl: 'string;',
  introduction: 'string;',
  name: 'string;',
  platformTags: [{ platformTag: 's' }],
  starPointAverage: 5,
  subjectTags: [{ subjectTag: 'string' }],
  teacherId: 5,
  totalReviewCount: 5,
  lectures: [defaultData],
  analects: ['1'],
  profile: ['1'],
};

function Lectures() {
  const [data, setData] = useState(defaultData2);
  const [isPending, setIsPending] = useState<boolean>(true);
  const { teacherId } = useParams();

  useEffect(() => {
    axios
      .get(`${process.env.REACT_APP_API_URL}/teachers/${teacherId}`, {
        headers: {
          'ngrok-skip-browser-warning': 'asdasdas',
        },
      })
      .then((res: any) => {
        return res.data.data;
      })
      .then(data => {
        console.log(data);
        setData(data);
        setIsPending(false);
      });
  }, []);

  return (
    <Container height={data.lectures.length < 7 ? '100vh' : ''}>
      {isPending ? (
        <FlexContainer />
      ) : (
        <FlexContainer dir="col">
          <FlexContainer
            display={isLogin() ? 'flex' : 'none'}
            width="100%"
            justify={!data.lectures.length ? 'center' : 'right'}
          >
            <Link to="createLecture">
              <PButton>강의 등록</PButton>
            </Link>
          </FlexContainer>
          {!data.lectures.length ? (
            <FlexContainer height="100vh">등록된 강의가 없습니다</FlexContainer>
          ) : (
            <FlexContainer dir="col">
              {data.lectures.map((el, index) => {
                return <Lecture key={index} lecture={el} first={index === 0} />;
              })}
            </FlexContainer>
          )}
        </FlexContainer>
      )}
    </Container>
  );
}

export default Lectures;

const PButton = Button.PointBtn;

type Container = {
  height?: string;
};
const Container = styled.div<Container>`
  height: ${props => props.height};
  padding: 3rem;
`;

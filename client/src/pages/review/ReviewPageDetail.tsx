/* eslint-disable jsx-a11y/anchor-is-valid */
import GlobalStyle from 'GlobalStyles';
import { Routes, Route, Link } from 'react-router-dom';
import { useParams } from 'react-router';
import { useEffect, useState } from 'react';
import axios from 'axios';
import styled from 'styled-components';
import { FlexContainer } from './ReviewPage';
import Information from './TeacherDetail/Information';
import Lectures from './TeacherDetail/Lectures';
import TeacherReview from './TeacherDetail/TeacherReview';

function ReviewPageDetail() {
  const { teacherId } = useParams();
  const [selected, setSelected] = useState<string>('강사 소개');
  const [data, setData] = useState<object>({});

  useEffect(() => {
    axios
      .get(`http://13.125.1.215:8080/teachers/${teacherId}`)
      .then((res: any) => {
        return res.data.data;
      })
      .then(data => {
        console.log(data);
        setData(data);
      });
  }, []);
  return (
    <FlexContainer dir="col" justify="start" height="100vh">
      <GlobalStyle />
      <FlexContainer width="100vw" gap="0">
        <StyledLink
          to=""
          check={selected === '강사 소개' ? 'true' : 'false'}
          onClick={() => {
            setSelected('강사 소개');
          }}
        >
          강사 소개
        </StyledLink>
        <StyledLink
          to="lectures"
          check={selected === '개설 강좌' ? 'true' : 'false'}
          onClick={() => {
            setSelected('개설 강좌');
          }}
        >
          개설 강좌
        </StyledLink>
        <StyledLink
          to="teacherReview"
          check={selected === '수강 후기' ? 'true' : 'false'}
          onClick={() => {
            setSelected('수강 후기');
          }}
        >
          수강 후기
        </StyledLink>
      </FlexContainer>
      <Routes>
        <Route path="" element={<Information />} />
        <Route path="lectures" element={<Lectures />} />
        <Route path="teacherReview" element={<TeacherReview />} />
      </Routes>
    </FlexContainer>
  );
}

export default ReviewPageDetail;

type Tap = {
  check?: string;
};

const StyledLink = styled(Link)<Tap>`
  text-align: center;
  padding: 1rem 5rem;
  flex-grow: 1;
  border: 1px solid #6667ab;
  border-top: 0;
  border-left: 0;
  background-color: ${props => (props.check === 'true' ? '#6667ab' : 'none')};
  color: ${props => (props.check === 'true' ? 'white' : 'black')};
`;

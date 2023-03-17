/* eslint-disable jsx-a11y/anchor-is-valid */
import GlobalStyle from 'GlobalStyles';
import { Routes, Route, Link } from 'react-router-dom';
import { useState } from 'react';
import styled from 'styled-components';
import { FlexContainer } from './ReviewPage';
import Information from './TeacherDetail/Information';
import Lectures from './TeacherDetail/Lectures';
import TeacherReview from './TeacherDetail/TeacherReview';

function ReviewPageDetail() {
  const [selected, setSelected] = useState<string>('강사 소개');

  return (
    <FlexContainer width="100vw" dir="col" justify="flex-start">
      <GlobalStyle />
      <FlexContainer width="100%" gap="0">
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

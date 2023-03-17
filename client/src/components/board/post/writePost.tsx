import { useLocation } from 'react-router';

import styled from 'styled-components';
import theme from 'theme';

import GoBackMenu from './goBackMenu';

function WritePost() {
  const urlData = useLocation().pathname;
  return (
    <Container>
      <GoBackMenu />
      {urlData === '/free/write' ? (
        <h1>자유게시판 글 작성 페이지입니다.</h1>
      ) : null}
      {urlData === '/free/articles/edit' ? (
        <h1>자유게시판 글 수정 페이지입니다.</h1>
      ) : null}
      {urlData === '/qna/write' ? (
        <h1>질문게시판 글 작성 페이지입니다.</h1>
      ) : null}
      {urlData === '/qna/articles/edit' ? (
        <h1>질문게시판 글 수정 페이지입니다.</h1>
      ) : null}
    </Container>
  );
}

const Container = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
`;

export default WritePost;

import { Routes, Route } from 'react-router-dom';

import styled from 'styled-components';
import theme from 'theme';

import PostList from 'components/board/post/postList';
import WritePost from 'components/board/post/postWrite';
import PostContent from 'components/board/freeBoard/postContent';

function FreeBoard() {
  return (
    <Container>
      <Main>
        <Title>
          <H2>자유게시판</H2>
          <p>다양한 이야기를 자유롭게 나누는 공간입니다.</p>
        </Title>
        <Routes>
          <Route path="" element={<PostList />} />
          <Route path="articles/:id" element={<PostContent />} />
          <Route path="articles/write" element={<WritePost />} />
          <Route path="articles/:id/edit" element={<WritePost />} />
        </Routes>
      </Main>
    </Container>
  );
}

const Container = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
`;

const Main = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 100%;
  max-width: 62.5%;
  margin: 0 auto;
`;

const Title = styled.div`
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

export default FreeBoard;

import React from 'react';
import styled from 'styled-components';
import Button from 'components/common/Button';
import theme from 'theme';

import PostList from 'components/board/postList';
import GoBackMenu from 'components/board/goBackMenu';

function FreeBoard() {
  return (
    <Container>
      <Main>
        <Title>
          <H2>자유게시판</H2>
          <p>다양한 이야기를 자유롭게 나누는 공간입니다.</p>
        </Title>
        <GoBackMenu />
        <Button.WriteBtn>작성하기</Button.WriteBtn>
        <Button.SubMenuBtn className="selected">전체</Button.SubMenuBtn>
        <Button.SubMenuBtn>일상</Button.SubMenuBtn>
        <PostList />
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
  border: 1px solid ${theme.colors.gray};
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

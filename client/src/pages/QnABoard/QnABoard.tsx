import React from 'react';
import styled from 'styled-components';
import theme from '../../theme';

function QnABoard() {
  return (
    <Container>
      <Main>
        <Title>
          <H2>질문게시판</H2>
          <p>학습 중 생긴 궁금증에 관해 질문하고 답변할 수 있는 공간입니다.</p>
        </Title>
        <h1>QnABoard Page </h1>
      </Main>
    </Container>
  );
}

const Container = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  height: 100%;
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

export default QnABoard;

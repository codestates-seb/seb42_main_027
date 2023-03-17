import styled from 'styled-components';
import theme from 'theme';

import GoBackMenu from './goBackMenu';
import WriteComment from '../comment/writeComment';

function PostContentFree() {
  return (
    <Container>
      <GoBackMenu />
      <TitleDiv>
        <Top>
          <Category>일상</Category>
          <div>수정삭제버튼</div>
        </Top>
        <Title>이곳에 제목이 들어갑니다.</Title>
        <Writer>닉네임 약 1시간 전</Writer>
        <View>36</View>
      </TitleDiv>
      <MainDiv>
        <div>게시글 본문</div>
        <div>추천버튼</div>
      </MainDiv>
      <CommentCnt>n개의 댓글</CommentCnt>
      <WriteComment />이 밑으로 댓글
    </Container>
  );
}

const Container = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
`;

const TitleDiv = styled.div`
  display: flex;
  flex-direction: column;
  padding: ${theme.gap.px20};
  border-bottom: 1px solid ${theme.colors.gray};
`;

const Top = styled.div``;
const Title = styled.div``;
const Writer = styled.div``;
const View = styled.div``;

const Category = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  width: 40px;
  height: 18px;
  border: 1px solid ${theme.colors.pointColor};
  border-radius: 5px;
  font-size: ${theme.fontSizes.sm};
  font-weight: bold;
  color: ${theme.colors.pointColor};
  background-color: ${theme.colors.white};
  margin-bottom: 4px;
`;

const MainDiv = styled.div`
  display: flex;
  flex-direction: column;
  padding: ${theme.gap.px20};
  border-bottom: 1px solid ${theme.colors.gray};
`;

const CommentCnt = styled.div`
  padding: ${theme.gap.px20};
  border-bottom: 1px solid ${theme.colors.gray};
`;

export default PostContentFree;

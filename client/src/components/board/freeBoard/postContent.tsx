import { Link } from 'react-router-dom';

import styled from 'styled-components';
import theme from 'theme';
import Button from 'components/common/Button';
import ProfileIcon from 'assets/icons/defaultProfileIcon';
import CountIcon from 'assets/icons/countIcon';

import GoBackMenu from '../post/goBackMenu';
import CommentList from './commentList';

function PostContent() {
  return (
    <Container>
      <GoBackMenu />
      <TitleDiv>
        <Top>
          <Category>일상</Category>
          <UDBtnDiv>
            <Link to="edit">
              <Button.UDWhiteBtn>수정</Button.UDWhiteBtn>
            </Link>
            <Button.UDWhiteBtn>삭제</Button.UDWhiteBtn>
          </UDBtnDiv>
        </Top>
        <H2>이곳에 제목이 들어갑니다.</H2>
        <Writer>
          <ProfileIcon.Default />
          <div>닉네임 약 1시간 전</div>
          <View>
            <CountIcon.View />
            36
          </View>
        </Writer>
      </TitleDiv>
      <MainDiv>
        <div>여기에 게시물 내용이 들어갑니다.</div>
        <VoteDiv>
          <Button.VoteDownBtn>
            <CountIcon.VoteDown />
          </Button.VoteDownBtn>
          <VoteCount>0</VoteCount>
          <Button.VoteUpBtn>
            <CountIcon.VoteUp />
          </Button.VoteUpBtn>
        </VoteDiv>
      </MainDiv>
      <CommentCnt>n개의 댓글</CommentCnt>
      <CommentList />
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

const Top = styled.div`
  display: flex;
  justify-content: space-between;
  margin-bottom: ${theme.gap.px10};
`;

const H2 = styled.h2`
  font-weight: bold;
  font-size: ${theme.fontSizes.subTitle};
  margin-bottom: ${theme.gap.px20};
`;

const Writer = styled.div`
  display: flex;
  align-items: center;
  position: relative;
`;
const View = styled.div`
  display: flex;
  position: absolute;
  right: 0;
  bottom: 0;
`;

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

const UDBtnDiv = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: calc(${theme.gap.px40} * 2 + 6px);
`;

const MainDiv = styled.div`
  display: flex;
  flex-direction: column;
  padding: ${theme.gap.px20};
  border-bottom: 1px solid ${theme.colors.gray};
`;

const VoteDiv = styled.div`
  display: flex;
  justify-content: right;
  margin-bottom: ${theme.gap.px120};
`;

const VoteCount = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  width: 1.875rem;
  height: 1.25rem;
  font-size: ${theme.fontSizes.xs};
  border-top: 1px solid ${theme.colors.gray};
  border-bottom: 1px solid ${theme.colors.gray};
`;

const CommentCnt = styled.div`
  padding: ${theme.gap.px20};
  border-bottom: 1px solid ${theme.colors.gray};
`;

export default PostContent;

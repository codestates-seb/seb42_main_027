import { useState } from 'react';

import styled from 'styled-components';
import theme from 'theme';
import Button from 'components/common/Button';
import ProfileIcon from 'assets/icons/defaultProfileIcon';
import CountIcon from 'assets/icons/countIcon';

import WriteComment from '../comment/writeComment';
import RecommentList from './recommentList';

function CommentBlock() {
  const [openRecom, setOpenRecom] = useState(false);
  // console.log(data);

  const openRecomHandler = (e: React.MouseEvent<HTMLButtonElement>) => {
    // if (e.target instanceof Element) {
    //   console.log(e.target.log);
    // }
    setOpenRecom(!openRecom);
  };

  return (
    <Container>
      <TitleDiv>
        <Writer>
          <ProfileIcon.Default />
          <div>닉네임 약 1시간 전</div>
          <Category>작성자</Category>
        </Writer>
        <UDBtnDiv>
          <Button.UDWhiteBtn>수정</Button.UDWhiteBtn>
          <Button.UDWhiteBtn>삭제</Button.UDWhiteBtn>
        </UDBtnDiv>
      </TitleDiv>
      <MainDiv>
        <TextDiv>내용</TextDiv>
        <BottomDiv>
          <Button.RecommentBtn onClick={openRecomHandler}>
            댓글 쓰기
          </Button.RecommentBtn>
          <VoteDiv>
            <Button.VoteDownBtn>
              <CountIcon.VoteDown />
            </Button.VoteDownBtn>
            <VoteCount>0</VoteCount>
            <Button.VoteUpBtn>
              <CountIcon.VoteUp />
            </Button.VoteUpBtn>
          </VoteDiv>
        </BottomDiv>
      </MainDiv>
      {openRecom ? (
        <WriteRecomDiv>
          <WriteComment />
        </WriteRecomDiv>
      ) : null}
      <RecommentList />
    </Container>
  );
}

const Container = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  padding: ${theme.gap.px20};
  padding-bottom: 0;
  border-bottom: 1px solid ${theme.colors.gray};
`;

const TitleDiv = styled.div`
  display: flex;
  justify-content: space-between;
  margin-bottom: ${theme.gap.px20};
`;

const Writer = styled.div`
  display: flex;
  align-items: center;
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
  margin-bottom: ${theme.gap.px20};
`;

const TextDiv = styled.div`
  display: flex;
  margin-bottom: ${theme.gap.px20};
`;

const BottomDiv = styled.div`
  display: flex;
  justify-content: space-between;
`;

const VoteDiv = styled.div`
  display: flex;
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

const WriteRecomDiv = styled.div`
  padding-left: ${theme.gap.px20};
  border-top: 1px dashed ${theme.colors.gray};
`;

export default CommentBlock;

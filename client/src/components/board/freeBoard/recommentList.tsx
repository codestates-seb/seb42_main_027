import styled from 'styled-components';
import theme from 'theme';
import Button from 'components/common/Button';
import ProfileIcon from 'assets/icons/defaultProfileIcon';
import CountIcon from 'assets/icons/countIcon';

function RecommentList() {
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
        <TextDiv>여기에 대댓글 내용이 들어갑니다.</TextDiv>
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
    </Container>
  );
}

const Container = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  padding: ${theme.gap.px20};
  padding-bottom: 0;
  border-top: 1px dashed ${theme.colors.gray};
`;

const TitleDiv = styled.div`
  display: flex;
  justify-content: space-between;
  margin-bottom: ${theme.gap.px20};
  padding-left: ${theme.gap.px40};
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
  padding-left: ${theme.gap.px40};
`;

const TextDiv = styled.div`
  display: flex;
  margin-bottom: ${theme.gap.px20};
`;

const VoteDiv = styled.div`
  display: flex;
  justify-content: right;
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

export default RecommentList;

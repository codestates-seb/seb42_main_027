import { useState } from 'react';
import { useLocation } from 'react-router';

import styled from 'styled-components';
import theme from 'theme';
import Button from 'components/common/Button';
import ProfileIcon from 'assets/icons/defaultProfileIcon';
import StateIcon from 'assets/icons/stateIcon';
import CountIcon from 'assets/icons/countIcon';

import PostVote from 'apis/board/postVote';
import useUserInfoStore from 'stores/userInfoStore';
import deleteComment from 'apis/board/deleteComment';
import CalElapsedTime from '../post/calElapsedTime';
import WriteComment from '../comment/writeComment';
import RecommentList from './recommentList';

interface Data {
  freeCommentId: number;
  content: string;
  createdAt: string;
  modifiedAt?: string;
  voteCount: number;
  member: {
    memberId: number;
    iconImageUrl?: string;
    displayName: string;
    state: string;
  };
  memberSim: boolean;
}

type Props = {
  data: Data;
  checkState: boolean;
  setCheckState: React.Dispatch<React.SetStateAction<boolean>>;
};

function CommentBlock({ data, checkState, setCheckState }: Props) {
  const { userInfo } = useUserInfoStore(state => state);
  const urlData = useLocation().pathname.slice(0, 4);
  const [openEdit, setOpenEidt] = useState(false);
  const [openRecom, setOpenRecom] = useState(false);
  // console.log(data.member.displayName);

  const calTime: string = CalElapsedTime(data.createdAt);

  const openEditHandler = (e: React.MouseEvent<HTMLButtonElement>) => {
    setOpenEidt(!openEdit);
  };

  const openRecomHandler = (e: React.MouseEvent<HTMLButtonElement>) => {
    setOpenRecom(!openRecom);
  };

  const voteHandler = async (value: string) => {
    try {
      await PostVote('frees/comments', data.freeCommentId, value);
      setCheckState(!checkState);
    } catch (err) {
      console.error(err);
    }
  };

  const fetchDeleteComment = async () => {
    try {
      const confirm = window.confirm('댓글을 삭제하시겠습니까?');
      if (confirm) {
        await deleteComment('frees', Number(data.freeCommentId));
        setCheckState(!checkState);
      }
    } catch (err) {
      console.error(err);
    }
  };

  return (
    <Container>
      <TitleDiv>
        <Writer>
          <ProfileIcon.Default />
          <NameDiv>
            {data.member.displayName}
            {data.member.state === 'TEACHER' ? (
              <StateIcon.Teacher title="강사" />
            ) : null}
            {data.member.state === 'ADMIN' ? (
              <StateIcon.Admin title="관리자" />
            ) : null}
          </NameDiv>
          <div> · {calTime}</div>
          {data.memberSim ? <Category>작성자</Category> : null}
        </Writer>
        {data.member.memberId === userInfo.memberId ? (
          <UDBtnDiv>
            <Button.UDWhiteBtn onClick={openEditHandler}>
              수정
            </Button.UDWhiteBtn>
            <Button.UDWhiteBtn onClick={fetchDeleteComment}>
              삭제
            </Button.UDWhiteBtn>
          </UDBtnDiv>
        ) : null}
      </TitleDiv>
      <MainDiv>
        <TextDiv>{data.content}</TextDiv>
        <BottomDiv>
          <Button.RecommentBtn onClick={openRecomHandler}>
            {/* 댓글 쓰기 */}
          </Button.RecommentBtn>
          <VoteDiv>
            <Button.VoteDownBtn onClick={e => voteHandler('down')}>
              <CountIcon.VoteDown />
            </Button.VoteDownBtn>
            <VoteCount>{data.voteCount}</VoteCount>
            <Button.VoteUpBtn onClick={e => voteHandler('up')}>
              <CountIcon.VoteUp />
            </Button.VoteUpBtn>
          </VoteDiv>
        </BottomDiv>
      </MainDiv>
      {openRecom ? (
        <WriteRecomDiv>
          <WriteComment checkState={checkState} setCheckState={setCheckState} />
        </WriteRecomDiv>
      ) : null}
      {/* <RecommentList /> */}
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

const NameDiv = styled.div`
  margin: 0 3px;
  display: flex;
`;

const Writer = styled.div`
  display: flex;
  align-items: center;
`;

const Category = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  min-width: 40px;
  height: 18px;
  padding: 0 calc(${theme.gap.px10} / 2);
  border: 1px solid ${theme.colors.pointColor};
  border-radius: 5px;
  font-size: ${theme.fontSizes.sm};
  font-weight: bold;
  color: ${theme.colors.pointColor};
  background-color: ${theme.colors.white};
  margin-left: 6px;
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

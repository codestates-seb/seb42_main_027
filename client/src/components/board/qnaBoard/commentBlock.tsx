import { useState } from 'react';
import { Link, useLocation, useParams, useNavigate } from 'react-router-dom';

import styled from 'styled-components';
import theme from 'theme';
import Button from 'components/common/Button';
import CountIcon from 'assets/icons/countIcon';
import StateIcon from 'assets/icons/stateIcon';

import PostVote from 'apis/board/postVote';
import useUserInfoStore from 'stores/userInfoStore';
import deleteComment from 'apis/board/deleteComment';
import PatchComment from 'apis/board/patchComment';
import CalElapsedTime from '../post/calElapsedTime';
import WriteComment from '../comment/writeComment';
import EditComment from '../comment/editComment';
// import RecommentList from './recommentList';

interface Comment {
  qnaCommentId: number;
  content: string;
  createdAt: string;
  modifiedAt: string | null;
  voteCount: number;
  member: {
    memberId: number;
    iconImageUrl?: string;
    displayName: string;
    state: string;
  };
}

type Props = {
  data: Comment;
  checkState: boolean;
  setCheckState: React.Dispatch<React.SetStateAction<boolean>>;
};

function CommentBlock({ data, checkState, setCheckState }: Props) {
  const { userInfo } = useUserInfoStore(state => state);
  const [openEdit, setOpenEidt] = useState(false);
  const [openRecom, setOpenRecom] = useState(false);
  const [checkEdit, setCheckEdit] = useState<boolean>(false);
  const [editData, setEditData] = useState<string>('');

  const calTime: string = CalElapsedTime(data.createdAt);

  const openEditHandler = (e: React.MouseEvent<HTMLButtonElement>) => {
    setOpenEidt(!openEdit);
  };

  const openRecomHandler = (e: React.MouseEvent<HTMLButtonElement>) => {
    setOpenRecom(!openRecom);
  };

  const voteHandler = async (value: string) => {
    try {
      await PostVote('qnas/comments', data.qnaCommentId, value);
      setCheckState(!checkState);
    } catch (err) {
      console.error(err);
    }
  };

  const fetchDeleteComment = async () => {
    try {
      const confirm = window.confirm('댓글을 삭제하시겠습니까?');
      if (confirm) {
        await deleteComment('qnas', Number(data.qnaCommentId));
        setCheckState(!checkState);
      }
    } catch (err) {
      console.error(err);
    }
  };

  const editHandler = () => {
    setCheckEdit(!checkEdit);
    setEditData(data.content);
  };

  const fetchEditComment = async () => {
    try {
      const patchData = {
        content: editData,
        modifiedAt: `${new Date()}`,
      };
      await PatchComment(patchData, 'qnas', Number(data.qnaCommentId));
      setCheckEdit(!checkEdit);
      setEditData('');
      setCheckState(!checkState);
    } catch (err) {
      console.error(err);
    }
  };

  return (
    <Container>
      {checkEdit ? (
        <WriteCommentDiv>
          <EditComment
            data={data}
            checkState={checkState}
            setCheckState={setCheckState}
            editData={editData}
            setEditData={setEditData}
          />
        </WriteCommentDiv>
      ) : (
        <MainDiv>
          <TextDiv>{data.content}</TextDiv>
        </MainDiv>
      )}
      <TitleDiv>
        <Writer>
          <div>
            {data.member.displayName}
            {data.member.state === 'TEACHER' ? (
              <StateIcon.Teacher title="강사" />
            ) : null}
            {data.member.state === 'ADMIN' ? (
              <StateIcon.Admin title="관리자" />
            ) : null}
          </div>
          <div> · {calTime}</div>
        </Writer>
        <UDVDiv>
          <RecomWriteDiv>
            <BottomDiv>
              <Button.RecommentBtn onClick={openRecomHandler}>
                댓글 쓰기
              </Button.RecommentBtn>
            </BottomDiv>
          </RecomWriteDiv>
          {data.member.memberId === userInfo.memberId ? (
            <UDBtnDiv>
              {checkEdit ? (
                <Button.UDWhiteBtn onClick={editHandler}>
                  취소
                </Button.UDWhiteBtn>
              ) : (
                <Button.UDWhiteBtn onClick={editHandler}>
                  수정
                </Button.UDWhiteBtn>
              )}
              {checkEdit ? (
                <Button.UDWhiteBtn onClick={fetchEditComment}>
                  확인
                </Button.UDWhiteBtn>
              ) : (
                <Button.UDWhiteBtn onClick={fetchDeleteComment}>
                  삭제
                </Button.UDWhiteBtn>
              )}
            </UDBtnDiv>
          ) : null}
          <VoteDiv>
            <Button.VoteDownBtn onClick={e => voteHandler('down')}>
              <CountIcon.VoteDown />
            </Button.VoteDownBtn>
            <VoteCount>{data.voteCount}</VoteCount>
            <Button.VoteUpBtn onClick={e => voteHandler('up')}>
              <CountIcon.VoteUp />
            </Button.VoteUpBtn>
          </VoteDiv>
        </UDVDiv>
      </TitleDiv>
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
  border-top: 1px solid ${theme.colors.pointColor};
`;

const TitleDiv = styled.div`
  display: flex;
  justify-content: space-between;
  margin-bottom: ${theme.gap.px20};
`;

const Writer = styled.div`
  display: flex;
  align-items: center;
  color: ${theme.colors.pointColor};
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
`;

const UDBtnDiv = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: calc(${theme.gap.px40} * 2 + 6px);
  margin-left: ${theme.gap.px20};
`;

const MainDiv = styled.div`
  display: flex;
  flex-direction: column;
  margin-bottom: ${theme.gap.px10};
`;

const RecomWriteDiv = styled(MainDiv)`
  flex-direction: row;
  margin: 0;
`;

const TextDiv = styled.div`
  display: flex;
`;

const BottomDiv = styled.div`
  display: flex;
  justify-content: space-between;
`;

const UDVDiv = styled.div`
  display: flex;
`;

const VoteDiv = styled.div`
  display: flex;
  margin-left: ${theme.gap.px20};
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

const WriteCommentDiv = styled.div`
  display: flex;
  width: 100%;
`;
export default CommentBlock;

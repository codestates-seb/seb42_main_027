import { useEffect, useState } from 'react';
import { Link, useLocation, useParams, useNavigate } from 'react-router-dom';
import useUserInfoStore from 'stores/userInfoStore';

import styled from 'styled-components';
import theme from 'theme';
import Button from 'components/common/Button';
import ProfileIcon from 'assets/icons/defaultProfileIcon';
import StateIcon from 'assets/icons/stateIcon';
import CountIcon from 'assets/icons/countIcon';

import DeletePost from 'apis/board/deletePost';
import PostVote from 'apis/board/postVote';
import PostAdopt from 'apis/board/postAdopt';
import PatchAnswer from 'apis/board/patchAnswer';
import CalElapsedTime from '../post/calElapsedTime';
import CommentBlock from './commentBlock';
import WriteAnswerComment from '../comment/writeAnswerComment';
import TextEditor from '../customTextEditor';

interface AnswerData {
  answerId: number;
  content: 'string';
  voteCount: number;
  commentCount: number;
  createdAt: string;
  modifiedAt: string | null;
  adoptStatus: string;
  answerCount: number;
  member: {
    memberId: number;
    iconImageUrl?: string;
    displayName: string;
    state: string;
  };
  comments: [Comment];
}

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

interface VoteInfo {
  memberId: number;
  email: string;
  questionId: number;
  questionvoteStatus: string;
  answerVoteStatus: [AnswerVoteInfo];
  qnaCommentVoteStatus: [CommentVoteInfo];
}

interface AnswerVoteInfo {
  answerId: number;
  voteStatus: string;
}

interface CommentVoteInfo {
  qnaCommentVoteId: number;
  voteStatus: string;
}

type Props = {
  data: AnswerData;
  checkState: boolean;
  setCheckState: React.Dispatch<React.SetStateAction<boolean>>;
  questionWriter: number;
  voteInfo: VoteInfo | Record<string, never>;
};

function AnswerContent({
  data,
  checkState,
  setCheckState,
  questionWriter,
  voteInfo,
}: Props) {
  const { userInfo } = useUserInfoStore(state => state);
  const [checkEdit, setCheckEdit] = useState<boolean>(false);
  const [editData, setEditData] = useState<string>(data.content);
  const [uploadImages, setUploadImages] = useState<string[] | []>([]);
  const [comDivIsOpen, setComDivIsOpen] = useState<boolean>(false);
  const [voteTotal, SetVoteTotal] = useState<number>(data.voteCount);
  const [isVoteStatus, SetIsVoteStatus] = useState<string | null>('');
  const navigate = useNavigate();
  const idData = Number(useParams().id);
  const calTime = CalElapsedTime(data.createdAt);

  const openComDivHandler = (e: React.MouseEvent<HTMLButtonElement>) => {
    setComDivIsOpen(!comDivIsOpen);
  };

  const patchAnswerHandler = async () => {
    try {
      const patchData = {
        content: editData,
        uploadImages,
        modifiedAt: `${new Date()}`,
      };
      await PatchAnswer(patchData, data.answerId);
      await setCheckState(!checkState);
      setCheckEdit(!checkEdit);
    } catch (err) {
      // console.error(err);
    }
  };

  const fetchDeletePost = async () => {
    try {
      const confirm = window.confirm('답변을 삭제하시겠습니까?');
      if (confirm) {
        await DeletePost('qnas/answers', data.answerId);
        alert('답변을 삭제하였습니다.');
        setCheckState(!checkState);
      }
    } catch (err) {
      // console.error(err);
    }
  };

  const fetchPostAdopt = async () => {
    try {
      await PostAdopt(idData, data.answerId);
      setCheckState(!checkState);
    } catch (err) {
      // console.error(err);
    }
  };

  const voteHandler = async (value: string) => {
    try {
      const res = await PostVote('qnas/answers', data.answerId, value);
      await SetVoteTotal(res.data.answerVoteTotalCount);
      await SetIsVoteStatus(res.data.status);
    } catch (err) {
      // console.error(err);
    }
  };

  const editHandler = () => {
    setEditData(data.content);
    setCheckEdit(!checkEdit);
  };

  return (
    <Container>
      <TitleDiv>
        <Top>
          <CategoryDiv>
            <Category>답변</Category>
            {data.adoptStatus === 'TRUE' ? (
              <Category className="seleted">답변채택</Category>
            ) : null}
          </CategoryDiv>
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
                <Button.UDWhiteBtn onClick={patchAnswerHandler}>
                  확인
                </Button.UDWhiteBtn>
              ) : (
                <Button.UDWhiteBtn onClick={fetchDeletePost}>
                  삭제
                </Button.UDWhiteBtn>
              )}
            </UDBtnDiv>
          ) : null}
        </Top>
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
        </Writer>
      </TitleDiv>
      {checkEdit ? (
        <EditDiv>
          <TextEditorDiv>
            <TextEditor
              textContent={editData}
              setTextContent={setEditData}
              uploadImages={uploadImages}
              setUploadImages={setUploadImages}
              path="boards/qnas/answers/contents"
            />
          </TextEditorDiv>
        </EditDiv>
      ) : (
        <MainDiv>
          <TextDiv dangerouslySetInnerHTML={{ __html: data.content }} />
          {questionWriter === userInfo.memberId ? (
            <div>
              {data.adoptStatus === 'TRUE' ? (
                <BtnSelect onClick={fetchPostAdopt} className="selected">
                  채택완료
                </BtnSelect>
              ) : (
                <BtnSelect onClick={fetchPostAdopt}>채택하기</BtnSelect>
              )}
            </div>
          ) : null}
          <VoteDiv>
            <Button.VoteDownBtn
              className={isVoteStatus === 'DOWN' ? 'selected' : ''}
              onClick={e => voteHandler('down')}
            >
              <CountIcon.VoteDown />
            </Button.VoteDownBtn>
            <VoteCount>{voteTotal}</VoteCount>
            <Button.VoteUpBtn
              className={isVoteStatus === 'UP' ? 'selected' : ''}
              onClick={e => voteHandler('up')}
            >
              <CountIcon.VoteUp />
            </Button.VoteUpBtn>
          </VoteDiv>
          <CommentContainer>
            {data.commentCount === 0 ? null : (
              <CommentViewDiv>
                {data.comments.map((ele: Comment) => {
                  return (
                    <CommentBlock
                      key={ele.qnaCommentId}
                      data={ele}
                      checkState={checkState}
                      setCheckState={setCheckState}
                      voteStatusArray={voteInfo.qnaCommentVoteStatus}
                    />
                  );
                })}
              </CommentViewDiv>
            )}
            {comDivIsOpen ? (
              <CommentDiv>
                <ComBtnDiv>
                  <Button.RecommentBtn onClick={openComDivHandler}>
                    닫기
                  </Button.RecommentBtn>
                </ComBtnDiv>
                <WriteCommentDiv>
                  <WriteAnswerComment
                    answerId={data.answerId}
                    checkState={checkState}
                    setCheckState={setCheckState}
                  />
                </WriteCommentDiv>
              </CommentDiv>
            ) : (
              <CommentDiv>
                <ComBtnDiv>
                  <Button.RecommentBtn onClick={openComDivHandler}>
                    댓글 쓰기
                  </Button.RecommentBtn>
                </ComBtnDiv>
              </CommentDiv>
            )}
          </CommentContainer>
        </MainDiv>
      )}
    </Container>
  );
}

const Container = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
`;

const NoData = styled.div`
  display: flex;
  width: 100%;
  justify-content: center;
  align-items: center;
  padding-top: ${theme.gap.px60};
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
  align-items: center;
  margin-bottom: ${theme.gap.px10};
`;

const CategoryDiv = styled.div`
  display: flex;
  align-items: center;
`;

const Writer = styled.div`
  display: flex;
  align-items: center;
  position: relative;
`;

const NameDiv = styled.div`
  margin: 0 3px;
  display: flex;
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
  margin-bottom: 4px;
  margin-right: 5px;

  &.seleted {
    color: ${theme.colors.white};
    background-color: ${theme.colors.pointColor};
  }
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

const EditDiv = styled(MainDiv)`
  padding-bottom: ${theme.gap.px100};
`;

const TextEditorDiv = styled.div`
  display: flex;
  justify-content: center;
  width: 100%;
`;

const BtnSelect = styled(Button.UDWhiteBtn)`
  width: 3.375rem;
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

const AnswerCnt = styled.div`
  padding: ${theme.gap.px20};
  border-bottom: 1px solid ${theme.colors.gray};
`;

const CommentContainer = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
`;

const CommentViewDiv = styled.div`
  display: flex;
  flex-direction: column;
  margin-left: ${theme.gap.px40};
`;

const CommentDiv = styled.div`
  display: flex;
  flex-direction: column;
  border-top: 1px solid ${theme.colors.pointColor};
  padding-top: ${theme.gap.px20};
  margin-left: ${theme.gap.px40};
  margin-bottom: ${theme.gap.px100};
`;

const ComBtnDiv = styled.div`
  display: flex;
  justify-content: left;
  padding-left: ${theme.gap.px20};
`;

const WriteCommentDiv = styled.div`
  display: flex;
  width: 100%;
`;

const TextDiv = styled.div`
  font-size: 1rem;
  line-height: 2rem;
  margin-bottom: ${theme.gap.px20};
  white-space: pre-wrap;

  .ql-size-small {
    font-size: ${theme.fontSizes.sm};
  }
  .ql-size-large {
    font-size: ${theme.fontSizes.md};
  }
  .ql-size-huge {
    font-size: ${theme.fontSizes.lg};
  }
  strong {
    font-weight: bold;
  }
  em {
    font-style: italic;
  }
  blockquote {
    border-left: 4px solid #ccc;
    margin-bottom: 5px;
    margin-top: 5px;
    padding-left: 16px;
  }
  ol {
    padding-left: 3em;
  }
  ol > li {
    list-style: decimal;
    &.ql-indent-1 {
      margin-left: 3em;
    }
  }
  ul {
    padding-left: 3em;
  }
  ul > li {
    list-style: disc;
    &.ql-indent-1 {
      margin-left: 3em;
    }
  }
  a {
    text-decoration: underline;
  }
  img {
    max-width: 50rem;
    height: auto;
  }
`;
export default AnswerContent;

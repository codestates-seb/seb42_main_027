import { useEffect, useState } from 'react';
import { Link, useLocation, useParams, useNavigate } from 'react-router-dom';
import useUserInfoStore from 'stores/userInfoStore';
import { useIsLoginStore } from 'stores/loginStore';

import styled from 'styled-components';
import theme from 'theme';
import Button from 'components/common/Button';
import ProfileIcon from 'assets/icons/defaultProfileIcon';
import StateIcon from 'assets/icons/stateIcon';
import CountIcon from 'assets/icons/countIcon';
import { AiOutlineExclamationCircle } from 'react-icons/ai';

import DeletePost from 'apis/board/deletePost';
import getPostDetail from 'apis/board/getPostDetail';
import PostVote from 'apis/board/postVote';
import PostAnswer from 'apis/board/postAnswer';
import TextEditor from '../customTextEditor';
import CalElapsedTime from '../post/calElapsedTime';

import GoBackMenu from '../post/goBackMenu';
import WriteComment from '../comment/writeComment';
import AnswerContent from './answerContent';
import CommentBlock from './commentBlock';

interface Data {
  questionId: number;
  title: 'string';
  content: 'string';
  category: string;
  adoptAnswerId: number;
  viewCount: number;
  voteCount: number;
  commentCount: number;
  answerCount: number;
  createdAt: string;
  modifiedAt: string | null;
  member: {
    memberId: number;
    iconImageUrl?: string;
    displayName: string;
    state: string;
  };
  answers: [AnswerData];
  comments: [Comment];
  loginUserVoteInfo: [VoteInfo];
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

function PostContent() {
  const { userInfo } = useUserInfoStore(state => state);
  const { isLoginInStore } = useIsLoginStore(state => state);
  const navigate = useNavigate();
  const [isPending, setIsPending] = useState(true);
  const [listData, setListData] = useState<Data | Record<string, never>>({});
  const [textContent, setTextContent] = useState<string>('');
  const [checkState, setCheckState] = useState<boolean>(false);
  const [uploadImages, setUploadImages] = useState<string[] | []>([]);
  const [comDivIsOpen, setComDivIsOpen] = useState<boolean>(false);
  const [voteTotal, SetVoteTotal] = useState<number>(0);
  const [isVoteStatus, SetIsVoteStatus] = useState<string | null>('');
  const [voteInfo, setVoteInfo] = useState<VoteInfo | Record<string, never>>(
    {},
  );
  const idData = Number(useParams().id);

  let calTime = '';
  if (!isPending) {
    calTime = CalElapsedTime(listData.createdAt);
  }

  const openComDivHandler = (e: React.MouseEvent<HTMLButtonElement>) => {
    setComDivIsOpen(!comDivIsOpen);
  };

  const fetchPostDetail = async () => {
    try {
      const buffer = await getPostDetail('qnas/questions', idData);
      setListData(buffer.data);
      SetVoteTotal(buffer.data.voteCount);
      if (buffer.data.loginUserVoteInfo) {
        setVoteInfo(buffer.data.loginUserVoteInfo);
        if (buffer.data.loginUserVoteInfo.questionvoteStatus) {
          SetIsVoteStatus(buffer.data.loginUserVoteInfo.questionvoteStatus);
        }
      }
      setIsPending(false);
    } catch (err) {
      console.error(err);
    }
  };

  const fetchDeletePost = async () => {
    try {
      const confirm = window.confirm('게시글을 삭제하시겠습니까?');
      if (confirm) {
        await DeletePost('qnas/questions', idData);
        // alert('게시물을 삭제하였습니다.');
        navigate('/qna');
      }
    } catch (err) {
      console.error(err);
    }
  };

  const postHandler = async () => {
    try {
      if (textContent === '') {
        alert('답변 내용은 빈 칸으로 둘 수 없습니다.');
      } else {
        const data = {
          content: textContent,
          uploadImages,
          createdAt: `${new Date()}`,
          questionId: listData.questionId,
        };
        console.log('submit data', data);
        await PostAnswer(data);
        await setTextContent('');
        await setCheckState(!checkState);
        window.scrollTo(0, 0);
      }
    } catch (err) {
      console.error(err);
    }
  };

  const voteHandler = async (value: string) => {
    try {
      const res = await PostVote('qnas/questions', idData, value);
      await SetVoteTotal(res.data.questionVoteTotalCount);
      await SetIsVoteStatus(res.data.status);
    } catch (err) {
      console.error(err);
    }
  };

  useEffect(() => {
    fetchPostDetail();
  }, [checkState]);

  return (
    <Container>
      <GoBackMenu />
      {isPending ? (
        <NoData>LOADING...</NoData>
      ) : (
        <div>
          <TitleDiv>
            <Top>
              <CategoryDiv>
                <Category>{listData.category}</Category>
                {listData.adoptAnswerId ? (
                  <Category className="seleted">답변채택</Category>
                ) : null}
              </CategoryDiv>
              {listData.member.memberId === userInfo.memberId ? (
                <UDBtnDiv>
                  <Link to="edit">
                    <Button.UDWhiteBtn>수정</Button.UDWhiteBtn>
                  </Link>
                  <Button.UDWhiteBtn onClick={fetchDeletePost}>
                    삭제
                  </Button.UDWhiteBtn>
                </UDBtnDiv>
              ) : null}
            </Top>
            <H2>{listData.title}</H2>
            <Writer>
              <ProfileIcon.Default />
              <NameDiv>
                {listData.member.displayName}
                {listData.member.state === 'TEACHER' ? (
                  <StateIcon.Teacher title="강사" />
                ) : null}
                {listData.member.state === 'ADMIN' ? (
                  <StateIcon.Admin title="관리자" />
                ) : null}
              </NameDiv>
              <div> · {calTime}</div>
              <View>
                <CountIcon.View />
                {listData.viewCount}
              </View>
            </Writer>
          </TitleDiv>
          <MainDiv>
            <TextDiv dangerouslySetInnerHTML={{ __html: listData.content }} />
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
              {listData.commentCount === 0 ? null : (
                <CommentViewDiv>
                  {listData.comments.map((ele: Comment) => {
                    return (
                      <CommentBlock
                        key={ele.qnaCommentId}
                        data={ele}
                        checkState={checkState}
                        setCheckState={setCheckState}
                        // voteInfo={voteInfo}
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
                    <WriteComment
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
          <AnswerCnt>{listData.answerCount}개의 답변</AnswerCnt>
          {listData.category === '공지' ? (
            <PostAnswerDiv>
              <NoticeDiv>공지글에는 답변을 작성할 수 없습니다.</NoticeDiv>
            </PostAnswerDiv>
          ) : (
            <div>
              <AnswerContainer>
                {listData.answerCount === 0 ? null : (
                  <div>
                    {listData.answers.map((ele: AnswerData) => {
                      return (
                        <AnswerContent
                          key={ele.answerId}
                          data={ele}
                          checkState={checkState}
                          setCheckState={setCheckState}
                          questionWriter={listData.member.memberId}
                        />
                      );
                    })}
                  </div>
                )}
              </AnswerContainer>
              <PostAnswerDiv>
                <Label htmlFor="post">답변 작성하기</Label>
                <TextEditorDiv id="post">
                  {isLoginInStore ? (
                    <TextEditor
                      textContent={textContent}
                      setTextContent={setTextContent}
                      uploadImages={uploadImages}
                      setUploadImages={setUploadImages}
                      path="boards/qnas/answers/contents"
                    />
                  ) : (
                    <GuideDiv>
                      <AiOutlineExclamationCircle />
                      <div>
                        답변을 쓰려면 <Link to="/login">로그인</Link>이
                        필요합니다.
                      </div>
                    </GuideDiv>
                  )}
                </TextEditorDiv>
                <BtnDiv>
                  {isLoginInStore ? (
                    <Button.WriteBtn onClick={postHandler}>
                      확인
                    </Button.WriteBtn>
                  ) : (
                    <Button.WriteBtn className="disabled">확인</Button.WriteBtn>
                  )}
                </BtnDiv>
              </PostAnswerDiv>
            </div>
          )}
          {/* <CommentCnt>{listData.commentsListNum}개의 댓글</CommentCnt>
          <CommentContainer>
            <WriteCommentDiv>
              <WriteComment />
            </WriteCommentDiv>
            {listData.commentsListNum === 0 ? null : (
              <div>
                {listData.comments.map((ele: Comment) => {
                  return <CommentBlock key={ele.freeCommentId} data={ele} />;
                })}
              </div>
            )}
          </CommentContainer> */}
        </div>
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

const NameDiv = styled.div`
  margin: 0 3px;
`;

const View = styled.div`
  display: flex;
  position: absolute;
  right: 0;
  bottom: 0;
`;

const CategoryDiv = styled.div`
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

const VoteDiv = styled.div`
  display: flex;
  justify-content: right;
  margin-bottom: ${theme.gap.px60};
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

const AnswerContainer = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
`;

const TextEditorDiv = styled.div`
  display: flex;
  justify-content: center;
  width: 100%;
`;

const PostAnswerDiv = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  padding: ${theme.gap.px20};
  margin-bottom: ${theme.gap.px40};
`;

const Label = styled.label`
  margin-bottom: ${theme.gap.px20};
`;

const GuideDiv = styled.div`
  display: flex;
  width: 100%;
  height: 73px;
  padding: ${theme.gap.px10};
  border: 1px solid ${theme.colors.gray};
  /* border-radius: 5px; */
  color: ${theme.colors.gray};
  /* margin-left: ${theme.gap.px10}; */
  a {
    font-weight: bold;
    text-decoration: underline;
    color: ${theme.colors.pointColor};
  }
  > div {
    margin-left: 6px;
  }
`;

const BtnDiv = styled.div`
  display: flex;
  justify-content: right;
  padding: ${theme.gap.px20} 0;
  margin-bottom: ${theme.gap.px80};
  > div {
    display: flex;
    justify-content: space-between;
    width: 200px;
  }
`;

const NoticeDiv = styled.div`
  margin-bottom: ${theme.gap.px60};
`;

export default PostContent;

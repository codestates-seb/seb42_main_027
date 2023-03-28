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
import getPostDetail from 'apis/board/getPostDetail';
import PostVote from 'apis/board/postVote';
import CalElapsedTime from '../post/calElapsedTime';

import GoBackMenu from '../post/goBackMenu';
import WriteComment from '../comment/writeComment';
import CommentBlock from './commentBlock';

interface Data {
  freeId: number;
  title: 'string';
  content: 'string';
  uploadImages?: string[] | [];
  category: 'string';
  viewCount: number;
  voteCount: number;
  createdAt: string;
  modifiedAt?: string;
  commentsListNum: number;
  member: {
    memberId: number;
    iconImageUrl?: string;
    displayName: string;
    state: string;
  };
  comments: [];
}

interface Comment {
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

function PostContent() {
  const { userInfo } = useUserInfoStore(state => state);
  const navigate = useNavigate();
  const [isPending, setIsPending] = useState(true);
  const [listData, setListData] = useState<Data | Record<string, never>>({});
  const [checkState, setCheckState] = useState<boolean>(false);
  const urlData = useLocation().pathname.slice(0, 5);
  const idData = Number(useParams().id);

  let calTime = '';
  if (!isPending) {
    calTime = CalElapsedTime(listData.createdAt);
  }

  const voteHandler = async (value: string) => {
    try {
      await PostVote('frees', idData, value);
      setCheckState(!checkState);
    } catch (err) {
      console.error(err);
    }
  };

  const fetchPostDetail = async () => {
    try {
      const buffer = await getPostDetail('frees', idData);
      setListData(buffer.data);
      setIsPending(false);
    } catch (err) {
      console.error(err);
    }
  };

  const fetchDeletePost = async () => {
    try {
      const confirm = window.confirm('게시글을 삭제하시겠습니까?');
      if (confirm) {
        await DeletePost('frees', idData);
        alert('게시물을 삭제하였습니다.');
        navigate('/free');
      }
    } catch (err) {
      console.error(err);
    }
  };

  useEffect(() => {
    fetchPostDetail();
  }, [checkState]);

  console.log(listData);
  console.log('userInfo memeberId', userInfo.memberId);

  return (
    <Container>
      <GoBackMenu />
      {isPending ? (
        <NoData>LOADING...</NoData>
      ) : (
        <div>
          <TitleDiv>
            <Top>
              <Category>{listData.category}</Category>
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
              <Button.VoteDownBtn onClick={e => voteHandler('down')}>
                <CountIcon.VoteDown />
              </Button.VoteDownBtn>
              <VoteCount>{listData.voteCount}</VoteCount>
              <Button.VoteUpBtn onClick={e => voteHandler('up')}>
                <CountIcon.VoteUp />
              </Button.VoteUpBtn>
            </VoteDiv>
          </MainDiv>
          <CommentCnt>{listData.commentsListNum}개의 댓글</CommentCnt>
          <CommentContainer>
            <WriteCommentDiv>
              <WriteComment
                checkState={checkState}
                setCheckState={setCheckState}
              />
            </WriteCommentDiv>
            {listData.commentsListNum === 0 ? null : (
              <div>
                {listData.comments.map((ele: Comment) => {
                  return (
                    <CommentBlock
                      key={ele.freeCommentId}
                      data={ele}
                      checkState={checkState}
                      setCheckState={setCheckState}
                    />
                  );
                })}
              </div>
            )}
          </CommentContainer>
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

const NoData = styled.div`
  display: flex;
  width: 100%;
  justify-content: center;
  align-items: center;
  padding-top: ${theme.gap.px60};
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

const CommentContainer = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  min-height: 253px;
  margin-bottom: ${theme.gap.px100};
`;

const WriteCommentDiv = styled.div`
  display: flex;
  width: 100%;
  padding-bottom: calc(${theme.gap.px60} + 7px);
  border-bottom: 1px solid ${theme.colors.gray};
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

export default PostContent;

import styled from 'styled-components';
import useUserInfoStore from 'stores/userInfoStore';
import { useEffect, useState } from 'react';
import UserCard from 'components/myPage/UserCard';
import getFreePosts from 'apis/getFreePosts';
import getComment from 'apis/getComment';
import CountIcon from 'assets/icons/countIcon';
import { Link } from 'react-router-dom';
import theme from '../theme';

const { colors } = theme;
const { fontSizes } = theme;

const Container = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
`;

const ListContainer = styled.div`
  display: flex;
  align-items: center;
  justify-content: space-between;
  width: 80%;
`;

const PostListContainer = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 45%;
`;

const PostListTitleContainer = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 100%;
`;

const List = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  height: 20rem;
  overflow: auto;
`;

const ListItem = styled.div`
  display: flex;
  flex-direction: column;
  position: relative;
  width: 100%;
  padding: 1rem ${theme.gap.px20};
  border-bottom: 1px solid ${theme.colors.gray};

  &.notice {
    background-color: ${theme.colors.palePurple};
  }
`;

// 제목 컴포넌트
const Title = styled.h2`
  font-size: 1rem;
`;

// 카테고리 컴포넌트
const Category = styled.span`
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

// 조회수 및 투표수 컴포넌트

const Top = styled.div`
  display: flex;
  margin-bottom: 4px;
`;

const Bottom = styled.div`
  display: flex;
  justify-content: space-between;
`;

const Count = styled.div`
  flex: 1;
  display: flex;
  justify-content: center;
  align-items: center;
  color: ${colors.gray};
  svg {
    margin: 0 0.3rem;
  }
`;

const Content = styled.p`
  font-size: 0.9rem;
  width: 100%;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
`;

const ContentContainer = styled.div`
  flex: 10;
  width: 60%;
`;

const SourcesInfo = styled.p`
  margin-top: 0.1rem;
  font-size: 0.8rem;
  color: ${colors.fontColor};
  display: flex;
`;

const Source = styled(Link)`
  font-size: 0.8rem;
  color: ${colors.pointColor};
`;

const CommentSource = styled.p`
  font-size: 0.8rem;
  color: black;
  //! 글자수 제한 필요
`;

type PostProps = {
  question: {
    questionId: number;
    title: string;
  };
  freeId: number;
  questionId: number;
  lectureReviewId: number;
  answerId: number;
  title: string;
  content: string;
  category: string;
  viewCount: number;
  voteCount: number;
  createdAt: string;
  modifiedAt: null;
  member: {
    memberId: number;
    displayName: string;
    iconImageUrl: null;
    state: string;
  };
  commentsListNum: number;
};

type CommentsProps = {
  qnaComment: {
    qnaCommentId: number;
    content: string;
    modifiedAt: string;
    voteCount: number;
    answer: {
      answerId: number;
      content: string;
    };
  };
  freeComment: {
    freeCommentId: number;
    content: string;
  };
  lectureReview: {
    lectureReviewId: number;
    title: string;
  };
  question: {
    questionId: number;
    title: string;
  };
  free: {
    freeId: number;
    title: string;
  };
  freeReCommentId: number;
  freeCommentId: number;
  qnaCommentId: number;
  lectureReviewCommentId: number;
  qnaReCommentId: number;
  title: string;
  content: string;
  category: string;
  viewCount: number;
  voteCount: number;
  createdAt: string;
  modifiedAt: null;
  member: {
    memberId: number;
    displayName: string;
    iconImageUrl: null;
    state: string;
  };
  commentsListNum: number;
};

function MyPage() {
  const { userInfo } = useUserInfoStore(state => state);
  const [freePosts, setFreePosts] = useState([]);
  const [freeComments, setFreeComments] = useState([]);
  const [selectPostCategories, setSelectPostCategories] =
    useState('자유 게시판');
  const [selectCommentCategories, setSelectCommentCategories] =
    useState('자유 게시판');

  const patchFreePosts = async (id: number | null, select: string) => {
    try {
      // 테스트용 memberId 사용
      const response = await getFreePosts(id, select);
      setFreePosts(response);
    } catch (error) {
      console.error(error);
    }
  };

  const patchComments = async (id: number | null, select: string) => {
    try {
      // 테스트용 memberId 사용
      const response = await getComment(id, select);
      setFreeComments(response);
    } catch (error) {
      console.error(error);
    }
  };

  const handleChangeBoard = (e: React.ChangeEvent<HTMLSelectElement>) => {
    setFreePosts([]);
    setSelectPostCategories(e.target.value);
  };

  const handleChangeCommentCategorie = (
    e: React.ChangeEvent<HTMLSelectElement>,
  ) => {
    setFreeComments([]);
    setSelectCommentCategories(e.target.value);
  };

  useEffect(() => {
    patchFreePosts(userInfo.memberId, selectPostCategories);
    patchComments(userInfo.memberId, selectCommentCategories);
  }, [selectPostCategories, selectCommentCategories, userInfo]);

  return (
    <Container>
      <UserCard />
      <ListContainer>
        <PostListContainer>
          <PostListTitleContainer>
            <h2>내가 작성한 게시글</h2>
            <select onChange={handleChangeBoard} value={selectPostCategories}>
              <option value="자유 게시판">자유 게시판</option>
              <option value="질문 게시판">질문 게시판</option>
              <option value="답변 게시판">답변 게시판</option>
              <option value="강의 리뷰">강의 리뷰</option>
            </select>
          </PostListTitleContainer>
          <List>
            {freePosts.map((post: PostProps) => {
              function selectKey(select: string) {
                if (select === '질문 게시판') {
                  return post.questionId;
                }
                if (select === '답변 게시판') {
                  return post.answerId;
                }
                if (select === '강의 리뷰') {
                  return post.lectureReviewId;
                }
                return post.freeId;
              }

              return (
                <ListItem key={selectKey(selectPostCategories)}>
                  {post.category ? (
                    <Top>
                      <Category>{post.category}</Category>
                    </Top>
                  ) : null}
                  <Bottom>
                    {post.title ? (
                      <ContentContainer>
                        <Title>{post.title}</Title>
                        <Content>{post.content}</Content>
                      </ContentContainer>
                    ) : (
                      <Content>{post.content}</Content>
                    )}
                    <Count>
                      {post.viewCount ? (
                        <>
                          <CountIcon.View />({post.viewCount})
                        </>
                      ) : null}
                      <CountIcon.Vote />
                      {post.voteCount}
                    </Count>
                  </Bottom>
                  {selectPostCategories === '답변 게시판' ? (
                    <SourcesInfo>
                      <Source to={`/qna/articles/${post.question?.questionId}`}>
                        {post.question?.title}
                      </Source>
                      에 남긴 답변
                    </SourcesInfo>
                  ) : null}
                </ListItem>
              );
            })}
          </List>
        </PostListContainer>

        <PostListContainer>
          <PostListTitleContainer>
            <h2>내가 작성한 댓글</h2>
            <select
              onChange={handleChangeCommentCategorie}
              value={selectCommentCategories}
            >
              <option value="자유 게시판">자유 게시판</option>
              <option value="답변 게시글">답변 댓글</option>
              <option value="강의 리뷰">강의 리뷰</option>
              <option value="자유 대댓글">자유게시판 대댓글</option>
              <option value="질문답변 대댓글">질문답변 대댓글</option>
            </select>
          </PostListTitleContainer>
          <List>
            {freeComments.map((comment: CommentsProps) => {
              function selectKey(select: string) {
                if (select === '답변 게시글') {
                  return comment.qnaCommentId;
                }
                if (select === '강의 리뷰') {
                  return comment.lectureReviewCommentId;
                }
                if (select === '자유 대댓글') {
                  return comment.freeReCommentId;
                }
                if (select === '질문답변 대댓글') {
                  return comment.qnaReCommentId;
                }
                if (select === '자유 게시판') {
                  return comment.freeCommentId;
                }
                return comment.freeCommentId;
              }

              return (
                <ListItem key={selectKey(selectCommentCategories)}>
                  <Bottom>
                    <Content>{comment.content}</Content>
                    <Count>
                      <CountIcon.Vote />
                      {comment.voteCount}
                    </Count>
                  </Bottom>

                  {selectCommentCategories === '자유 게시판' ? (
                    <SourcesInfo>
                      <Source to={`/free/articles/${comment.free?.freeId}`}>
                        {comment.free?.title}
                      </Source>
                      에 남긴 답변
                    </SourcesInfo>
                  ) : null}

                  {selectCommentCategories === '답변 게시글' &&
                  comment.question ? (
                    <SourcesInfo>
                      <Source
                        to={`/qna/articles/${comment.question?.questionId}`}
                      >
                        {comment.question?.title}
                      </Source>
                      에 남긴 답변
                    </SourcesInfo>
                  ) : null}

                  {selectCommentCategories === '강의 리뷰' ? (
                    <SourcesInfo>
                      <Source
                        to={`/qna/articles/${comment.lectureReview?.lectureReviewId}`}
                      >
                        {comment.lectureReview?.title}
                      </Source>
                      에 남긴 답변
                    </SourcesInfo>
                  ) : null}

                  {selectCommentCategories === '자유 대댓글' ? (
                    <SourcesInfo>
                      <CommentSource>
                        {comment.freeComment?.content}
                      </CommentSource>
                      에 남긴 답변
                    </SourcesInfo>
                  ) : null}

                  {selectCommentCategories === '질문답변 대댓글' ? (
                    <SourcesInfo>
                      {comment.qnaComment?.content}에 남긴 답변
                    </SourcesInfo>
                  ) : null}
                </ListItem>
              );
            })}
          </List>
        </PostListContainer>
      </ListContainer>
    </Container>
  );
}

export default MyPage;

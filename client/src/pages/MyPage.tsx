import styled from 'styled-components';
import useUserInfoStore from 'stores/userInfoStore';
import { useEffect, useState } from 'react';
import UserCard from 'components/myPage/UserCard';
import getFreePosts from 'apis/getFreePosts';
import getComment from 'apis/getComment';
import CountIcon from 'assets/icons/countIcon';
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
  margin: 0;
  font-size: ${fontSizes.md};
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
const Count = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  color: ${colors.gray};
  svg {
    margin: 0 0.3rem;
  }
`;

const Top = styled.div`
  display: flex;
  margin-bottom: 4px;
`;

const Bottom = styled.div`
  display: flex;
  justify-content: space-between;
`;

type PostProps = {
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
    setSelectPostCategories(e.target.value);
  };
  const handleChangeCommentCategorie = (
    e: React.ChangeEvent<HTMLSelectElement>,
  ) => {
    setSelectCommentCategories(e.target.value);
  };

  useEffect(() => {
    patchFreePosts(userInfo.memberId, selectPostCategories);
    patchComments(2, selectCommentCategories);
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
                    {post.title ? <Title>{post.title}</Title> : null}

                    <Count>
                      <CountIcon.View />
                      {post.viewCount}
                      <CountIcon.Vote />
                      {post.voteCount}
                    </Count>
                  </Bottom>
                </ListItem>
              );
            })}
          </List>
        </PostListContainer>

        <PostListContainer>
          <PostListTitleContainer>
            <h2>내가 작성한 게시글</h2>
            <select
              onChange={handleChangeCommentCategorie}
              value={selectCommentCategories}
            >
              <option value="자유 게시판">자유 게시판</option>
              <option value="답변 게시글">답변 댓글</option>
              <option value="강의 리뷰">강의 리뷰</option>
              <option value="대댓글">대댓글</option>
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
                if (select === '대댓글') {
                  return comment.qnaReCommentId;
                }
                return comment.freeCommentId;
              }

              return (
                <ListItem key={selectKey(selectCommentCategories)}>
                  <Bottom>
                    <Title>{comment.content}</Title>
                    <Count>
                      <CountIcon.Vote />
                      {comment.voteCount}
                    </Count>
                  </Bottom>
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

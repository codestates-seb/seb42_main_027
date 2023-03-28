import CountIcon from 'assets/icons/countIcon';
import { useState } from 'react';
import { Link } from 'react-router-dom';
import useUserInfoStore from 'stores/userInfoStore';
import styled from 'styled-components';
import theme from 'theme';

const { colors } = theme;

const PostListContainer = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 45%;
  margin-top: 8rem;
  margin-left: 6rem;
  border: 0.15rem solid ${colors.pointColor};
  border-radius: 1rem;
  background-color: white;
  padding: 1rem;
  width: 45rem;

  @media screen and (max-width: 1439px) {
    width: 27rem;
  }
`;

const PostListTitleContainer = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 100%;
  border-bottom: 0.1rem solid ${colors.gray};
  padding-bottom: 0.5rem;
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

// 조회수 및 투표수 컴포넌트

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
const ContentContainer = styled.div`
  flex: 10;
  width: 60%;
`;

const Top = styled.div`
  display: flex;
  margin-bottom: 4px;
`;

const StyleSelect = styled.select`
  width: 8rem;
  border-radius: 0.2rem;
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

type PostListProps = {
  freePosts: never[];
  setFreePosts: React.Dispatch<React.SetStateAction<never[]>>;
  selectPostCategories: string;
  setSelectPostCategories: React.Dispatch<React.SetStateAction<string>>;
};

function PostList({
  freePosts,
  setFreePosts,
  selectPostCategories,
  setSelectPostCategories,
}: PostListProps) {
  const handleChangeBoard = (e: React.ChangeEvent<HTMLSelectElement>) => {
    setFreePosts([]);
    setSelectPostCategories(e.target.value);
  };

  return (
    <>
      {/* 내가 작성한 게시글 */}
      <PostListContainer>
        <PostListTitleContainer>
          <h2>내가 작성한 게시글</h2>
          <StyleSelect
            onChange={handleChangeBoard}
            value={selectPostCategories}
          >
            <option value="자유 게시판">자유 게시판</option>
            <option value="질문 게시판">질문 게시판</option>
            <option value="답변 게시판">답변 게시판</option>
            <option value="강의 리뷰">강의 리뷰</option>
          </StyleSelect>
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
                      <Title>
                        {selectPostCategories === '자유 게시판' ? (
                          <Link to={`/free/articles/${post.freeId}`}>
                            {post.title}
                          </Link>
                        ) : null}
                        {selectPostCategories === '질문 게시판' ? (
                          <Link to={`/qna/articles/${post.questionId}`}>
                            {post.title}
                          </Link>
                        ) : null}
                        {selectPostCategories === '답변 게시판'
                          ? post.title
                          : null}
                        {selectPostCategories === '강의 리뷰' ? (
                          <Link
                            to={`/lecturereviewdetail/${post.lectureReviewId}`}
                          >
                            {post.title}
                          </Link>
                        ) : null}
                      </Title>
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
    </>
  );
}

export default PostList;

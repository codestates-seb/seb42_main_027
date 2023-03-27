import CountIcon from 'assets/icons/countIcon';
import { useState } from 'react';
import { Link } from 'react-router-dom';
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
  width: 50rem;

  @media screen and (max-width: 1919px) {
    width: 30rem;
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

const CommentSource = styled.p`
  font-size: 0.8rem;
  color: black;
  //! 글자수 제한 필요
`;

const StyleSelect = styled.select`
  width: 8rem;
  border-radius: 0.2rem;
`;

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
type CommentListProps = {
  selectCommentCategories: string;
  setSelectCommentCategories: React.Dispatch<React.SetStateAction<string>>;
};

function CommentList({
  selectCommentCategories,
  setSelectCommentCategories,
}: CommentListProps) {
  const [freeComments, setFreeComments] = useState([]);

  const handleChangeCommentCategorie = (
    e: React.ChangeEvent<HTMLSelectElement>,
  ) => {
    setFreeComments([]);
    setSelectCommentCategories(e.target.value);
  };

  return (
    <>
      <PostListContainer>
        <PostListTitleContainer>
          <h2>내가 작성한 댓글</h2>
          <StyleSelect
            onChange={handleChangeCommentCategorie}
            value={selectCommentCategories}
          >
            <option value="자유 게시판">자유 게시판</option>
            <option value="답변 게시글">답변 댓글</option>
            <option value="강의 리뷰">강의 리뷰</option>
            <option value="자유 대댓글">자유게시판 대댓글</option>
            <option value="질문답변 대댓글">질문답변 대댓글</option>
          </StyleSelect>
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
      ;
    </>
  );
}
export default CommentList;

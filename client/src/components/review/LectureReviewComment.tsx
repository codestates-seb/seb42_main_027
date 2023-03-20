/* eslint-disable react/button-has-type */
/* eslint-disable react/require-default-props */
import GlobalStyle from 'GlobalStyles';
import styled from 'styled-components';
import { FlexContainer } from 'pages/review/ReviewPage';
import { Link } from 'react-router-dom';
import isLogin from 'utils/isLogin';
import { SmallFont } from 'pages/review/TeacherDetail/Information';
import { useState } from 'react';
import axios from 'axios';
import {
  BsFillHandThumbsUpFill,
  BsFillHandThumbsDownFill,
} from 'react-icons/bs';

type Props = {
  lectureReviewCommentId: number;
  content: string;
  createdAt: string;
  modifiedAt: string;
  voteCount: number;
  member: {
    memberId: number;
    displayName: string;
    IconImageUrl: string;
    state: string; // 강사, 학생, 관리자
  };
};

function LectureReviewComment({
  lectureReviewCommentId,
  content,
  createdAt,
  modifiedAt,
  voteCount,
  member,
}: Props) {
  const [commentVote, setCommentVote] = useState(voteCount);
  const [voteStatus, setVoteStatus] = useState('');
  const Authorization = localStorage.getItem('token');

  const commentUpHandler = () => {
    axios
      .post(
        `${process.env.REACT_APP_API_URL}/votes/reviews/lectures/comments/${lectureReviewCommentId}/up`,
        {
          headers: { Authorization },
        },
      )
      .then(res => res.data.data)
      .then(data => {
        setCommentVote(commentVote + 1);
        setVoteStatus(data.status);
      });
  };
  const commentDownHandler = () => {
    axios
      .post(
        `${process.env.REACT_APP_API_URL}/votes/reviews/lectures/comments/${lectureReviewCommentId}/down`,
        {
          headers: { Authorization },
        },
      )
      .then(res => res.data.data)
      .then(data => {
        setCommentVote(commentVote - 1);
        setVoteStatus(data.status);
      });
  };

  return (
    <Container>
      <FlexContainer width="100%" align="start" dir="col" gap="0.2rem">
        <span>{member.displayName}</span>
        <span>{content}</span>
        <FlexContainer
          width="100%"
          justify="space-between"
          align="end"
          padding="1rem 0 0 0"
        >
          <VerySmallGrayFont>{createdAt}</VerySmallGrayFont>
          <FlexContainer>
            <BsFillHandThumbsUpFill size="1rem" onClick={commentUpHandler} />
            {commentVote}
            <BsFillHandThumbsDownFill
              size="1rem"
              onClick={commentDownHandler}
            />
          </FlexContainer>
        </FlexContainer>
      </FlexContainer>
    </Container>
  );
}

export default LectureReviewComment;

type Container = {
  first?: boolean;
};

const Container = styled.div<Container>`
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 0.5rem;
  padding: 1rem;
  border: 1px solid black;
  background-color: #b9b9b9;
`;

const VerySmallGrayFont = styled.div`
  font-size: small;
  color: gray;
`;

/* eslint-disable no-unused-expressions */
/* eslint-disable react/button-has-type */
/* eslint-disable react/require-default-props */
import GlobalStyle from 'GlobalStyles';
import styled from 'styled-components';
import { FlexContainer } from 'pages/review/ReviewPage';
import { Link, useParams } from 'react-router-dom';
import isLogin from 'utils/isLogin';
import { useState, useEffect } from 'react';
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
  vStatus: { lectureReviewCommentId: number; voteStatus: string }[];
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
  vStatus,
  modifiedAt,
  voteCount,
  member,
}: Props) {
  const [commentVote, setCommentVote] = useState(voteCount);
  const [updateContent, setUpdateContent] = useState<string>('');
  const [isOpen, setIsOpen] = useState<boolean>(false);
  const { teacherId } = useParams();

  console.log(!teacherId);

  const voStatus = vStatus.filter(
    el => el.lectureReviewCommentId === lectureReviewCommentId,
  );
  let tmp: string | (() => string);
  !voStatus.length ? (tmp = 'NONE') : (tmp = voStatus[0].voteStatus);

  const [voteStatus, setVoteStatus] = useState(tmp);
  const Authorization = localStorage.getItem('token');

  // useEffect(() => {
  //   console.log(tmp, voteStatus);
  // }, [voteStatus]);

  const updateOpenHandler = () => {
    setIsOpen(!isOpen);
  };
  const updateHandler = () => {
    if (!updateContent) alert('내용을 입력하하세요');
    else {
      axios
        .patch(
          `${process.env.REACT_APP_API_URL}/comments/reviews/lectures/${lectureReviewCommentId}`,
          {
            content: updateContent,
            createdAt: new Date(),
            lectureReviewCommentId,
          },
          {
            headers: {
              Authorization,
              'ngrok-skip-browser-warning': '69420',
            },
          },
        )
        .then(() => {
          window.location.reload();
        });
    }
  };
  const commentUpHandler = () => {
    axios
      .post(
        `${process.env.REACT_APP_API_URL}/votes/reviews/lectures/comments/${lectureReviewCommentId}/up`,
        {},
        {
          headers: { Authorization, 'ngrok-skip-browser-warning': '69420' },
        },
      )
      .then(res => res.data.data)
      .then(data => {
        setCommentVote(data.lectureReviewCommentTotalCount);
        setVoteStatus(data.status);
      });
  };
  const commentDownHandler = () => {
    axios
      .post(
        `${process.env.REACT_APP_API_URL}/votes/reviews/lectures/comments/${lectureReviewCommentId}/down`,
        {},
        {
          headers: { Authorization, 'ngrok-skip-browser-warning': '69420' },
        },
      )
      .then(res => res.data.data)
      .then(data => {
        setCommentVote(data.lectureReviewCommentTotalCount);
        setVoteStatus(data.status);
      });
  };

  return (
    <Container>
      <FlexContainer width="100%" align="start" dir="col" gap="0.2rem">
        <FlexContainer width="100%" justify="space-between" padding="0 0.2rem">
          <VerySmallGrayFont>{member.displayName}</VerySmallGrayFont>
          <FlexContainer>
            <UpButton voteStatus={voteStatus} onClick={commentUpHandler}>
              <BsFillHandThumbsUpFill size="1rem" />
            </UpButton>
            {commentVote}
            <DownButton voteStatus={voteStatus} onClick={commentDownHandler}>
              <BsFillHandThumbsDownFill size="1rem" />
            </DownButton>
          </FlexContainer>
        </FlexContainer>
        {isOpen ? (
          <Textarea
            value={updateContent}
            placeholder="수정 내용 입력"
            onChange={e => {
              setUpdateContent(e.target.value);
            }}
          />
        ) : (
          <ContentBox>{content}</ContentBox>
        )}
        <FlexContainer
          width="100%"
          justify="space-between"
          align="end"
          padding="1rem 0 0 0"
        >
          <VerySmallGrayFont>{createdAt.slice(0, 10)}</VerySmallGrayFont>
        </FlexContainer>
        {!teacherId && isLogin() ? (
          <FlexContainer width="100%" justify="right">
            {isOpen ? (
              <FlexContainer gap="0.8rem">
                <Ubutton onClick={updateHandler}>확인</Ubutton>
                <Ubutton
                  onClick={() => {
                    setIsOpen(!isOpen);
                  }}
                >
                  취소
                </Ubutton>
              </FlexContainer>
            ) : (
              <FlexContainer gap="0.8rem">
                <Ubutton onClick={updateOpenHandler}>수정</Ubutton>
                <Ubutton
                  onClick={() => {
                    axios
                      .delete(
                        `${process.env.REACT_APP_API_URL}/comments/reviews/lectures/${lectureReviewCommentId}`,
                        {
                          headers: {
                            Authorization,
                            'ngrok-skip-browser-warning': '69420',
                          },
                        },
                      )
                      .then(() => {
                        window.location.reload();
                      });
                  }}
                >
                  삭제
                </Ubutton>
              </FlexContainer>
            )}
          </FlexContainer>
        ) : null}
      </FlexContainer>
    </Container>
  );
}

export default LectureReviewComment;

type Container = {
  first?: boolean;
};

type Button = {
  voteStatus?: string;
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
  font-weight: bold;
`;

const UpButton = styled.button<Button>`
  border: none;
  pointer-events: ${props => (props.voteStatus === 'DOWN' ? 'none' : 'all')};
  background-color: #b9b9b9;
  color: ${props => (props.voteStatus === 'UP' ? '#f48224' : 'black')};
`;

const DownButton = styled.button<Button>`
  border: none;
  pointer-events: ${props => (props.voteStatus === 'UP' ? 'none' : 'all')};
  background-color: #b9b9b9;
  color: ${props => (props.voteStatus === 'DOWN' ? '#f48224' : 'black')};
`;

const ContentBox = styled.div`
  width: 100%;
  padding: 1rem 0;
`;

const Textarea = styled.textarea`
  width: 100%;
  height: 6rem;
  padding: 1rem;
`;

const Ubutton = styled.button`
  padding: 0.4rem;
  background-color: gray;
  color: white;
  border-radius: 0.4rem;
`;

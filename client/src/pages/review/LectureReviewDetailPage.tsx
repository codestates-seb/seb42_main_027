/* eslint-disable react/no-array-index-key */
/* eslint-disable react/button-has-type */
/* eslint-disable react/require-default-props */
import GlobalStyle from 'GlobalStyles';
import styled from 'styled-components';
import { FlexContainer } from 'pages/review/ReviewPage';
import {
  BsFillHandThumbsUpFill,
  BsFillHandThumbsDownFill,
} from 'react-icons/bs';
import { AiFillStar } from 'react-icons/ai';
import { useEffect, useState } from 'react';
import axios from 'axios';
import Loading from 'components/review/Loading';
import LectureReviewComment from 'components/review/LectureReviewComment';
import { useParams, useNavigate } from 'react-router';
import ReviewCommentCreate from 'components/review/ReviewCommentCreate';
import { Link } from 'react-router-dom';
import isLogin from 'utils/isLogin';

const defaultDetailData = {
  lectureReviewId: 1,
  title: '디폴트 제목',
  starPoint: 5,
  content: '디폴트 내용',
  createdAt: '2023.03.10',
  modifiedAt: '2023.03.11',
  viewCount: 12,
  voteCount: 14,
  teacher: {
    teacherId: 1,
    name: '디폴트 선생명',
    starPointAverage: 5.0,
  },
  lecture: {
    lectureId: 1,
    title: '디폴트 강의명',
    starPointAverage: 5.0,
  },

  member: {
    memberId: 0,
    displayName: '디폴트 작성자 멤버명',
    IconImageUrl: '디폴트 작성자 멤버 이미지 url',
    state: '학생', // 강사, 학생, 관리자
  },

  comments: [
    {
      lectureReviewCommentId: 0,
      content: '디폴트 답글내용',
      createdAt: '2023.03.10',
      modifiedAt: '2023.03.11',
      voteCount: 0,
      member: {
        memberId: 1,
        displayName: '디폴트 답글 멤버명',
        IconImageUrl: '디폴트 답글 멤버 이미지 url',
        state: '강사', // 강사, 학생, 관리자
      },
    },
  ],
  loginUserLectureReviewVoteInfo: {
    commentVoteStatus: [{ lectureReviewCommentId: 1, voteStatus: 'DOWN' }],
    lectureReviewVoteStatus: {
      lectureReviewId: 1,
      voteStatus: 'DOWN',
      memberId: 1,
      username: '테스트계정',
    },
  },
};

function LectureReviewDetailPage() {
  const [detailData, setDetailData] = useState(defaultDetailData);
  const [isPending, setIsPending] = useState(true);
  const [reviewVote, setReviewVote] = useState(detailData.voteCount);
  const [voteStatus, setVoteStatus] = useState('');

  const { lectureReviewId } = useParams();
  const Authorization = localStorage.getItem('token');
  const navigate = useNavigate();

  useEffect(() => {
    setIsPending(true);
    axios
      .get(
        `${process.env.REACT_APP_API_URL}/boards/reviews/lectures/${lectureReviewId}`,
        {
          headers: { Authorization, 'ngrok-skip-browser-warning': '69420' },
        },
      )
      .then((res: any) => {
        return res.data.data;
      })
      .then(data => {
        console.log(data);
        setDetailData(data);
        setReviewVote(data.voteCount);
        if (
          data.loginUserLectureReviewVoteInfo &&
          data.loginUserLectureReviewVoteInfo.lectureReviewVoteStatus
        ) {
          setVoteStatus(
            data.loginUserLectureReviewVoteInfo.lectureReviewVoteStatus
              .voteStatus,
          );
        } else {
          setVoteStatus('NONE');
        }

        setIsPending(false);
      });
  }, [lectureReviewId]);

  const reviewUpHandler = () => {
    axios
      .post(
        `${process.env.REACT_APP_API_URL}/votes/reviews/lectures/${lectureReviewId}/up`,
        {},
        {
          headers: { Authorization, 'ngrok-skip-browser-warning': '69420' },
        },
      )
      .then(res => res.data.data)
      .then(data => {
        // setReviewVote(data.voteCount);
        setReviewVote(data.lectureReviewTotalCount);
        setVoteStatus(data.status);
      });
  };
  const reviewDownHandler = () => {
    axios
      .post(
        `${process.env.REACT_APP_API_URL}/votes/reviews/lectures/${lectureReviewId}/down`,
        {},
        {
          headers: { Authorization, 'ngrok-skip-browser-warning': '69420' },
        },
      )
      .then(res => res.data.data)
      .then(data => {
        // setReviewVote(data.voteCount);
        setReviewVote(data.lectureReviewTotalCount);
        setVoteStatus(data.status);
      });
  };

  return (
    <Container>
      {isPending ? (
        <Loading />
      ) : (
        <FlexContainer
          width="50rem"
          dir="col"
          backColor="white"
          justify="start"
        >
          <FlexContainer
            width="100%"
            justify="space-between"
            padding="1.5rem 4rem"
            borderTop="2px solid black"
            borderBottom="1px solid black"
            gap="0.2rem"
            backColor="#6667ab"
          >
            <TitleSpan>{detailData.title}</TitleSpan>
            <FlexContainer>
              <NameSpan>{detailData.member.displayName}</NameSpan>
              <NameSpan>조회수: {detailData.viewCount}</NameSpan>
            </FlexContainer>
          </FlexContainer>
          <FlexContainer
            display={isLogin() ? 'flex' : 'none'}
            width="80%"
            justify="right"
            padding="1rem 0 0 0"
          >
            <Link
              to={`/lecturereviewdetail/${lectureReviewId}/update/${detailData.lecture.lectureId}`}
            >
              <button>수정</button>
            </Link>
            <button
              onClick={() => {
                axios
                  .delete(
                    `${process.env.REACT_APP_API_URL}/boards/reviews/lectures/${lectureReviewId}`,
                    {
                      headers: {
                        Authorization,
                        'ngrok-skip-browser-warning': '69420',
                      },
                    },
                  )
                  .then(() => {
                    navigate(-1);
                  });
              }}
            >
              삭제
            </button>
          </FlexContainer>
          <FlexContainer width="100%" dir="col" padding="1rem">
            <FlexContainer
              width="90%"
              justify="space-between"
              borderTop="1px solid gray"
              borderBottom="1px solid gray"
              padding="1rem 0"
            >
              <FlexContainer dir="col" width="40%">
                <FlexContainer>{detailData.teacher.name}</FlexContainer>
                <FlexContainer gap="0.2rem">
                  <AiFillStar color="gold" size="1.4rem" />
                  <span>{detailData.starPoint.toFixed(1)}</span>
                </FlexContainer>
              </FlexContainer>
              <FlexContainer dir="col" width="60%">
                <span>{detailData.lecture.title}</span>
                <FlexContainer gap="1rem">
                  <FlexContainer gap="0.2rem">
                    전체:
                    <AiFillStar color="gold" size="1.4rem" />
                    {detailData.lecture.starPointAverage.toFixed(1)}
                  </FlexContainer>
                  <FlexContainer gap="0.2rem">
                    내 평점:
                    <AiFillStar color="gold" size="1.4rem" />
                    {detailData.starPoint}
                  </FlexContainer>
                </FlexContainer>
              </FlexContainer>
            </FlexContainer>
          </FlexContainer>

          <FlexContainer width="100%" justify="start" padding="1rem 5rem">
            {detailData.content}
          </FlexContainer>

          <FlexContainer width="100%" justify="right" padding="0.4rem 5rem">
            <FlexContainer>
              <UpButton voteStatus={voteStatus} onClick={reviewUpHandler}>
                <BsFillHandThumbsUpFill size="1.5rem" />
              </UpButton>
              {reviewVote}
              <DownButton voteStatus={voteStatus} onClick={reviewDownHandler}>
                <BsFillHandThumbsDownFill size="1.5rem" />
              </DownButton>
            </FlexContainer>
          </FlexContainer>

          <FlexContainer
            dir="col"
            width="100%"
            justify="start"
            align="start"
            padding="3rem 5rem"
          >
            <span>답변</span>
            {Authorization ? (
              <ReviewCommentCreate lectureReviewId={Number(lectureReviewId)} />
            ) : null}
            {detailData.comments.map((el, index) => {
              return (
                <LectureReviewComment
                  key={index}
                  vStatus={
                    detailData.loginUserLectureReviewVoteInfo
                      ? detailData.loginUserLectureReviewVoteInfo
                          .commentVoteStatus
                      : [{ lectureReviewCommentId: -1, voteStatus: 'DOWN' }]
                  }
                  lectureReviewCommentId={el.lectureReviewCommentId}
                  content={el.content}
                  createdAt={el.createdAt}
                  modifiedAt={el.modifiedAt}
                  voteCount={el.voteCount}
                  member={el.member}
                />
              );
            })}
          </FlexContainer>
        </FlexContainer>
      )}
    </Container>
  );
}

export default LectureReviewDetailPage;

type Container = {
  reviewOpen?: boolean;
};

type Button = {
  voteStatus?: string;
};

const Container = styled.div<Container>`
  width: 100%;
  background-color: rgba(0, 0, 0, 0.1);
  padding: 3rem;

  display: flex;
  justify-content: center;
  align-items: center;
  gap: 0.5rem;
`;

const UpButton = styled.button<Button>`
  border: none;
  pointer-events: ${props => (props.voteStatus === 'DOWN' ? 'none' : 'all')};
  background-color: white;
  color: ${props => (props.voteStatus === 'UP' ? '#f48224' : 'black')};
`;

const DownButton = styled.button<Button>`
  border: none;
  pointer-events: ${props => (props.voteStatus === 'UP' ? 'none' : 'all')};
  background-color: white;
  color: ${props => (props.voteStatus === 'DOWN' ? '#f48224' : 'black')};
`;

const TitleSpan = styled.span`
  color: white;
  font-size: large;
`;

const NameSpan = styled.span`
  color: white;
  font-size: 0.9rem;
`;

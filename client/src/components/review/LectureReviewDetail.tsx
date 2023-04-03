/* eslint-disable react/no-array-index-key */
/* eslint-disable react/button-has-type */
/* eslint-disable react/require-default-props */
import styled from 'styled-components';
import { FlexContainer } from 'pages/review/TeacherList/ReviewPage';

import { AiFillStar } from 'react-icons/ai';
import { useEffect, useState } from 'react';
import axios from 'axios';
import theme from 'theme';
import { Link } from 'react-router-dom';
import Button from 'components/common/Button';
import CountIcon from 'assets/icons/countIcon';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import LectureReviewComment from './LectureReviewComment';
import Loading from './Loading';

type Props = {
  lectureReviewId: number;
  reviewOpen: boolean;
  setReviewOpen: React.Dispatch<React.SetStateAction<boolean>>;
};

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

function LectureReviewDetail({
  lectureReviewId,
  reviewOpen,
  setReviewOpen,
}: Props) {
  const [detailData, setDetailData] = useState(defaultDetailData);
  const [isPending, setIsPending] = useState(true);
  const [reviewVote, setReviewVote] = useState(detailData.voteCount);
  const [voteStatus, setVoteStatus] = useState('');

  const Authorization = localStorage.getItem('token');

  const up = () => toast.success('UP!');
  const down = () => toast.error('DOWN!');
  const cancle = () => toast.info('Cancel!');
  const login = () => toast.info('Login!');

  useEffect(() => {
    if (lectureReviewId < 0) return;
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

  const closeHandler = () => {
    setReviewOpen(false);
  };

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
        setReviewVote(data.lectureReviewVoteTotalCount);
        setVoteStatus(data.status);
        if (data.status === 'UP') up();
        else if (data.status === 'NONE') cancle();
      })
      .catch(() => {
        login();
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
        setReviewVote(data.lectureReviewVoteTotalCount);
        setVoteStatus(data.status);
        if (data.status === 'DOWN') down();
        else if (data.status === 'NONE') cancle();
      })
      .catch(() => {
        login();
      });
  };

  return (
    <Container reviewOpen={reviewOpen} onClick={closeHandler}>
      <ToastContainer pauseOnHover autoClose={1000} />
      {isPending ? (
        <Loading />
      ) : (
        <ModalContainer onClick={e => e.stopPropagation()}>
          <FlexContainer
            width="100%"
            justify="space-between"
            padding="2rem 4rem"
            borderTop="2px solid black"
            borderBottom="1px solid black"
            gap="0.2rem"
            backColor="#6667ab"
          >
            <TitleSpan>{detailData.title}</TitleSpan>
            <FlexContainer>
              <SubSpan>{detailData.member.displayName}</SubSpan>
              <SubSpan>
                <CountIcon.View /> {detailData.viewCount}
              </SubSpan>
            </FlexContainer>
          </FlexContainer>

          {/* 강사 평가 */}
          <FlexContainer
            width="100%"
            justify="space-between"
            borderBottom={`1px solid  ${theme.colors.gray}`}
            padding="1rem 0"
          >
            <FlexContainer dir="col" width="50%">
              <FlexContainer>{detailData.teacher.name}</FlexContainer>
              <FlexContainer gap="0.2rem">
                강사 평균:
                <AiFillStar color="gold" size="1.4rem" />
                <span>{detailData.starPoint.toFixed(1)}</span>
              </FlexContainer>
            </FlexContainer>
            <FlexContainer dir="col" width="50%">
              <span>{detailData.lecture.title}</span>
              <FlexContainer gap="1rem">
                <FlexContainer gap="0.2rem">
                  강의 평점:
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
          {/* 리뷰 내용 */}
          <FlexContainer
            width="100%"
            dir="col"
            justify="start"
            padding="1rem 5rem"
          >
            <FlexContainer
              width="100%"
              dir="col"
              borderBottom="0.5px solid gray"
              padding="0 0 1rem 0"
            >
              <IntroSpan>해당 페이지는 읽기 전용입니다</IntroSpan>
              <OriginalSpan>
                <Link to={`/lecturereviewdetail/${lectureReviewId}`}>
                  (원본 게시물로 이동)
                </Link>
              </OriginalSpan>
            </FlexContainer>

            <ContentBox
              dangerouslySetInnerHTML={{ __html: detailData.content }}
            />
          </FlexContainer>
          {/* 리뷰 추천 */}
          <FlexContainer width="100%" justify="right" padding="1rem 5rem">
            <FlexContainer gap="0">
              <DownButton voteStatus={voteStatus} onClick={reviewDownHandler}>
                <Button.VoteDownBtn>
                  <CountIcon.VoteDown />
                </Button.VoteDownBtn>
              </DownButton>
              <VoteCount>{reviewVote}</VoteCount>
              <UpButton voteStatus={voteStatus} onClick={reviewUpHandler}>
                <Button.VoteUpBtn>
                  <CountIcon.VoteUp />
                </Button.VoteUpBtn>
              </UpButton>
            </FlexContainer>
          </FlexContainer>

          <FlexContainer
            dir="col"
            width="100%"
            justify="start"
            align="start"
            padding="1rem 5rem"
          >
            {detailData.comments.length ? <IntroSpan>답변</IntroSpan> : null}

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
        </ModalContainer>
      )}
    </Container>
  );
}

export default LectureReviewDetail;

type Container = {
  reviewOpen?: boolean;
};

type Button = {
  voteStatus?: string;
};

const Container = styled.div<Container>`
  width: 100%;
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  z-index: 12;
  background-color: rgba(0, 0, 0, 0.3);

  display: ${props => (props.reviewOpen ? 'flex' : 'none')};
  justify-content: center;
  align-items: center;
  gap: 0.5rem;
`;

const ModalContainer = styled.div`
  width: 50rem;
  height: 45rem;

  background-color: white;

  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);

  display: flex;
  flex-direction: column;
  justify-content: start;
  align-items: center;

  overflow-y: auto;
  gap: 0.4rem;
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

const ContentBox = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
  justify-content: start;
  align-items: start;
  padding: 3rem 1rem;
  gap: 0.4rem;

  img {
    max-width: 90%;
  }
`;

const TitleSpan = styled.span`
  font-size: large;
  font-weight: bold;
  color: white;
`;

const SubSpan = styled.span`
  font-size: medium;
  color: white;
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 0.2rem;
`;

const IntroSpan = styled.span`
  font-size: medium;
  font-weight: bold;
  color: gray;
`;

const OriginalSpan = styled.span`
  font-size: medium;
  font-weight: bold;
  color: #3d9eff;
  :hover {
    color: red;
  }
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

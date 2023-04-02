/* eslint-disable react/no-array-index-key */
/* eslint-disable react/button-has-type */
/* eslint-disable react/require-default-props */

import styled from 'styled-components';
import { FlexContainer } from 'pages/review/TeacherList/ReviewPage';
import { AiFillStar } from 'react-icons/ai';
import { useEffect, useState } from 'react';
import axios from 'axios';
import Loading from 'components/review/Loading';
import LectureReviewComment from 'components/review/LectureReviewComment';
import { useParams, useNavigate } from 'react-router';
import ReviewCommentCreate from 'components/review/ReviewCommentCreate';
import { Link } from 'react-router-dom';
import useUserInfoStore from 'stores/userInfoStore';
import theme from 'theme';
import GoBackMenu from 'components/board/post/goBackMenu';
import Button from 'components/common/Button';
import CountIcon from 'assets/icons/countIcon';
import Swal from 'sweetalert2';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { Title } from 'pages/FreeBoard';

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
  const { userInfo } = useUserInfoStore(state => state);
  const Authorization = localStorage.getItem('token');
  const navigate = useNavigate();

  const up = () => toast.success('UP!');
  const down = () => toast.error('DOWN!');
  const cancle = () => toast.info('Cancel!');
  const login = () => toast.info('Login!');

  useEffect(() => {
    window.scrollTo(0, 0);
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
      })
      .catch(() => {
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
    <Container>
      <ToastContainer pauseOnHover autoClose={1000} />
      {isPending ? (
        <Loading />
      ) : (
        <FlexContainer
          width="62.5%"
          dir="col"
          backColor="white"
          justify="start"
        >
          <Title>
            <H2>리뷰게시판</H2>
            <p>객관적인 리뷰를 볼 수 있는 공간입니다.</p>
          </Title>
          {/* 수정 삭제 */}
          <FlexContainer
            display={
              userInfo.memberId === detailData.member.memberId ? 'flex' : 'none'
            }
            width="100%"
            justify="right"
          >
            <Link
              to={`/lecturereviewdetail/${lectureReviewId}/update/${detailData.lecture.lectureId}`}
            >
              <button>수정</button>
            </Link>
            <button
              onClick={() => {
                Swal.fire({
                  title: '리뷰를 삭제하시겠습니까?',
                  text: '다시 되돌릴 수 없습니다. 신중하세요.',
                  icon: 'warning',

                  showCancelButton: true, // cancel버튼 보이기. 기본은 원래 없음
                  confirmButtonColor: '#3085d6', // confrim 버튼 색깔 지정
                  cancelButtonColor: '#d33', // cancel 버튼 색깔 지정
                  confirmButtonText: '승인', // confirm 버튼 텍스트 지정
                  cancelButtonText: '취소', // cancel 버튼 텍스트 지정

                  reverseButtons: true, // 버튼 순서 거꾸로
                }).then(result => {
                  // 만약 Promise리턴을 받으면,
                  if (result.isConfirmed) {
                    // 만약 모달창에서 confirm 버튼을 눌렀다면
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
                  }
                });
              }}
            >
              삭제
            </button>
          </FlexContainer>
          <GoBackMenu />
          {/* 리뷰 제목 */}
          <TitleDiv>
            <H2>{detailData.title}</H2>
            <FlexContainer>
              <NameSpan>{detailData.member.displayName}</NameSpan>
              <NameSpan>
                <CountIcon.View /> {detailData.viewCount}
              </NameSpan>
            </FlexContainer>
          </TitleDiv>
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
          <ContentBox
            dangerouslySetInnerHTML={{ __html: detailData.content }}
          />
          {/* 추천수 */}
          <FlexContainer width="100%" justify="right" padding="0.4rem 1.5rem">
            {/* 추천수 버튼 */}
            <FlexContainer gap="0" width="100%" justify="right">
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
          {/* 댓글 */}
          <FlexContainer
            dir="col"
            width="100%"
            justify="center"
            align="center"
            padding="3rem 1rem"
          >
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
  toggle?: boolean;
};

type Button = {
  voteStatus?: string;
};

const Container = styled.div<Container>`
  width: 100%;
  background-color: ${props => (props.toggle ? 'gray' : 'white')};
  display: flex;
  flex-direction: column;
  justify-content: start;
  align-items: center;
  gap: 0.5rem;
  transition: all 0.5s ease;
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

const NameSpan = styled.span`
  color: black;
  font-size: 0.9rem;
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 0.2rem;
`;

const H2 = styled.h2`
  font-weight: bold;
  font-size: ${theme.fontSizes.subTitle};
  margin-bottom: ${theme.gap.px10};
`;

const TitleDiv = styled.div`
  width: 100%;
  display: flex;

  justify-content: space-between;
  padding: ${theme.gap.px20};
  border-bottom: 1px solid ${theme.colors.gray};
`;

const ContentBox = styled.div`
  width: 100%;
  min-height: 200px;
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

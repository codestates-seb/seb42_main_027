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
import LectureReviewComment from './LectureReviewComment';
import ReviewCommentCreate from './ReviewCommentCreate';

type Props = {
  lectureReviewId: number;
  reviewOpen: boolean;
  setReviewOpen: React.Dispatch<React.SetStateAction<boolean>>;
};

const defaultDetailData = {
  lectureReviewId: 0,
  title: '디폴트 제목',
  starPoint: 5,
  content:
    '디폴트 내용 주저리주저리주저리주저리주저 리주저리주저리주저리주저 리주저리주저리 주저리주저리주 저리주저리주저리주저리주저리주저리 리주저리주저리주저리주저리주저리주저리주저리주저리주저리주저리주저리주저리주저리주저리주저리주저리주저리주저리주저리주저리주저리주저리주저리주저리주저리주저리주저리주저리주저리주저리주저리주저리주저리주저리주저리주저',
  createdAt: '2023.03.10',
  modifiedAt: '2023.03.11',
  viewCount: 0,
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
    {
      lectureReviewCommentId: 1,
      content: '디폴트 답글내용2',
      createdAt: '2023.03.10',
      modifiedAt: '2023.03.11',
      voteCount: 12,
      member: {
        memberId: 2,
        displayName: '디폴트 답글 멤버명2',
        IconImageUrl: '디폴트 답글 멤버 이미지 url2',
        state: '강사', // 강사, 학생, 관리자
      },
    },
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
};

function LectureReviewDetail({
  lectureReviewId,
  reviewOpen,
  setReviewOpen,
}: Props) {
  const [detailData, setDetailData] = useState(defaultDetailData);
  const [reviewVote, setReviewVote] = useState(detailData.voteCount);
  const [voteStatus, setVoteStatus] = useState('');

  useEffect(() => {
    // axios
    //   .get(
    //     `http://13.125.1.215:8080/boards/reviews/lectures/${lectureReviewId}`,
    //   )
    //   .then((res: any) => {
    //     return res.data.data;
    //   })
    //   .then(data => {
    //     console.log(data);
    //     setDetailData(data);
    //   });
  }, [lectureReviewId]);

  const closeHandler = () => {
    setReviewOpen(false);
  };

  const reviewUpHandler = () => {
    axios
      .post(
        `http://13.125.1.215:8080/votes/reviews/lectures/${lectureReviewId}/up`,
        {
          header: {
            Authorization:
              'Bearer eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJBRE1JTiIsIlVTRVIiXSwidXNlcm5hbWUiOiJhQGdtYWlsLmNvbSIsInN1YiI6ImFAZ21haWwuY29tIiwiaWF0IjoxNjc5MTQ0ODc2LCJleHAiOjE2NzkxNzAwNzZ9.06r-zPdih5j5xgQ2FWlEFx3pd3XsEvhkHgv01Zt_Fm0',
          },
        },
      )
      .then(res => res.data.data)
      .then(data => {
        setReviewVote(reviewVote + 1);
        setVoteStatus(data.status);
      });
  };
  const reviewDownHandler = () => {
    axios
      .post(
        `http://13.125.1.215:8080/votes/reviews/lectures/${lectureReviewId}/down`,
        {
          header: {
            Authorization:
              'Bearer eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJBRE1JTiIsIlVTRVIiXSwidXNlcm5hbWUiOiJhQGdtYWlsLmNvbSIsInN1YiI6ImFAZ21haWwuY29tIiwiaWF0IjoxNjc5MTQ0ODc2LCJleHAiOjE2NzkxNzAwNzZ9.06r-zPdih5j5xgQ2FWlEFx3pd3XsEvhkHgv01Zt_Fm0',
          },
        },
      )
      .then(res => res.data.data)
      .then(data => {
        setReviewVote(reviewVote - 1);
        setVoteStatus(data.status);
      });
  };

  return (
    <Container reviewOpen={reviewOpen} onClick={closeHandler}>
      <ModalContainer onClick={e => e.stopPropagation()}>
        <FlexContainer
          width="100%"
          height="100%"
          justify="space-between"
          padding="1.5rem 2rem"
          borderTop="2px solid black"
          borderBottom="1px solid black"
          gap="0.2rem"
          backColor="#6667ab"
        >
          <span>{detailData.title}</span>
          <FlexContainer>
            <span>{detailData.member.displayName}</span>
            <span>조회수: {detailData.viewCount}</span>
          </FlexContainer>
        </FlexContainer>

        <FlexContainer width="100%" dir="col" padding="2rem">
          <FlexContainer
            width="90%"
            justify="space-between"
            borderTop="1px solid gray"
            borderBottom="1px solid gray"
            padding="1rem 6rem"
          >
            <FlexContainer dir="col">
              <span>강사:{detailData.teacher.name}</span>
              <span>
                <AiFillStar />
                {detailData.starPoint}
              </span>
            </FlexContainer>
            <FlexContainer dir="col">
              <span>강좌:{detailData.lecture.title}</span>
              <FlexContainer gap="3rem">
                <span>
                  전체:
                  <AiFillStar />
                  {detailData.lecture.starPointAverage}
                </span>
                <span>
                  내 평점:
                  <AiFillStar />
                  {detailData.starPoint}
                </span>
              </FlexContainer>
            </FlexContainer>
          </FlexContainer>
        </FlexContainer>

        <FlexContainer width="100%" justify="start" padding="1rem 5rem">
          {detailData.content}
        </FlexContainer>

        <FlexContainer width="100%" justify="right" padding="1rem 5rem">
          <FlexContainer>
            <BsFillHandThumbsUpFill size="1rem" onClick={reviewUpHandler} />
            {reviewVote}
            <BsFillHandThumbsDownFill size="1rem" onClick={reviewDownHandler} />
          </FlexContainer>
        </FlexContainer>

        <FlexContainer
          dir="col"
          width="100%"
          justify="start"
          align="start"
          padding="1rem 5rem"
        >
          <span>답변</span>
          <ReviewCommentCreate lectureReviewId={lectureReviewId} />
          {detailData.comments.map((el, index) => {
            return (
              <LectureReviewComment
                key={index}
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
    </Container>
  );
}

export default LectureReviewDetail;

type Container = {
  reviewOpen?: boolean;
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
`;

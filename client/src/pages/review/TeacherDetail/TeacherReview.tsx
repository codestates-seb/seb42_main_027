/* eslint-disable react/no-array-index-key */
import GlobalStyle from 'GlobalStyles';
import { useEffect, useState } from 'react';
import axios from 'axios';
import { useParams } from 'react-router';
import styled from 'styled-components';
import ScoreChart from 'components/review/ScoreChart';
import LectureReview from 'components/review/LectureReview';
import isLogin from 'utils/isLogin';
import Button from 'components/common/Button';
import LectureReviewDetail from 'components/review/LectureReviewDetail';
import { FlexContainer } from '../ReviewPage';
import { MiddleFont, SmallFont } from './Information';

type Data = {
  teacherId: number;
  name: string;
  introduction: string;
  imageUrl: string;
  profile: string[];
  analects: string[];
  starPointAverage: number;
  totalReviewCount: number;
  starPointCount: {
    '1점갯수': number;
    '5점갯수': number;
    '4점갯수': number;
    '2점갯수': number;
    '3점갯수': number;
  };
  gradeTags: { gradeTag: string }[];
  subjectTags: { subjectTag: string }[];

  platformTags: { platformTag: string }[];

  lectures: {
    lectureId: number;
    title: string;
    status: string;
    lectureReviews: {
      lectureReviewId: number;
      title: string;
      starPoint: number;
      content: string;
      createdAt: string;
      modifiedAt: string;
      viewCount: number;
      voteCount: number;
      teacher: {
        teacherId: number;
        name: string;
        starPointAverage: number;
      };
      lecture: {
        lectureId: number;
        title: string;
        starPointAverage: number;
      };
      member: {
        memberId: number;
        email: string;
        displayName: string;
        password: string;
        iconImageUrl: string;
        createdAt: string;
        roles: string[];
        memberStatus: string;
      };
    }[];
  }[];
};

const defaultData = {
  teacherId: 1,
  name: '홍길동',
  introduction: '홍길동 강사 간단 소개',
  imageUrl: '이미지 url',
  profile: [
    '서울대학교 국어국문학과 학사',
    '전 메가스터디 국어 강사',
    '현 이투스 국어 강사',
    '간단 경력 리스트 입력',
  ],
  analects: [
    '잠은 죽어서 자야한다',
    '자가진단 빨리하자',
    '14급 15급 공무원 하면 딱 맞을새끼들 빠가새끼들 빨리때리치워',
    '어록 리스트 입력',
  ],
  starPointAverage: 4.2,
  totalReviewCount: 17,
  starPointCount: {
    '1점갯수': 1,
    '5점갯수': 12,
    '4점갯수': 3,
    '2점갯수': 1,
    '3점갯수': 0,
  },
  gradeTags: [
    {
      gradeTag: '고1',
    },
    {
      gradeTag: '고2',
    },
    {
      gradeTag: '고3',
    },
    {
      gradeTag: '예비고1',
    },
    {
      gradeTag: '예비고2',
    },
    {
      gradeTag: '예비고3',
    },
  ],
  subjectTags: [
    {
      subjectTag: '국어',
    },
    {
      subjectTag: '한국사',
    },
  ],
  platformTags: [
    {
      platformTag: '이투스',
    },
    {
      platformTag: 'EBS',
    },
  ],

  lectures: [
    {
      lectureId: 1,
      title: '강의 타이틀명!',
      status: '진행중',
      lectureReviews: [
        {
          lectureReviewId: 1,
          title: '이 강의 추천합니다!',
          starPoint: 5,
          content: '강의하시는데 ~~궁 ~~궁 해서 추천합니다',
          createdAt: '2023.03.10.18:52:36',
          modifiedAt: '2023.03.10.18:52:36',
          viewCount: 1,
          voteCount: 0,
          totalCommentCount: 0,
          teacher: {
            teacherId: 1,
            name: '홍길동',
            starPointAverage: 0.0,
          },
          lecture: {
            lectureId: 1,
            title: '강의 이름!',
            starPointAverage: 0.0,
          },
          member: {
            memberId: 1,
            email: 'ghdrlfehd@gmail.com',
            displayName: '홍길동',
            password: '1111',
            iconImageUrl: 'IconUrl',
            createdAt: '2023.03.10.18:52:36',
            roles: [],
            memberStatus: 'MEMBER_ACTIVE',
          },
        },
      ],
    },
  ],
};

function TeacherReview() {
  const [data, setData] = useState(defaultData);
  const [isPending, setIsPending] = useState<boolean>(true);
  const [reviewOpen, setReviewOpen] = useState<boolean>(false);
  const [lectureReviewId, setLectureReviewId] = useState<number>(-1);
  const { teacherId } = useParams();

  const Authorization = localStorage.getItem('token');

  useEffect(() => {
    axios
      .get(`${process.env.REACT_APP_API_URL}/teachers/${teacherId}/reviews`, {
        headers: { 'ngrok-skip-browser-warning': '69420' },
      })
      .then((res: any) => {
        return res.data.data;
      })
      .then(data => {
        console.log(data);
        setData(data);
        setIsPending(false);
      });
  }, []);

  const list = ['추천', '만족도', '제목', '작성자', '등록일'];

  const reviewCreateHandler = () => {
    axios.post(
      `${process.env.REACT_APP_API_URL}/boards/reviews/lectures`,
      {
        title: '리뷰글 제목',
        starPoint: 5,
        content: '리뷰글 내용',
        lectureId: 64,
        createdAt: '생성날짜(String)',
      },
      {
        headers: { Authorization },
      },
    );
  };

  return (
    <Container
      height={
        data.lectures.length &&
        data.lectures
          .map(lecture => {
            return lecture.lectureReviews.length;
          })
          .reduce((acc, cur) => acc + cur) < 6
          ? '100vh'
          : '100%'
      }
    >
      <LectureReviewDetail
        reviewOpen={reviewOpen}
        setReviewOpen={setReviewOpen}
        lectureReviewId={lectureReviewId}
      />
      {isPending ? (
        <FlexContainer height="100vh" />
      ) : (
        <FlexContainer dir="col">
          {!data.lectures.length ? (
            <FlexContainer height="100vh">
              등록된 강의와 후기가 없습니다
            </FlexContainer>
          ) : (
            <FlexContainer width="50rem" dir="col" gap="1rem">
              <ScoreChart
                totalReviewCount={data.totalReviewCount}
                starPointAverage={data.starPointAverage}
                starPointCount={data.starPointCount}
                lectures={data.lectures}
              />

              <FlexContainer
                width="100%"
                borderTop="2px solid black"
                borderBottom="1px solid black"
                padding="1.5rem 1rem"
              >
                {list.map((el, index) => {
                  return (
                    <FlexContainer key={index} grow={el === '제목' ? 5 : 1}>
                      <SmallFont>{el}</SmallFont>
                    </FlexContainer>
                  );
                })}
                <MiddleFont />
              </FlexContainer>
              {!data.lectures.filter(lecture => {
                return lecture.lectureReviews.length;
              }).length ? (
                <FlexContainer dir="col" width="100%" gap="0">
                  <div>등록된 수강 후기가 없습니다</div>
                </FlexContainer>
              ) : (
                data.lectures.map((lecture, index) => {
                  return (
                    <FlexContainer width="100%" gap="0" dir="col" key={index}>
                      {lecture.lectureReviews.map((el, index) => {
                        return (
                          <LectureReview
                            key={index}
                            setReviewOpen={setReviewOpen}
                            setLectureReviewId={setLectureReviewId}
                            lectureReviewId={el.lectureReviewId}
                            title={el.title}
                            starPoint={el.starPoint}
                            createdAt={el.createdAt}
                            voteCount={el.voteCount}
                            lecture={el.lecture}
                            member={el.member}
                            totalCommentCount={el.totalCommentCount}
                          />
                        );
                      })}
                    </FlexContainer>
                  );
                })
              )}
            </FlexContainer>
          )}
        </FlexContainer>
      )}
    </Container>
  );
}

export default TeacherReview;

const PButton = Button.PointBtn;

type Container = {
  height?: string;
};
const Container = styled.div<Container>`
  height: ${props => props.height};

  padding: 3rem;
`;

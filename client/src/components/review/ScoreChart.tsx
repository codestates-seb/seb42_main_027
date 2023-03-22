/* eslint-disable consistent-return */
/* eslint-disable radix */
/* eslint-disable react/no-array-index-key */
import GlobalStyle from 'GlobalStyles';
import styled from 'styled-components';
import { FlexContainer } from 'pages/review/ReviewPage';
import {
  BigFont,
  MiddleFont,
  SmallFont,
} from 'pages/review/TeacherDetail/Information';

type Props = {
  starPointAverage: number;
  totalReviewCount: number;
  starPointCount: {
    [key: string]: number;
    '1점갯수': number;
    '5점갯수': number;
    '4점갯수': number;
    '2점갯수': number;
    '3점갯수': number;
  };
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

function ScoreChart({
  totalReviewCount,
  starPointAverage,
  starPointCount,
  lectures,
}: Props) {
  const scoreArr = [1, 2, 3, 4, 5];
  const totalStarCount = scoreArr.reduce((acc, cur) => {
    return acc + starPointCount[`${cur}점갯수`];
  }, 0);

  // const BestReview = lectures.map(el => {
  //   return el.lectureReviews.length ? el.lectureReviews[0].content : 'none';
  // });

  return (
    <FlexContainer>
      <ChartBox>
        <SmallFont>수강만족도</SmallFont>
        <BigFont>{starPointAverage.toFixed(1)}</BigFont>
        <BigFont>⭐️⭐️⭐️⭐️⭐️</BigFont>
        <SmallFont>{`${totalReviewCount} Reviews`}</SmallFont>
      </ChartBox>
      <ChartBox leftBorder align="start">
        {scoreArr.map((el, index) => {
          return (
            <FlexContainer key={index}>
              <SmallFont>{`${el}점`}</SmallFont>
              <ScoreBox />
              <SmallFont>
                {totalStarCount ? `${starPointCount[`${el}점갯수`]}` : '0'}
              </SmallFont>
            </FlexContainer>
          );
        })}
      </ChartBox>
      <ChartBox>
        <FlexContainer dir="col" height="100%">
          {lectures.map((el, index) => {
            if (index > 2) return; // 강의 리뷰는 3개까지 뿌림
            return (
              <FlexContainer key={index}>
                <SmallFont>
                  {el.lectureReviews.length
                    ? `⭐️${el.lectureReviews[0].starPoint}`
                    : null}
                </SmallFont>
                <SmallFont>
                  {el.lectureReviews.length
                    ? `${el.lectureReviews[0].content.slice(0, 7)}...`
                    : null}
                </SmallFont>
              </FlexContainer>
            );
          })}
        </FlexContainer>
      </ChartBox>
    </FlexContainer>
  );
}

export default ScoreChart;

type ChartBox = {
  leftBorder?: boolean;
  align?: string;
};

const ChartBox = styled.div<ChartBox>`
  padding: 0 2rem;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: ${props => props.align || 'center'};
  gap: 0.8rem;
  border-left: ${props => (props.leftBorder ? '1px solid gray' : '')};
`;

const ScoreBox = styled.div<ChartBox>`
  width: 170px;
  height: 30px;
  border-radius: 1rem;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  background-color: gray;
`;

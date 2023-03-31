/* eslint-disable no-nested-ternary */
/* eslint-disable consistent-return */
/* eslint-disable radix */
/* eslint-disable react/no-array-index-key */

import styled from 'styled-components';
import { FlexContainer } from 'pages/review/TeacherList/ReviewPage';
import { BigFont, SmallFont } from 'pages/review/TeacherDetail/Information';
import { BsStarFill, BsStar, BsStarHalf } from 'react-icons/bs';

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
    <FlexContainer gap="0">
      {/* 강의 만족도 */}
      <ChartBox>
        <SmallFont>수강만족도</SmallFont>
        <BigFont>{starPointAverage.toFixed(1)}</BigFont>
        <BigFont>
          {scoreArr.map((el, index) => {
            return el <= Number(starPointAverage.toFixed(1)) ? (
              <BsStarFill key={index} color="gold" />
            ) : el - 0.5 <= Number(starPointAverage.toFixed(1)) ? (
              <BsStarHalf key={index} color="gold" />
            ) : (
              <BsStar key={index} color="gold" />
            );
          })}
        </BigFont>
        <SmallFont>{`${totalReviewCount} Reviews`}</SmallFont>
      </ChartBox>
      {/* 그래프 */}
      <ChartBox leftBorder align="start">
        {scoreArr.map((el, index) => {
          const score = starPointCount[`${el}점갯수`];
          return (
            <FlexContainer key={index}>
              <ScoreFont>{`${el}점`}</ScoreFont>
              <ScoreBox
                percent={
                  starPointCount[`${el}점갯수`]
                    ? (score / totalStarCount) * 100
                    : 0
                }
              />
              <ScoreFont>{totalStarCount ? `${score}` : '0'}</ScoreFont>
            </FlexContainer>
          );
        })}
      </ChartBox>
      {/* 리뷰 3개 */}
      <ChartBox align="start">
        <FlexContainer dir="col" align="start">
          {lectures.map((el, index) => {
            if (index > 4) return; // 강의 리뷰는 5개까지 뿌림
            return (
              <FlexContainer key={index} gap="0.6rem">
                <VerySmallFont>
                  {el.lectureReviews.length ? (
                    <BsStarFill color="gold" />
                  ) : null}
                  {el.lectureReviews.length
                    ? `${el.lectureReviews[0].starPoint}`
                    : null}
                </VerySmallFont>
                <VerySmallFont>
                  {el.lectureReviews.length
                    ? `${
                        el.lectureReviews[0].title.length > 13
                          ? `${el.lectureReviews[0].title.slice(0, 13)}...`
                          : el.lectureReviews[0].title
                      }`
                    : null}
                </VerySmallFont>
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

type ScoreBox = {
  percent?: number;
};

const ChartBox = styled.div<ChartBox>`
  width: 20rem;
  padding: 0 2rem;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: ${props => props.align || 'center'};
  gap: 0.8rem;
  border-left: ${props => (props.leftBorder ? '1px solid gray' : '')};

  @media screen and (min-width: 1500px) {
    width: 22rem;
  }
`;

const ScoreBox = styled.div<ScoreBox>`
  width: 170px;
  height: 30px;
  border-radius: 1rem;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  background: ${props =>
    props.percent
      ? `linear-gradient(to right, #6667ab ${props.percent}% , #b2b2b2 0%)`
      : '#b2b2b2'};
`;

const VerySmallFont = styled.span`
  font-size: 0.8rem;
  display: flex;
  justify-content: center;
  align-items: center;

  gap: 0.3rem;
`;

const ScoreFont = styled.span`
  width: 2rem;
  color: gray;
  font-weight: bold;
  display: flex;
  justify-content: center;
  align-items: center;
`;

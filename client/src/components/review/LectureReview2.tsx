/* eslint-disable react/button-has-type */
/* eslint-disable react/require-default-props */
import GlobalStyle from 'GlobalStyles';
import styled from 'styled-components';
import { FlexContainer } from 'pages/review/ReviewPage';
import { SmallFont } from 'pages/review/TeacherDetail/Information';
import { BsFillHandThumbsUpFill } from 'react-icons/bs';
import { AiFillStar } from 'react-icons/ai';
import { Link } from 'react-router-dom';

type Props = {
  lectureReviewId: number;
  title: string;
  starPoint: number;
  createdAt: string;
  voteCount: number;
  totalCommentCount: number;
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
};

function LectureReview2({
  lectureReviewId,
  title,
  totalCommentCount,
  starPoint,
  createdAt,
  voteCount,
  lecture,
  member,
}: Props) {
  return (
    <Container>
      <FlexContainer dir="col" grow={1} gap="0.1rem">
        <SmallFont>
          {voteCount >= 30 ? (
            <BestBox>Best</BestBox>
          ) : (
            <BsFillHandThumbsUpFill size="1.5rem" />
          )}
        </SmallFont>
        <SmallFont>{voteCount}</SmallFont>
      </FlexContainer>
      <FlexContainer grow={1} gap="0.2rem">
        <AiFillStar color="gold" size="1.5rem" />
        <SmallFont>{starPoint}</SmallFont>
      </FlexContainer>
      <FlexContainer
        dir="col"
        align="start"
        grow={2}
        gap="0.4rem"
        padding="0 0 0 2rem"
      >
        <VerySmallGrayFont>{`${lecture.title}(${totalCommentCount})`}</VerySmallGrayFont>

        <SmallFont2>
          <Link
            to={`/lecturereviewdetail/${lectureReviewId}`}
          >{`${title} (${totalCommentCount})`}</Link>
        </SmallFont2>
      </FlexContainer>
      <FlexContainer grow={1}>
        <SmallFont>{member.displayName}</SmallFont>
      </FlexContainer>
      <FlexContainer grow={1}>
        <VerySmallGrayFont>{createdAt.slice(0, 10)}</VerySmallGrayFont>
      </FlexContainer>
    </Container>
  );
}

export default LectureReview2;

type Container = {
  first?: boolean;
};

const Container = styled.div<Container>`
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 0.5rem;
  padding: 1rem 0;
  border-bottom: 0.5px solid black;
`;

const VerySmallGrayFont = styled.div`
  font-size: small;
  color: gray;
`;

const SmallFont2 = styled.div`
  font-size: 1.1rem;
  cursor: pointer;
`;

const BestBox = styled.div`
  padding: 0.5rem;
  background-color: red;
  color: white;
  border-radius: 1rem;
`;

/* eslint-disable react/button-has-type */
/* eslint-disable react/require-default-props */
import GlobalStyle from 'GlobalStyles';
import styled from 'styled-components';
import { FlexContainer } from 'pages/review/ReviewPage';
import { SmallFont } from 'pages/review/TeacherDetail/Information';
import { BsFillHandThumbsUpFill } from 'react-icons/bs';
import { AiFillStar } from 'react-icons/ai';

type Props = {
  setReviewOpen: React.Dispatch<React.SetStateAction<boolean>>;
  setLectureReviewId: React.Dispatch<React.SetStateAction<number>>;
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

function LectureReview({
  lectureReviewId,
  title,
  totalCommentCount,
  starPoint,
  createdAt,
  voteCount,
  lecture,
  member,
  setReviewOpen,
  setLectureReviewId,
}: Props) {
  const reviewOpenHandler = () => {
    setReviewOpen(true);
    setLectureReviewId(lectureReviewId);
  };

  return (
    <Container>
      <FlexContainer
        width="10rem"
        dir="col"
        gap="0.1rem"
        padding="0 0 0 0.5rem"
      >
        <SmallFont>
          {voteCount >= 30 ? (
            <BestBox>Best</BestBox>
          ) : (
            <BsFillHandThumbsUpFill size="1.5rem" />
          )}
        </SmallFont>
        <SmallFont>{voteCount}</SmallFont>
      </FlexContainer>
      <FlexContainer width="10rem" padding="0 0 0 3rem" gap="0.2rem">
        <AiFillStar color="gold" size="1.5rem" />
        <SmallFont>{starPoint}</SmallFont>
      </FlexContainer>
      <FlexContainer
        width="60rem"
        dir="col"
        align="start"
        gap="0.4rem"
        padding="0 0 0 2rem"
      >
        <VerySmallGrayFont>{`${lecture.title}(${totalCommentCount})`}</VerySmallGrayFont>

        <SmallFont2 onClick={reviewOpenHandler}>{`${title.slice(
          0,
          20,
        )}... (${totalCommentCount})`}</SmallFont2>
      </FlexContainer>
      <FlexContainer width="15rem">
        <VerySmallGrayFont>
          {`${member.displayName.slice(0, 5)}...`}
        </VerySmallGrayFont>
      </FlexContainer>
      <FlexContainer width="20rem">
        <VerySmallGrayFont>{createdAt.slice(0, 10)}</VerySmallGrayFont>
      </FlexContainer>
    </Container>
  );
}

export default LectureReview;

type Container = {
  first?: boolean;
};

const Container = styled.div<Container>`
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 1rem;
  padding: 1rem 1.5rem;
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

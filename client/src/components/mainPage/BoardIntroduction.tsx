import BaseButton from 'components/common/BaseButton';
import { useState } from 'react';
import { useNavigate } from 'react-router';
import styled from 'styled-components';
import theme from 'theme';
import reviewMac from '../../assets/images/Group 72.png';
import MainPageCarousel from './MainPageCarousel';

const { colors } = theme;

const Container = styled.div`
  width: 100%;
  padding-bottom: 5rem;
  display: flex;
  flex-direction: column;
  overflow: hidden;
  background-color: white;
`;

const ContentWrapper = styled.div`
  width: 100%;
  height: 20rem;
  padding: 1rem 3rem;
  display: flex;
  justify-content: flex-end;
  align-items: flex-end;
  margin-bottom: 4rem;
`;

const TitleContainer = styled.div`
  display: flex;
  flex-direction: column;
  width: 35rem;
`;

const Title = styled.div`
  font-size: 2.5rem;
  color: ${colors.black};
  margin-bottom: 0.5rem;
`;

const MidContentWrapper = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: flex-start;
  margin: 0 3rem;
`;

const SubTitleWrapper = styled.div`
  display: flex;
  flex-direction: column;
  align-items: flex-end;
`;

const SelectBtn = styled.div`
  font-size: 2.3rem;
  margin-bottom: 1.2rem;
  color: ${colors.black};
  height: 3rem;
  position: relative;
  cursor: pointer;

  &:hover {
    &:after {
      content: '';
      position: absolute;
      right: 0;
      bottom: -7px;
      width: 0;
      height: 0.1rem;
      background-color: black;
      animation: expand 0.3s linear forwards;
    }
  }

  @keyframes expand {
    to {
      width: 100%;
    }
  }
`;

const SubTitle = styled.div`
  font-size: 1.2rem;
  color: ${colors.black};
`;

const MacWrapper = styled.div`
  display: flex;
  padding: 1rem 4rem;
`;

function ReviewIntroduction() {
  // const [currentIndex, setCurrentIndex] = useState(0);
  const navigate = useNavigate();

  const handleClickFree = () => {
    navigate('/free');
  };
  const handleClickTeacher = () => {
    navigate('/ReviewPage');
  };
  const handleClickLecture = () => {
    navigate('/lectureslist');
  };
  const handleClickQuestion = () => {
    navigate('/qna');
  };
  const handleClickEvent = () => {
    navigate('/eventlist');
  };

  // const handleHoverFree = () => {
  //   setCurrentIndex(0);
  // };
  // const handleHoverTeacher = () => {
  //   setCurrentIndex(1);
  // };
  // const handleHoverLecture = () => {
  //   setCurrentIndex(2);
  // };
  // const handleHoverQuestion = () => {
  //   setCurrentIndex(3);
  // };

  return (
    <Container>
      <ContentWrapper data-aos="fade-left">
        <TitleContainer>
          <Title>
            게시판 기능으로 수강생들끼리 정보 공유하고, 더 나은 인강 선택하세요!
          </Title>
          <SubTitle>
            게시판 기능은 수강생들이 인강에 대해 서로 의견을 나눌 수 있는
            공간입니다. 게시글을 작성하고 댓글을 달며 자유롭게 소통할 수 있으며,
            인강에 대한 질문이나 정보 공유도 가능합니다. 이를 통해
            수강생들끼리의 정보 교류를 도모하고, 보다 나은 인강 선택을 할 수
            있도록 도와줍니다.
          </SubTitle>
        </TitleContainer>
      </ContentWrapper>

      <MidContentWrapper>
        <MacWrapper>
          <MainPageCarousel autoplay={3000} />
        </MacWrapper>

        <SubTitleWrapper>
          <SelectBtn onClick={handleClickFree}>자유게시판</SelectBtn>
          <SelectBtn onClick={handleClickTeacher}>강사리뷰</SelectBtn>
          <SelectBtn onClick={handleClickLecture}>강의리뷰</SelectBtn>
          <SelectBtn onClick={handleClickQuestion}>질문게시판</SelectBtn>
          <SelectBtn onClick={handleClickEvent}>이벤트게시판</SelectBtn>
        </SubTitleWrapper>
      </MidContentWrapper>
    </Container>
  );
}
export default ReviewIntroduction;

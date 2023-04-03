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
  margin-top: 4rem;
  width: 100%;
  display: flex;
  align-items: flex-end;
  margin-bottom: 4rem;
`;

const TitleContainer = styled.div`
  display: flex;
  flex-direction: column;
  margin-left: 3rem;
`;

const Title = styled.div`
  width: 45rem;
  font-size: 2.5rem;
  color: ${colors.black};
  margin-bottom: 1rem;
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
  width: 40rem;
  font-size: 1.2rem;
  color: ${colors.black};
`;

const MacWrapper = styled.div`
  display: flex;
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
      <ContentWrapper data-aos="fade-down">
        <TitleContainer>
          <Title>
            게시판 기능으로 수강생들끼리 정보 공유하고, 더 나은 인강 선택하세요!
          </Title>
          <SubTitle>
            게시판에서 수강생들끼리 의견 공유하고, 정보 교류로 높은 만족도를
            느껴보세요!
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

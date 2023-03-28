import React, { useEffect } from 'react';
import AOS from 'aos';
import 'aos/dist/aos.css';
import StarrySky from 'components/common/StarrySky';
import styled from 'styled-components';
import theme from 'theme';
import BaseButton from 'components/common/BaseButton';

const { colors } = theme;

const Container = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: flex-start;
`;

const ReviewIntroduction = styled.div`
  width: 100%;
  height: 60rem;
  display: flex;
  overflow: hidden;
  background-color: ${colors.pointColor};
`;

const Title = styled.div`
  font-size: 4rem;
  color: ${colors.white};
`;

const SubTitle = styled.div`
  font-size: 1.5rem;
  color: ${colors.white};
`;

const BoardIntroduction = styled.div`
  width: 100%;
  height: 60rem;
  display: flex;
`;

function MainPage() {
  useEffect(() => {
    AOS.init({
      duration: 1000,
      once: true,
    });
  }, []);

  return (
    <Container>
      <ReviewIntroduction>
        <div data-aos="fade-up">
          <Title>국내 모든 인강 정보와 후기를 한 눈에</Title>
          <SubTitle>
            Lorem ipsum dolor sit, amet consectetur adipisicing elit. Corporis
            at repudiandae ducimus distinctio fuga iste, labore animi
            perspiciatis, accusantium illo laudantium provident expedita vero
            molestiae saepe. Voluptatem eveniet unde quaerat.
          </SubTitle>
        </div>
      </ReviewIntroduction>

      <BoardIntroduction>
        <h1 data-aos="fade-up">Hello World!</h1>
      </BoardIntroduction>
    </Container>
  );
}

export default MainPage;

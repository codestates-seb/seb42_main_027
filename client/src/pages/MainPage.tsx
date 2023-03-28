import { useEffect } from 'react';
import styled from 'styled-components';
import AOS from 'aos';
import 'aos/dist/aos.css';

import ReviewIntroduction from 'components/mainPage/ReviewIntroduction';
import BoardIntroduction from 'components/mainPage/BoardIntroduction';

const Container = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: flex-start;
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
      <ReviewIntroduction />
      <BoardIntroduction />
    </Container>
  );
}

export default MainPage;

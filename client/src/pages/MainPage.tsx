import React, { useRef } from 'react';
import styled from 'styled-components';
import StarrySky from 'components/common/StarrySky';
import useIntersectionObsever from 'components/mainPage/useIntersectionObsever';

const Container = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
`;

const ContentDiv = styled.div`
  height: 100px;
  font-size: 36px;
  margin: 20px;
  display: flex;
  align-items: center;
  justify-content: center;

  animation-name: opacity;
  animation-duration: 5000ms;

  @keyframes opacity {
    from {
      opacity: 0;
    }
    to {
      opacity: 1;
    }
  }
`;

function Main() {
  const ref = useRef<HTMLDivElement>(null);
  const isInViewport = useIntersectionObsever(ref);
  return (
    <Container>
      {/* <StarrySky /> */}
      <div style={{ height: '2000px' }} />
      <ContentDiv ref={ref} className={isInViewport ? 'animation' : ''}>
        글자 등장! 글자 등장! 글자 등장! 글자 등장! 글자 등장! 글자 등장!
      </ContentDiv>
    </Container>
  );
}

export default Main;

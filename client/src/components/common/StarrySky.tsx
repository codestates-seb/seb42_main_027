/* eslint-disable react/no-array-index-key */
import styled from 'styled-components';
import { useEffect } from 'react';
import anime from 'animejs';

function StarrySky() {
  const num = 200; // 별 개수
  const vw = Math.max(document.documentElement.clientWidth, window.innerWidth); // 별이 찍히는 최대 가로 길이
  const vh = Math.max(
    document.documentElement.clientHeight,
    window.innerHeight,
  ); // 별이 찍히는 최대 세로 길이

  useEffect(() => {
    starryNight();
    shootingStars();
  }, []);

  const starryNight = () => {
    anime({
      targets: ['#sky .star'],
      opacity: [
        {
          duration: 700,
          value: '0',
        },
        {
          duration: 700,
          value: '1',
        },
      ],
      easing: 'linear',
      loop: true,
      delay: (el, i) => 50 * i,
    });
  };

  const shootingStars = () => {
    anime({
      targets: ['#shootingstars .wish'],
      easing: 'linear',
      loop: true,
      delay: (el, i) => 1000 * i,
      opacity: [
        {
          duration: 700,
          value: '1',
        },
      ],
      width: [
        {
          value: '150px',
        },
        {
          value: '0px',
        },
      ],
      translateX: 350,
    });
  };

  const randomRadius = () => {
    return Math.random() * 0.7 + 0.6;
  };

  const getRandomX = () => {
    return Math.floor(Math.random() * Math.floor(vw)).toString();
  };

  const getRandomY = () => {
    return Math.floor(Math.random() * Math.floor(vh)).toString();
  };

  return (
    <Container id="App">
      <svg id="sky">
        {[...Array(num)].map((x, y) => (
          <circle
            cx={getRandomX()}
            cy={getRandomY()}
            r={randomRadius()}
            stroke="none"
            strokeWidth="0"
            fill="white"
            key={y}
            className="star"
          />
        ))}
      </svg>
      <div id="shootingstars">
        {[...Array(60)].map((x, y) => (
          <div
            key={y}
            className="wish"
            style={{
              left: `${getRandomY()}px`,
              top: `${getRandomX()}px`,
            }}
          />
        ))}
      </div>
    </Container>
  );
}

const Container = styled.div`
  width: 100%;
  height: 100%;
  position: fixed;
  left: 0;
  z-index: -1;
  background: linear-gradient(to right, #ff47a1 0%, #ff9f4d 100%);

  #sky {
    width: 100%;
    height: 100%;
    position: fixed;
    overflow: hidden;
    margin: 0;
    padding: 0;
  }

  #shootingstars {
    margin: 0;
    padding: 0;
    width: 150vh;
    height: 100vw;
    position: fixed;
    overflow: hidden;
    transform: translatex(calc(50vw - 50%)) translatey(calc(50vh - 50%))
      rotate(120deg);
  }

  .wish {
    height: 2px;
    top: 300px;
    width: 100px;
    margin: 0;
    opacity: 0;
    padding: 0;
    background-color: white;
    position: absolute;
    background: linear-gradient(-45deg, white, rgba(0, 0, 255, 0));
    filter: drop-shadow(0 0 6px white);
    overflow: hidden;
  }
`;

export default StarrySky;

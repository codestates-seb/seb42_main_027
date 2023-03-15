/* eslint-disable react/no-array-index-key */
/* eslint-disable react/jsx-props-no-spreading */
/* eslint-disable react/require-default-props */
import { useMemo } from 'react';
import styled from 'styled-components';

import Slider, { Settings } from 'react-slick';
import 'slick-carousel/slick/slick.css';
import 'slick-carousel/slick/slick-theme.css';

type sliderProps = {
  /** 자동재생 (속도 설정시 number 타입으로) */
  autoplay?: boolean | number;
  /** 슬라이더 속도 */
  speed?: number;
  /** 반복 여부 */
  loop?: boolean;
};

type itemsProps = {
  item: string;
  name: string;
};

const items: itemsProps[] = [
  {
    item: 'http://placehold.it/1300x400',
    name: '이미지01',
  },
  {
    item: 'http://placehold.it/1300x400/ff0000',
    name: '이미지02',
  },
  {
    item: 'http://placehold.it/1300x400/00ffff',
    name: '이미지03',
  },
];

function Carousel({ autoplay = true, speed = 300, loop = true }: sliderProps) {
  const settings = useMemo<Settings>(
    () => ({
      dots: true,
      infinite: loop,
      speed,
      slidesToShow: 1,
      autoplay: Boolean(autoplay),
      autoplaySpeed: typeof autoplay === 'boolean' ? 3000 : autoplay,
    }),
    [autoplay, loop, speed],
  );
  return (
    <StyledSlider {...settings}>
      {items.map((item, index) => {
        return (
          <SliderItem key={index}>
            <img src={item.item} alt={item.name} />
          </SliderItem>
        );
      })}
    </StyledSlider>
  );
}

export default Carousel;

export const StyledSlider = styled(Slider)`
  height: 90%; //슬라이드 컨테이너 영역

  .slick-list {
    //슬라이드 스크린
    width: 90vw;
    height: 10rem;
    margin: 0 auto;
    overflow-x: hidden;
    background: none;
  }

  .slick-slide div {
    //슬라이더  컨텐츠
    cursor: pointer;
  }

  .slick-dots {
    //슬라이드의 위치
    bottom: 0.5rem;
    margin-top: 200px;
  }

  .slick-track {
    //이건 잘 모르겠음
    height: 100%;
  }

  .slick-prev {
    background-color: none;
    left: 2%;
    z-index: 1;
  }
  .slick-next {
    background-color: none;
    right: 2%;
    z-index: 1;
  }
`;

const SliderItem = styled.div`
  text-align: center;
  img {
    max-width: 100%;
    height: auto;
  }
`;

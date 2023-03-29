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
    item: 'https://cdn.pixabay.com/photo/2023/03/21/08/36/bamboo-7866718_1280.jpg',
    name: '이미지01',
  },
  {
    item: 'https://cdn.pixabay.com/photo/2016/09/11/02/07/greece-1660546_1280.jpg',
    name: '이미지02',
  },
  {
    item: 'https://cdn.pixabay.com/photo/2017/11/09/17/36/bridge-2934151_1280.png',
    name: '이미지03',
  },
  {
    item: 'https://cdn.pixabay.com/photo/2017/11/10/01/25/fantasy-2935093__480.jpg',
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
    width: 100rem;
    height: 100%;
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
    width: 100%;
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
    width: 100%;
    height: 15rem;
    @media screen and (min-width: 750px) {
      height: 25rem;
    }
  }
`;

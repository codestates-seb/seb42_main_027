/* eslint-disable react/no-array-index-key */
/* eslint-disable react/jsx-props-no-spreading */
/* eslint-disable react/require-default-props */
import { useMemo } from 'react';
import styled from 'styled-components';

import Slider, { Settings } from 'react-slick';
import 'slick-carousel/slick/slick.css';
import 'slick-carousel/slick/slick-theme.css';
import freeBoard from '../../assets/images/freeBoard.png';
import lecture from '../../assets/images/lecture.png';
import teacher from '../../assets/images/teacher.png';
import qua from '../../assets/images/qua.png';

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
    item: `${freeBoard}`,
    name: '이미지01',
  },
  {
    item: `${lecture}`,
    name: '이미지02',
  },
  {
    item: `${teacher}`,
    name: '이미지02',
  },
  {
    item: `${qua}`,
    name: '이미지02',
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
    width: 34rem;
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

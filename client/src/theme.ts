const fontSizes = {
  xs: '0.5rem',
  sm: '0.75rem',
  base: '1rem',
  md: '1.25rem',
  lg: '1.5rem',
};

// 자주 사용하는 색을 객체로 만들자.
const colors = {
  black: '#000000',
  white: '#FFFFFF',
  white_hover: '#DDDDDD',
  fontColor: '#565656',
  pointColor: '#6667AB',
  pointColor_hover: '#525392',
  gray: '#B8B8B8',
  success: '#65E271',
  danger: '#EC3C3C',
};

export const radius = {
  $md: '0.8rem',
};

// 자주 사용하는 스타일 속성을 theme으로 만들어보자.
const common = {
  flexCenter: `
    display: flex;
    justify-contents: center;
    align-items: center;
  `,
  flexCenterColumn: `
    display: flex;
    flex-direction: column;
    justify-contents: center;
    align-items: center;
  `,
};

// 요소 사이 기본 간격 설정. 기본 20px
const gap = {
  px20: '1.25rem',
  px40: '2.5rem',
  px60: '3.75rem',
};

// theme 객체에 감싸서 반환한다.
const theme = {
  fontSizes,
  colors,
  common,
  gap,
};

export default theme;

const fontSizes = {
  xs: '0.5rem',
  sm: '0.75rem',
  base: '1rem',
  md: '1.25rem',
  lg: '1.5rem',
  subTitle: '2rem',
};

// 자주 사용하는 색을 객체로 만들자.
const colors = {
  black: '#000000',
  white: '#FFFFFF',
  white_hover: '#DDDDDD',
  fontColor: '#565656',
  pointColor: '#6667AB',
  pointColor_disabled: `#B2B3D5`,
  pointColor_hover: '#525392',
  palePurple: '#E6E6EF',
  lightGray: '#DDDDDD',
  gray: '#B8B8B8',
  yellow: '#FFD54F',
  success: '#65E271',
  danger: '#EC3C3C',
};

// 아이콘 컬러 변경시 사용할 필터값 정리
const filterColors = {
  pointColor:
    'invert(45%) sepia(17%) saturate(1352%) hue-rotate(201deg) brightness(89%) contrast(87%);',
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
  px10: '0.625rem',
  px20: '1.25rem',
  px40: '2.5rem',
  px60: '3.75rem',
  px80: '5rem',
  px100: '6.25rem',
  px120: '7.5rem',
  px150: '9.375rem',
};

// theme 객체에 감싸서 반환한다.
const theme = {
  fontSizes,
  colors,
  filterColors,
  common,
  gap,
  radius,
};

export default theme;

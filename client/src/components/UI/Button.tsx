import styled from 'styled-components';
import theme from '../../theme';

const DefaultBtn = styled.button``;

const WhiteBtn = styled.button`
  width: 5.25rem;
  height: 2.125rem;
  text-align: center;
  color: ${theme.colors.pointColor};
  background-color: ${theme.colors.white};
  border: 1px solid ${theme.colors.pointColor};
  border-radius: 13px;
  &:hover {
    cursor: pointer;
    background-color: ${theme.colors.white_hover};
  }
`;

const PointBtn = styled.button`
  width: 5.25rem;
  height: 2.125rem;
  text-align: center;
  color: ${theme.colors.white};
  background-color: ${theme.colors.pointColor};
  border: 1px solid ${theme.colors.pointColor};
  border-radius: 13px;
  &:hover {
    cursor: pointer;
    background-color: ${theme.colors.pointColor_hover};
  }
`;

const WriteBtn = styled.button`
  width: 5.625rem;
  height: 2.5rem;
  text-align: center;
  color: ${theme.colors.white};
  background-color: ${theme.colors.pointColor};
  border: 1px solid ${theme.colors.pointColor};
  border-radius: 5px;
  &:hover {
    cursor: pointer;
    background-color: ${theme.colors.pointColor_hover};
    border: 1px solid ${theme.colors.pointColor_hover};
  }
`;

const SubMenuBtn = styled.button`
  width: 5rem;
  height: 2.5rem;
  text-align: center;
  color: ${theme.colors.black};
  background-color: ${theme.colors.white};
  border-radius: 25px;
  &:hover {
    cursor: pointer;
    color: ${theme.colors.pointColor};
  }
  &.selected {
    cursor: pointer;
    color: ${theme.colors.black};
    background-color: ${theme.colors.lightGray};
  }
`;

// Button 객체에 감싸서 반환
const Button = {
  DefaultBtn,
  WhiteBtn,
  PointBtn,
  WriteBtn,
  SubMenuBtn,
};

export default Button;

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
  font-size: ${theme.fontSizes.sm};
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

const FilterBtn = styled.button`
  width: 5rem;
  height: 2.5rem;
  text-align: center;
  font-size: ${theme.fontSizes.sm};
  color: ${theme.colors.black};
  background-color: ${theme.colors.white};
  border: 1px solid ${theme.colors.gray};
  border-radius: 5px;
  &:hover {
    cursor: pointer;
    border: 1px solid ${theme.colors.black};
  }
`;

const UDWhiteBtn = styled(FilterBtn)`
  width: 2.5rem;
  height: 1.25rem;
  font-size: ${theme.fontSizes.xs};
  &:hover {
    cursor: pointer;
    border: 1px solid ${theme.colors.black};
  }
  &:active {
    cursor: pointer;
    background-color: ${theme.colors.lightGray};
  }
`;

const VoteUpBtn = styled(FilterBtn)`
  width: 20px;
  height: 20px;
  border-radius: 0 5px 5px 0;
  &:hover {
    cursor: pointer;
    border: 1px solid ${theme.colors.black};
  }
  &:active {
    cursor: pointer;
    background-color: ${theme.colors.lightGray};
  }
`;

const VoteDownBtn = styled(VoteUpBtn)`
  border-radius: 5px 0 0 5px;
`;

const RecommentBtn = styled.button`
  font-size: ${theme.fontSizes.sm};
  color: ${theme.colors.pointColor};
  &:hover {
    cursor: pointer;
    color: ${theme.colors.pointColor_hover};
    text-decoration: underline;
  }
`;

// Button 객체에 감싸서 반환
const Button = {
  DefaultBtn,
  WhiteBtn,
  PointBtn,
  WriteBtn,
  SubMenuBtn,
  FilterBtn,
  UDWhiteBtn,
  VoteUpBtn,
  VoteDownBtn,
  RecommentBtn,
};

export default Button;

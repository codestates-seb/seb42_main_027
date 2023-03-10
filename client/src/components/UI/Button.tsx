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
  font-weight: bold;
`;

const PointBtn = styled.button`
  width: 5.25rem;
  height: 2.125rem;
  text-align: center;
  color: ${theme.colors.white};
  background-color: ${theme.colors.pointColor};
  border: 1px solid ${theme.colors.pointColor};
  border-radius: 13px;
  font-weight: bold;
`;

// Button 객체에 감싸서 반환
const Button = {
  DefaultBtn,
  WhiteBtn,
  PointBtn,
};

export default Button;

import styled from 'styled-components';
import theme from 'theme';

const { colors } = theme;

export const Container = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 30%;
  margin: 0 auto;
`;

export const Title = styled.h1`
  color: ${colors.pointColor};
  font-size: 2rem;
  margin-bottom: 1rem;
`;

import styled from 'styled-components';
import theme from 'theme';

const { colors } = theme;

export const Container = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: flex-start;
  width: 30rem;
  height: 100vh;
  margin: 0 auto;
`;

export const Title = styled.h1`
  margin-top: 10rem;
  color: ${colors.pointColor};
  font-size: 3rem;
  margin-bottom: 1rem;
`;

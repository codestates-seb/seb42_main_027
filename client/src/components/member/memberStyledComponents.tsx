import styled from 'styled-components';
import theme from 'theme';

const { colors } = theme;

export const Body = styled.div`
  display: flex;
  height: 100vh;
  align-items: flex-start;
`;

export const Container = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: flex-start;
  width: 40rem;
  height: 100%;
  margin-left: 20rem;
`;

export const Title = styled.h1`
  margin-top: 10rem;
  color: ${colors.pointColor};
  font-size: 3rem;
  margin-bottom: 1rem;
`;

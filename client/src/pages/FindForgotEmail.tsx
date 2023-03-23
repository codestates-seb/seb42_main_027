import styled from 'styled-components';
import theme from 'theme';
import { Container, Title } from 'components/member/memberStyledComponents';

const { colors } = theme;

const SubTitle = styled.p`
  color: ${colors.pointColor};
  font-size: 1rem;
  margin-bottom: 0.5rem;
`;

function FindForgotEmail() {
  return (
    <Container>
      <Title>시작하기</Title>
      <SubTitle>지금 가입하고 국내 모든 인강 정보와 후기를 한눈에</SubTitle>
      <SubTitle>확인하세요.</SubTitle>
    </Container>
  );
}

export default FindForgotEmail;

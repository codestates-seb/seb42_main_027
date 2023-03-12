import styled from 'styled-components';
import theme from 'theme';
import BaseButton from '../components/UI/BaseButton';

const { colors } = theme;
const Container = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
`;

const Title = styled.h1`
  color: ${theme.colors.pointColor};
  font-size: 2rem;
  margin-bottom: 1rem;
`;

const Form = styled.form`
  display: flex;
  flex-direction: column;
`;

const ButtonGroup = styled.div`
  display: flex;
  justify-content: space-between;
`;

const Label = styled.label`
  display: flex;
  flex-direction: column;
  color: ${colors.pointColor};
`;

function Login() {
  return (
    <div>
      <Container>
        <Title>로그인</Title>
        <Form>
          <Label htmlFor="email">
            Email
            <input type="text" id="email" />
          </Label>

          <ButtonGroup>
            <BaseButton
              color="pointColor"
              size="md"
              onClick={() => console.log('clicked')}
              disabled={false}
            >
              다음
            </BaseButton>
            <BaseButton
              color="white"
              size="md"
              onClick={() => console.log('clicked')}
              disabled={false}
            >
              Google 로그인
            </BaseButton>
          </ButtonGroup>
        </Form>
      </Container>
    </div>
  );
}

export default Login;

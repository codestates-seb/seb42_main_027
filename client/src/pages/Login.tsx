import Input from 'components/UI/Input';
import styled from 'styled-components';
import theme from 'theme';
import BaseButton from '../components/UI/BaseButton';

const Container = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 40%;
  margin: 0 auto;
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

function Login() {
  return (
    <div>
      <Container>
        <Title>로그인</Title>
        <Form>
          <Input type="email" htmlFor="email" label="Email" id="email" />
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

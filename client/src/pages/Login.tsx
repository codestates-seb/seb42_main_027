import PButton from 'components/login/PButton';
import Input from 'components/UI/Input';
import { useState } from 'react';
import styled from 'styled-components';
import theme from 'theme';
import { validateEmail } from 'components/login/loginRegex';
import BaseButton from '../components/UI/BaseButton';

const { colors } = theme;

const Container = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
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
  align-items: center;
`;

const ButtonGroup = styled.div`
  display: flex;
  margin-bottom: 1rem;
`;

const Separator = styled.span`
  color: ${colors.pointColor};
  margin: 0 0.8rem;
`;

function Login() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [isPasswordInputOpen, setIsPasswordInputOpen] = useState(false);

  const handleChangeEmail = (e: React.ChangeEvent<HTMLInputElement>) => {
    setEmail(e.target.value);
  };

  const handleChangePassword = (e: React.ChangeEvent<HTMLInputElement>) => {
    setPassword(e.target.value);
  };

  const handleClickNextBtn = () => {
    setIsPasswordInputOpen(true);
  };

  const handleSubmit = (e: React.MouseEvent<HTMLButtonElement>) => {
    e.preventDefault();
    if (!email || !validateEmail(email)) {
      window.alert('올바르지 않은 이메일 형식입니다.');
    }
    if (!password) window.alert('암호를 입력하세요.');
    console.log('submit', email, password);
  };

  return (
    <div>
      <Container>
        <Title>로그인</Title>
        <Form>
          <Input
            onChange={handleChangeEmail}
            type="email"
            htmlFor="email"
            label="Email"
            id="email"
          />
          {isPasswordInputOpen ? (
            <Input
              onChange={handleChangePassword}
              type="password"
              htmlFor="password"
              label="암호"
              id="password"
            />
          ) : null}

          <ButtonGroup>
            {isPasswordInputOpen ? (
              <BaseButton
                color="pointColor"
                size="md"
                disabled={false}
                onClick={handleSubmit}
              >
                로그인
              </BaseButton>
            ) : (
              <BaseButton
                color="pointColor"
                size="md"
                onClick={handleClickNextBtn}
                disabled={false}
              >
                다음
              </BaseButton>
            )}

            <BaseButton color="white" size="md" disabled={false}>
              Google 로그인
            </BaseButton>
          </ButtonGroup>
        </Form>
        <ButtonGroup>
          <PButton>이메일 찾기</PButton>
          <Separator>|</Separator>
          <PButton>암호 찾기</PButton>
        </ButtonGroup>
      </Container>
    </div>
  );
}

export default Login;

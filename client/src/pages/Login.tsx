import PButton from 'components/login/PButton';
import Input from 'components/UI/Input';
import { useState } from 'react';
import styled from 'styled-components';
import theme from 'theme';
import { validateEmail } from 'components/login/loginRegex';
import { fetchLogin } from 'api';
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

  const pathData = {
    email: '',
    password: '',
  };

  const handleChangeEmail = (e: React.ChangeEvent<HTMLInputElement>) => {
    setEmail(e.target.value);
  };

  const handleChangePassword = (e: React.ChangeEvent<HTMLInputElement>) => {
    setPassword(e.target.value);
  };

  const handleClickNextBtn = () => {
    setIsPasswordInputOpen(true);
  };

  // const getLogin = async () => {
  //   const response = await fetchLogin(pathData);
  //   localStorage.setItem('token', response.data.jwt);
  // };

  const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    if (!email || !validateEmail(email))
      window.alert('올바르지 않은 이메일 형식입니다.');
    if (!password) window.alert('암호를 입력하세요.');

    pathData.email = email;
    pathData.password = password;
    // getLogin();
  };

  return (
    <div>
      <Container>
        <Title>로그인</Title>
        <Form onSubmit={handleSubmit}>
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
                buttonType="submit"
                color="pointColor"
                size="md"
                disabled={false}
              >
                로그인
              </BaseButton>
            ) : (
              <BaseButton
                buttonType="button"
                color="pointColor"
                size="md"
                onClick={handleClickNextBtn}
                disabled={false}
              >
                다음
              </BaseButton>
            )}

            <BaseButton
              buttonType="button"
              color="white"
              size="md"
              disabled={false}
            >
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

import PButton from 'components/login/PButton';
import Input from 'components/UI/Input';
import { useState } from 'react';
import styled from 'styled-components';
import theme from 'theme';
import login from 'components/login/login';
import { useNavigate } from 'react-router';
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

const FailLoginMessage = styled.p`
  color: ${colors.danger};
  margin-bottom: 1rem;
`;

function Login() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [isPasswordInputOpen, setIsPasswordInputOpen] = useState(false);
  const [failedLogin, setFailedLogin] = useState(false);
  const [loginError, setLoginError] = useState('');

  const navigate = useNavigate();
  const pathData = {
    username: '',
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

  const handleSubmit = async (e: React.MouseEvent<HTMLButtonElement>) => {
    e.preventDefault();
    setLoginError(
      '아이디(로그인 전용 아이디) 또는 비밀번호를 잘못 입력했습니다. 입력하신 내용을 다시 확인해주세요.',
    );
    if (!password) setLoginError('암호를 입력하세요.');
    if (!email) setLoginError('이메일를 입력하세요.');

    pathData.username = email;
    pathData.password = password;
    try {
      await login(pathData);
      navigate(-1);
    } catch (error) {
      setFailedLogin(true);
      console.error(error);
    }
  };

  return (
    <div>
      <Container>
        <Title>로그인</Title>
        <Form>
          <Input
            onChange={handleChangeEmail}
            type="email"
            id="email"
            label="Email"
          />
          {isPasswordInputOpen ? (
            <Input
              onChange={handleChangePassword}
              type="password"
              id="password"
              label="암호"
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
        {failedLogin ? <FailLoginMessage>{loginError}</FailLoginMessage> : null}
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

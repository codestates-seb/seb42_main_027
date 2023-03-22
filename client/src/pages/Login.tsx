import { useState } from 'react';
import { useNavigate } from 'react-router';
import styled from 'styled-components';

import PButton from 'components/member/login/PButton';
import Input from 'components/common/Input';
import theme from 'theme';
import login from 'apis/login';
import { useIsLoginStore } from 'stores/loginStore';
import { Container, Title } from 'components/member/memberStyledComponents';
import getUserInfo from 'apis/getUserInfo';
import useUserInfoStore from 'stores/userInfoStore';
import BaseButton from '../components/common/BaseButton';

const { colors } = theme;

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
  const { setIsLoginInStore } = useIsLoginStore(state => state);
  const { userInfo, setUserInfo } = useUserInfoStore(state => state);
  const navigate = useNavigate();
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

  const fetchUserInfo = async () => {
    try {
      await getUserInfo(email);
      localStorage.setItem('email', email);
    } catch (error) {
      console.error(error);
    }
  };

  const handleSubmit = async (e: React.MouseEvent<HTMLButtonElement>) => {
    e.preventDefault();
    setLoginError(
      '아이디(로그인 전용 아이디) 또는 비밀번호를 잘못 입력했습니다. 입력하신 내용을 다시 확인해주세요.',
    );
    if (!password) setLoginError('암호를 입력하세요.');
    if (!email) setLoginError('이메일를 입력하세요.');

    pathData.email = email;
    pathData.password = password;
    try {
      const data = await login(pathData);
      navigate(-1);
      setIsLoginInStore(true);
      fetchUserInfo();
      console.log('data', data);
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
            value={email}
            onChange={handleChangeEmail}
            type="email"
            id="email"
            label="Email"
          />
          {isPasswordInputOpen ? (
            <Input
              value={password}
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

import styled from 'styled-components';
import Input from 'components/common/Input';
import { useState } from 'react';
import theme from 'theme';
import BaseButton from 'components/common/BaseButton';
import { validatePhoneNum } from './signUpRegex';

const { colors } = theme;

const Form = styled.form`
  display: flex;
  flex-direction: column;
  align-items: center;
`;

const Container = styled.div`
  width: 100%;
  border-bottom: 0.1rem solid ${colors.gray};
  margin-bottom: 1rem;
`;

const SignUpInfo = styled.p`
  color: ${colors.gray};
  margin: 1rem 0;
  font-size: 0.9rem;
`;

function StudentSignUpForm() {
  const [userName, setUserName] = useState('');
  const [phoneNum, setPhoneNum] = useState('');
  const [isPhoneNumSuccess, setIsPhoneNumSuccess] = useState({
    isSuccess: '',
    errorMessage: '',
  });
  const [displayName, setDisplayName] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');

  const colorSelector = (value: string) => {
    if (value === '') {
      return undefined;
    }
    if (value === 'true') {
      return 'success';
    }
    return 'danger';
  };

  console.log(isPhoneNumSuccess);

  const handleChangeUserName = (e: React.ChangeEvent<HTMLInputElement>) => {
    setUserName(e.target.value);
  };
  const handleChangePhoneNum = (e: React.ChangeEvent<HTMLInputElement>) => {
    setPhoneNum(e.target.value);
    if (validatePhoneNum(e.target.value)) {
      setIsPhoneNumSuccess({ isSuccess: 'true', errorMessage: '' });
    } else {
      setIsPhoneNumSuccess({
        isSuccess: 'false',
        errorMessage: '형직에 맞지 않는 번호입니다.',
      });
    }
  };
  const handleChangeDisplayName = (e: React.ChangeEvent<HTMLInputElement>) => {
    setDisplayName(e.target.value);
  };
  const handleChangeEmail = (e: React.ChangeEvent<HTMLInputElement>) => {
    setEmail(e.target.value);
  };
  const handleChangePassword = (e: React.ChangeEvent<HTMLInputElement>) => {
    setPassword(e.target.value);
  };
  const handleChangeConfirmPassword = (
    e: React.ChangeEvent<HTMLInputElement>,
  ) => {
    setConfirmPassword(e.target.value);
  };

  return (
    <Form>
      <Container>
        <Input
          value={userName}
          onChange={handleChangeUserName}
          type="text"
          id="user-name"
          label="이름"
        />

        <Input
          value={phoneNum}
          onChange={handleChangePhoneNum}
          type=""
          id="phone-num"
          label="전화번호"
          color={colorSelector(isPhoneNumSuccess.isSuccess)}
          errorMessage={isPhoneNumSuccess.errorMessage}
        />
      </Container>
      <Container>
        <Input
          value={displayName}
          onChange={handleChangeDisplayName}
          type="text"
          id="display-name"
          label="닉네임"
        />
        <Input
          value={email}
          onChange={handleChangeEmail}
          type="email"
          id="email"
          label="Email"
        />
        <Input
          value={password}
          onChange={handleChangePassword}
          type="text"
          id="password"
          label="암호"
        />
        <Input
          value={confirmPassword}
          onChange={handleChangeConfirmPassword}
          type="password"
          id="confirm-password"
          label="암호 확인"
        />
      </Container>
      <BaseButton color="pointColor" size="md" disabled={false}>
        가입하기
      </BaseButton>
      <SignUpInfo>
        유효한 주민등록번호를 입력하십시오. 새 기기나 웹 브라우저에 로그인할 때
        해당 주민등록번호를 사용하여 신원을 확인합니다. 메시지 또는 데이터
        요금이 적용될 수 있습니다.
      </SignUpInfo>
    </Form>
  );
}

export default StudentSignUpForm;

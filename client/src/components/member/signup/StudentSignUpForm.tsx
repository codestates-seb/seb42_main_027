import styled from 'styled-components';
import Input from 'components/common/Input';
import { useState } from 'react';
import theme from 'theme';

const { colors } = theme;

const Form = styled.form`
  display: flex;
  flex-direction: column;
  align-items: center;
`;

const Container = styled.div`
  border-bottom: 0.1rem solid ${colors.gray};
  margin-bottom: 1rem;
`;
function StudentSignUpForm() {
  const [userName, setUserName] = useState('');
  const [phoneNum, setPhoneNum] = useState('');
  const [displayName, setDisplayName] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPPassword] = useState('');

  const handleChangeUserName = (e: React.ChangeEvent<HTMLInputElement>) => {
    setUserName(e.target.value);
  };
  const handleChangePhoneNum = (e: React.ChangeEvent<HTMLInputElement>) => {
    setUserName(e.target.value);
  };
  const handleChangeDisplayName = (e: React.ChangeEvent<HTMLInputElement>) => {
    setUserName(e.target.value);
  };
  const handleChangeEmail = (e: React.ChangeEvent<HTMLInputElement>) => {
    setUserName(e.target.value);
  };
  const handleChangePassword = (e: React.ChangeEvent<HTMLInputElement>) => {
    setUserName(e.target.value);
  };
  const handleChangeConfirmPassword = (
    e: React.ChangeEvent<HTMLInputElement>,
  ) => {
    setUserName(e.target.value);
  };

  return (
    <Form>
      <Container>
        <Input
          onChange={handleChangeEmail}
          type="text"
          id="user-name"
          label="이름"
        />
        <Input
          onChange={handleChangeEmail}
          type=""
          id="phone-num"
          label="전화번호"
        />
      </Container>
      <Container>
        <Input
          onChange={handleChangeEmail}
          type="text"
          id="display-name"
          label="닉네임"
        />
        <Input
          onChange={handleChangeEmail}
          type="text"
          id="email"
          label="Email"
        />
        <Input
          onChange={handleChangeEmail}
          type="text"
          id="password"
          label="암호"
        />
        <Input
          onChange={handleChangeEmail}
          type="text"
          id="confirm-password"
          label="암호 확인"
        />
      </Container>
    </Form>
  );
}

export default StudentSignUpForm;

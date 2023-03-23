import styled from 'styled-components';
import theme from 'theme';
import { Container, Title } from 'components/member/memberStyledComponents';
import Input from 'components/common/Input';
import { useState } from 'react';
import { validatePhoneNum, validateEmail, validatePassword } from 'utils/regex';
import BaseButton from 'components/common/BaseButton';
import getForgotEmail from 'apis/getForgotEmail';
import ModalWrapper from 'components/common/ModalWrapper';
import { useNavigate } from 'react-router';
import postForgotPassword from 'apis/postForgotPassword';
import EditUserInfoInput from 'components/myPage/EditUserInfo';
import FindPasswordModal from 'components/findPassword/FindPasswordModal';

const { colors, fontSizes } = theme;

const SubTitle = styled.p`
  color: ${colors.pointColor};
  font-size: 1rem;
  margin-bottom: 1rem;
  width: 73%;
  text-align: center;
`;

const Form = styled.form`
  display: flex;
  flex-direction: column;
  width: 70%;
`;

const SignUpFailedMessage = styled.p`
  color: ${colors.danger};
  margin: 0.5rem 0;
  font-size: 0.9rem;
  text-align: center;
`;

// ModalStyle

function FindForgotPassword() {
  const [isOpen, setIsOpen] = useState(false);
  const [isSuccess, setIsSuccess] = useState(true);
  const [userName, setUserName] = useState('');
  const [isUserNameSuccess, setIsUserNameSuccess] = useState({
    isSuccess: '',
    errorMessage: '',
  });
  const [email, setEmail] = useState('');
  const [isEmailSuccess, setIsEmailSuccess] = useState({
    isSuccess: '',
    errorMessage: '',
  });
  const [phoneNum, setPhoneNum] = useState('');
  const [isPhoneNumSuccess, setIsPhoneNumSuccess] = useState({
    isSuccess: '',
    errorMessage: '',
  });

  // password 변경

  const pathData = {
    username: '',
    phoneNumber: '',
    email: '',
  };

  const colorSelector = (value: string) => {
    if (value === '') {
      return undefined;
    }
    if (value === 'true') {
      return 'success';
    }
    return 'danger';
  };

  const validationName = (value: string) => {
    if (value.length === 0) {
      setIsUserNameSuccess({
        isSuccess: 'false',
        errorMessage: '필수 정보입니다.',
      });
    } else if (value.length >= 2 && value.length < 5) {
      setIsUserNameSuccess({ isSuccess: 'true', errorMessage: '' });
    } else {
      setIsUserNameSuccess({
        isSuccess: 'false',
        errorMessage: '2글자 이상 5글자 미만으로 입력해주세요.',
      });
    }
  };

  const validationEmail = (value: string) => {
    if (value.length === 0) {
      setIsEmailSuccess({
        isSuccess: 'false',
        errorMessage: '필수 정보입니다.',
      });
    } else if (validateEmail(value)) {
      setIsEmailSuccess({ isSuccess: 'true', errorMessage: '' });
    } else {
      setIsEmailSuccess({
        isSuccess: 'false',
        errorMessage: '이메일 형식이 올바르지 않습니다.',
      });
    }
  };

  const validationPhoneNumber = (value: string) => {
    if (value.length === 0) {
      setIsPhoneNumSuccess({
        isSuccess: 'false',
        errorMessage: '필수 정보입니다.',
      });
    } else if (validatePhoneNum(value)) {
      setIsPhoneNumSuccess({ isSuccess: 'true', errorMessage: '' });
    } else {
      setIsPhoneNumSuccess({
        isSuccess: 'false',
        errorMessage: '형직에 맞지 않는 번호입니다.',
      });
    }
  };

  const handleChangeUserName = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { value } = e.target;
    setUserName(value);
    validationName(value);
  };

  const handleChangeEmail = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { value } = e.target;
    setEmail(value);
    validationEmail(value);
  };

  const handleChangePhoneNum = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { value } = e.target;
    setPhoneNum(value);
    validationPhoneNumber(value);
  };

  // 비밀번호 변경

  const handleSubmit = async (e: React.MouseEvent<HTMLButtonElement>) => {
    e.preventDefault();
    pathData.username = userName;
    pathData.email = email;
    pathData.phoneNumber = phoneNum;
    validationName(userName);
    validationPhoneNumber(phoneNum);
    if (
      isUserNameSuccess.isSuccess === 'true' &&
      isPhoneNumSuccess.isSuccess === 'true' &&
      isEmailSuccess.isSuccess === 'true'
    ) {
      try {
        const response = await postForgotPassword(pathData);
        setIsSuccess(true);
        setIsOpen(true);
        console.log(response);
      } catch (error: any) {
        console.error(error);
        // ! 수정 필요
        if (error.response.data === '수정') {
          setIsSuccess(false);
        }
      }
    }
  };

  const handleOpenModal = () => {
    setIsOpen(true);
  };

  return (
    <Container>
      <Title>암호 찾기</Title>
      <SubTitle>암호를 찾고자하는 정보를 입력해주세요.</SubTitle>
      <Form>
        <Input
          value={userName}
          onChange={handleChangeUserName}
          type="text"
          id="user-name"
          label="이름"
          color={colorSelector(isUserNameSuccess.isSuccess)}
          errorMessage={isUserNameSuccess.errorMessage}
        />
        <Input
          value={email}
          onChange={handleChangeEmail}
          type="email"
          id="email"
          label="Email"
          color={colorSelector(isEmailSuccess.isSuccess)}
          errorMessage={isEmailSuccess.errorMessage}
        />
        <Input
          value={phoneNum}
          onChange={handleChangePhoneNum}
          type=""
          id="phone-num"
          label="전화번호"
          placeholder="'-'를 제외하고 입력하세요."
          color={colorSelector(isPhoneNumSuccess.isSuccess)}
          errorMessage={isPhoneNumSuccess.errorMessage}
        />
        {isSuccess ? null : (
          <SignUpFailedMessage>암호가 존재하지 않습니다.</SignUpFailedMessage>
        )}
        <BaseButton
          onClick={handleSubmit}
          color="pointColor"
          size="md"
          disabled={false}
        >
          다음
        </BaseButton>

        {/* 임시 버튼 */}
        <button type="button" onClick={handleOpenModal}>
          modal open
        </button>
        <FindPasswordModal isOpen={isOpen} />
      </Form>
    </Container>
  );
}

export default FindForgotPassword;

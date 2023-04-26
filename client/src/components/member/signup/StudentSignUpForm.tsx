import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';
import theme from 'theme';
import BaseButton from 'components/common/BaseButton';
import postSignUp from 'apis/postSignUp';
import Input from 'components/common/Input';
import Swal from 'sweetalert2';
import {
  validatePhoneNum,
  validateEmail,
  validatePassword,
} from '../../../utils/regex';
import userIcon from '../../../assets/images/blank-profile-picture-973460_960_720.webp';

const { colors } = theme;

const Form = styled.form`
  display: flex;
  flex-direction: column;
  align-items: flex-start;
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

const SignUpFailedMessage = styled.p`
  color: ${colors.danger};
  margin-bottom: 0.5rem;
  font-size: 0.9rem;
`;

const StyleButton = styled.div`
  width: 10rem;
  height: 3rem;
`;

function StudentSignUpForm() {
  const [isSuccess, setIsSuccess] = useState(true);
  const [userName, setUserName] = useState('');
  const [isUserNameSuccess, setIsUserNameSuccess] = useState({
    isSuccess: '',
    errorMessage: '',
  });
  const [phoneNum, setPhoneNum] = useState('');
  const [isPhoneNumSuccess, setIsPhoneNumSuccess] = useState({
    isSuccess: '',
    errorMessage: '',
  });
  const [displayName, setDisplayName] = useState('');
  const [isDisplayNameSuccess, setIsDisplayNameSuccess] = useState({
    isSuccess: '',
    errorMessage: '',
  });
  const [email, setEmail] = useState('');
  const [isEmailSuccess, setIsEmailSuccess] = useState({
    isSuccess: '',
    errorMessage: '',
  });
  const [password, setPassword] = useState('');
  const [isPasswordSuccess, setIsPasswordSuccess] = useState({
    isSuccess: '',
    errorMessage: '',
  });
  const [confirmPassword, setConfirmPassword] = useState('');
  const [isConfirmPasswordSuccess, setIsConfirmPasswordSuccess] = useState({
    isSuccess: '',
    errorMessage: '',
  });

  const navigate = useNavigate();
  const currentTime = new Date().toString();

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
        errorMessage: '형식에 맞지 않는 번호입니다.',
      });
    }
  };

  const validationDisplayName = (value: string) => {
    if (value.length === 0) {
      setIsDisplayNameSuccess({
        isSuccess: 'false',
        errorMessage: '필수 정보입니다.',
      });
    } else if (value.length >= 2 && value.length < 5) {
      setIsDisplayNameSuccess({ isSuccess: 'true', errorMessage: '' });
    } else {
      setIsDisplayNameSuccess({
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

  const validationPassword = (value: string) => {
    if (value.length === 0) {
      setIsPasswordSuccess({
        isSuccess: 'false',
        errorMessage: '필수 정보입니다.',
      });
    } else if (validatePassword(value)) {
      setIsPasswordSuccess({ isSuccess: 'true', errorMessage: '' });
    } else {
      setIsPasswordSuccess({
        isSuccess: 'false',
        errorMessage: '8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.',
      });
    }
  };

  const validationConfirmPassword = (value: string) => {
    if (value.length === 0) {
      setIsConfirmPasswordSuccess({
        isSuccess: 'false',
        errorMessage: '필수 정보입니다.',
      });
    } else if (password === value) {
      setIsConfirmPasswordSuccess({ isSuccess: 'true', errorMessage: '' });
    } else {
      setIsConfirmPasswordSuccess({
        isSuccess: 'false',
        errorMessage: '암호가 일치하지 않습니다.',
      });
    }
  };
  const handleChangeUserName = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { value } = e.target;
    setUserName(value);
    validationName(value);
  };

  const handleChangePhoneNum = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { value } = e.target;
    setPhoneNum(value);
    validationPhoneNumber(value);
  };

  const handleChangeDisplayName = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { value } = e.target;
    setDisplayName(value);
    validationDisplayName(value);
  };

  const handleChangeEmail = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { value } = e.target;
    setEmail(value);
    validationEmail(value);
  };

  const handleChangePassword = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { value } = e.target;
    setPassword(value);
    validationPassword(value);
  };

  const handleChangeConfirmPassword = (
    e: React.ChangeEvent<HTMLInputElement>,
  ) => {
    const { value } = e.target;
    setConfirmPassword(value);
    validationConfirmPassword(value);
  };

  const pathData = {
    username: '',
    phoneNumber: '',
    displayName: '',
    email: '',
    password: '',
    confirmPassword: '',
    createdAt: '',
    state: '',
  };

  const handleSubmit = async (e: React.MouseEvent<HTMLButtonElement>) => {
    e.preventDefault();
    pathData.username = userName;
    pathData.phoneNumber = phoneNum;
    pathData.displayName = displayName;
    pathData.email = email;
    pathData.password = password;
    pathData.confirmPassword = confirmPassword;
    pathData.createdAt = currentTime;
    pathData.state = 'STUDENT';
    validationName(userName);
    validationPhoneNumber(phoneNum);
    validationDisplayName(displayName);
    validationEmail(email);
    validationPassword(password);
    validationConfirmPassword(confirmPassword);

    try {
      await postSignUp(pathData);
      // navigate('/');
      setTimeout(() => {
        Swal.fire({
          title: '가입 성공!',
          icon: 'success',

          confirmButtonColor: '#6667ab', // confrim 버튼 색깔 지정
          confirmButtonText: '확인', // confirm 버튼 텍스트 지정
        }).then(() => {
          navigate('/login');
        });
        // navigate('/login');
      }, 100);
      setIsSuccess(true);
    } catch (error: any) {
      console.error(error);
      if (error.response.status === 409) {
        setIsSuccess(false);
      }
    }
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
          color={colorSelector(isUserNameSuccess.isSuccess)}
          errorMessage={isUserNameSuccess.errorMessage}
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
      </Container>
      <Container>
        <Input
          value={displayName}
          onChange={handleChangeDisplayName}
          type="text"
          id="display-name"
          label="닉네임"
          color={colorSelector(isDisplayNameSuccess.isSuccess)}
          errorMessage={isDisplayNameSuccess.errorMessage}
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
          value={password}
          onChange={handleChangePassword}
          type="password"
          id="password"
          label="암호"
          color={colorSelector(isPasswordSuccess.isSuccess)}
          errorMessage={isPasswordSuccess.errorMessage}
        />
        <Input
          value={confirmPassword}
          onChange={handleChangeConfirmPassword}
          type="password"
          id="confirm-password"
          label="암호 확인"
          color={colorSelector(isConfirmPasswordSuccess.isSuccess)}
          errorMessage={isConfirmPasswordSuccess.errorMessage}
        />
      </Container>
      {isSuccess ? null : (
        <SignUpFailedMessage>
          이미 가입된 계정이 있습니다. 로그인해 주세요
        </SignUpFailedMessage>
      )}
      <StyleButton>
        <BaseButton
          onClick={handleSubmit}
          color="pointColor"
          size="md"
          disabled={false}
        >
          가입하기
        </BaseButton>
      </StyleButton>

      <SignUpInfo>
        유효한 전화번호를 입력하십시오. 새 기기나 웹 브라우저에 로그인할 때 해당
        전화번호를 사용하여 신원을 확인합니다. 메시지 또는 데이터 요금이 적용될
        수 있습니다.
      </SignUpInfo>
    </Form>
  );
}

export default StudentSignUpForm;

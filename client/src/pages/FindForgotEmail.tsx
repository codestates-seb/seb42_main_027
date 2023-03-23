import styled from 'styled-components';
import theme from 'theme';
import { Container, Title } from 'components/member/memberStyledComponents';
import Input from 'components/common/Input';
import { useState } from 'react';
import { validatePhoneNum } from 'utils/regex';
import BaseButton from 'components/common/BaseButton';
import getForgotEmail from 'apis/getForgotEmail';

const { colors } = theme;

const SubTitle = styled.p`
  color: ${colors.pointColor};
  font-size: 1rem;
  margin-bottom: 0.5rem;
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

function FindForgotEmail() {
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

  const pathData = {
    username: '',
    phoneNumber: '',
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

  const handleChangePhoneNum = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { value } = e.target;
    setPhoneNum(value);
    validationPhoneNumber(value);
  };

  const handleSubmit = async (e: React.MouseEvent<HTMLButtonElement>) => {
    e.preventDefault();
    pathData.username = userName;
    pathData.phoneNumber = phoneNum;
    validationName(userName);
    validationPhoneNumber(phoneNum);
    if (
      isUserNameSuccess.isSuccess === 'true' &&
      isPhoneNumSuccess.isSuccess === 'true'
    ) {
      try {
        const response = await getForgotEmail(pathData);
        setIsSuccess(true);
        console.log(response);
      } catch (error: any) {
        console.error(error);
        if (error.response.data === '이메일이 존재하지 않습니다.') {
          setIsSuccess(false);
        }
      }
    }
  };

  return (
    <Container>
      <Title>이메일 찾기</Title>
      <SubTitle>
        회원정보에 등록된 사용자의 정보와 입력한 사용자의 정보가 일치해야 등록된
        이메일을 확인할 수 있습니다.
      </SubTitle>
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
          <SignUpFailedMessage>이메일이 존재하지 않습니다.</SignUpFailedMessage>
        )}
        <BaseButton
          onClick={handleSubmit}
          color="pointColor"
          size="md"
          disabled={false}
        >
          다음
        </BaseButton>
      </Form>
    </Container>
  );
}

export default FindForgotEmail;

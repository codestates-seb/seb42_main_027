import styled from 'styled-components';
import theme from 'theme';
import { Container, Title } from 'components/member/memberStyledComponents';
import Input from 'components/common/Input';
import { useState } from 'react';
import { validatePhoneNum } from 'utils/regex';
import BaseButton from 'components/common/BaseButton';
import getForgotEmail from 'apis/getForgotEmail';
import ModalWrapper from 'components/common/ModalWrapper';
import { useNavigate } from 'react-router';

const { colors, fontSizes } = theme;

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

// ModalStyle
const ModalContainer = styled.div`
  display: flex;
  flex-direction: column;
  height: 100%;
`;

const ModalTitle = styled.h2`
  font-size: ${fontSizes.md};
  color: ${colors.pointColor};
  padding: 0.3rem 0;
  border-bottom: 0.1rem solid ${colors.gray};
  margin-bottom: 1rem;
`;

const ModalSubTitle = styled.h2`
  font-size: 0.9rem;
  margin-bottom: 1rem;
`;

const ModalContent = styled.h2`
  text-align: center;
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
  font-size: 1.2rem;
  color: ${colors.fontColor};
`;

const ModalButtonContainer = styled.div`
  display: flex;
  flex-direction: column;
  & :first-child {
    margin-bottom: 0.3rem;
  }
`;

function FindForgotEmail() {
  const [isOpen, setIsOpen] = useState(false);
  const [foundEmail, setFoundEmail] = useState('');
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

  const navigate = useNavigate();

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
        setFoundEmail(response);
        setIsSuccess(true);
        setIsOpen(true);
      } catch (error: any) {
        console.error(error);
        if (error.response.data === '이메일이 존재하지 않습니다.') {
          setIsSuccess(false);
        }
      }
    }
  };

  const handleOpenModal = () => {
    setIsOpen(true);
  };

  const handleClickLoginBtn = () => {
    navigate('/login');
  };
  const handleClickFindPasswordBtn = () => {
    navigate('/findpassword');
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

        {/* 임시 버튼 */}
        <button type="button" onClick={handleOpenModal}>
          modal open
        </button>

        <ModalWrapper isOpen={isOpen} shouldCloseOnOverlayClick={false}>
          <ModalContainer>
            <ModalTitle>아이디 찾기</ModalTitle>
            <ModalSubTitle>
              고객님의 정보와 일치하는 이메일 입니다.
            </ModalSubTitle>
            <ModalContent>{foundEmail}</ModalContent>
            <ModalButtonContainer>
              <BaseButton
                onClick={handleClickLoginBtn}
                color="pointColor"
                size="md"
                disabled={false}
              >
                로그인 하기
              </BaseButton>
              <BaseButton
                onClick={handleClickFindPasswordBtn}
                color="white"
                size="md"
                disabled={false}
              >
                암호 찾기
              </BaseButton>
            </ModalButtonContainer>
          </ModalContainer>
        </ModalWrapper>
      </Form>
    </Container>
  );
}

export default FindForgotEmail;

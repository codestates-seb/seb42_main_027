import styled from 'styled-components';
import { useState } from 'react';
import { validatePassword } from 'utils/regex';
import theme from 'theme';
import ModalWrapper from 'components/common/ModalWrapper';
import EditUserInfoInput from 'components/myPage/EditUserInfo';
import BaseButton from 'components/common/BaseButton';
import { useNavigate } from 'react-router';

const { colors, fontSizes } = theme;

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

const EditUserInfoContainer = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  margin-bottom: 0.5rem;
`;

type FindPasswordModalProps = {
  isOpen: boolean;
};
function FindPasswordModal({ isOpen }: FindPasswordModalProps) {
  const [password, setPassword] = useState('');
  const [isPasswordSuccess, setIsPasswordSuccess] = useState({
    isSuccess: '',
    errorMessage: '',
  });
  const [editPassword, setEditPassword] = useState('');
  const [isEditPasswordSuccess, setIsEditPasswordSuccess] = useState({
    isSuccess: '',
    errorMessage: '',
  });
  const [confirmEditPassword, setConfirmEditPassword] = useState('');
  const [isConfirmEditPasswordSuccess, setIsConfirmEditPasswordSuccess] =
    useState({
      isSuccess: '',
      errorMessage: '',
    });

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

  const validationEditPassword = (value: string) => {
    if (value.length === 0) {
      setIsEditPasswordSuccess({
        isSuccess: 'false',
        errorMessage: '필수 정보입니다.',
      });
    } else if (validatePassword(value)) {
      setIsEditPasswordSuccess({ isSuccess: 'true', errorMessage: '' });
    } else {
      setIsEditPasswordSuccess({
        isSuccess: 'false',
        errorMessage: '8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.',
      });
    }
  };

  const validationConfirmEditPassword = (value: string) => {
    if (value.length === 0) {
      setIsConfirmEditPasswordSuccess({
        isSuccess: 'false',
        errorMessage: '필수 정보입니다.',
      });
    } else if (editPassword === value) {
      setIsConfirmEditPasswordSuccess({ isSuccess: 'true', errorMessage: '' });
    } else {
      setIsConfirmEditPasswordSuccess({
        isSuccess: 'false',
        errorMessage: '암호가 일치하지 않습니다.',
      });
    }
  };

  const handleChangePassword = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { value } = e.target;
    setPassword(value);
    validationPassword(value);
  };

  const handleChangeEditPassword = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { value } = e.target;
    setEditPassword(value);
    validationEditPassword(value);
  };

  const handleChangeConfirmEditPassword = (
    e: React.ChangeEvent<HTMLInputElement>,
  ) => {
    const { value } = e.target;
    setConfirmEditPassword(value);
    validationConfirmEditPassword(value);
  };

  const handleClickLoginBtn = () => {
    navigate('/login');
  };

  return (
    <ModalWrapper isOpen={isOpen} shouldCloseOnOverlayClick={false}>
      <ModalContainer>
        <ModalTitle>암호 재설정</ModalTitle>
        <ModalSubTitle>암호를 변경해 주세요.</ModalSubTitle>
        <EditUserInfoContainer>
          <EditUserInfoInput
            placeholder="현재 암호"
            onChange={handleChangePassword}
            value={password}
            errorMessage={isPasswordSuccess.errorMessage}
            color={colorSelector(isPasswordSuccess.isSuccess)}
          />
          <EditUserInfoInput
            placeholder="새로운 암호"
            onChange={handleChangeEditPassword}
            value={editPassword}
            errorMessage={isEditPasswordSuccess.errorMessage}
            color={colorSelector(isEditPasswordSuccess.isSuccess)}
          />
          <EditUserInfoInput
            placeholder="새로운 암호 확인"
            onChange={handleChangeConfirmEditPassword}
            value={confirmEditPassword}
            errorMessage={isConfirmEditPasswordSuccess.errorMessage}
            color={colorSelector(isConfirmEditPasswordSuccess.isSuccess)}
          />
        </EditUserInfoContainer>
        <ModalButtonContainer>
          <BaseButton
            onClick={handleClickLoginBtn}
            color="pointColor"
            size="md"
            disabled={false}
          >
            암호 변경
          </BaseButton>
        </ModalButtonContainer>
      </ModalContainer>
    </ModalWrapper>
  );
}

export default FindPasswordModal;

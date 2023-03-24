import styled from 'styled-components';
import { useState } from 'react';
import { validatePassword } from 'utils/regex';
import theme from 'theme';
import ModalWrapper from 'components/common/ModalWrapper';
import EditUserInfoInput from 'components/myPage/EditUserInfoInput';
import BaseButton from 'components/common/BaseButton';
import { useNavigate } from 'react-router';
import useUserInfoStore from 'stores/userInfoStore';
import patchFindPassword from 'apis/patchFindPassword';
import patchUserPassword from '../../apis/patchUserPassword';

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

const ModalButtonContainer = styled.div`
  display: flex;
  flex-direction: column;
  & :first-child {
    margin-bottom: 0.3rem;
  }
`;

const EditUserInfoContainer = styled.form`
  display: flex;
  flex-direction: column;
  width: 100%;
  margin-bottom: 0.5rem;
  height: 100%;
`;

const SignUpFailedMessage = styled.p`
  color: ${colors.danger};
  margin-bottom: 0.5rem;
  font-size: 0.9rem;
  text-align: center;
`;

type FindPasswordModalProps = {
  isOpen: boolean;
  email: string;
};

function FindPasswordModal({ isOpen, email }: FindPasswordModalProps) {
  const [isSuccess, setIsSuccess] = useState(true);

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

  const { userInfo } = useUserInfoStore(state => state);
  const navigate = useNavigate();

  const pathData = {
    newPassword: '',
    confirmPassword: '',
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

  const handleClickLoginBtn = async (
    e: React.MouseEvent<HTMLButtonElement>,
  ) => {
    e.preventDefault();

    pathData.newPassword = editPassword;
    pathData.confirmPassword = confirmEditPassword;

    validationEditPassword(editPassword);
    validationConfirmEditPassword(confirmEditPassword);
    if (
      isEditPasswordSuccess.isSuccess === 'true' &&
      isConfirmEditPasswordSuccess.isSuccess === 'true'
    ) {
      try {
        //! 로그인이 안된 상태에서 요청을 해야하지만 memberId가 필요하다
        //! OPTION 1 : 암호 재설정 전 단계의 요청에서 memberId를 받는다.
        //! OPTION 2 : 암호 찾기 과정에서 암호재설정 api요청을 하나 더 만든다.
        await patchFindPassword(pathData, email);
        console.log('암호 수정 성공');
        navigate('/login');
      } catch (error: any) {
        console.error(error);
        //! 현재 암호가 올바르지 않으면 에러 메세지 표시 기능 추가
        // if (error.response.data === '수정') {
        // }
      }
    }
  };

  return (
    <ModalWrapper isOpen={isOpen} shouldCloseOnOverlayClick={false}>
      <ModalContainer>
        <ModalTitle>암호 재설정</ModalTitle>
        <ModalSubTitle>암호를 변경해 주세요.</ModalSubTitle>
        <EditUserInfoContainer>
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
          {isSuccess ? (
            <SignUpFailedMessage> </SignUpFailedMessage>
          ) : (
            <SignUpFailedMessage>현재 암호를 확인해주세요.</SignUpFailedMessage>
          )}
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

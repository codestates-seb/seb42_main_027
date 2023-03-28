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

const ModalContent = styled.h2`
  margin-top: 4rem;
  display: flex;
  justify-content: flex-start;
  align-items: flex-start;
  height: 100%;
  font-size: 1.2rem;
  color: ${colors.fontColor};
  padding: 1rem;
  width: 95%;
`;

type FindPasswordModalProps = {
  isOpen: boolean;
  email: string;
};

function FindPasswordModal({ isOpen, email }: FindPasswordModalProps) {
  const navigate = useNavigate();

  const handleClickLoginBtn = () => {
    setTimeout(() => {
      navigate('/');
    }, 100);
    setTimeout(() => {
      navigate('/login');
    }, 150);
  };

  return (
    <ModalWrapper isOpen={isOpen} shouldCloseOnOverlayClick={false}>
      <ModalContainer>
        <ModalTitle>임시 암호발급</ModalTitle>
        <ModalSubTitle>
          임시 암호가 회원님의 이메일로 전송 되었습니다.
        </ModalSubTitle>

        <ModalContent>
          임시 암호가 회원님이 가입한 {email}로 전송 되었습니다. 확인 후
          마이페이지에서 변경하여 사용해 주세요.
        </ModalContent>

        <ModalButtonContainer>
          <BaseButton
            onClick={handleClickLoginBtn}
            color="pointColor"
            size="md"
            disabled={false}
          >
            로그인 하기
          </BaseButton>
        </ModalButtonContainer>
      </ModalContainer>
    </ModalWrapper>
  );
}

export default FindPasswordModal;

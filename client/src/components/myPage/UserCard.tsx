import { useEffect, useState } from 'react';
import useUserInfoStore from 'stores/userInfoStore';
import patchUserInfo from 'apis/patchUserInfo';
import styled from 'styled-components';
import { validatePassword, validatePhoneNum } from 'utils/regex';
import theme from 'theme';
import patchUserPassword from 'apis/patchUserPassword';
import EditUserInfoInput from './EditUserInfoInput';

const { colors } = theme;
const { fontSizes } = theme;

const UserCardContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  /* width: 80%; */
  /* margin: 0 auto */
  padding: 0 8rem;
  height: 100vh;
  box-shadow: 5px 0 5px rgba(0, 0, 0, 0.1);
  border-left: 0.1rem solid rgb(235, 235, 235);
`;

const ProfileImage = styled.img`
  width: 14rem;
  height: 14rem;
  object-fit: cover;
  border-radius: 50%;
  margin-top: 2rem;
  margin-right: 5rem;
  box-shadow: 0px 0 5px rgba(0, 0, 0, 0.3);
`;

const UserInfoContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  margin-top: 1rem;
  width: 20rem;
`;

const NameTagContainer = styled.div`
  display: flex;
  align-items: center;
  width: 100%;
`;

const Name = styled.h2`
  font-size: 2rem;
  margin: 1rem 0;
  margin-right: 1rem;
`;

const UserState = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 0.2rem 0.5rem;
  color: ${colors.pointColor};
  font-size: ${fontSizes.md};
  border: solid 0.2rem ${colors.pointColor};
  border-radius: 0.8rem;
`;

const UserInfo = styled.p`
  font-size: ${fontSizes.md};
  margin: 0.3rem 0;
`;

const ButtonContainer = styled.div`
  display: flex;
  justify-content: flex-end;
  width: 100%;
  & :first-child {
    margin-right: 0.5rem;
  }
`;

const EditBtn = styled.span`
  text-align: end;
  color: ${colors.gray};
  cursor: pointer;
  :hover {
    color: ${colors.fontColor};
  }
`;

const EditUserInfoContainer = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  margin-bottom: 0.5rem;
`;

function UserCard() {
  const { userInfo } = useUserInfoStore(state => state);
  const [isEdit, setIsEdit] = useState(false);
  const [isEditPassword, setIsEditPassword] = useState(false);

  const [displayName, setDisplayName] = useState(userInfo.displayName);
  const [isDisplayNameSuccess, setIsDisplayNameSuccess] = useState({
    isSuccess: '',
    errorMessage: '',
  });

  const [phoneNum, setPhoneNum] = useState(userInfo.phoneNumber);
  const [isPhoneNumSuccess, setIsPhoneNumSuccess] = useState({
    isSuccess: '',
    errorMessage: '',
  });

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

  const userData = {
    profileImage: 'https://i.pravatar.cc/150?img=7',
    name: userInfo.username,
    email: userInfo.email,
    displayName: userInfo.displayName,
    phoneNumber: userInfo.phoneNumber,
    password: userInfo.password,
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

  const handleChangeDisplayName = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { value } = e.target;
    setDisplayName(value);
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

  const handleChangePhoneNum = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { value } = e.target;
    setPhoneNum(value);
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

  const handleChangePassword = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { value } = e.target;

    setPassword(value);
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

  const handleChangeEditPassword = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { value } = e.target;

    setEditPassword(value);
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

  const handleChangeConfirmEditPassword = (
    e: React.ChangeEvent<HTMLInputElement>,
  ) => {
    const { value } = e.target;

    setConfirmEditPassword(value);
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

  const UserInfoPathData = {
    phoneNumber: phoneNum,
    displayName,
  };

  const PasswordPathData = {
    nowPassword: password,
    newPassword: editPassword,
    confirmPassword: confirmEditPassword,
  };

  const resetUserInfoValue = () => {
    const resetValue = {
      isSuccess: '',
      errorMessage: '',
    };
    setIsDisplayNameSuccess(resetValue);
    setIsPhoneNumSuccess(resetValue);
  };

  const resetPasswordValue = () => {
    const resetValue = {
      isSuccess: '',
      errorMessage: '',
    };
    setPassword('');
    setEditPassword('');
    setConfirmEditPassword('');
    setIsPasswordSuccess(resetValue);
    setIsEditPasswordSuccess(resetValue);
    setIsConfirmEditPasswordSuccess(resetValue);
  };

  const handleClickEdit = async () => {
    setIsEdit(true);
    if (isEdit === true) {
      try {
        await patchUserInfo(UserInfoPathData, userInfo.memberId);
        alert('정보가 수정되었습니다.');
        setIsEdit(false);
        resetUserInfoValue();
      } catch (error) {
        console.error(error);
      }
    }
  };

  const handleClickEditPassword = async () => {
    setIsEditPassword(true);
    if (isEditPassword === true) {
      try {
        await patchUserPassword(PasswordPathData, userInfo.memberId);
        alert('정보가 수정되었습니다.');
        setIsEditPassword(false);
        resetPasswordValue();
      } catch (error) {
        console.error(error);
      }
    }
  };

  const handleCancelEditUserInfo = () => {
    setIsEdit(false);
    setDisplayName(userInfo.displayName);
    setPhoneNum(userInfo.phoneNumber);
    resetUserInfoValue();
  };

  const handleCancelEditPassword = () => {
    setIsEditPassword(false);
    resetPasswordValue();
  };

  useEffect(() => {
    setDisplayName(userInfo.displayName);
    setPhoneNum(userInfo.phoneNumber);
  }, [userInfo]);

  return (
    <UserCardContainer>
      <ProfileImage src={userData.profileImage} />
      <UserInfoContainer>
        <NameTagContainer>
          <Name>{userData.name}</Name>
          {userInfo.state === 'STUDENT' ? (
            <UserState> 학생</UserState>
          ) : (
            <UserState> 강사</UserState>
          )}
        </NameTagContainer>
        <UserInfo>이메일: {userData.email}</UserInfo>

        {/* 정보 수정 */}
        {isEdit ? (
          <EditUserInfoContainer>
            <EditUserInfoInput
              placeholder={userData.displayName}
              onChange={handleChangeDisplayName}
              value={displayName}
              errorMessage={isDisplayNameSuccess.errorMessage}
              color={colorSelector(isDisplayNameSuccess.isSuccess)}
            />
            <EditUserInfoInput
              placeholder={userData.phoneNumber}
              onChange={handleChangePhoneNum}
              value={phoneNum}
              errorMessage={isPhoneNumSuccess.errorMessage}
              color={colorSelector(isPhoneNumSuccess.isSuccess)}
            />
          </EditUserInfoContainer>
        ) : (
          <>
            <UserInfo>전화번호: {userData.phoneNumber}</UserInfo>
            <UserInfo>닉네임: {userData.displayName}</UserInfo>
          </>
        )}

        {isEditPassword ? (
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
        ) : null}

        <ButtonContainer>
          {isEdit && !isEditPassword ? (
            <EditBtn onClick={handleCancelEditUserInfo}>정보 취소</EditBtn>
          ) : null}

          {!isEdit && isEditPassword ? (
            <EditBtn onClick={handleCancelEditPassword}>암호 취소</EditBtn>
          ) : null}

          {isEdit ? null : (
            <EditBtn onClick={handleClickEditPassword}>
              {isEditPassword ? '저장' : '암호수정'}
            </EditBtn>
          )}

          {isEditPassword ? null : (
            <EditBtn onClick={handleClickEdit}>
              {isEdit ? '저장' : '수정'}
            </EditBtn>
          )}
        </ButtonContainer>
      </UserInfoContainer>
    </UserCardContainer>
  );
}

export default UserCard;

import styled from 'styled-components';
import useUserInfoStore from 'stores/userInfoStore';
import { useState } from 'react';
import EditUserInfo from 'components/myPage/EditUserInfo';
import { validatePhoneNum, validatePassword } from 'utils/regex';
import EditUserInfoInput from 'components/myPage/EditUserInfo';
import theme from '../theme';

const { colors } = theme;
const { fontSizes } = theme;

const Container = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  width: 50%;
  margin: 0 auto;
`;

const ProfileImage = styled.img`
  width: 14rem;
  height: 14rem;
  object-fit: cover;
  border-radius: 50%;
  margin-top: 2rem;
  margin-right: 5rem;
`;

const UserInfo = styled.div`
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  margin-top: 1rem;
  width: 60rem;
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

const StudentTag = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 0.2rem 0.5rem;
  color: ${colors.pointColor};
  font-size: ${fontSizes.md};
  border: solid 0.2rem ${colors.pointColor};
  border-radius: 0.8rem;
`;

const TeacherTag = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 0.2rem 0rem;
  color: ${colors.pointColor};
  font-size: ${fontSizes.md};
  border: solid 0.2rem ${colors.pointColor};
  border-radius: 0.8rem;
`;

const Email = styled.p`
  font-size: ${fontSizes.md};
  margin: 0.3rem 0;
`;

const DisplayName = styled.p`
  font-size: ${fontSizes.md};
  margin: 0.3rem 0;
`;

const EditBtn = styled.span`
  width: 100%;
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

const EditUserPassword = styled.input`
  padding: 0.1rem 0.2rem;
  border: solid 0.1rem ${colors.gray};
  border-radius: 0.2rem;
  margin-bottom: 0.2rem;
`;

function MyPage() {
  const { userInfo } = useUserInfoStore(state => state);
  const [isEdit, setIsEdit] = useState(false);

  const [displayName, setDisplayName] = useState('');
  const [isDisplayNameSuccess, setIsDisplayNameSuccess] = useState({
    isSuccess: '',
    errorMessage: '',
  });

  const [phoneNum, setPhoneNum] = useState('');
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

  const handleClickEdit = () => {
    setIsEdit(!isEdit);
  };

  console.log(userInfo.memberState);

  return (
    <Container>
      <ProfileImage src={userData.profileImage} />
      <UserInfo>
        <NameTagContainer>
          <Name>{userData.name}</Name>
          {userInfo.memberState === 'STUDENT' ? (
            <StudentTag>학생</StudentTag>
          ) : (
            <StudentTag>강사</StudentTag>
          )}
        </NameTagContainer>
        <Email>{userData.email}</Email>
        {isEdit ? (
          <>
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
            <EditUserInfoContainer>
              <span>암호</span>
              <EditUserPassword placeholder="현재 암호" type="password" />
              <EditUserPassword placeholder="변경할 암호" type="password" />
              <EditUserPassword placeholder="암호 확인" type="password" />
            </EditUserInfoContainer>
          </>
        ) : (
          <DisplayName>{userData.displayName}</DisplayName>
        )}
        <EditBtn onClick={handleClickEdit}>{isEdit ? '저장' : '수정'}</EditBtn>
      </UserInfo>
    </Container>
  );
}

export default MyPage;

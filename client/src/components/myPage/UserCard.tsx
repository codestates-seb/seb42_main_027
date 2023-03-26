import { useEffect, useState } from 'react';
import useUserInfoStore from 'stores/userInfoStore';
import patchUserInfo from 'apis/patchUserInfo';
import styled from 'styled-components';
import { validatePassword, validatePhoneNum } from 'utils/regex';
import theme from 'theme';
import patchUserPassword from 'apis/patchUserPassword';
import EditUserInfoInput from './EditUserInfoInput';
import EditUserInfo from './EditUserInfo';

const { colors } = theme;
const { fontSizes } = theme;

const UserCardContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 30%;
  /* margin: 0 auto */
  padding: 0 8rem;
  height: 100vh;
  box-shadow: 5px 0 5px rgba(0, 0, 0, 0.1);
  border-left: 0.1rem solid rgb(235, 235, 235);
`;

const ProfileImage = styled.img`
  width: 10rem;
  height: 10rem;
  object-fit: cover;
  border-radius: 50%;
  box-shadow: 0px 0 5px rgba(0, 0, 0, 0.3);
  margin-top: 8rem;
`;

const UserInfoContainer = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: flex-start;
  margin-top: 1rem;
  width: 20rem;
`;

const NameTagContainer = styled.div`
  display: flex;
  justify-content: center;
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

function UserCard() {
  const { userInfo } = useUserInfoStore(state => state);

  const userData = {
    profileImage: 'https://i.pravatar.cc/150?img=7',
    name: userInfo.username,
    email: userInfo.email,
    displayName: userInfo.displayName,
    phoneNumber: userInfo.phoneNumber,
    password: userInfo.password,
  };

  return (
    <UserCardContainer>
      <ProfileImage src={userData.profileImage} />
      <UserInfoContainer>
        <NameTagContainer>
          <Name>{userData.displayName}</Name>
          {userInfo.state === 'STUDENT' ? (
            <UserState> 학생</UserState>
          ) : (
            <UserState> 강사</UserState>
          )}
        </NameTagContainer>
        <UserInfo>{userData.email}</UserInfo>
        <EditUserInfo />
      </UserInfoContainer>
    </UserCardContainer>
  );
}

export default UserCard;

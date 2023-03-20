import styled from 'styled-components';
import useUserInfoStore from 'stores/userInfoStore';
import { useState } from 'react';
import EditUserInfo from 'components/myPage/EditUserInfo';
import { validatePhoneNum, validatePassword } from 'utils/regex';
import EditUserInfoInput from 'components/myPage/EditUserInfo';
import patchUserInfo from 'apis/patchUserInfo';
import UserCard from 'components/myPage/UserCard';
import theme from '../theme';

const { colors } = theme;
const { fontSizes } = theme;

const Container = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
`;

function MyPage() {
  return (
    <Container>
      <UserCard />
      <div>
        <div>
          <h2>내가 작성한 게시글</h2>
          <input type="text" list="list" placeholder="dkanrjsk" />
          <datalist id="list">
            <option value="컴퓨터공학과" />
            <option value="영어영문과" />
            <option value="경영학과" />
            <option value="사회체육과" />
          </datalist>
        </div>
      </div>
    </Container>
  );
}

export default MyPage;

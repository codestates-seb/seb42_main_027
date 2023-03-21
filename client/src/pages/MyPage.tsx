import styled from 'styled-components';
import useUserInfoStore from 'stores/userInfoStore';
import { useEffect, useState } from 'react';
import EditUserInfo from 'components/myPage/EditUserInfo';
import { validatePhoneNum, validatePassword } from 'utils/regex';
import EditUserInfoInput from 'components/myPage/EditUserInfo';
import patchUserInfo from 'apis/patchUserInfo';
import UserCard from 'components/myPage/UserCard';
import getFreePosts from 'apis/getFreePosts';
import theme from '../theme';

const { colors } = theme;
const { fontSizes } = theme;

const Container = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
`;

const ListContainer = styled.div`
  display: flex;
  align-items: center;
  justify-content: space-between;
  width: 60%;
`;

const PostListContainer = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  width: 45%;
`;

const PostListTitleContainer = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 100%;
`;

function MyPage() {
  const { userInfo } = useUserInfoStore(state => state);

  const patchFreePosts = async () => {
    try {
      await getFreePosts(userInfo.memberId);
    } catch (error) {
      console.error(error);
    }
  };

  useEffect(() => {
    patchFreePosts();
  });

  return (
    <Container>
      <UserCard />
      <ListContainer>
        <PostListContainer>
          <PostListTitleContainer>
            <h2>내가 작성한 게시글</h2>
            <select>
              <option>자유 게시판</option>
              <option>질문 게시판</option>
              <option>강의 리뷰</option>
            </select>
          </PostListTitleContainer>
        </PostListContainer>

        <PostListContainer>
          <PostListTitleContainer>
            <h2>내가 작성한 게시글</h2>
            <select>
              <option>자유 게시판</option>
              <option>질문 게시판</option>
              <option>강의 리뷰</option>
            </select>
          </PostListTitleContainer>
        </PostListContainer>
      </ListContainer>
    </Container>
  );
}

export default MyPage;

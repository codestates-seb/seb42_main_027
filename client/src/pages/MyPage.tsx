import styled from 'styled-components';
import useUserInfoStore from 'stores/userInfoStore';
import { useEffect, useState } from 'react';
import UserCard from 'components/myPage/UserCard';
import getFreePosts from 'apis/getFreePosts';
import getComment from 'apis/getComment';
import CountIcon from 'assets/icons/countIcon';
import { Link, Routes } from 'react-router-dom';
import PostList from 'components/myPage/PostList';
import CommentList from 'components/myPage/CommentList';
import EditUserInfo from 'components/myPage/EditUserInfo';
import theme from '../theme';
import PostsCommentsList from './PostsCommentsList';

const Container = styled.div`
  display: flex;
  /* flex-direction: column; */
  justify-content: flex-end;
  align-items: center;
  width: 100%;
`;

function MyPage() {
  const { userInfo } = useUserInfoStore(state => state);
  const [selectPostCategories, setSelectPostCategories] =
    useState('자유 게시판');
  const [selectCommentCategories, setSelectCommentCategories] =
    useState('자유 게시판');
  const [sideNav, setSideNav] = useState('myProfile');
  const { memberId } = userInfo;

  useEffect(() => {
    getFreePosts(memberId, selectPostCategories);
    getComment(memberId, selectCommentCategories);
  }, [
    selectPostCategories,
    selectCommentCategories,
    userInfo,
    memberId,
    sideNav,
  ]);

  return (
    <Container>
      <UserCard sideNav={sideNav} setSideNav={setSideNav} />

      {sideNav === 'myProfile' ? (
        <EditUserInfo />
      ) : (
        <PostsCommentsList
          selectCommentCategories={selectCommentCategories}
          setSelectCommentCategories={setSelectCommentCategories}
          selectPostCategories={selectPostCategories}
          setSelectPostCategories={setSelectPostCategories}
        />
      )}
    </Container>
  );
}

export default MyPage;

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
  const [freePosts, setFreePosts] = useState([]);
  const [freeComments, setFreeComments] = useState([]);
  const { memberId } = userInfo;

  const patchFreePosts = async (id: number | null, select: string) => {
    try {
      // 테스트용 memberId 사용
      const response = await getFreePosts(id, select);
      setFreePosts(response);
    } catch (error) {
      console.error(error);
    }
  };

  const patchComments = async (id: number | null, select: string) => {
    try {
      // 테스트용 memberId 사용
      const response = await getComment(id, select);
      setFreeComments(response);
    } catch (error) {
      console.error(error);
    }
  };

  useEffect(() => {
    patchFreePosts(memberId, selectPostCategories);
    patchComments(memberId, selectCommentCategories);
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
          freePosts={freePosts}
          setFreePosts={setFreePosts}
          freeComments={freeComments}
          setFreeComments={setFreeComments}
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

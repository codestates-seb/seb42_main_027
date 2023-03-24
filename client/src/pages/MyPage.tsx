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
  flex-direction: column;
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

const List = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  height: 20rem;
  overflow: auto;
`;

const ListItem = styled.div`
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 1rem 0;
  border-bottom: 0.1rem solid #ccc;
`;

// 제목 컴포넌트
const Title = styled.h2`
  margin: 0;
  font-size: ${fontSizes.md};
`;

// 카테고리 컴포넌트
const Category = styled.span`
  font-size: ${fontSizes.sm};
  color: ${colors.fontColor};
`;

// 조회수 및 투표수 컴포넌트
const Count = styled.span`
  font-size: ${fontSizes.sm};
  color: ${colors.fontColor};
`;

type PostProps = {
  freeId: number;
  title: string;
  content: string;
  category: string;
  viewCount: number;
  voteCount: number;
  createdAt: string;
  modifiedAt: null;
  member: {
    memberId: number;
    displayName: string;
    iconImageUrl: null;
    state: string;
  };
  commentsListNum: number;
};

function MyPage() {
  const { userInfo } = useUserInfoStore(state => state);
  const [freePosts, setFreePosts] = useState([]);
  const [select, setSelect] = useState('자유 게시판');

  const patchFreePosts = async (id: number, select: string) => {
    try {
      // 테스트용 memberId 사용
      const response = await getFreePosts(id, select);
      setFreePosts(response);
    } catch (error) {
      console.error(error);
    }
  };

  const handleChangeBoard = (e: React.ChangeEvent<HTMLSelectElement>) => {
    setSelect(e.target.value);
  };

  useEffect(() => {
    patchFreePosts(4, select);
  }, [select]);

  return (
    <Container>
      <UserCard />
      <ListContainer>
        <PostListContainer>
          <PostListTitleContainer>
            <h2>내가 작성한 게시글</h2>
            <select onChange={handleChangeBoard} value={select}>
              <option value="자유 게시판">자유 게시판</option>
              <option value="질문 게시판">질문 게시판</option>
              <option value="강의 리뷰">강의 리뷰</option>
            </select>
          </PostListTitleContainer>
          <List>
            {freePosts.map((post: PostProps) => {
              return (
                <ListItem key={post.freeId}>
                  <Category>{post.category}</Category>
                  <Title>{post.title}</Title>
                  <Count>
                    조회수: {post.viewCount} | 투표수: {post.voteCount}
                  </Count>
                </ListItem>
              );
            })}
          </List>
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

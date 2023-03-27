import CommentList from 'components/myPage/CommentList';
import PostList from 'components/myPage/PostList';
import styled from 'styled-components';

const ListContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: space-between;
  width: 50%;
  height: 100vh;
`;

type PostsCommentsListProps = {
  selectPostCategories: string;
  setSelectPostCategories: React.Dispatch<React.SetStateAction<string>>;
  selectCommentCategories: string;
  setSelectCommentCategories: React.Dispatch<React.SetStateAction<string>>;
};

function PostsCommentsList({
  selectPostCategories,
  setSelectPostCategories,
  selectCommentCategories,
  setSelectCommentCategories,
}: PostsCommentsListProps) {
  return (
    <ListContainer>
      <PostList
        selectPostCategories={selectPostCategories}
        setSelectPostCategories={setSelectPostCategories}
      />
      <CommentList
        selectCommentCategories={selectCommentCategories}
        setSelectCommentCategories={setSelectCommentCategories}
      />
    </ListContainer>
  );
}
export default PostsCommentsList;

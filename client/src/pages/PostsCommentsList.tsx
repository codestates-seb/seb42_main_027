import CommentList from 'components/myPage/CommentList';
import PostList from 'components/myPage/PostList';
import styled from 'styled-components';

const ListContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  width: 60%;
  height: 100vh;
  background-color: #f9fbfc;
`;

type PostsCommentsListProps = {
  freePosts: never[];
  freeComments: never[];
  setFreePosts: React.Dispatch<React.SetStateAction<never[]>>;
  setFreeComments: React.Dispatch<React.SetStateAction<never[]>>;
  selectPostCategories: string;
  setSelectPostCategories: React.Dispatch<React.SetStateAction<string>>;
  selectCommentCategories: string;
  setSelectCommentCategories: React.Dispatch<React.SetStateAction<string>>;
};

function PostsCommentsList({
  freePosts,
  setFreePosts,
  freeComments,
  setFreeComments,
  selectPostCategories,
  setSelectPostCategories,
  selectCommentCategories,
  setSelectCommentCategories,
}: PostsCommentsListProps) {
  return (
    <ListContainer>
      <PostList
        freePosts={freePosts}
        setFreePosts={setFreePosts}
        selectPostCategories={selectPostCategories}
        setSelectPostCategories={setSelectPostCategories}
      />
      <CommentList
        freeComments={freeComments}
        setFreeComments={setFreeComments}
        selectCommentCategories={selectCommentCategories}
        setSelectCommentCategories={setSelectCommentCategories}
      />
    </ListContainer>
  );
}
export default PostsCommentsList;

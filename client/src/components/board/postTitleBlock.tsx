import styled from 'styled-components';
import theme from 'theme';

interface PostData {
  id: number;
  category: string;
  username: string;
  userimg: string;
  title: string;
  content: string;
  view: string;
  comment: string;
  vote: string;
  createdAt: string;
  updatedAt: string;
}

interface Props {
  ele: PostData;
}

function PostTitleBlock(ele: Props) {
  console.log('ele', ele);
  const data = ele;

  return (
    <Container>
      <Category>{data.ele.category}</Category>
      <Title>{data.ele.title}</Title>
      <UserData>
        <div>{data.ele.userimg}</div>
        <div>{data.ele.username}</div>
        <div>{data.ele.createdAt}</div>
      </UserData>
      <Count>
        <div>{data.ele.view}</div>
        <div>{data.ele.comment}</div>
        <div>{data.ele.vote}</div>
      </Count>
    </Container>
  );
}

const Container = styled.div`
  display: flex;
  flex-direction: column;
  position: relative;
  width: 100%;
  height: 100px;
  padding: 1rem ${theme.gap.px20};
  border-bottom: 1px solid ${theme.colors.gray};
`;

const Category = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  width: 40px;
  height: 18px;
  border: 1px solid ${theme.colors.pointColor};
  border-radius: 5px;
  font-size: ${theme.fontSizes.sm};
  font-weight: bold;
  color: ${theme.colors.pointColor};
  margin-bottom: 4px;
`;

const Title = styled.div`
  display: flex;
  font-size: ${theme.fontSizes.md};
  font-weight: bold;
  margin-bottom: 6px;

  &:hover {
    color: ${theme.colors.pointColor};
  }
`;

const UserData = styled.div`
  display: flex;
`;

const Count = styled.div`
  display: flex;
  position: absolute;
  right: ${theme.gap.px20};
  bottom: 1rem;
`;

export default PostTitleBlock;

import { Link } from 'react-router-dom';
import styled from 'styled-components';
import theme from 'theme';
import { FaUserCircle } from 'react-icons/fa';
import { AiOutlineEye } from 'react-icons/ai';
import { BiCommentDetail, BiLike } from 'react-icons/bi';

interface PostData {
  id: number;
  category: string;
  selected?: boolean;
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
    <Container className={data.ele.category === '공지' ? 'notice' : ''}>
      <Top>
        <Category>{data.ele.category}</Category>
        {data.ele.selected ? <SelectedAnswer>답변채택</SelectedAnswer> : null}
      </Top>
      <Link to="articles">
        <Title>{data.ele.title}</Title>
      </Link>
      <UserData>
        <ProfileImg>
          <FaUserCircle className="user-profile-img" />
        </ProfileImg>
        <div>{data.ele.username}</div>
        <div>{data.ele.createdAt}</div>
      </UserData>
      <Count>
        <div>
          <AiOutlineEye />
          {data.ele.view}
        </div>
        <div>
          <BiCommentDetail />
          {data.ele.comment}
        </div>
        <div>
          <BiLike />
          {data.ele.vote}
        </div>
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

  &.notice {
    background-color: ${theme.colors.palePurple};
  }
`;

const Top = styled.div`
  display: flex;
  margin-bottom: 4px;
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
  background-color: ${theme.colors.white};
`;

const SelectedAnswer = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  height: 18px;
  border: 1px solid ${theme.colors.pointColor};
  border-radius: 5px;
  font-size: ${theme.fontSizes.sm};
  font-weight: bold;
  color: ${theme.colors.white};
  background-color: ${theme.colors.pointColor};
  padding: 3px 8px;
  margin-left: 5px;
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

const ProfileImg = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  width: 16px;
  height: 16px;

  > .user-profile-img {
    width: ;
    filter: ${theme.filterColors.pointColor};
  }
`;

const Count = styled.div`
  display: flex;
  position: absolute;
  right: ${theme.gap.px20};
  bottom: 1rem;
`;

export default PostTitleBlock;

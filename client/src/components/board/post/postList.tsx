import { useState } from 'react';
import { useLocation } from 'react-router';
import styled from 'styled-components';

import { dummyData, dummyData2 } from './dummyData';
import FreeBoardMenu from './boardMenu';
import PostTitleBlock from './postTitleBlock';

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

function PostList() {
  const [isPending, setIsPending] = useState(false);
  const urlData = useLocation().pathname;
  let listData: any;

  if (urlData === '/free') {
    listData = dummyData;
  } else if (urlData === '/qna') {
    listData = dummyData2;
  }

  return (
    <Container>
      <FreeBoardMenu />
      {isPending ? (
        <h1>로딩페이지가 들어갈 자리입니다.</h1>
      ) : (
        <div>
          {listData.length === 0 ? (
            <h1>작성된 게시물이 없습니다.</h1>
          ) : (
            <div>
              {listData.map((ele: PostData) => {
                return <PostTitleBlock key={ele.id} ele={ele} />;
              })}
            </div>
          )}
        </div>
      )}
    </Container>
  );
}

const Container = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
`;

export default PostList;

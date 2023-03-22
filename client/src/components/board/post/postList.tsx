import { useState, useEffect } from 'react';
import { useLocation } from 'react-router';
import styled from 'styled-components';
import theme from 'theme';

import getPostList from 'apis/board/getPostList';
// import { dummyData, dummyData2 } from './dummyData';
import FreeBoardMenu from './boardMenu';
import PostTitleBlock from './postTitleBlock';

interface Data {
  freeId?: number;
  questionId?: number;
  category?: 'string';
  selected?: boolean;
  member: {
    memberId: number;
    iconImageUrl?: string;
    displayName: string;
    state: string;
  };
  title: 'string';
  content: 'string';
  viewCount: number;
  voteCount: number;
  createdAt: string;
  modifiedAt?: string;
  commentsListNum: number;
}

interface PageInfo {
  page: number;
  size: number;
  totalElements: number;
  totalPages: number;
}

function PostList() {
  const [isPending, setIsPending] = useState(true);
  const [listData, setListData] = useState<Data[] | []>([]);
  const urlData = useLocation().pathname;

  const fetchPostList = async () => {
    try {
      if (urlData === '/free') {
        const buffer = await getPostList('frees', 1);
        setListData(buffer.data);
        // listData = dummyData;
        setIsPending(false);
      } else if (urlData === '/qna') {
        // listData = dummyData2;
      }
      // listData = listData.data;
    } catch (err) {
      console.error(err);
    }
  };

  useEffect(() => {
    fetchPostList();
  }, []);

  console.log('listData', listData);

  return (
    <Container>
      <FreeBoardMenu />
      {isPending ? (
        <MainDiv>
          <h1>로딩페이지가 들어갈 자리입니다.</h1>
        </MainDiv>
      ) : (
        <MainDiv>
          {listData.length === 0 ? (
            <h1>작성된 게시물이 없습니다.</h1>
          ) : (
            <div>
              {listData.map((ele: Data) => {
                return <PostTitleBlock key={ele.freeId} ele={ele} />;
              })}
            </div>
          )}
        </MainDiv>
      )}
    </Container>
  );
}

const Container = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
`;

const MainDiv = styled.div`
  display: flex;
  margin-bottom: ${theme.gap.px60};
  width: 100%;

  > div {
    display: flex;
    flex-direction: column;
    width: 100%;
  }
`;
export default PostList;

import { useState, useEffect } from 'react';
import { useLocation } from 'react-router';
import styled from 'styled-components';
import theme from 'theme';

import { boardMenuStore } from 'stores/boardMenuStore';
import { boardSortStore } from 'stores/boardSortStore';

import getPostList from 'apis/board/getPostList';
// import { dummyData, dummyData2 } from './dummyData';
import FreeBoardMenu from './boardMenu';
import PostTitleBlock from './postTitleBlock';

interface Data {
  freeId?: number;
  questionId?: number;
  category: string;
  adoptAnswerId?: number;
  member: {
    memberId: number;
    iconImageUrl?: string;
    displayName: string;
    state: string;
  };
  title: string;
  content: string;
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
  const { setSelectedMenuStore } = boardMenuStore(state => state);
  const { setSelectedSortStore } = boardSortStore(state => state);
  const urlData = useLocation().pathname;

  const fetchPostList = async () => {
    try {
      if (urlData === '/free') {
        const buffer = await getPostList('frees', 1);
        setListData(buffer.data);
        setIsPending(false);
      } else if (urlData === '/qna') {
        const buffer = await getPostList('qnas/questions', 1);
        setListData(buffer.data);
        setIsPending(false);
      }
    } catch (err) {
      console.error(err);
    }
  };

  useEffect(() => {
    fetchPostList();
    setSelectedMenuStore('0');
    setSelectedSortStore('최신순');
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
              {urlData === '/free' ? (
                <div>
                  {listData.map((ele: Data) => {
                    return <PostTitleBlock key={ele.freeId} ele={ele} />;
                  })}
                </div>
              ) : (
                <div>
                  {listData.map((ele: Data) => {
                    return <PostTitleBlock key={ele.questionId} ele={ele} />;
                  })}
                </div>
              )}
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

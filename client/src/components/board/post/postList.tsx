import { useState, useEffect } from 'react';
import { useLocation } from 'react-router';
import styled from 'styled-components';
import theme from 'theme';

import { boardMenuStore } from 'stores/boardMenuStore';
import { boardSortStore } from 'stores/boardSortStore';

import getPostList from 'apis/board/getPostList';
import Pagenation from '../Pagenation';
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
  uploadImages?: string[] | [];
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

const DefaultPageInfo = {
  page: 0,
  size: 0,
  totalElements: 0,
  totalPages: 0,
};

function PostList() {
  const [isPending, setIsPending] = useState(true);
  const [listData, setListData] = useState<Data[] | []>([]);
  const { selectedMenuStore } = boardMenuStore(state => state);
  const { selectedSortStore } = boardSortStore(state => state);
  const [pageInfo, setPageInfo] = useState<PageInfo>(DefaultPageInfo);
  const [curPage, setCurPage] = useState<number>(1);
  const urlData = useLocation().pathname;

  const fetchPostList = async () => {
    try {
      if (urlData === '/free') {
        const buffer = await getPostList(
          'frees',
          selectedMenuStore,
          selectedSortStore,
          1,
        );
        setListData(buffer.data);
        setPageInfo(buffer.pageInfo);
        setIsPending(false);
      } else if (urlData === '/qna') {
        const buffer = await getPostList(
          'qnas/questions',
          selectedMenuStore,
          selectedSortStore,
          1,
        );
        setListData(buffer.data);
        setPageInfo(buffer.pageInfo);
        setIsPending(false);
      }
    } catch (err) {
      console.error(err);
    }
  };

  useEffect(() => {
    fetchPostList();
  }, [selectedMenuStore, selectedSortStore]);

  console.log('listData', listData);
  console.log('pageInfo', pageInfo);

  return (
    <Container>
      <FreeBoardMenu />
      {isPending ? (
        <MainDiv>
          <NoData>로딩페이지가 들어갈 자리입니다.</NoData>
        </MainDiv>
      ) : (
        <MainDiv>
          {listData.length === 0 ? (
            <NoData>작성된 게시물이 없습니다.</NoData>
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
      <Pagenation
        size={pageInfo.totalPages}
        currentPage={curPage}
        pageSize={15}
        setCurPage={setCurPage}
      />
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

const NoData = styled.div`
  display: flex;
  width: 100%;
  justify-content: center;
  align-items: center;
  padding-top: ${theme.gap.px60};
`;

export default PostList;

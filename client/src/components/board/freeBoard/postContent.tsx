import { useEffect, useState } from 'react';
import { Link, useLocation, useParams, useNavigate } from 'react-router-dom';
import useUserInfoStore from 'stores/userInfoStore';

import styled from 'styled-components';
import theme from 'theme';
import Button from 'components/common/Button';
import ProfileIcon from 'assets/icons/defaultProfileIcon';
import CountIcon from 'assets/icons/countIcon';

import DeletePost from 'apis/board/deletePost';
import getPostDetail from 'apis/board/getPostDetail';
import CalElapsedTime from '../post/calElapsedTime';

import GoBackMenu from '../post/goBackMenu';
import CommentList from './commentList';

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
  // comments?: any;
}

function PostContent() {
  const { userInfo } = useUserInfoStore(state => state);
  const navigate = useNavigate();
  const [isPending, setIsPending] = useState(true);
  const [listData, setListData] = useState<Data | Record<string, never>>({});
  const urlData = useLocation().pathname.slice(0, 5);
  const idData = Number(useParams().id);

  let elapsedTime: number = CalElapsedTime(listData.createdAt);
  let calTime = '';

  if (!isPending) {
    if (elapsedTime < 60) {
      calTime = '방금 전';
    } else if (elapsedTime < 3600) {
      elapsedTime = Math.round(elapsedTime / 60);
      calTime = `${elapsedTime}분 전`;
    } else if (elapsedTime < 43200) {
      elapsedTime = Math.round(elapsedTime / 3600);
      calTime = `${elapsedTime}시간 전`;
    } else if (elapsedTime < 129600) {
      elapsedTime = Math.round(elapsedTime / 43200);
      calTime = `${elapsedTime}일 전`;
    } else {
      calTime = listData.createdAt.slice(0, 24);
    }
  }

  const fetchPostDetail = async () => {
    try {
      if (urlData === '/free') {
        const buffer = await getPostDetail('frees', idData);
        setListData(buffer.data);
        // listData = dummyData;
        setIsPending(false);
      } else {
        // listData = dummyData2;
      }
      // listData = listData.data;
    } catch (err) {
      console.error(err);
    }
  };

  const fetchDeletePost = async () => {
    try {
      const confirm = window.confirm('게시글을 삭제하시겠습니까?');
      if (confirm) {
        if (urlData === '/free') {
          await DeletePost('frees', idData);
          alert('게시물을 삭제하였습니다.');
          navigate('/free');
        } else {
          await DeletePost('qnas/answers', idData);
          alert('게시물을 삭제하였습니다.');
          navigate('/qna');
        }
      }
    } catch (err) {
      console.error(err);
    }
  };

  useEffect(() => {
    fetchPostDetail();
  }, []);
  console.log('userInfoData:', userInfo.memberId);

  console.log(listData);
  console.log('userInfo memeberId', userInfo.memberId);
  // console.log('comments', listData.comments[0].content);

  return (
    <Container>
      <GoBackMenu />
      {isPending ? (
        <h1>로딩페이지가 들어갈 자리입니다.</h1>
      ) : (
        <div>
          <TitleDiv>
            <Top>
              <Category>{listData.category}</Category>
              {listData.member.memberId === userInfo.memberId ? (
                <UDBtnDiv>
                  <Link to="edit">
                    <Button.UDWhiteBtn>수정</Button.UDWhiteBtn>
                  </Link>
                  <Button.UDWhiteBtn onClick={fetchDeletePost}>
                    삭제
                  </Button.UDWhiteBtn>
                </UDBtnDiv>
              ) : null}
            </Top>
            <H2>{listData.title}</H2>
            <Writer>
              <ProfileIcon.Default />
              <div>{listData.member.displayName}</div>
              <div> · {calTime}</div>
              <View>
                <CountIcon.View />
                {listData.viewCount}
              </View>
            </Writer>
          </TitleDiv>
          <MainDiv>
            <div>{listData.content}</div>
            <VoteDiv>
              <Button.VoteDownBtn>
                <CountIcon.VoteDown />
              </Button.VoteDownBtn>
              <VoteCount>{listData.voteCount}</VoteCount>
              <Button.VoteUpBtn>
                <CountIcon.VoteUp />
              </Button.VoteUpBtn>
            </VoteDiv>
          </MainDiv>
          <CommentCnt>{listData.commentsListNum}개의 댓글</CommentCnt>
          <CommentList />
          {/* <CommentList data={listData.comments} /> */}
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

const TitleDiv = styled.div`
  display: flex;
  flex-direction: column;
  padding: ${theme.gap.px20};
  border-bottom: 1px solid ${theme.colors.gray};
`;

const Top = styled.div`
  display: flex;
  justify-content: space-between;
  margin-bottom: ${theme.gap.px10};
`;

const H2 = styled.h2`
  font-weight: bold;
  font-size: ${theme.fontSizes.subTitle};
  margin-bottom: ${theme.gap.px20};
`;

const Writer = styled.div`
  display: flex;
  align-items: center;
  position: relative;
`;
const View = styled.div`
  display: flex;
  position: absolute;
  right: 0;
  bottom: 0;
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
  margin-bottom: 4px;
`;

const UDBtnDiv = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: calc(${theme.gap.px40} * 2 + 6px);
`;

const MainDiv = styled.div`
  display: flex;
  flex-direction: column;
  padding: ${theme.gap.px20};
  border-bottom: 1px solid ${theme.colors.gray};
`;

const VoteDiv = styled.div`
  display: flex;
  justify-content: right;
  margin-bottom: ${theme.gap.px120};
`;

const VoteCount = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  width: 1.875rem;
  height: 1.25rem;
  font-size: ${theme.fontSizes.xs};
  border-top: 1px solid ${theme.colors.gray};
  border-bottom: 1px solid ${theme.colors.gray};
`;

const CommentCnt = styled.div`
  padding: ${theme.gap.px20};
  border-bottom: 1px solid ${theme.colors.gray};
`;

export default PostContent;

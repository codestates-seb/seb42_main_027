/* eslint-disable react/no-array-index-key */
/* eslint-disable react/button-has-type */
/* eslint-disable react/require-default-props */
import GlobalStyle from 'GlobalStyles';
import styled from 'styled-components';
import { FlexContainer } from 'pages/review/TeacherList/ReviewPage';
import theme from 'theme';
import { useEffect, useState } from 'react';
import axios from 'axios';
import Loading from 'components/review/Loading';

import { useParams, useNavigate } from 'react-router';
import { Link } from 'react-router-dom';
import isLogin from 'utils/isLogin';
import GoBackMenu from 'components/board/post/goBackMenu';
import useUserInfoStore from 'stores/userInfoStore';

const defaultDetailData = {
  eventId: 1,
  imageUrl: '이미지 URL',
  title: 'Default Title',
  content:
    '<p>이벤트 게시글 첫 번째 줄의 내용입니다</p><p>이벤트 게시글 두 번째 줄의 내용입니다</p><p>이벤트 게시글 세 번째 줄의 내용입니다</p>',
  date: '날짜수정',
};

function EventDetail() {
  const [detailData, setDetailData] = useState(defaultDetailData);
  const [isPending, setIsPending] = useState(true);

  const { eventId } = useParams();
  const Authorization = localStorage.getItem('token');
  const navigate = useNavigate();

  const { userInfo } = useUserInfoStore(state => state);

  useEffect(() => {
    setIsPending(true);
    axios
      .get(`${process.env.REACT_APP_API_URL}/boards/events/ours/${eventId}`, {
        headers: { Authorization, 'ngrok-skip-browser-warning': '69420' },
      })
      .then((res: any) => {
        return res.data.data;
      })
      .then(data => {
        console.log(data);
        setDetailData(data);
        setIsPending(false);
      })
      .catch(() => {
        setIsPending(false);
      });
  }, []);

  return (
    <Container>
      {isPending ? (
        <Loading />
      ) : (
        <FlexContainer
          width="62.5%"
          dir="col"
          backColor="white"
          justify="start"
        >
          <Title>
            <H2>이벤트게시판</H2>
            <p>다양한 이벤트를 한 눈에 볼 수 있는 공간입니다.</p>
          </Title>
          <FlexContainer
            display={isLogin() ? 'flex' : 'none'}
            width="100%"
            justify="right"
          >
            <Link to="update">
              <button>수정</button>
            </Link>
            <button
              onClick={() => {
                axios
                  .delete(
                    `${process.env.REACT_APP_API_URL}/boards/events/ours/${eventId}`,
                    {
                      headers: {
                        Authorization,
                        'ngrok-skip-browser-warning': '69420',
                      },
                    },
                  )
                  .then(() => {
                    navigate(-1);
                  });
              }}
            >
              삭제
            </button>
          </FlexContainer>
          <GoBackMenu />
          <FlexContainer width="100%" dir="col">
            {/* 이벤트 제목 */}
            <TitleDiv>
              <H2>{detailData.title}</H2>
            </TitleDiv>
            {/* 이벤트 내용 */}
            <MainDiv>
              <FlexContainer
                dir="col"
                align="start"
                gap="0.4rem"
                dangerouslySetInnerHTML={{ __html: detailData.content }}
              />
            </MainDiv>
            <CommentContainer>댓글 금지</CommentContainer>
          </FlexContainer>
        </FlexContainer>
      )}
    </Container>
  );
}

export default EventDetail;

type Container = {
  reviewOpen?: boolean;
};

const Container = styled.div<Container>`
  width: 100%;

  display: flex;
  justify-content: center;
  align-items: start;
`;

const TitleDiv = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
  padding: ${theme.gap.px20};
  border-bottom: 1px solid ${theme.colors.gray};
`;

const H2 = styled.h2`
  font-weight: bold;
  font-size: ${theme.fontSizes.subTitle};
  margin-bottom: ${theme.gap.px10};
`;

const MainDiv = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
  padding: ${theme.gap.px20};
  border-bottom: 1px solid ${theme.colors.gray};
  img {
    max-width: 100%;
  }
`;

const Title = styled.div`
  box-sizing: border-box;
  width: 100%;
  height: 150px;
  padding: 45px 42px;
  border-radius: 25px;
  background-color: ${theme.colors.palePurple};
`;

const CommentContainer = styled.div`
  display: flex;
  font-style: normal;
  color: gray;
  flex-direction: column;
  width: 100%;
  min-height: 5rem;
  margin-bottom: ${theme.gap.px100};
`;

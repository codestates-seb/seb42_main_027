/* eslint-disable no-nested-ternary */
/* eslint-disable react/no-array-index-key */
import GlobalStyle from 'GlobalStyles';
import styled from 'styled-components';
import Pagenation from 'components/review/Pagenation';

import { useEffect, useState } from 'react';
import axios from 'axios';
import Loading from 'components/review/Loading';
import theme from 'theme';
import FreeBoardMenu from 'components/board/post/boardMenu';
import Event from 'components/review/Event';
import useUserInfoStore from 'stores/userInfoStore';
import isLogin from 'utils/isLogin';
import GoBackMenu from 'components/board/post/goBackMenu';
import { FlexContainer } from '../TeacherList/ReviewPage';
import { SmallFont } from '../TeacherDetail/Information';

type EventType = {
  eventId: number;
  status: string;
  imageUrl: string;
  title: string;
  content: string;
  date: string;
};

// type CrolingEventType = {
//   eventId: number;
//   imageUrl: string;
//   title: string;
//   content: string;
//   date: string;
// };

type PageInfo = {
  page: number;
  size: number;
  totalElements: number;
  totalPages: number;
};

const defaultEvent = {
  eventId: 1,
  status: 'none',
  imageUrl: 'http://',
  title:
    'Default 너무길어서 잘릴 제목의 길이인데 더 이상 할 말이 생각나지 않는다 이러면 어떻게하지',
  content: 'Default Content',
  date: '2023-03-02 ~ 2023-03-21',
};
const defaultEvent2 = {
  eventId: 1,
  status: 'none',
  imageUrl: 'http://',
  title: 'Default Title',
  content: 'Default Content',
  date: '2023-03-02 ~ 2023-04-29',
};

const defaultPageInfo = {
  page: 1,
  size: 6,
  totalElements: 6,
  totalPages: 1,
};

function EventList() {
  const [event, setEvent] = useState<EventType[]>([
    defaultEvent2,
    defaultEvent,
    defaultEvent,
  ]);
  const [crolingEvent, setCrolingEvent] = useState([]);
  const [isPending, setIsPending] = useState(false);

  const [pageInfo, setPageInfo] = useState<PageInfo>(defaultPageInfo);
  const [curPage, setCurPage] = useState<number>(1);
  const [pageSize, setPageSize] = useState<number>(6);

  const listArr = ['번호', '말머리', '제목', '기간', '조회'];
  const { userInfo } = useUserInfoStore(state => state);

  useEffect(() => {
    setIsPending(true);
    axios
      .get(
        `${process.env.REACT_APP_API_URL}/boards/events/ours?page=${curPage}`,
        {
          headers: { 'ngrok-skip-browser-warning': '69420' },
        },
      )
      .then((res: any) => {
        console.log(res.data.data);
        console.log(res.data.pageInfo);
        setEvent(res.data.data);
        setPageInfo(res.data.pageInfo);
        setIsPending(false);
      })
      .catch(() => {
        setIsPending(false);
      });
  }, [curPage]);

  return (
    <EventContainer>
      <FlexContainer width="62.5%" dir="col" gap="0">
        <GlobalStyle />
        <Title>
          <H2>이벤트게시판</H2>
          <p>다양한 이벤트를 한 눈에 볼 수 있는 공간입니다.</p>
        </Title>
        <GoBackMenu />

        {isLogin() ? (
          <FreeBoardMenu />
        ) : (
          <FlexContainer padding="2rem">관리자 게시판</FlexContainer>
        )}

        {isPending ? (
          <Loading />
        ) : (
          <FlexContainer
            width="100%"
            dir="col"
            gap="0.1rem"
            padding="0 0 2rem 0"
          >
            <FlexContainer
              width="100%"
              borderTop="2px solid black"
              borderBottom="1px solid black"
              padding="1.5rem 1rem"
            >
              {listArr.map((el, index) => {
                return (
                  <FlexContainer
                    key={index}
                    grow={el === '제목' ? 7 : el === '기간' ? 2 : 1}
                  >
                    <SmallFont>{el}</SmallFont>
                  </FlexContainer>
                );
              })}
            </FlexContainer>
            {!event.length ? (
              <FlexContainer height="50vh">
                등록된 이벤트가 없습니다
              </FlexContainer>
            ) : (
              <FlexContainer width="100%" dir="col" gap="0.1rem">
                {event.map((el, index) => {
                  return <Event key={index} event={el} />;
                })}
              </FlexContainer>
            )}
          </FlexContainer>
        )}

        <Pagenation
          size={pageInfo.totalPages}
          currentPage={curPage}
          pageSize={pageSize}
          setCurPage={setCurPage}
        />
      </FlexContainer>
    </EventContainer>
  );
}

export default EventList;

const EventContainer = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
`;

const Title = styled.div`
  width: 100%;
  height: 150px;
  padding: 45px 42px;
  border-radius: 25px;
  background-color: ${theme.colors.palePurple};
`;

const H2 = styled.h2`
  font-weight: bold;
  font-size: ${theme.fontSizes.subTitle};
  margin-bottom: ${theme.gap.px10};
`;

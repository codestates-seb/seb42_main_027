/* eslint-disable no-nested-ternary */
/* eslint-disable react/no-array-index-key */
import GlobalStyle from 'GlobalStyles';
import styled from 'styled-components';
import Pagenation from 'components/review/Pagenation';

import { useEffect, useState } from 'react';
import axios from 'axios';
import Loading from 'components/review/Loading';
import theme from 'theme';

import Event from 'components/review/Event';
import useUserInfoStore from 'stores/userInfoStore';
import Button from 'components/common/Button';
import { Link } from 'react-router-dom';
import { HiPencil } from 'react-icons/hi';
import CrolingEvent from 'components/review/CrolingEvent';
import { Title } from 'pages/FreeBoard';
import { FlexContainer } from '../TeacherList/ReviewPage';
import { SmallFont } from '../TeacherDetail/Information';

type EventType = {
  eventId: number;
  imageUrl: string;
  title: string;
  viewCount: number;
  date: string;
};

type CrolingEventType = {
  imageUrl: string;
  title: string;
  source: string;
  hyperLink: string;
  date: string;
};

type PageInfo = {
  page: number;
  size: number;
  totalElements: number;
  totalPages: number;
};

const defaultEvent = {
  eventId: 1,
  imageUrl: 'http://',
  title:
    'Default 너무길어서 잘릴 제목의 길이인데 더 이상 할 말이 생각나지 않는다 이러면 어떻게하지',
  date: '2023-03-02 ~ 2023-03-21',
  viewCount: 0,
};

const defaultPageInfo = {
  page: 1,
  size: 6,
  totalElements: 6,
  totalPages: 1,
};

function EventList() {
  const [event, setEvent] = useState([]);
  const [crolingEvent, setCrolingEvent] = useState([]);
  const [isPending, setIsPending] = useState(false);

  const [pageInfo, setPageInfo] = useState<PageInfo>(defaultPageInfo);
  const [curPage, setCurPage] = useState<number>(1);
  const [pageSize, setPageSize] = useState<number>(15);

  const listArr = ['말머리', '제목', '기간', '상태'];
  const { userInfo } = useUserInfoStore(state => state);

  useEffect(() => {
    setIsPending(true);
    axios
      .get(`${process.env.REACT_APP_API_URL}/boards/events/ours?page=1`, {
        headers: { 'ngrok-skip-browser-warning': '69420' },
      })
      .then((res: any) => {
        setEvent(res.data.data);
        setPageInfo(res.data.pageInfo);
      })
      .then(() => {
        axios
          .get(
            `${process.env.REACT_APP_API_URL}/boards/events/theirs?page=${
              curPage - 1
            }&size=${pageSize}`,
            {
              headers: { 'ngrok-skip-browser-warning': '69420' },
            },
          )
          .then(res => {
            return res.data;
          })
          .then(data => {
            setCrolingEvent(data.data);
            setPageInfo(data.pageInfo);
            setIsPending(false);
          })
          .catch(() => {
            setIsPending(false);
          });
      })
      .catch(() => {
        setIsPending(false);
      });
  }, [curPage]);

  return (
    <EventContainer>
      <FlexContainer width="62.5%" dir="col" gap="2rem">
        <GlobalStyle />
        <Title>
          <H2>이벤트게시판</H2>
          <p>다양한 이벤트를 한 눈에 볼 수 있는 공간입니다.</p>
        </Title>

        {/* 작성하기 버튼 */}
        {userInfo.state === 'ADMIN' ? (
          <MenuDiv>
            {localStorage.getItem('token') ? (
              <Link to="articles/write">
                <Button.WriteBtn>
                  <HiPencil />
                  작성하기
                </Button.WriteBtn>
              </Link>
            ) : (
              <Link to="../login">
                <Button.WriteBtn>
                  <HiPencil />
                  작성하기
                </Button.WriteBtn>
              </Link>
            )}
          </MenuDiv>
        ) : null}

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
              padding="1.5rem 1.3rem 1.5rem 2rem"
            >
              {listArr.map((el, index) => {
                return (
                  <FlexContainer
                    key={index}
                    width={
                      el === '제목' ? '60%' : el === '기간' ? '17%' : '12%'
                    }
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
              <FlexContainer
                width="100%"
                dir="col"
                gap="0rem"
                padding="0 0 2rem 0"
              >
                {event.map((el, index) => {
                  return <Event key={index} event={el} />;
                })}
                {crolingEvent.map((el, index) => {
                  return <CrolingEvent key={index} event={el} />;
                })}
              </FlexContainer>
            )}
            {pageInfo.totalPages ? (
              <Pagenation
                size={pageInfo.totalPages}
                currentPage={curPage}
                pageSize={pageInfo.size}
                setCurPage={setCurPage}
              />
            ) : null}
          </FlexContainer>
        )}
      </FlexContainer>
    </EventContainer>
  );
}

export default EventList;

const EventContainer = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
`;

const MenuDiv = styled.div`
  display: flex;
  justify-content: right;
  align-items: center;
  width: 100%;
  min-height: 100px;
  border-bottom: 1px solid ${theme.colors.gray};
`;

const H2 = styled.h2`
  font-weight: bold;
  font-size: ${theme.fontSizes.subTitle};
  margin-bottom: ${theme.gap.px10};
`;

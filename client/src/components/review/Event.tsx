import styled from 'styled-components';
import { FlexContainer } from 'pages/review/TeacherList/ReviewPage';
import { Link } from 'react-router-dom';
import { useState } from 'react';
import CountIcon from 'assets/icons/countIcon';

type Props = {
  event: {
    eventId: number;
    title: string;
    date: string;
    viewCount: number;
    imageUrl: string;
  };
};

function Event({ event }: Props) {
  const [offsetX, setOffsetX] = useState(0);
  const [offsetY, setOffsetY] = useState(0);

  const endCheck = (last: string) => {
    const today = new Date();
    const lastDay = last.slice(13);

    if (Number(today.getFullYear()) < Number(lastDay.slice(0, 4))) return false;
    if (Number(today.getMonth()) + 1 < Number(lastDay.slice(5, 7)))
      return false;
    if (Number(today.getDate()) <= Number(lastDay.slice(8, 10))) return false;
    return true;
  };

  return (
    <Container>
      {/* 말머리 */}
      <FlexContainer width="14%">
        <MiddleFont>자체</MiddleFont>
      </FlexContainer>
      {/* 제목 */}
      <FlexContainer
        onMouseOver={e => {
          setOffsetX(e.clientX);
          setOffsetY(e.clientY);
        }}
        width="58%"
        dir="col"
        align="start"
      >
        <Link to={`/eventdetail/${event.eventId}`}>
          <TitleSpan
            offsetX={offsetX}
            offsetY={offsetY}
            end={endCheck(event.date)}
          >
            {event.title.length > 30
              ? `${event.title.slice(0, 35)}...`
              : event.title}
            <Img
              src={event.imageUrl || 'http://placehold.it/200X200'}
              alt="dummyImage"
            />
          </TitleSpan>
        </Link>
      </FlexContainer>
      {/* 기간 */}
      <FlexContainer width="13%">
        <FlexContainer dir="col" gap="0" align="start">
          <DateFont>{event.date.slice(0, 12)}</DateFont>
          <DateFont>{event.date.slice(12)}</DateFont>
        </FlexContainer>
      </FlexContainer>
      {/* 상태/조회수 */}
      <FlexContainer width="12%" gap="0.2rem">
        <CountIcon.View />
        {event.viewCount}
      </FlexContainer>
    </Container>
  );
}

export default Event;

type Container = {
  first?: boolean;
};

const Container = styled.div<Container>`
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 1rem;
  padding: 1rem 1.5rem;
  border-top: ${props => (props.first ? '2px solid black' : null)};
  border-bottom: 0.5px solid black;
`;

const MiddleFont = styled.div`
  font-size: 0.9rem;
  color: orange;
  font-weight: bold;
`;

const DateFont = styled.span`
  font-size: 0.8rem;
  color: gray;
`;

type TitleSpan = {
  offsetX: number;
  offsetY: number;
  end?: boolean;
};

const TitleSpan = styled.span<TitleSpan>`
  font-size: 1rem;
  font-weight: bold;
  color: ${props => (props.end ? 'gray' : 'black')};
  text-decoration: ${props => (props.end ? 'line-through' : 'none')};

  :hover {
    color: ${props => (props.end ? 'gray' : 'red')};
    img {
      display: flex;
      position: fixed;
      top: ${props => `${props.offsetY - 90}px`};
      left: ${props => `${props.offsetX}px`};
      z-index: 1;
    }
  }
`;

const Img = styled.img`
  display: none;
  width: 10.5rem;
  height: 6rem;
  border-radius: 0.5rem;
  background-color: #b8b8b8;
`;

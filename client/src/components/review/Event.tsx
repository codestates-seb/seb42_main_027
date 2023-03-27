/* eslint-disable react/button-has-type */
/* eslint-disable react/require-default-props */
import GlobalStyle from 'GlobalStyles';
import styled from 'styled-components';
import { FlexContainer } from 'pages/review/TeacherList/ReviewPage';
import { Link } from 'react-router-dom';
import { useState } from 'react';

type Props = {
  event: {
    eventId: number;
    status: string;
    imageUrl: string;
    title: string;
    content: string;
    date: string;
  };
};

function Event({ event }: Props) {
  const [offsetX, setOffsetX] = useState(0);
  const [offsetY, setOffsetY] = useState(0);

  const endCheck = (last: string) => {
    const today = new Date();
    const lastDay = last.slice(13);
    console.log(Number(today.getMonth()) + 1, Number(lastDay.slice(5, 7)));

    if (Number(today.getFullYear()) < Number(lastDay.slice(0, 4))) return false;
    if (Number(today.getMonth()) + 1 < Number(lastDay.slice(5, 7)))
      return false;
    if (Number(today.getDate()) <= Number(lastDay.slice(8, 10))) return false;
    return true;
  };

  return (
    <Container>
      <FlexContainer width="5rem">
        <MiddleFont>{event.eventId}</MiddleFont>
      </FlexContainer>
      <FlexContainer width="6rem">
        <MiddleFont>자체</MiddleFont>
      </FlexContainer>
      <FlexContainer
        onMouseOver={e => {
          setOffsetX(e.clientX);
          setOffsetY(e.clientY);
        }}
        width="26rem"
        dir="col"
        align="start"
        gap="0.3rem"
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
            <Img src="http://placehold.it/200X200" alt="dummyImage" />
          </TitleSpan>
        </Link>
      </FlexContainer>

      <FlexContainer width="6rem" padding="0 0 0 1rem">
        <DateFont>{event.date}</DateFont>
      </FlexContainer>
      <FlexContainer width="6rem" padding="0 0 0 1.2rem">
        {30}
      </FlexContainer>
    </Container>
  );
}

export default Event;

type Container = {
  first?: boolean;
};
type TitleSpan = {
  offsetX: number;
  offsetY: number;
  end?: boolean;
};
const Container = styled.div<Container>`
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 1rem;
  padding: 1rem 0;
  border-top: ${props => (props.first ? '2px solid black' : null)};
  border-bottom: 0.5px solid black;
`;

const MiddleFont = styled.div`
  font-size: 0.9rem;
`;

const DateFont = styled.span`
  font-size: 0.8rem;
  color: gray;
`;

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

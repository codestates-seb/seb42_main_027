/* eslint-disable react/button-has-type */
/* eslint-disable react/require-default-props */
import GlobalStyle from 'GlobalStyles';
import styled from 'styled-components';
import { FlexContainer } from 'pages/review/TeacherList/ReviewPage';
import { Link } from 'react-router-dom';
import { useState } from 'react';

type Props = {
  event: {
    imageUrl: string;
    title: string;
    source: string;
    hyperLink: string;
    date: string;
    // imageUrl: string;
  };
};

function CrolingEvent({ event }: Props) {
  const [offsetX, setOffsetX] = useState(0);
  const [offsetY, setOffsetY] = useState(0);
  const id = Math.random() * (500 - 1) + 1;

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
      <FlexContainer width="6rem">
        <MiddleFont>외부</MiddleFont>
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
        <TitleSpan end={endCheck(event.date)} href={event.hyperLink}>
          {event.title.length > 30
            ? `${event.title.slice(0, 35)}...`
            : event.title}
        </TitleSpan>
      </FlexContainer>

      <FlexContainer width="6rem" padding="0 0 0 1rem">
        <DateFont>{event.date}</DateFont>
      </FlexContainer>
      <FlexContainer width="6rem" padding="0 0 0 1.2rem">
        {endCheck(event.date) ? <EndFont>종료</EndFont> : '진행중'}
      </FlexContainer>
    </Container>
  );
}

export default CrolingEvent;

type Container = {
  first?: boolean;
};
type TitleSpan = {
  end?: boolean;
};

const Container = styled.div<Container>`
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 1rem;
  padding: 0.5rem 0;
  border-top: ${props => (props.first ? '2px solid black' : null)};
  border-bottom: 0.5px solid black;
`;

const MiddleFont = styled.div`
  font-size: 0.9rem;
  color: #6667ab;
  font-weight: bold;
`;

const DateFont = styled.span`
  font-size: 0.8rem;
  color: gray;
`;

const TitleSpan = styled.a<TitleSpan>`
  font-size: 1rem;
  font-weight: bold;
  color: ${props => (props.end ? 'gray' : 'black')};
  text-decoration: ${props => (props.end ? 'line-through' : 'none')};

  :hover {
    color: ${props => (props.end ? 'gray' : 'red')};
  }
  cursor: pointer;
`;

const Img = styled.img`
  display: none;
  width: 10.5rem;
  height: 6rem;
  border-radius: 0.5rem;
  background-color: #b8b8b8;
`;

const EndFont = styled.span`
  font-size: small;
  color: gray;
`;

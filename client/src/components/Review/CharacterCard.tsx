/* eslint-disable react/no-array-index-key */
import styled from 'styled-components';

type Props = {
  teacher: {
    name: string;
    belong: string;
    subject: string;
    img: string;
    best: string;
    grade: number;
  }[];
};

function CharacterCard({ teacher }: Props) {
  return (
    <Container>
      {teacher.map((el, index) => {
        return (
          <CardContainer key={index}>
            <Img src="http://placehold.it/200X200" alt="dummyImage" />
            <Span>{el.name}</Span>
            <Span>{el.belong}</Span>
            <Span>{el.subject}</Span>
            <LargeSpan>
              {el.best.length > 7 ? `${el.best.slice(0, 7)}...` : el.best}
            </LargeSpan>
            <Span>⭐️ {el.grade}</Span>
          </CardContainer>
        );
      })}
    </Container>
  );
}

export default CharacterCard;

const Container = styled.div`
  margin: 2rem 0 4rem 0;
  width: 50rem;
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  align-items: center;
  gap: 2rem;
`;

const CardContainer = styled.div`
  width: 12rem;
  padding: 0.5rem;
  padding-bottom: 1rem;
  background-color: white;
  border: 0.3rem solid #6667ab;
  border-radius: 1.2rem;

  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  gap: 1rem;
`;

const Img = styled.img`
  width: 10.5rem;
  height: 6rem;
  border-radius: 0.5rem;
  background-color: #b8b8b8;
`;

const Span = styled.span`
  font-weight: bold;
`;

const LargeSpan = styled.span`
  margin: 1rem 0;
  font-size: large;
  font-weight: bold;
`;

import styled from 'styled-components';
import StarrySky from 'components/common/StarrySky';

function Main() {
  return (
    <Container>
      <StarrySky />
      <div>이곳에 작성하시면 됩니다</div>
    </Container>
  );
}

const Container = styled.div`
  width: 100%;

  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
`;

export default Main;

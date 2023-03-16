import styled from 'styled-components';
import theme from 'theme';

import GoBackMenu from './goBackMenu';

function PostContent() {
  return (
    <Container>
      <GoBackMenu />
      <h1>글 작성 페이지입니다.</h1>
    </Container>
  );
}

const Container = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
`;

export default PostContent;

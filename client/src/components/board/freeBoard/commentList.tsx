import styled from 'styled-components';
import theme from 'theme';

import WriteComment from '../comment/writeComment';
import CommentBlock from './commentBlock';

function CommentList() {
  return (
    <Container>
      <WriteCommentDiv>
        <WriteComment />
      </WriteCommentDiv>
      {/* {data.length === 0 ? null : (
        <div>
          {data.map((ele: any) => {
            return <CommentBlock key={ele.freeCommentId} ele={data} />;
          })}
        </div>
      )} */}
      <CommentBlock />
    </Container>
  );
}

const Container = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  min-height: 253px;
  margin-bottom: ${theme.gap.px100};
`;

const WriteCommentDiv = styled.div`
  display: flex;
  width: 100%;
  padding-bottom: calc(${theme.gap.px60} + 7px);
  border-bottom: 1px solid ${theme.colors.gray};
`;
export default CommentList;

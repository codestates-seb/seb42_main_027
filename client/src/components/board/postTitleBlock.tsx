import styled from 'styled-components';

function PostTitleBlock() {
  return (
    <Container>
      <div>일상</div>
      <div>제목</div>
      {/* <div>{post.title}</div> */}
      <div>
        <div>프로필이미지</div>
        <div>닉네임</div>
        <div>작성시간</div>
      </div>
      <div>
        <div>조회수</div>
        <div>댓글수</div>
        <div>추천수</div>
      </div>
    </Container>
  );
}

const Container = styled.div``;

export default PostTitleBlock;

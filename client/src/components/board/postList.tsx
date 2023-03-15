import { useState } from 'react';
import styled from 'styled-components';

import { dummyData } from './dummyData';
import PostTitleBlock from './postTitleBlock';

function PostList() {
  const [isPending, setIsPending] = useState(false);

  console.log(dummyData);

  return (
    <Container>
      <div>
        {isPending ? (
          <h1>로딩페이지가 들어갈 자리입니다.</h1>
        ) : (
          <h1>메뉴버튼이 들어갈 자리입니다.</h1>
        )}
      </div>
      <div>
        {isPending ? (
          <h1>로딩페이지가 들어갈 자리입니다.</h1>
        ) : (
          <div>
            {dummyData.length === 0 ? (
              <h1>작성된 게시물이 없습니다.</h1>
            ) : (
              <div>
                <h1>이곳에 게시글 목록이 나타납니다.</h1>
                <PostTitleBlock />
                {/* <div>
                  {dummyData.map((obj, idx) => {
                    return <PostTitleBlock key={idx} post={obj} />;
                  })}
                </div> */}
              </div>
            )}
          </div>
        )}
      </div>
    </Container>
  );
}

const Container = styled.div``;

export default PostList;

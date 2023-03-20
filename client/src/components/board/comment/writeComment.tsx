import { useState } from 'react';
import { Link, useLocation, useParams } from 'react-router-dom';

import styled from 'styled-components';
import theme from 'theme';
import Button from 'components/common/Button';
import ProfileIcon from 'assets/icons/defaultProfileIcon';
import { AiOutlineExclamationCircle } from 'react-icons/ai';

import { useIsLoginStore } from 'stores/loginStore';
import useUserInfoStore from 'stores/userInfoStore';
import PostComment from 'apis/board/postComment';

function WriteComment() {
  const urlData = useLocation().pathname.slice(0, 4);
  const paramsData = useParams();
  const { isLoginInStore } = useIsLoginStore(state => state);
  const { userInfo } = useUserInfoStore(state => state);
  const [comment, setComment] = useState<string>('');

  console.log('comment', comment);

  const postHandler = async () => {
    try {
      if (comment === '') {
        alert('내용은 빈 칸으로 둘 수 없습니다.');
      } else {
        const data = {
          content: comment,
          createdAt: `${new Date()}`,
        };
        console.log('submit data', data);
        if (urlData === '/fre') {
          await PostComment(data, 'frees', Number(paramsData.id));
          // 새로고침 넣기
        }
      }
    } catch (err) {
      console.error(err);
    }
  };

  return (
    <Container>
      <Main>
        <InputDiv>
          <ProfileIcon.Default />
          {isLoginInStore ? (
            <Textarea
              defaultValue={comment}
              placeholder="댓글을 입력해 주세요."
              onChange={e => setComment(e.target.value)}
            />
          ) : (
            <GuideDiv>
              <AiOutlineExclamationCircle />
              <div>
                댓글을 쓰려면 <Link to="/login">로그인</Link>이 필요합니다.
              </div>
            </GuideDiv>
          )}
        </InputDiv>
        <SubmitDiv>
          <CommentBtn
            className={isLoginInStore ? '' : 'disabled'}
            onClick={postHandler}
          >
            댓글 쓰기
          </CommentBtn>
        </SubmitDiv>
      </Main>
    </Container>
  );
}

const Container = styled.div`
  display: flex;
  width: 100%;
  min-height: 206px;
`;

const Main = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  margin: ${theme.gap.px20};
  border: 1px solid ${theme.colors.gray};
  border-radius: 5px;
`;

const InputDiv = styled.div`
  display: flex;
  margin: ${theme.gap.px20};
  margin-bottom: 13px;
`;

const Textarea = styled.textarea`
  display: flex;
  width: 100%;
  resize: none;
  padding: ${theme.gap.px10};
  border: 1px solid ${theme.colors.gray};
  border-radius: 5px;
  margin-left: ${theme.gap.px10};
`;

const SubmitDiv = styled.div`
  display: flex;
  justify-content: right;
  align-items: center;
  margin-right: ${theme.gap.px20};
`;

const CommentBtn = styled(Button.WriteBtn)`
  width: ${theme.gap.px120};

  &.disabled {
    opacity: 0.5;
    pointer-events: none;
  }
`;

const GuideDiv = styled.div`
  display: flex;
  width: 100%;
  height: 73px;
  padding: ${theme.gap.px10};
  border: 1px solid ${theme.colors.gray};
  border-radius: 5px;
  color: ${theme.colors.gray};
  a {
    font-weight: bold;
    text-decoration: underline;
    color: ${theme.colors.pointColor};
  }
  > div {
    margin-left: 6px;
  }
`;

export default WriteComment;

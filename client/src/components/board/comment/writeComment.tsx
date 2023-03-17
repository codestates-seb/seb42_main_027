import { Link } from 'react-router-dom';

import styled from 'styled-components';
import theme from 'theme';
import Button from 'components/common/Button';
import ProfileIcon from 'assets/icons/defaultProfileIcon';
import { AiOutlineExclamationCircle } from 'react-icons/ai';

import { useIsLoginStore } from 'stores/loginStore';

function WriteComment() {
  const { isLogin, setIsLogin } = useIsLoginStore(state => state);

  return (
    <Container>
      <Main>
        <InputDiv>
          <ProfileIcon.Default />
          {isLogin ? (
            <input />
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
          <CommentBtn className={isLogin ? '' : 'disabled'}>
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

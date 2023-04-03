/* eslint-disable react/button-has-type */
/* eslint-disable react/require-default-props */
import GlobalStyle from 'GlobalStyles';
import styled from 'styled-components';
import { FlexContainer } from 'pages/review/TeacherList/ReviewPage';
import { useNavigate } from 'react-router-dom';
import { SmallFont } from 'pages/review/TeacherDetail/Information';
import { useState } from 'react';
import axios from 'axios';
import theme from 'theme';
import ProfileIcon from 'assets/icons/defaultProfileIcon';
import { useIsLoginStore } from 'stores/loginStore';
import Button from 'components/common/Button';

type Props = {
  lectureReviewId: number;
};
function ReviewCommentCreate({ lectureReviewId }: Props) {
  const [content, setContent] = useState<string>('');
  const Authorization = localStorage.getItem('token');
  const { isLoginInStore } = useIsLoginStore(state => state);

  const createHandler = () => {
    if (!content) {
      alert('빈 곳을 채워주세요!');
    } else {
      const data = {
        content,
        createdAt: new Date(),
        lectureReviewId,
      };

      axios
        .post(
          `${process.env.REACT_APP_API_URL}/comments/reviews/lectures`,
          data,
          {
            headers: { Authorization, 'ngrok-skip-browser-warning': '69420' },
          },
        )
        .then(() => {
          window.location.reload();
        });
    }
  };

  return (
    <Container>
      <Main>
        <InputDiv>
          <ProfileIcon.Default />
          {isLoginInStore ? (
            <Textarea
              value={content}
              onChange={e => {
                setContent(e.target.value);
              }}
            />
          ) : null}
        </InputDiv>
        <SubmitDiv>
          <CommentBtn
            className={isLoginInStore ? '' : 'disabled'}
            onClick={createHandler}
          >
            댓글 쓰기
          </CommentBtn>
        </SubmitDiv>
      </Main>
    </Container>
  );
}

export default ReviewCommentCreate;

type Container = {
  first?: boolean;
};

const Container = styled.div<Container>`
  display: flex;
  width: 100%;
  min-height: 206px;
`;

const Main = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  margin: 0.5rem;
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
  min-height: 6rem;
  resize: none;
  padding: ${theme.gap.px10};
  border: 1px solid ${theme.colors.gray};
  border-radius: 5px;
  margin-left: ${theme.gap.px10};
`;

const Ubutton = styled.button`
  padding: 0.4rem;
  background-color: gray;
  color: white;
  border-radius: 0.4rem;
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

/* eslint-disable prefer-destructuring */
/* eslint-disable react/no-array-index-key */
/* eslint-disable jsx-a11y/label-has-associated-control */
import GlobalStyle from 'GlobalStyles';
import { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router';
import styled from 'styled-components';
import axios from 'axios';

import TextEditor from 'components/common/textEditor';
import { FlexContainer } from '../TeacherList/ReviewPage';
import { UploadButton } from '../TeacherList/CreateTeacher';

const defaultData = {
  imageUrl: '이미지 URL',
  title: '제목',
  content: '내용',
  date: new Date(),
};

function UpdateEvent() {
  const [title, setTitle] = useState<string>('');
  const [content, setContent] = useState<string>('');
  const [start, setStart] = useState<string>('');
  const [end, setEnd] = useState<string>('');

  const { eventId } = useParams();

  const navigate = useNavigate();
  const Authorization = localStorage.getItem('token');

  useEffect(() => {
    axios
      .get(`${process.env.REACT_APP_API_URL}/boards/events/ours/${eventId}`, {
        headers: { Authorization, 'ngrok-skip-browser-warning': '69420' },
      })
      .then((res: any) => {
        return res.data.data;
      })
      .then(data => {
        console.log(data);
        setTitle(data.title);
        setContent(data.content);
      })
      .catch(() => {
        alert('Get Data Fail!');
      });
  }, []);

  const updateHandler = () => {
    if (!title || !content) {
      alert('빈 곳을 채워주세요!');
    } else if (
      new Date(
        Number(start.slice(0, 4)),
        Number(start.slice(5, 7)) - 1,
        Number(start.slice(8, 10)),
      ) >
      new Date(
        Number(end.slice(0, 4)),
        Number(end.slice(5, 7)) - 1,
        Number(end.slice(8, 10)),
      )
    ) {
      alert('기간을 제대로 설정해주세요');
    } else {
      const data = {
        title,
        content,
        imageUrl: '이미지 URL',
        date: `${start} ~ ${end}`,
      };

      axios
        .patch(
          `${process.env.REACT_APP_API_URL}/boards/events/ours/${eventId}`,
          data,
          {
            headers: {
              Authorization,
              'ngrok-skip-browser-warning': 'asdasdas',
            },
          },
        )
        .then(res => {
          return res.data.data;
        })
        .then(() => {
          navigate(-1);
        })
        .catch(() => {
          alert('fail');
        });
    }
  };

  return (
    <Container>
      <FlexContainer dir="col" width="40rem" gap="3rem">
        {/* 이벤트 제목 */}
        <FlexContainer dir="col" align="start" gap="0" width="100%">
          <label htmlFor="title">Title</label>
          <Input
            id="title"
            placeholder="제목"
            value={title}
            onChange={e => {
              setTitle(e.target.value);
            }}
          />
        </FlexContainer>
        {/* 이벤트 기간 */}
        <FlexContainer dir="col" align="start" gap="0" width="100%">
          <label htmlFor="date">Date</label>
          <DateInput
            id="date"
            type="date"
            placeholder="기간"
            value={start}
            onChange={e => {
              setStart(e.target.value);
            }}
          />
          <DateInput
            id="date"
            type="date"
            placeholder="기간"
            value={end}
            onChange={e => {
              setEnd(e.target.value);
            }}
          />
        </FlexContainer>
        {/* 이벤트 내용 */}
        <FlexContainer dir="col" align="start" gap="0" width="100%">
          <label htmlFor="content">Content</label>
          <TextEditor
            textContent={content}
            setTextContent={setContent}
            path="boards/events/contents"
          />
        </FlexContainer>
        {/* 등록 버튼 */}
        <FlexContainer>
          <UploadButton onClick={updateHandler}>이벤트 수정</UploadButton>
          <UploadButton
            onClick={() => {
              navigate(-1);
            }}
          >
            수정 취소
          </UploadButton>
        </FlexContainer>
      </FlexContainer>
    </Container>
  );
}

export default UpdateEvent;

const Container = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;

  padding: 2rem;
`;

const Input = styled.input`
  width: 100%;
  padding: 0.3rem 0.5rem;
  border: 0.5px solid gray;
`;

const DateInput = styled.input`
  width: 40%;
  padding: 0.3rem 0.5rem;
  border: 0.5px solid gray;
`;

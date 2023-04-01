/* eslint-disable react/no-array-index-key */
/* eslint-disable jsx-a11y/label-has-associated-control */
import GlobalStyle from 'GlobalStyles';
import { useState, useEffect } from 'react';
import { useNavigate } from 'react-router';
import styled from 'styled-components';
import axios from 'axios';
import TextEditor from 'components/common/textEditor';
import { FlexContainer } from '../TeacherList/ReviewPage';
import { UploadButton } from '../TeacherList/CreateTeacher';

function CreateEvent() {
  const [title, setTitle] = useState<string>('');
  const [content, setContent] = useState<string>('');
  const [imageUrl, setImageUrl] = useState<string>('');
  const [imagePreview, setImagePreview] = useState<string>('');
  const [start, setStart] = useState<string>('');
  const [end, setEnd] = useState<string>('');

  const navigate = useNavigate();
  const Authorization = localStorage.getItem('token');

  useEffect(() => {
    window.scrollTo(0, 0);
    console.log(content);
  }, [content]);

  const createHandler = () => {
    if (!title || !content || !start || !end) {
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
        imageUrl,
        date: `${start} ~ ${end}`,
      };

      axios
        .post(`${process.env.REACT_APP_API_URL}/boards/events/ours`, data, {
          headers: {
            Authorization,
            'ngrok-skip-browser-warning': 'asdasdas',
          },
        })
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
        {/* 썸네일 */}
        <FlexContainer dir="col" align="start" width="100%">
          <label htmlFor="thumnail">썸네일</label>
          <input
            id="thumnail"
            type="file"
            accept="image/*"
            onChange={(e: any) => {
              if (e.target.files.length) {
                console.log(e.target.files[0]);
                const formData = new FormData();
                formData.append('image', e.target.files[0]);
                formData.append('filePath', 'boards/events/thumnail');

                axios
                  .post(`${process.env.REACT_APP_API_URL}/upload`, formData, {
                    headers: {
                      'ngrok-skip-browser-warning': 'asdasdas',
                    },
                  })
                  .then(res => res.data.data)
                  .then((data: string) => {
                    setImageUrl(data);
                  });

                const fileReader = new FileReader();
                fileReader.readAsDataURL(e.target.files[0]);
                fileReader.onload = (e: any) => {
                  setImagePreview(e.target.result);
                };
              } else {
                setImageUrl('');
                setImagePreview('http://placehold.it/200X200');
              }
            }}
          />
          <Img src={imagePreview} />
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
          <UploadButton
            onClick={() => {
              navigate(-1);
            }}
          >
            등록 취소
          </UploadButton>
          <UploadButton onClick={createHandler}>이벤트 등록</UploadButton>
        </FlexContainer>
      </FlexContainer>
    </Container>
  );
}

export default CreateEvent;

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

const Img = styled.img`
  width: 340px;
  height: 240px;
  border-radius: 0.5rem;
  background-color: #b8b8b8;
`;

import React, { useRef, useMemo } from 'react';
import axios, { AxiosError } from 'axios';
import ReactQuill from 'react-quill';
import 'react-quill/dist/quill.snow.css';
import styled from 'styled-components';
import theme from 'theme';

const apiUrl = process.env.REACT_APP_API_URL;

type Props = {
  textContent: string;
  setTextContent: React.Dispatch<React.SetStateAction<string>>;
  path: string;
};

function TextEditor({ textContent, setTextContent, path }: Props) {
  const QuillRef: any = useRef();
  console.log('textContent', textContent);

  const imageHandler = () => {
    const input = document.createElement('input');
    const formData = new FormData();
    let url = '';

    input.setAttribute('type', 'file');
    input.setAttribute('accept', 'image/jpg, image/png, image/jpeg, image/gif');
    input.click();

    input.onchange = async () => {
      const file = input.files;

      if (file) {
        formData.append('image', file[0]);
        formData.append('filePath', path);
      }

      try {
        const response = await axios.post(`${apiUrl}/upload`, formData, {
          headers: {
            Authorization: `Bearer ${localStorage.getItem('token')}`,
            'ngrok-skip-browser-warning': '69420',
          },
        });

        url = response.data.data;

        if (QuillRef.current) {
          const index = QuillRef.current?.getEditor().getSelection()?.index;

          if (index !== null && index !== undefined) {
            const quill = QuillRef.current?.getEditor();
            quill?.setSelection(index, 1);
            quill?.clipboard.dangerouslyPasteHTML(
              index,
              `<img src=${url} style="max-width: 23.75rem; height: auto;" alt="사진" />`,
            );
          }
        }
        return { ...response, success: true };
      } catch (error) {
        const err = error as AxiosError;
        return { ...err.response, success: false };
      }
    };
  };

  const modules = useMemo(
    () => ({
      toolbar: {
        container: [
          [{ size: ['small', false, 'large', 'huge'] }],
          ['bold', 'italic', 'underline', 'strike', 'blockquote'],
          [{ color: [] }, { background: [] }],
          [{ list: 'ordered' }, { list: 'bullet' }],
          ['link', 'image'],
        ],
        handlers: { image: imageHandler },
      },
    }),
    [],
  );

  return (
    <TextEditorDiv>
      <ReactQuill
        ref={QuillRef}
        theme="snow"
        style={{ height: '100%' }}
        modules={modules}
        value={textContent}
        onChange={setTextContent}
        placeholder="내용을 입력해 주세요."
      />
    </TextEditorDiv>
  );
}

const TextEditorDiv = styled.div`
  width: 100%; // 제가 추가했습니다... 문제시 삭제하셔요ㅎ;
  min-height: 25rem;
  padding-bottom: ${theme.gap.px40};

  white-space: pre-wrap;
  strong {
    font-weight: bold;
  }
  em {
    font-style: italic;
  }
`;

export default TextEditor;

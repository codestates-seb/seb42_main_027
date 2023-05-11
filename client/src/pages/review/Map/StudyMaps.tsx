/* eslint-disable react/button-has-type */
import styled from 'styled-components';

import { useEffect, useState } from 'react';
import KakaoMap from 'components/book/KakaoMap';
import { FlexContainer } from '../TeacherList/ReviewPage';

function StudyMaps() {
  const [text, seTtext] = useState('');
  const [keyword, setKeyword] = useState('이태원');

  useEffect(() => {
    console.log(keyword);
  }, [keyword]);

  return (
    <FlexContainer dir="col" padding="3rem">
      <FlexContainer gap="0.3rem">
        <Input
          placeholder="위치를 검색하세요"
          value={text}
          onChange={(e: any) => {
            seTtext(e.target.value);
          }}
        />
        <Button
          onClick={() => {
            setKeyword(text);
          }}
        >
          검색
        </Button>
      </FlexContainer>
      <KakaoMap keyword={keyword} latitude={33.450701} longitude={126.570667} />
    </FlexContainer>
  );
}

export default StudyMaps;

const Input = styled.input`
  border: 1px solid black;
  padding: 0.3rem;
`;

const Button = styled.button`
  border: 1px solid black;
  background-color: #667abb;
  color: white;
  padding: 0.4rem;
`;

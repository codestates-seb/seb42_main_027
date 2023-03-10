/* eslint-disable react/no-array-index-key */
import GlobalStyle from 'GlobalStyles';
import styled from 'styled-components';
import { FlexContainer } from 'pages/Review/ReviewPage';
import { useState } from 'react';

function SortBar() {
  const sortArr: string[] = ['전체', '이투스', '메가스터디', '에듀윌', '그 외'];
  const [sortTag, setSortTag] = useState<string>('');

  const sortTagHandler = (e: any) => {
    setSortTag(e.target.value);
  };

  return (
    <FlexContainer>
      <GlobalStyle />
      {sortArr.map((el, index) => {
        return (
          <SortButton key={index} value={el} onClick={sortTagHandler}>
            {el}
          </SortButton>
        );
      })}
    </FlexContainer>
  );
}

export default SortBar;

const SortButton = styled.button`
  width: 5.9rem;
  border: none;
  padding: 0.5rem 1rem;
  background-color: #6667ab;
  color: white;
  border-radius: 5rem;
  cursor: pointer;
`;

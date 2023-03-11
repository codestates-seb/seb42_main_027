/* eslint-disable react/no-array-index-key */
import GlobalStyle from 'GlobalStyles';
import styled from 'styled-components';
import { FlexContainer } from 'pages/Review/ReviewPage';
import { useState } from 'react';
import Button from 'components/UI/Button';

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
          <PButton key={index} value={el} onClick={sortTagHandler}>
            {el}
          </PButton>
        );
      })}
    </FlexContainer>
  );
}

export default SortBar;

const PButton = Button.PointBtn;

/* eslint-disable react/no-array-index-key */
import GlobalStyle from 'GlobalStyles';
import styled from 'styled-components';
import { FlexContainer } from 'pages/Review/ReviewPage';
import Button from 'components/UI/Button';
import Filter from './Filter';

type Props = {
  buttonOpen: boolean;
  setSortTag: React.Dispatch<React.SetStateAction<string>>;
  setButtonOpen: React.Dispatch<React.SetStateAction<boolean>>;
};

function SortBar({ setSortTag, buttonOpen, setButtonOpen }: Props) {
  const sortArr: string[] = ['Update', '평점', '가나다', '랜덤'];

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
      <Filter buttonOpen={buttonOpen} setButtonOpen={setButtonOpen} />
    </FlexContainer>
  );
}

export default SortBar;

const PButton = Button.PointBtn;

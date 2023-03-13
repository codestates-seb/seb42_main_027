/* eslint-disable react/no-array-index-key */
import GlobalStyle from 'GlobalStyles';
import styled from 'styled-components';
import { FlexContainer } from 'pages/Review/ReviewPage';
import Button from 'components/UI/Button';
import Filter from './Filter';

type Props = {
  setSortTag: React.Dispatch<React.SetStateAction<string>>;
  subject: string;
  setSubject: React.Dispatch<React.SetStateAction<string>>;
  buttonOpen: boolean;
  setButtonOpen: React.Dispatch<React.SetStateAction<boolean>>;
};

function SortBar({
  setSubject,
  setSortTag,
  subject,
  buttonOpen,
  setButtonOpen,
}: Props) {
  const sortArr: string[] = ['Default', '평점', '가나다', '랜덤'];

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
      <Filter
        subject={subject}
        setSubject={setSubject}
        buttonOpen={buttonOpen}
        setButtonOpen={setButtonOpen}
      />
    </FlexContainer>
  );
}

export default SortBar;

const PButton = Button.PointBtn;

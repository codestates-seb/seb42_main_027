/* eslint-disable react/no-array-index-key */
import GlobalStyle from 'GlobalStyles';
import styled from 'styled-components';
import { FlexContainer } from 'pages/review/ReviewPage';
import Filter from './Filter';

type Props = {
  sortTag: string;
  buttonOpen: boolean;
  setSortTag: React.Dispatch<React.SetStateAction<string>>;
  setButtonOpen: React.Dispatch<React.SetStateAction<boolean>>;
};

function SortBar({ setSortTag, buttonOpen, sortTag, setButtonOpen }: Props) {
  const sortArr: string[] = ['Update', '평점', '가나다', '랜덤'];

  const sortTagHandler = (e: any) => {
    setSortTag(e.target.value);
  };

  return (
    <FlexContainer>
      <GlobalStyle />
      {sortArr.map((el, index) => {
        return (
          <PointBtn
            key={index}
            isSelected={sortTag === el}
            value={el}
            onClick={sortTagHandler}
          >
            {el}
          </PointBtn>
        );
      })}
      <Filter buttonOpen={buttonOpen} setButtonOpen={setButtonOpen} />
    </FlexContainer>
  );
}

export default SortBar;

type PointBtn = {
  isSelected?: boolean;
};

const PointBtn = styled.button<PointBtn>`
  width: 5.25rem;
  height: 2.125rem;
  text-align: center;
  color: ${props => (props.isSelected ? 'white' : 'black')};
  background-color: ${props => (props.isSelected ? '#6667ab' : 'white')};
  border: 1px solid #6667ab;
  border-radius: 13px;
  &:hover {
    cursor: pointer;
    background-color: #525392;
    color: white;
  }
`;

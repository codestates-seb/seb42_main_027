/* eslint-disable react/no-unused-prop-types */
/* eslint-disable react/no-array-index-key */
import GlobalStyle from 'GlobalStyles';
import styled from 'styled-components';
import { FlexContainer } from 'pages/review/ReviewPage';

type Props = {
  buttonOpen: boolean;
  setButtonOpen: React.Dispatch<React.SetStateAction<boolean>>;
};

function Filter({ buttonOpen, setButtonOpen }: Props) {
  const subjectHandler = () => {
    setButtonOpen(!buttonOpen);
  };

  return (
    <FlexContainer gap="0.3rem">
      <GlobalStyle />
      <SubjectSelectButton isOpen={buttonOpen} onClick={subjectHandler}>
        Filter
      </SubjectSelectButton>
    </FlexContainer>
  );
}

export default Filter;

type SubjectSelectButton = {
  isOpen: boolean;
};

const SubjectSelectButton = styled.button<SubjectSelectButton>`
  width: 5.25rem;
  height: 2.125rem;
  text-align: center;
  color: white;
  background-color: ${props => (props.isOpen ? '#6667ab' : '#b8b8b8')};
  border: none;
  border-radius: 13px;
  font-weight: bold;

  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  cursor: pointer;

  &:hover {
    cursor: pointer;
    background-color: #525392;
    color: white;
  }
`;

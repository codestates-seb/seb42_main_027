/* eslint-disable react/no-unused-prop-types */
/* eslint-disable react/no-array-index-key */
import GlobalStyle from 'GlobalStyles';
import styled from 'styled-components';
import { FlexContainer } from 'pages/Review/ReviewPage';

type Props = {
  subject: string;
  buttonOpen: boolean;
  setButtonOpen: React.Dispatch<React.SetStateAction<boolean>>;
  setSubject: React.Dispatch<React.SetStateAction<string>>;
};

function Filter({ subject, setSubject, buttonOpen, setButtonOpen }: Props) {
  const subjectHandler = () => {
    if (buttonOpen) setSubject('교과 목록');
    setButtonOpen(!buttonOpen);
  };

  return (
    <FlexContainer gap="0.3rem">
      <GlobalStyle />
      <SubjectSelectButton isOpen={buttonOpen} onClick={subjectHandler}>
        {subject}
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
`;

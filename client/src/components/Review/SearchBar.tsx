/* eslint-disable react/no-unused-prop-types */
/* eslint-disable react/no-array-index-key */
import GlobalStyle from 'GlobalStyles';
import styled from 'styled-components';
import { FlexContainer } from 'pages/Review/ReviewPage';
import { useState } from 'react';
import SubjectMenu from './SubjectMenu';

function SearchBar() {
  const [buttonOpen, setButtonOpen] = useState<boolean>(false);
  const [subject, setSubject] = useState<string>('과목 선택');
  const [search, setSearch] = useState<string>('');

  const subjectHandler = () => {
    setButtonOpen(!buttonOpen);
  };

  const searchHandler = (e: any) => {
    setSearch(e.target.value);
  };

  return (
    <FlexContainer gap="0.3rem">
      <GlobalStyle />
      <SubjectSelectButton isOpen={buttonOpen} onClick={subjectHandler}>
        {subject}
        {buttonOpen ? (
          <SubjectSelectAccordion>
            <SubjectMenu setSubject={setSubject} />
          </SubjectSelectAccordion>
        ) : null}
      </SubjectSelectButton>
      <SearchInput
        placeholder="강사님을 검색하세요"
        value={search}
        onChange={searchHandler}
      />
    </FlexContainer>
  );
}

export default SearchBar;

type SubjectSelectButton = {
  isOpen: boolean;
};

const SubjectSelectButton = styled.div<SubjectSelectButton>`
  width: 8rem;
  border: none;
  padding: 0.7rem 0.8rem;
  background-color: #b8b8b8;
  border-radius: ${props =>
    props.isOpen ? '1rem 1rem 0rem 0rem' : '1rem 1rem 1rem 1rem'};
  position: relative;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  cursor: pointer;
`;

const SubjectSelectAccordion = styled.div`
  position: absolute;
  top: 100%;
`;

const SearchInput = styled.input`
  width: 32rem;
  border: none;
  padding: 0.7rem 0.8rem;
  border-radius: 5rem;
  background-color: #b8b8b8;
`;

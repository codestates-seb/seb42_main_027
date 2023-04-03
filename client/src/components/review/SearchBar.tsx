import GlobalStyle from 'GlobalStyles';
import styled from 'styled-components';
import { FlexContainer } from 'pages/review/TeacherList/ReviewPage';

import { useEffect, useState } from 'react';

type Props = {
  search: string;
  setSearch: React.Dispatch<React.SetStateAction<string>>;
  setCurPage: React.Dispatch<React.SetStateAction<number>>;
};

function SearchBar({ search, setSearch, setCurPage }: Props) {
  const [text, setText] = useState('');

  useEffect(() => {
    const debounce = setTimeout(() => {
      return setSearch(text);
    }, 350);
    return () => {
      clearTimeout(debounce);
    };
  }, [text]);

  return (
    <FlexContainer gap="0.3rem">
      <GlobalStyle />
      <SearchInput
        placeholder="검색하세요"
        value={text}
        onChange={e => {
          setText(e.target.value);
          setCurPage(1);
        }}
      />
    </FlexContainer>
  );
}

export default SearchBar;

const SearchInput = styled.input`
  width: 32rem;
  border: none;
  padding: 0.7rem 0.8rem;
  border-radius: 5rem;
  background-color: #b8b8b8;
  transition: all 0.2s;
  border: 2px solid white;

  :focus {
    border: 2px solid #6667ab;
  }
`;

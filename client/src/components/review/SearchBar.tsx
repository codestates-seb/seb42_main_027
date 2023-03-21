import GlobalStyle from 'GlobalStyles';
import styled from 'styled-components';
import { FlexContainer } from 'pages/review/ReviewPage';

type Props = {
  search: string;
  setSearch: React.Dispatch<React.SetStateAction<string>>;
};

function SearchBar({ search, setSearch }: Props) {
  const searchHandler = (e: any) => {
    setSearch(e.target.value);
  };

  return (
    <FlexContainer gap="0.3rem">
      <GlobalStyle />
      <SearchInput
        placeholder="검색하세요"
        value={search}
        onChange={searchHandler}
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
`;

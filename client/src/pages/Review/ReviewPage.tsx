import GlobalStyle from 'GlobalStyles';
import styled from 'styled-components';
import SortBar from 'components/Review/SortBar';
import SearchBar from 'components/Review/SearchBar';
import Pagenation from 'components/Review/Pagenation';
import CharacterCard from 'components/Review/CharacterCard';
import { dummy } from 'components/Review/CharacterDummy';

function ReviewPage() {
  return (
    <FlexContainer dir="col">
      <GlobalStyle />
      <SortBar />
      <SearchBar />
      <CharacterCard teacher={dummy} />
      <Pagenation size={48} currentPage={1} pageSize={6} />
    </FlexContainer>
  );
}

export default ReviewPage;

type Container = {
  dir?: string;
  gap?: string;
  justify?: string;
  align?: string;
  width?: string;
  height?: string;
};

export const FlexContainer = styled.div<Container>`
  width: ${props => props.width};
  height: ${props => props.height};
  display: flex;
  flex-direction: ${props => (props.dir === 'col' ? 'column' : 'row')};
  justify-content: ${props => props.justify || 'center'};
  align-items: ${props => props.align || 'center'};
  gap: ${props => props.gap || '1rem'};
`;

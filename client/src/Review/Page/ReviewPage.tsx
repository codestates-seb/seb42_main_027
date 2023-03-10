import GlobalStyle from 'GlobalStyles';
import styled from 'styled-components';
import SortBar from 'Review/Component/SortBar';
import SearchBar from 'Review/Component/SearchBar';
import CharacterCard from 'Review/Component/CharacterCard';
import Pagenation from 'Review/Component/Pagenation';
import { dummy } from 'Review/Component/CharacterDummy';

function ReviewPage() {
  return (
    <FlexContainer dir="col">
      <GlobalStyle />
      <SortBar />
      <SearchBar />
      <CharacterCard teacher={dummy} />
      <Pagenation />
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

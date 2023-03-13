import GlobalStyle from 'GlobalStyles';
import styled from 'styled-components';
import SortBar from 'components/Review/SortBar';
import SearchBar from 'components/Review/SearchBar';
import Pagenation from 'components/Review/Pagenation';
import CharacterCard from 'components/Review/CharacterCard';
import Carousel from 'components/Review/Carousel';
import SubjectMenu from 'components/Review/SubjectMenu';
import { dummy } from 'components/Review/CharacterDummy';
import { useEffect, useState } from 'react';

function ReviewPage() {
  const [buttonOpen, setButtonOpen] = useState<boolean>(false);
  const [subject, setSubject] = useState<string>('교과 목록');
  const [sortTag, setSortTag] = useState<string>('');
  const [search, setSearch] = useState<string>('');

  useEffect(() => {
    console.log('change subject!');
  }, [subject, sortTag, search]);

  return (
    <FlexContainer dir="col">
      <GlobalStyle />
      <Carousel />
      <SortBar
        setSortTag={setSortTag}
        subject={subject}
        setSubject={setSubject}
        buttonOpen={buttonOpen}
        setButtonOpen={setButtonOpen}
      />
      <SubjectMenu buttonOpen={buttonOpen} setSubject={setSubject} />
      <SearchBar search={search} setSearch={setSearch} />
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

type SubjectSelectButton = {
  isOpen: boolean;
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

const SubjectSelectButton = styled.div<SubjectSelectButton>`
  width: 5.25rem;
  height: 2.125rem;
  text-align: center;
  color: white;
  background-color: ${props => (props.isOpen ? '#6667ab' : '#b8b8b8')};
  border: none;
  border-radius: 13px;
  font-weight: bold;

  position: relative;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  cursor: pointer;
`;

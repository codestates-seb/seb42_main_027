/* eslint-disable react/no-array-index-key */
/* eslint-disable react/require-default-props */
import styled from 'styled-components';
import { FlexContainer } from 'pages/Review/ReviewPage';

type Props = {
  size: number; // 전체 강사 수
  currentPage: number; // 현재 페이지
  pageSize: number; // 한 페이지에 뿌릴 강사 수
  // sort:string; // 정렬 형태
  // word:string; // 검색어
};

function Pagenation({ size, currentPage, pageSize }: Props) {
  const totalPage = Math.ceil(size / pageSize); // 전체 Page 개수
  const pageArr = [];
  for (let i = 1; i <= totalPage; i += 1) pageArr.push(i);
  return (
    <FlexContainer>
      <PagenationContainer>
        <PageBox first={currentPage === 1}>⬅ prev</PageBox>
        <PageContainer>
          {pageArr.map((el, index) => {
            return (
              <PageBox key={index} selected={currentPage === el}>
                {el}
              </PageBox>
            );
          })}
        </PageContainer>
        <PageBox last={currentPage === totalPage}>next ➡</PageBox>
      </PagenationContainer>
    </FlexContainer>
  );
}

export default Pagenation;

const PagenationContainer = styled.div`
  width: 50rem;
  border-top: 1px solid #b8b8b8;

  display: flex;
  justify-content: space-between;
  align-items: center;
`;

const PageContainer = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
`;

type BorderTop = {
  first?: boolean;
  selected?: boolean;
  last?: boolean;
};

const PageBox = styled.span<BorderTop>`
  font-weight: bold;
  padding: 2rem 0.5rem;
  border-top: ${props => (props.selected ? '2px solid #6667ab' : null)};
  color: ${props => (props.first || props.last ? '#b8b8b8' : null)};
  cursor: pointer;
  :hover {
    color: #6667ab;
  }
`;

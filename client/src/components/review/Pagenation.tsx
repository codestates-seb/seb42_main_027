/* eslint-disable react/no-array-index-key */
/* eslint-disable react/require-default-props */
import styled from 'styled-components';
import { FlexContainer } from 'pages/review/TeacherList/ReviewPage';

type Props = {
  size: number; // 전체 page 수
  currentPage: number; // 현재 페이지
  pageSize: number; // 한 페이지에 뿌릴 강사 수
  setCurPage: React.Dispatch<React.SetStateAction<number>>;
};

function Pagenation({ size, currentPage, pageSize, setCurPage }: Props) {
  const preNum = Math.floor(currentPage / 5);
  const totalPage = size; // 전체 Page 개수
  const pageArr = [];
  const start = currentPage % 5 ? preNum * 5 + 1 : (preNum - 1) * 5 + 1;
  const last = Math.min(totalPage, start + 4);
  for (let i = start; i <= last; i += 1) pageArr.push(i);
  return (
    <FlexContainer>
      <PagenationContainer>
        <PageBox
          first={currentPage === 1}
          onClick={() => {
            if (currentPage > 1) {
              setCurPage(currentPage - 1);
            }
          }}
        >
          ⬅ prev
        </PageBox>

        <PageContainer>
          {totalPage > 5 && currentPage !== 1 ? (
            <PageBox
              selected={currentPage === 1}
              onClick={() => {
                setCurPage(1);
              }}
            >
              1...
            </PageBox>
          ) : null}
          <FlexContainer gap="0.2rem">
            {pageArr.map((el, index) => {
              return (
                <PageBox
                  key={index}
                  selected={currentPage === el}
                  onClick={() => {
                    setCurPage(Number(el));
                  }}
                >
                  {el}
                </PageBox>
              );
            })}
          </FlexContainer>
          {totalPage > 5 && currentPage !== totalPage ? (
            <PageBox
              selected={currentPage === totalPage}
              onClick={() => {
                setCurPage(Number(totalPage));
              }}
            >
              {`...${totalPage}`}
            </PageBox>
          ) : null}
        </PageContainer>
        <PageBox
          last={currentPage === totalPage}
          onClick={() => {
            if (currentPage < totalPage) {
              setCurPage(currentPage + 1);
            }
          }}
        >
          next ➡
        </PageBox>
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
  gap: 3rem;
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

import styled from 'styled-components';
import BookList from 'components/book/BookList';
import BookNav from 'components/book/BookNav';
import { useEffect, useState } from 'react';
import { useRecoilValue } from 'recoil';
import { cartList } from 'stores/bookCartStore';
import { ToastContainer } from 'react-toastify';
import { FlexContainer } from '../TeacherList/ReviewPage';

export type BookListDataType = {
  imageUrl: string;
  name: string;
  date: string;
  score: number;
  price: number;
  id: number;
};

const dummyBook = [
  {
    imageUrl: 'http://placehold.it/180x160',
    name: '공부 잘하는 법',
    date: '2023.02.',
    score: 4.9,
    price: 100,
    id: 0,
  },
  {
    imageUrl: 'http://placehold.it/180x160',
    name: '대머리 되는 법',
    date: '2021.12.',
    score: 2.5,
    price: 200,
    id: 1,
  },
  {
    imageUrl: 'http://placehold.it/180x160',
    name: '마 법',
    date: '2022.05.',
    score: 1.9,
    price: 300,
    id: 2,
  },
];

function BookLists() {
  const [sort, setSort] = useState('인기상품순');
  const [bookListData, setBookList] = useState(dummyBook);
  const cart = useRecoilValue(cartList);
  // 정렬 종류 배열
  const sortArr = ['인기상품순', '신상품순', '낮은가격순', '높은가격순'];

  // 정렬 매핑
  const sortConverter: any = {
    인기상품순: 'best',
    신상품순: 'new',
    낮은가격순: 'low',
    높은가격순: 'high',
  };

  useEffect(() => {
    console.log(cart);
  }, [cart]);

  return (
    <FlexContainer
      dir="col"
      width="100%"
      padding="2rem"
      min="100vh"
      justify="start"
    >
      {/* 네비 */}
      <BookNav />
      <ToastContainer pauseOnHover autoClose={1000} />
      {/* 정렬 목록 */}
      <FlexContainer width="50rem" justify="start">
        {sortArr.map((el, index) => {
          return (
            <BookSortSpan
              key={JSON.stringify(el)}
              seleted={sort === el}
              last={index === sortArr.length - 1}
              onClick={() => {
                setSort(el);
              }}
            >
              {sort === el ? `✓ ${el}` : el}
            </BookSortSpan>
          );
        })}
      </FlexContainer>
      {/* 도서 목록 */}
      {bookListData.map(book => {
        return <BookList key={JSON.stringify(book)} info={book} />;
      })}
    </FlexContainer>
  );
}

export default BookLists;

// 정렬 요소 타입
type BookSortSpanType = {
  seleted?: boolean;
  last?: boolean;
};

// 정렬 요소 컴포넌트
const BookSortSpan = styled.span<BookSortSpanType>`
  padding: 0 1rem 0 0;
  border-right: ${props => (props.last ? null : '1px solid gray')};
  font-weight: ${props => (props.seleted ? 'bold' : null)};
  cursor: pointer;
`;

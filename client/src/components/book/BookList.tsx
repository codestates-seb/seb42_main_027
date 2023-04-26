/* eslint-disable react/button-has-type */
import styled from 'styled-components';
import { FlexContainer } from 'pages/review/TeacherList/ReviewPage';
import { BookListDataType } from 'pages/review/Book/BookLists';
import Button from 'components/common/Button';
import { useRecoilState, useRecoilValue } from 'recoil';
import { cartList, includeCartList } from 'stores/bookCartStore';
import { toast } from 'react-toastify';

// Toast 함수 생성
const add = () => toast.success('장바구니에 상품이 추가되었습니다!');
const duple = () => toast.info('이미 추가된 상품입니다!');

type BookList = {
  info: BookListDataType;
};

const PButton = Button.PointBtn;

function BookList({ info }: BookList) {
  const [cart, setCart] = useRecoilState(cartList);
  const addFlag = useRecoilValue(includeCartList(info.id));
  return (
    <FlexContainer
      width="50rem"
      justify="space-between"
      gap="1rem"
      padding="0 0 1.5rem 0"
      borderBottom="0.5px solid gray"
    >
      {/* 이미지 + 도서정보 */}
      <FlexContainer align="start" gap="2rem">
        {/* 이미지 */}
        <img src={info.imageUrl} alt="dummy" />
        {/* 등록정보 */}
        <FlexContainer align="start" dir="col" padding="1rem 0 0 0">
          {/* 도서명 */}
          <div>{info.name}</div>
          {/* 등록월+별점 */}
          <FlexContainer>
            <div>등록월 {info.date}</div>
            <div>⭐️⭐️⭐️⭐️⭐️ {info.score}점</div>
          </FlexContainer>
        </FlexContainer>
      </FlexContainer>
      {/* 가격 */}
      <FlexContainer dir="col">
        {info.price}원
        <PButton
          onClick={() => {
            // 중복 여부 검사
            if (!addFlag) {
              add();
              setCart([...cart, { ...info, count: 1, checked: true }]);
            } else {
              duple();
            }
          }}
        >
          담기
        </PButton>
      </FlexContainer>
    </FlexContainer>
  );
}

export default BookList;

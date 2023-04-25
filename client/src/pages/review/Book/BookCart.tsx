import styled from 'styled-components';
import BookNav from 'components/book/BookNav';
import { useRecoilValue } from 'recoil';
import { cartList } from 'stores/bookCartStore';
import BookCartItem from 'components/book/BookCartItem';
import BookBill from 'components/book/BookBill';
import Payment from 'components/book/Payment';
import { FlexContainer } from '../TeacherList/ReviewPage';

function BookCart() {
  const cart = useRecoilValue(cartList);

  return (
    <FlexContainer
      dir="col"
      width="100%"
      justify="start"
      padding="2rem"
      min="100vh"
    >
      <BookNav />

      <FlexContainer align="start" width="80%" justify="space-between">
        <FlexContainer dir="col" align="start">
          <h3>장바구니</h3>
          {cart.map(el => {
            return <BookCartItem key={JSON.stringify(el)} item={el} />;
          })}
        </FlexContainer>
        <FlexContainer dir="col" align="start">
          <BookBill />
          <Payment />
        </FlexContainer>
      </FlexContainer>
    </FlexContainer>
  );
}

export default BookCart;

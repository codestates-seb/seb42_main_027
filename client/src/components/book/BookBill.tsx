/* eslint-disable react/button-has-type */
import React from 'react';
import { cartList, allPrice } from 'stores/bookCartStore';
import { FlexContainer } from 'pages/review/TeacherList/ReviewPage';
import { useRecoilValue } from 'recoil';
import styled from 'styled-components';

function BookBill() {
  const cart = useRecoilValue(cartList);
  const totalPrice = useRecoilValue(allPrice);

  return (
    <FlexContainer dir="col" width="20rem" align="start">
      <h2>주문 합계</h2>
      <div>총 아이템 개수: {cart.length} 개</div>
      <div>총 아이템 가격: {totalPrice} 원</div>
    </FlexContainer>
  );
}

export default BookBill;

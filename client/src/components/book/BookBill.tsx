/* eslint-disable react/button-has-type */
import React from 'react';
import { allPrice, allQuantity } from 'stores/bookCartStore';
import { FlexContainer } from 'pages/review/TeacherList/ReviewPage';
import { useRecoilValue } from 'recoil';
import styled from 'styled-components';
import Payment from './Payment';

function BookBill() {
  const totalPrice = useRecoilValue(allPrice);

  return (
    <FlexContainer dir="col" width="20rem" align="start" padding="0 0 0 5rem">
      <h2>주문 합계</h2>
      <div>총 아이템 개수: {useRecoilValue(allQuantity)} 개</div>
      <div>총 아이템 가격: {totalPrice} 원</div>
      <Payment />
    </FlexContainer>
  );
}

export default BookBill;

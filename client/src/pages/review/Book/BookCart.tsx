import styled from 'styled-components';
import BookNav from 'components/book/BookNav';
import { useRecoilState, useRecoilValue } from 'recoil';
import { cartList, allChecked } from 'stores/bookCartStore';
import BookCartItem from 'components/book/BookCartItem';
import BookBill from 'components/book/BookBill';
import Payment from 'components/book/Payment';
import { useEffect, useState } from 'react';
import { FlexContainer } from '../TeacherList/ReviewPage';

function BookCart() {
  const [cart, setCart] = useRecoilState(cartList);
  const recoilAllChecked = useRecoilValue(allChecked);
  const [allCheck, setAllCheck] = useState(recoilAllChecked);

  useEffect(() => {
    if (recoilAllChecked) {
      setAllCheck(true);
    } else {
      setAllCheck(false);
    }
  }, [cart]);

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
          <FlexContainer>
            {cart.length ? (
              <input
                type="checkbox"
                onClick={() => {
                  // 전체 선택이 비활성화 -> 활성화일 경우
                  if (!allCheck) {
                    const tmp = cart.slice().map(el => {
                      return { ...el, checked: true };
                    });
                    setCart([...tmp]);
                  }
                  // 활성화 -> 비활성화일 경우
                  else {
                    const tmp = cart.slice().map(el => {
                      return { ...el, checked: false };
                    });
                    setCart([...tmp]);
                  }
                }}
                checked={allCheck}
              />
            ) : null}
            <h3>장바구니</h3>
          </FlexContainer>
          {cart.length ? (
            cart.map(el => {
              return <BookCartItem key={JSON.stringify(el)} item={el} />;
            })
          ) : (
            <FlexContainer width="35rem" height="10rem">
              장바구니가 비었습니다
            </FlexContainer>
          )}
        </FlexContainer>
        <FlexContainer dir="col" align="start">
          <BookBill />
        </FlexContainer>
      </FlexContainer>
    </FlexContainer>
  );
}

export default BookCart;

/* eslint-disable react/button-has-type */
import React, { useEffect, useState } from 'react';
import { cartListType, cartList } from 'stores/bookCartStore';
import { FlexContainer } from 'pages/review/TeacherList/ReviewPage';
import { useRecoilState } from 'recoil';
import Button from 'components/common/Button';
import styled from 'styled-components';

type BookCartItem = {
  item: cartListType;
};

function BookCartItem({ item }: BookCartItem) {
  const [cart, setCart] = useRecoilState(cartList);
  const [quantity, setQuantity] = useState(item.count);
  const [check, setCheck] = useState(item.checked);

  // quantity 변환 시 전역 상태 cart 요소 변경
  useEffect(() => {
    const index = cart.findIndex(el => el.id === item.id);
    // 선택된 요소의 개수가 현재 quantity와 다를 경우만 실행
    if (cart[index].count !== quantity) {
      const tmp = cart[index];
      setCart([
        ...cart.slice(0, index),
        { ...tmp, count: quantity },
        ...cart.slice(index + 1),
      ]);
    }
  }, [quantity]);

  useEffect(() => {
    const index = cart.findIndex(el => el.id === item.id);

    if (cart[index].checked !== check) {
      const tmp = cart[index];
      setCart([
        ...cart.slice(0, index),
        { ...tmp, checked: check },
        ...cart.slice(index + 1),
      ]);
    }
  }, [check]);

  return (
    <FlexContainer width="40rem" justify="start">
      {/* 체크 박스 */}
      <input
        type="checkbox"
        onChange={(e: any) => {
          setCheck(e.target.checked);
        }}
        checked={check}
      />
      <FlexContainer align="start">
        {/* 아이템 이미지 */}
        <img src={item.imageUrl} alt={item.name} />
        {/* 아이템 정보 */}
        <FlexContainer dir="col" width="20rem" align="start" padding="1rem">
          <h3>{item.name}</h3>
          <div>{item.price} 원</div>
        </FlexContainer>
      </FlexContainer>
      <FlexContainer dir="col" width="10rem" gap="2rem">
        {/* 아이템 삭제 */}
        <PButton
          onClick={() => {
            setCart(cart.filter(el => el.id !== item.id));
          }}
        >
          삭제
        </PButton>
        {/* 수량 조절 */}
        <Input
          title="장바구니 아이템 수량 변경"
          type="number"
          min={1}
          value={quantity}
          onChange={e => {
            setQuantity(Number(e.target.value));
          }}
        />
      </FlexContainer>
    </FlexContainer>
  );
}

export default BookCartItem;

const PButton = Button.PointBtn;

const Input = styled.input`
  width: 5rem;
  border-bottom: 1px solid black;
  text-align: center;
  font-size: large;
  padding-left: 1rem;
`;

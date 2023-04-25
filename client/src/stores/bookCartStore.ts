/* eslint-disable no-param-reassign */
/* eslint-disable no-return-assign */
import { atom, selectorFamily, selector } from 'recoil';
import { BookListDataType } from 'pages/review/Book/BookLists';

export type cartListType = BookListDataType & {
  count: number;
};

export const cartList = atom<cartListType[]>({
  key: 'cartList',
  default: [],
});

// 현재 cartList의 중복 여부 검사
export const includeCartList = selectorFamily({
  key: 'IncludeCartList',
  get:
    id =>
    ({ get }) => {
      const cart = get(cartList);
      if (!cart.length) return false;
      return cart.filter(el => el.id === id).length;
    },
});

// 현재 cartList의 전체 가격
export const allPrice = selector({
  key: 'allPrice',
  get: ({ get }) => {
    const cart = get(cartList);
    if (!cart.length) return 0;

    return cart.reduce((acc, cur) => {
      return (acc += cur.count * cur.price);
    }, 0);
  },
});

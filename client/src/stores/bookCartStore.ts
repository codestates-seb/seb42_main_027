/* eslint-disable no-restricted-syntax */
/* eslint-disable no-param-reassign */
/* eslint-disable no-return-assign */
import { atom, selectorFamily, selector } from 'recoil';
import { BookListDataType } from 'pages/review/Book/BookLists';

export type cartListType = BookListDataType & {
  count: number;
  checked: boolean;
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

// 체크된 목록의 가격
export const allPrice = selector({
  key: 'allPrice',
  get: ({ get }) => {
    // 체크된 목록만 필터링
    const cart = get(cartList).filter(el => el.checked === true);
    if (!cart.length) return 0;

    return cart.reduce((acc, cur) => {
      return (acc += cur.count * cur.price);
    }, 0);
  },
});

// 체크된 목록 개수
export const allQuantity = selector({
  key: 'allQuantity',
  get: ({ get }) => {
    // 체크된 목록만 필터링
    const cart = get(cartList).filter(el => el.checked === true);
    if (!cart.length) return 0;

    return cart.reduce((acc, cur) => {
      return (acc += cur.count);
    }, 0);
  },
});

// 올 체크 여부 확인
export const allChecked = selector({
  key: 'allChecked',
  get: ({ get }) => {
    const cart = get(cartList);
    if (!cart.length) return false;
    for (const item of cart) {
      if (!item.checked) return false;
    }
    return true;
  },
});

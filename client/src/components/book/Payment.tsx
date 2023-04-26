/* eslint-disable react/button-has-type */
import React from 'react';
import Button from 'components/common/Button';
import { allPrice, cartList } from 'stores/bookCartStore';
import { useRecoilValue, useRecoilState } from 'recoil';
import { FlexContainer } from 'pages/review/TeacherList/ReviewPage';
import { useNavigate } from 'react-router';

export interface RequestPayAdditionalParams {
  digital?: boolean;
  vbank_due?: string;
  m_redirect_url?: string;
  app_scheme?: string;
  biz_num?: string;
}

export interface Display {
  card_quota?: number[];
}

export interface RequestPayParams extends RequestPayAdditionalParams {
  pg?: string;
  pay_method: string;
  escrow?: boolean;
  merchant_uid: string;
  name: string;
  amount: number;
  custom_data?: any;
  customer_uid: string;
  tax_free?: number;
  currency?: string;
  language?: string;
  buyer_name?: string;
  buyer_tel?: string;
  buyer_email?: string;
  buyer_addr?: string;
  buyer_postcode?: string;
  notice_url?: string | string[];
  display?: Display;
}

export interface RequestPayAdditionalResponse {
  apply_num?: string;
  vbank_num?: string;
  vbank_name?: string;
  vbank_holder?: string | null;
  vbank_date?: number;
}

export interface RequestPayResponse extends RequestPayAdditionalResponse {
  success: boolean;
  error_code: string;
  error_msg: string;
  imp_uid: string | null;
  merchant_uid: string;
  pay_method?: string;
  paid_amount?: number;
  status?: string;
  name?: string;
  pg_provider?: string;
  pg_tid?: string;
  buyer_name?: string;
  buyer_email?: string;
  buyer_tel?: string;
  buyer_addr?: string;
  buyer_postcode?: string;
  custom_data?: any;
  paid_at?: number;
  receipt_url?: string;
}

export type RequestPayResponseCallback = (response: RequestPayResponse) => void;

export interface Iamport {
  init: (accountID: string) => void;
  request_pay: (
    params: RequestPayParams,
    callback?: RequestPayResponseCallback,
  ) => void;
}

declare global {
  interface Window {
    IMP?: Iamport;
  }
}

function Payment() {
  const totalPrice = useRecoilValue(allPrice);
  const [cart, setCart] = useRecoilState(cartList);
  const navigate = useNavigate();
  const handlePayment = () => {
    window.IMP?.init('imp85173768');
    const amount = totalPrice;

    if (!amount) {
      alert('결제 금액을 확인해주세요');
      return;
    }

    const data: RequestPayParams = {
      pg: 'kcp.TC0ONETIME', // 결제 플랫폼
      pay_method: 'card', // 결제 방식
      merchant_uid: `mid_${new Date().getTime()}`, // 결제 uid
      name: '일타 도서구매', // 결제명
      amount, // 결제 금액
      customer_uid: '1ta-user', // 결제 고객 uid
      //   buyer_email: 'Iamport@chai.finance',
      //   buyer_name: '포트원 기술지원팀',
      //   buyer_tel: '010-1234-5678',
      //   buyer_addr: '서울특별시 강남구 삼성동',
      //   buyer_postcode: '123-456',
    };

    const callback = (response: RequestPayResponse) => {
      const { success } = response;
      if (success) {
        alert('결제 성공!');
        setCart([]);
        navigate('/booklists');
      } else {
        alert('결제 실패!');
      }
    };

    window.IMP?.request_pay(data, callback);
  };

  return (
    <FlexContainer>
      <PButton onClick={handlePayment}>카카오</PButton>
    </FlexContainer>
  );
}

export default Payment;

const PButton = Button.PointBtn;

/* eslint-disable react/no-array-index-key */
/* eslint-disable react/button-has-type */
/* eslint-disable import/no-unresolved */
import styled from 'styled-components';
import { FlexContainer } from 'pages/review/TeacherList/ReviewPage';
import { useEffect, useState } from 'react';

import theme from 'theme';

type Time = {
  status: string;
  attend: string;
};

function Ticktock() {
  const [isOpen, setIsOpen] = useState<boolean>(false);
  const [isOpen2, setIsOpen2] = useState<boolean>(false);
  const [toggle, setToggle] = useState<boolean>(
    JSON.parse(getItemWithExpireTime('toggle')) || false,
  );
  const [attendList, setAttendList] = useState<Time[]>(
    JSON.parse(getItemWithExpireTime('attendList')) || [],
  );

  const openHandler = () => {
    setIsOpen(!isOpen);
    setIsOpen2(!isOpen2);
  };

  function setItemWithExpireTime(
    keyName: string,
    keyValue: string,
    tts: number,
  ) {
    // localStorage에 저장할 객체
    const obj = {
      value: keyValue,
      expire: Date.now() + tts,
    };

    // 객체를 JSON 문자열로 변환
    const objString = JSON.stringify(obj);

    // setItem
    window.localStorage.setItem(keyName, objString);
  }

  function getItemWithExpireTime(keyName: string) {
    // localStorage 값 읽기 (문자열)
    const objString = window.localStorage.getItem(keyName);

    // null 체크
    if (!objString) {
      return null;
    }

    // 문자열을 객체로 변환
    const obj = JSON.parse(objString);

    // 현재 시간과 localStorage의 expire 시간 비교
    if (Date.now() > obj.expire) {
      // 만료시간이 지난 item 삭제
      window.localStorage.removeItem(keyName);

      // null 리턴
      return null;
    }

    // 만료기간이 남아있는 경우, value 값 리턴
    return obj.value;
  }

  return (
    <Container>
      <Button
        onClick={() => {
          if (isOpen2) return;
          setIsOpen(!isOpen);
        }}
      >
        째깍이
      </Button>
      {isOpen ? (
        <ModalContainer>
          {!toggle ? (
            <FlexContainer dir="col">
              <p>유저님 안녕하세요!</p>
              <p>퇴실 상태입니다. 입실해주세요.</p>
            </FlexContainer>
          ) : (
            <FlexContainer dir="col">
              <p>유저님 안녕하세요!</p>
              <p>입실 상태입니다.</p>
            </FlexContainer>
          )}

          <ToggleContainer
            onClick={() => {
              const date = new Date();
              const status = toggle ? '퇴실' : '입실';
              const attend = `${date.getHours()}:${date.getMinutes()}:${date.getSeconds()}`;

              setToggle(!toggle);
              setItemWithExpireTime(
                'toggle',
                JSON.stringify(!toggle),
                86400000,
              );

              setAttendList([...attendList, { status, attend }]);
              setItemWithExpireTime(
                'attendList',
                JSON.stringify([...attendList, { status, attend }]),
                86400000,
              );

              setTimeout(() => {
                setIsOpen(!isOpen);
              }, 1000);
            }}
          >
            <div
              className={`toggle-container ${toggle ? 'toggle--checked' : ''}`}
            />
            <div
              className={`toggle-circle ${toggle ? 'toggle--checked' : ''}`}
            />
          </ToggleContainer>
          <Button onClick={openHandler}>오늘 출결 내역 보기</Button>
        </ModalContainer>
      ) : null}
      {isOpen2 ? (
        <AttendanceContainer>
          <FlexContainer width="100%" justify="start" padding="1rem 0 0 1rem">
            <button onClick={openHandler}>{'<--'}</button>
          </FlexContainer>
          {attendList.map((el, index) => {
            return (
              <FlexContainer width="50%" justify="start" gap="1rem" key={index}>
                <span>{el.status}</span>
                <span>{el.attend}</span>
              </FlexContainer>
            );
          })}
        </AttendanceContainer>
      ) : null}
    </Container>
  );
}

export default Ticktock;

const Container = styled.div`
  position: fixed;
  width: 4rem;
  height: 4rem;
  bottom: 2rem;
  right: 2rem;
  border-radius: 25px;
  background: ${theme.colors.pointColor};
  z-index: 998;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  cursor: pointer;
  border: 1px solid gray;
  box-shadow: 2px 2px 2px 1px rgba(0, 0, 0, 0.2);
`;

const ModalContainer = styled.div`
  width: 18rem;
  height: 13rem;
  position: fixed;
  bottom: 7rem;
  right: 1.5rem;
  background: ${theme.colors.pointColor};
  border-radius: 25px;

  color: white;

  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  gap: 0.7rem;
  cursor: default;
  box-shadow: 2px 2px 2px 1px rgba(0, 0, 0, 0.2);
`;

const AttendanceContainer = styled.div`
  width: 15rem;
  height: 23rem;
  position: fixed;
  bottom: 7rem;
  right: 1.5rem;
  background: ${theme.colors.pointColor};
  border-radius: 25px;
  color: white;
  display: flex;
  flex-direction: column;
  justify-content: start;
  align-items: center;
  gap: 0.7rem;
  cursor: default;

  overflow: auto;
  box-shadow: 2px 2px 2px 1px rgba(0, 0, 0, 0.2);
`;

const ToggleContainer = styled.div`
  position: relative;
  cursor: pointer;

  > .toggle-container {
    width: 6rem;
    height: 2rem;
    border-radius: 30px;
    background-color: ${theme.colors.gray};
    transition: 0.5s;
  }
  > .toggle--checked {
    background-color: wheat;
    transition: 0.5s;
  }

  > .toggle-circle {
    position: absolute;
    z-index: 10;
    top: calc(100% / 12);
    left: calc(4%);
    width: 1.6rem;
    height: 1.6rem;
    border-radius: 50%;
    background-color: ${theme.colors.white};
    transition: 0.5s;
    &.toggle--checked {
      left: 4.2rem;
      transition: 0.5;
    }
  }
`;

const Button = styled.button`
  background-color: ${theme.colors.pointColor};
  color: white;
  cursor: pointer;
`;

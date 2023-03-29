/* eslint-disable no-param-reassign */
/* eslint-disable consistent-return */
/* eslint-disable react/no-array-index-key */
/* eslint-disable react/button-has-type */
/* eslint-disable import/no-unresolved */
import styled from 'styled-components';
import { FlexContainer } from 'pages/review/TeacherList/ReviewPage';
import { useEffect, useState } from 'react';
import { GiAlarmClock } from 'react-icons/gi';
import { FaBed } from 'react-icons/fa';
import { BiArrowBack } from 'react-icons/bi';
import theme from 'theme';
import useUserInfoStore from 'stores/userInfoStore';
import isLogin from 'utils/isLogin';

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
  const [studyTime, setStudyTime] = useState<number>(
    JSON.parse(getItemWithExpireTime('studyTime')) || 0,
  );

  const { userInfo } = useUserInfoStore(state => state);

  const today = new Date();
  const tomorrow = new Date(today.setDate(today.getDate() + 1));
  const tomorrowZero = new Date(tomorrow.setHours(24, 0, 0, 0));
  const differ = Math.floor(tomorrowZero.getTime() - today.getTime());

  useEffect(() => {
    if (!toggle && attendList.length) {
      setStudyTime(
        studyTime +
          timeCalc(
            attendList[attendList.length - 2].attend,
            attendList[attendList.length - 1].attend,
          ),
      );
      setItemWithExpireTime(
        'studyTime',
        JSON.stringify(
          studyTime +
            timeCalc(
              attendList[attendList.length - 2].attend,
              attendList[attendList.length - 1].attend,
            ),
        ),
        differ,
      );
    }
  }, [toggle]);

  const openHandler = () => {
    setIsOpen(!isOpen);
    setIsOpen2(!isOpen2);
  };

  const timeCalc = (start: string, end: string) => {
    const startSplit = start.split(':');
    const endSplit = end.split(':');

    const startTime =
      Number(startSplit[0]) * 3600 +
      Number(startSplit[1]) * 60 +
      Number(startSplit[2]);
    const endTime =
      Number(endSplit[0]) * 3600 +
      Number(endSplit[1]) * 60 +
      Number(endSplit[2]);

    return endTime - startTime;
  };

  const timePresent = (time: number) => {
    const hour = time > 3600 ? Math.floor(time / 3600) : 0;
    time -= hour * 3600;
    const min = time > 60 ? Math.floor(time / 60) : 0;
    time -= min * 60;
    const sec = time;

    return `${hour ? `${hour}시간 ` : ''} ${min ? `${min}분 ` : ''} ${sec}초`;
  };

  function setItemWithExpireTime(
    keyName: string,
    keyValue: string,
    tts: number,
  ) {
    // localStorage에 저장할 객체
    if (window.localStorage.getItem(keyName)) {
      const objString = window.localStorage.getItem(keyName);
      if (!objString) {
        return null;
      }
      const obj = JSON.parse(objString);
      obj.value = keyValue;
      const objString2 = JSON.stringify(obj);
      window.localStorage.setItem(keyName, objString2);
    } else {
      const obj = {
        value: keyValue,
        expire: Date.now() + tts,
      };

      // 객체를 JSON 문자열로 변환
      const objString = JSON.stringify(obj);

      // setItem
      window.localStorage.setItem(keyName, objString);
    }
  }

  function getItemWithExpireTime(keyName: string) {
    const objString = window.localStorage.getItem(keyName);
    if (!objString) {
      return null;
    }
    const obj = JSON.parse(objString);

    if (Date.now() > obj.expire) {
      window.localStorage.removeItem(keyName);
      return null;
    }

    return obj.value;
  }

  return (
    <Container login={isLogin()}>
      <Button
        onClick={() => {
          if (isOpen !== isOpen2) {
            setIsOpen(false);
            setIsOpen2(false);
          } else setIsOpen(!isOpen);
        }}
      >
        {toggle ? <GiAlarmClock size="3rem" /> : <FaBed size="2.5rem" />}
      </Button>
      {isOpen ? (
        <ModalContainer>
          {!toggle ? (
            <FlexContainer dir="col">
              <p>{userInfo.username || '유저'}님 안녕하세요!</p>
              <p>버튼을 누르면 공부를 시작합니다.</p>
            </FlexContainer>
          ) : (
            <FlexContainer dir="col">
              <p>{userInfo.username || '유저'}님 안녕하세요!</p>
              <p>아래 버튼을 눌러 공부를 끝냅니다.</p>
            </FlexContainer>
          )}

          <ToggleContainer
            onClick={() => {
              const date = new Date();
              const status = toggle ? '종료' : '시작';
              const attend = `${date.getHours()}:${date.getMinutes()}:${date.getSeconds()}`;

              setToggle(!toggle);
              setItemWithExpireTime('toggle', JSON.stringify(!toggle), differ);

              setAttendList([...attendList, { status, attend }]);

              setItemWithExpireTime(
                'attendList',
                JSON.stringify([...attendList, { status, attend }]),
                differ,
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
          <Button onClick={openHandler}>오늘 공부 내역 보기</Button>
        </ModalContainer>
      ) : null}
      {isOpen2 ? (
        <AttendanceContainer>
          <FlexContainer
            width="100%"
            justify="space-between"
            padding="1rem 1rem 0.5rem 1rem"
            gap="1.5rem"
            borderBottom="0.5px solid white"
          >
            <StudySpan onClick={openHandler}>
              <BiArrowBack />
            </StudySpan>
            <StudyTitleSpan>오늘 공부 내역</StudyTitleSpan>
            <StudySpan
              onClick={() => {
                setIsOpen(false);
                setIsOpen2(false);
              }}
            >
              X
            </StudySpan>
          </FlexContainer>
          <FlexContainer>{timePresent(studyTime)}</FlexContainer>
          <FlexContainer padding="0 0 1rem 0">{`${today.getFullYear()}.${
            today.getMonth() + 1
          }.${today.getDate()}`}</FlexContainer>

          <FlexContainer
            width="100%"
            justify="space-evenly"
            padding="0 0 0.5rem 0"
            borderBottom="0.5px solid white"
          >
            <StudyTitleSpan>분류</StudyTitleSpan>
            <StudyTitleSpan>시간</StudyTitleSpan>
          </FlexContainer>

          {attendList.map((el, index) => {
            return (
              <FlexContainer
                width="100%"
                justify="center"
                gap="1.5rem"
                key={index}
              >
                <FlexContainer width="5rem">{el.status}</FlexContainer>
                <FlexContainer
                  padding="0 0 0 0.7rem"
                  width="5rem"
                  justify="start"
                >
                  {el.attend}
                </FlexContainer>
              </FlexContainer>
            );
          })}
        </AttendanceContainer>
      ) : null}
    </Container>
  );
}

export default Ticktock;

type Container = {
  login?: boolean;
};

const Container = styled.div<Container>`
  position: fixed;
  width: 4rem;
  height: 4rem;
  bottom: 2rem;
  right: 2rem;
  border-radius: 25px;
  background: ${theme.colors.pointColor};
  z-index: 998;

  display: ${props => (props.login ? 'flex' : 'none')};
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
  gap: 0.5rem;
  cursor: default;

  overflow: auto;
  box-shadow: 2px 2px 2px 1px rgba(0, 0, 0, 0.2);
`;

const ToggleContainer = styled.div`
  position: relative;
  cursor: pointer;

  > .toggle-container {
    width: 6rem;
    height: 1.95rem;
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
  transition: 0.5s;
  :hover {
    color: wheat;
  }
`;

const StudyTitleSpan = styled.span`
  font-size: 1.1rem;
  font-weight: bold;
  color: white;
`;

const StudySpan = styled.span`
  font-size: large;
  font-weight: bold;
  color: white;
  transition: 0.5s;
  :hover {
    color: wheat;
  }
`;

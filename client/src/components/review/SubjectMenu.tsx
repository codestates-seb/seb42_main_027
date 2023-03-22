/* eslint-disable react/no-array-index-key */
import React from 'react';
import styled from 'styled-components';
import { FlexContainer } from 'pages/review/ReviewPage';

type Props = {
  buttonOpen: boolean;
  subject: string;
  grade: string;
  platform: string;
  setSubject: React.Dispatch<React.SetStateAction<string>>;
  setGrade: React.Dispatch<React.SetStateAction<string>>;
  setPlatform: React.Dispatch<React.SetStateAction<string>>;
  setCurPage: React.Dispatch<React.SetStateAction<number>>;
};

function SubjectMenu({
  buttonOpen,
  subject,
  grade,
  platform,
  setSubject,
  setGrade,
  setPlatform,
  setCurPage,
}: Props) {
  const subjectArr1: string[] = ['국어', '영어', '수학', '한국사'];
  const subjectArr2: string[] = [
    '사탐전체',
    '경제',
    '정치와법',
    '지리',
    '윤리',
    '역사',
    '일반사회',
  ];
  const subjectArr3: string[] = [
    '과탐전체',
    '물리학',
    '화학',
    '생명과학',
    '지구과학',
    '일반과학',
  ];
  const subjectArr4: string[] = ['기타전체', '제2외국어', '대학별고사', '그외'];
  const subjectArr5: string[] = ['중1', '중2', '중3'];
  const subjectArr6: string[] = ['고1', '고2', '고3', 'N수'];
  const subjectArr7: string[] = [
    '이투스',
    '메가스터디',
    'EBS',
    '에듀윌',
    '대성마이맥',
    '기타',
  ];

  const subjectClickHandler = (e: any) => {
    setSubject(e.target.value);
    setCurPage(1);
  };

  const gradeClickHandler = (e: any) => {
    setGrade(e.target.value);
    setCurPage(1);
  };

  const platformClickHandler = (e: any) => {
    setPlatform(e.target.value);
    setCurPage(1);
  };

  return (
    <MenuContainer isOpen={buttonOpen}>
      <FlexContainer dir="col" gap="0.4rem" justify="start">
        <MenuButton
          width="fit-content"
          isSelected={subject === '전체'}
          value="전체"
          onClick={subjectClickHandler}
        >
          과목 전체
        </MenuButton>
        <FlexContainer
          borderRadius="1rem"
          backColor="#d0d0d0"
          justify="start"
          align="start"
          gap="0"
        >
          <MenuSubContainer>
            공통
            {subjectArr1.map((el, index) => {
              return (
                <MenuButton
                  isSelected={subject === el}
                  key={index}
                  value={el}
                  onClick={subjectClickHandler}
                >
                  {el}
                </MenuButton>
              );
            })}
          </MenuSubContainer>
          <MenuSubContainer>
            사회탐구
            {subjectArr2.map((el, index) => {
              return (
                <MenuButton
                  isSelected={subject === el}
                  key={index}
                  value={el}
                  onClick={subjectClickHandler}
                >
                  {el}
                </MenuButton>
              );
            })}
          </MenuSubContainer>
          <MenuSubContainer>
            과학탐구
            {subjectArr3.map((el, index) => {
              return (
                <MenuButton
                  isSelected={subject === el}
                  key={index}
                  value={el}
                  onClick={subjectClickHandler}
                >
                  {el}
                </MenuButton>
              );
            })}
          </MenuSubContainer>
          <MenuSubContainer>
            기타
            {subjectArr4.map((el, index) => {
              return (
                <MenuButton
                  isSelected={subject === el}
                  key={index}
                  value={el}
                  onClick={subjectClickHandler}
                >
                  {el}
                </MenuButton>
              );
            })}
          </MenuSubContainer>
        </FlexContainer>
      </FlexContainer>

      <FlexContainer dir="col" gap="0.4rem" justify="start">
        <MenuButton
          width="fit-content"
          isSelected={grade === '전체'}
          value="전체"
          onClick={gradeClickHandler}
        >
          학년 전체
        </MenuButton>
        <FlexContainer
          borderRadius="1rem"
          backColor="#d0d0d0"
          justify="start"
          align="start"
          gap="0"
        >
          <MenuSubContainer>
            중등부
            {subjectArr5.map((el, index) => {
              return (
                <MenuButton
                  isSelected={grade === el}
                  key={index}
                  value={el}
                  onClick={gradeClickHandler}
                >
                  {el}
                </MenuButton>
              );
            })}
          </MenuSubContainer>
          <MenuSubContainer>
            고등부
            {subjectArr6.map((el, index) => {
              return (
                <MenuButton
                  isSelected={grade === el}
                  key={index}
                  value={el}
                  onClick={gradeClickHandler}
                >
                  {el}
                </MenuButton>
              );
            })}
          </MenuSubContainer>
        </FlexContainer>
      </FlexContainer>

      <FlexContainer dir="col" gap="0.4rem" justify="start">
        <MenuButton
          width="fit-content"
          isSelected={platform === '전체'}
          value="전체"
          onClick={platformClickHandler}
        >
          플랫폼 전체
        </MenuButton>
        <FlexContainer
          borderRadius="1rem"
          backColor="#d0d0d0"
          justify="start"
          align="start"
          gap="0"
        >
          <MenuSubContainer>
            플랫폼
            {subjectArr7.map((el, index) => {
              return (
                <MenuButton
                  isSelected={platform === el}
                  key={index}
                  value={el}
                  onClick={platformClickHandler}
                >
                  {el}
                </MenuButton>
              );
            })}
          </MenuSubContainer>
        </FlexContainer>
      </FlexContainer>
    </MenuContainer>
  );
}

export default SubjectMenu;

type MenuContainer = {
  isOpen?: boolean;
};

type MenuButton = {
  isSelected?: boolean;
  width?: string;
};

const MenuContainer = styled.div<MenuContainer>`
  padding: 0.5rem;
  border-radius: 1rem 1rem 1rem 1rem;
  border: 1px solid #6667ab;
  background-color: #b9b9b9;
  display: ${props => (props.isOpen ? 'flex' : 'none')};
  flex-wrap: wrap;
  justify-content: center;
  align-items: left;
  gap: 1rem;
`;

const MenuSubContainer = styled.div`
  width: fit-content;
  padding: 0.5rem;
  border-radius: 1rem;
  background-color: #d0d0d0;
  display: flex;
  flex-wrap: wrap;
  flex-direction: column;
  justify-content: left;
  align-items: center;
  gap: 0.3rem;
  color: black;
`;

const MenuButton = styled.button<MenuButton>`
  width: ${props => props.width || '100%'};
  padding: 0.4rem 0.5rem;
  cursor: pointer;
  border-radius: 0.5rem;
  border: 1px solid #6667ab;
  background-color: ${props => (props.isSelected ? '#6667ab' : '#dsdssd')};
  color: ${props => (props.isSelected ? 'white' : 'black')};
`;

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
};

function SubjectMenu({
  buttonOpen,
  subject,
  grade,
  platform,
  setSubject,
  setGrade,
  setPlatform,
}: Props) {
  const subjectArr1: string[] = ['국어', '영어', '수학', '한국사'];
  const subjectArr2: string[] = [
    '지리',
    '일반사회',
    '윤리',
    '역사',
    '통합사회',
  ];
  const subjectArr3: string[] = [
    '물리학',
    '지구과학',
    '화학',
    '생명과학',
    '통합과학',
  ];
  const subjectArr4: string[] = ['제2 외국어', '대학별고사'];
  const subjectArr5: string[] = [
    '예비중1',
    '중1',
    '예비중2',
    '중2',
    '예비중3',
    '중3',
  ];
  const subjectArr6: string[] = [
    '예비고1',
    '고1',
    '예비고2',
    '고2',
    '예비고3',
    '고3',
  ];
  const subjectArr7: string[] = [
    '이투스',
    '메가스터디',
    'EBS',
    '에듀윌',
    '기타',
  ];

  const subjectClickHandler = (e: any) => {
    setSubject(e.target.value);
  };

  const gradeClickHandler = (e: any) => {
    setGrade(e.target.value);
  };

  const platformClickHandler = (e: any) => {
    setPlatform(e.target.value);
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

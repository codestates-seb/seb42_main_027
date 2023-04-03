/* eslint-disable react/no-array-index-key */
import React from 'react';
import styled from 'styled-components';
import { FlexContainer } from 'pages/review/TeacherList/ReviewPage';

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
          isOpen={buttonOpen}
          width="fit-content"
          isSelected={subject === '전체'}
          value="전체"
          onClick={subjectClickHandler}
        >
          과목 전체
        </MenuButton>
        <MenuAllContainer isOpen={buttonOpen}>
          <MenuSubContainer isOpen={buttonOpen}>
            공통
            {subjectArr1.map((el, index) => {
              return (
                <MenuButton
                  isOpen={buttonOpen}
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
          <MenuSubContainer isOpen={buttonOpen}>
            사회탐구
            {subjectArr2.map((el, index) => {
              return (
                <MenuButton
                  isOpen={buttonOpen}
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
          <MenuSubContainer isOpen={buttonOpen}>
            과학탐구
            {subjectArr3.map((el, index) => {
              return (
                <MenuButton
                  isOpen={buttonOpen}
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
          <MenuSubContainer isOpen={buttonOpen}>
            기타
            {subjectArr4.map((el, index) => {
              return (
                <MenuButton
                  isOpen={buttonOpen}
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
        </MenuAllContainer>
      </FlexContainer>

      <FlexContainer dir="col" gap="0.4rem" justify="start">
        <MenuButton
          isOpen={buttonOpen}
          width="fit-content"
          isSelected={grade === '전체'}
          value="전체"
          onClick={gradeClickHandler}
        >
          학년 전체
        </MenuButton>
        <MenuAllContainer isOpen={buttonOpen}>
          <MenuSubContainer isOpen={buttonOpen}>
            중등부
            {subjectArr5.map((el, index) => {
              return (
                <MenuButton
                  isOpen={buttonOpen}
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
          <MenuSubContainer isOpen={buttonOpen}>
            고등부
            {subjectArr6.map((el, index) => {
              return (
                <MenuButton
                  isOpen={buttonOpen}
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
        </MenuAllContainer>
      </FlexContainer>

      <FlexContainer dir="col" gap="0.4rem" justify="start">
        <MenuButton
          isOpen={buttonOpen}
          width="fit-content"
          isSelected={platform === '전체'}
          value="전체"
          onClick={platformClickHandler}
        >
          플랫폼 전체
        </MenuButton>
        <MenuAllContainer isOpen={buttonOpen}>
          <MenuSubContainer isOpen={buttonOpen}>
            플랫폼
            {subjectArr7.map((el, index) => {
              return (
                <MenuButton
                  isOpen={buttonOpen}
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
        </MenuAllContainer>
      </FlexContainer>
    </MenuContainer>
  );
}

export default SubjectMenu;

type MenuContainer = {
  isOpen?: boolean;
};

type MenuAllContainer = {
  isOpen?: boolean;
  dir?: string;
};

type MenuSubContainer = {
  isOpen?: boolean;
};

type MenuButton = {
  isSelected?: boolean;
  width?: string;
  isOpen?: boolean;
};

const MenuContainer = styled.div<MenuContainer>`
  visibility: ${props => (props.isOpen ? 'visible' : 'hidden')};
  height: ${props => (props.isOpen ? '21.3rem' : '0vh')};
  padding: 0.5rem;

  border-radius: 1rem 1rem 1rem 1rem;
  border: 1px solid #6667ab;
  background-color: #b9b9b9;
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  align-items: left;
  gap: 1rem;
  transition: all 0.5s;
`;

const MenuAllContainer = styled.div<MenuAllContainer>`
  visibility: ${props => (props.isOpen ? 'visible' : 'hidden')};
  height: ${props => (props.isOpen ? '18rem' : '0vh')};
  background-color: #d0d0d0;
  border-radius: 1rem;

  display: flex;
  flex-direction: ${props => props.dir || 'row'};
  justify-content: start;
  align-items: start;
  gap: 0rem;
  transition: height 0.5s;
`;

const MenuSubContainer = styled.div<MenuSubContainer>`
  visibility: ${props => (props.isOpen ? 'visible' : 'hidden')};
  opacity: ${props => (props.isOpen ? '1' : '0')};
  width: fit-content;
  padding: 0.5rem;
  border-radius: 1rem;
  display: flex;
  flex-wrap: wrap;
  flex-direction: column;
  justify-content: left;
  align-items: center;
  gap: 0.3rem;
  color: black;
  transition: opacity 0.6s 0.2s;
`;

const MenuButton = styled.button<MenuButton>`
  width: ${props => props.width || '100%'};
  visibility: ${props => (props.isOpen ? 'visible' : 'hidden')};
  opacity: ${props => (props.isOpen ? '1' : '0')};
  padding: 0.4rem 0.5rem;
  cursor: pointer;
  border-radius: 0.5rem;
  border: 1px solid #6667ab;
  background-color: ${props => (props.isSelected ? '#6667ab' : '#dsdssd')};
  color: ${props => (props.isSelected ? 'white' : 'black')};
  transition: opacity 0.6s 0.2s;
`;

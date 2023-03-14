/* eslint-disable react/no-array-index-key */
import React from 'react';
import styled from 'styled-components';

type Props = {
  buttonOpen: boolean;
  setSubject: React.Dispatch<React.SetStateAction<string>>;
};

function SubjectMenu({ buttonOpen, setSubject }: Props) {
  const subjectArr1: string[] = ['국어', '영어', '수학', '한국사'];
  const subjectArr2: string[] = [
    '지리',
    '일반사회',
    '윤리',
    '역사',
    '통합사회',
  ];
  const subjectArr3: string[] = [
    '물리',
    '지구과학',
    '화학',
    '생명과학',
    '통합과학',
  ];
  const subjectArr4: string[] = ['제2 외국어', '대학별고사'];

  const buttonClickHandler = (e: any) => {
    setSubject(e.target.value);
  };

  return (
    <MenuContainer isOpen={buttonOpen}>
      <MenuSubContainer>
        공통
        {subjectArr1.map((el, index) => {
          return (
            <MenuButton key={index} value={el} onClick={buttonClickHandler}>
              {el}
            </MenuButton>
          );
        })}
      </MenuSubContainer>
      <MenuSubContainer>
        사회탐구
        {subjectArr2.map((el, index) => {
          return (
            <MenuButton key={index} value={el} onClick={buttonClickHandler}>
              {el}
            </MenuButton>
          );
        })}
      </MenuSubContainer>
      <MenuSubContainer>
        과학탐구
        {subjectArr3.map((el, index) => {
          return (
            <MenuButton key={index} value={el} onClick={buttonClickHandler}>
              {el}
            </MenuButton>
          );
        })}
      </MenuSubContainer>
      <MenuSubContainer>
        기타
        {subjectArr4.map((el, index) => {
          return (
            <MenuButton key={index} value={el} onClick={buttonClickHandler}>
              {el}
            </MenuButton>
          );
        })}
      </MenuSubContainer>
    </MenuContainer>
  );
}

export default SubjectMenu;

type MenuContainer = {
  isOpen?: boolean;
};
const MenuContainer = styled.div<MenuContainer>`
  padding: 0.5rem;
  border-radius: 1rem 1rem 1rem 1rem;
  background-color: #d0d0d0;
  display: ${props => (props.isOpen ? 'flex' : 'none')};
  justify-content: center;
  align-items: left;
  gap: 0.3rem;
`;

const MenuSubContainer = styled.div`
  width: fit-content;
  padding: 0.5rem;
  border-radius: 1rem;
  background-color: #b8b8b8;
  display: flex;
  flex-wrap: wrap;
  flex-direction: column;
  justify-content: left;
  align-items: center;
  gap: 0.3rem;
  color: black;
`;

const MenuButton = styled.button`
  width: 100%;
  background-color: #6667ab;
  padding: 0.4rem 0.5rem;
  color: white;
  cursor: pointer;
  border-radius: 0.5rem;
`;

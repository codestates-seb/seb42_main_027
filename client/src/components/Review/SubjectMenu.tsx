/* eslint-disable react/no-array-index-key */
import React from 'react';
import styled from 'styled-components';

type Props = {
  setSubject: React.Dispatch<React.SetStateAction<string>>;
};

function SubjectMenu({ setSubject }: Props) {
  const subjectArr: string[] = [
    '과목 선택',
    '국어',
    '영어',
    '수학',
    '사회',
    '과학',
    '기타',
  ];

  const buttonClickHandler = (e: any) => {
    setSubject(e.target.value);
  };

  return (
    <MenuContainer>
      {subjectArr.map((el, index) => {
        return (
          <MenuButton key={index} value={el} onClick={buttonClickHandler}>
            {el}
          </MenuButton>
        );
      })}
    </MenuContainer>
  );
}

export default SubjectMenu;

const MenuContainer = styled.div`
  width: 8rem;
  padding: 0.5rem;
  border-radius: 0rem 0rem 1rem 1rem;
  background-color: #b8b8b8;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  z-index: 1;
  gap: 0.3rem;
`;

const MenuButton = styled.button`
  width: 7rem;
  background-color: #b8b8b8;
  border: none;
  color: white;
  cursor: pointer;
  font-size: large;
`;

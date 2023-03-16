import { useState } from 'react';
import { Link } from 'react-router-dom';

import styled from 'styled-components';
import theme from 'theme';
import Button from 'components/common/Button';
import { HiPencil } from 'react-icons/hi';
import { MdMenuOpen } from 'react-icons/md';

function FreeBoardMenu() {
  const [selectMenu, isSelectMenu] = useState('0');

  const menuSelectHandler = (e: React.MouseEvent<HTMLButtonElement>) => {
    if (e.target instanceof Element) {
      isSelectMenu(e.target.id);
    }
  };
  return (
    <MenuDiv>
      <Link to="write">
        <Button.WriteBtn>
          <HiPencil />
          작성하기
        </Button.WriteBtn>
      </Link>
      <Category>
        {selectMenu === '0' ? (
          <Button.SubMenuBtn
            id="0"
            className="selected"
            onClick={menuSelectHandler}
          >
            전체
          </Button.SubMenuBtn>
        ) : (
          <Button.SubMenuBtn id="0" onClick={menuSelectHandler}>
            전체
          </Button.SubMenuBtn>
        )}
        {selectMenu === '1' ? (
          <Button.SubMenuBtn
            id="1"
            className="selected"
            onClick={menuSelectHandler}
          >
            공지
          </Button.SubMenuBtn>
        ) : (
          <Button.SubMenuBtn id="1" onClick={menuSelectHandler}>
            공지
          </Button.SubMenuBtn>
        )}
        {selectMenu === '2' ? (
          <Button.SubMenuBtn
            id="2"
            className="selected"
            onClick={menuSelectHandler}
          >
            일상
          </Button.SubMenuBtn>
        ) : (
          <Button.SubMenuBtn id="2" onClick={menuSelectHandler}>
            일상
          </Button.SubMenuBtn>
        )}
        {selectMenu === '3' ? (
          <Button.SubMenuBtn
            id="3"
            className="selected"
            onClick={menuSelectHandler}
          >
            정보
          </Button.SubMenuBtn>
        ) : (
          <Button.SubMenuBtn id="3" onClick={menuSelectHandler}>
            정보
          </Button.SubMenuBtn>
        )}
        {selectMenu === '4' ? (
          <Button.SubMenuBtn
            id="4"
            className="selected"
            onClick={menuSelectHandler}
          >
            유머
          </Button.SubMenuBtn>
        ) : (
          <Button.SubMenuBtn id="4" onClick={menuSelectHandler}>
            유머
          </Button.SubMenuBtn>
        )}
      </Category>
      <Button.FilterBtn>
        <MdMenuOpen />
        최신순
      </Button.FilterBtn>
    </MenuDiv>
  );
}

const MenuDiv = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 100%;
  height: 100px;
  border-bottom: 1px solid ${theme.colors.gray};
`;

const Category = styled.div``;

export default FreeBoardMenu;

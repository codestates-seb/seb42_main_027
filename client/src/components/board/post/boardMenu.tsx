import { useState } from 'react';
import { Link, useLocation } from 'react-router-dom';

import styled from 'styled-components';
import theme from 'theme';
import Button from 'components/common/Button';
import { HiPencil } from 'react-icons/hi';
import { MdMenuOpen } from 'react-icons/md';

function FreeBoardMenu() {
  const [selectMenu, setSelectMenu] = useState('0');
  const [sortMenuOpen, setSortMenuOpen] = useState(false);
  const [selectSort, setSelectSort] = useState('최신순');

  const urlData = useLocation().pathname.slice(0, 4);

  const menuSelectHandler = (e: React.MouseEvent<HTMLButtonElement>) => {
    if (e.target instanceof Element) {
      setSelectMenu(e.target.id);
    }
  };
  const sortMenuOpenHandler = () => {
    setSortMenuOpen(!sortMenuOpen);
  };
  const sortSelectHandler = (e: React.MouseEvent<HTMLButtonElement>) => {
    if (e.target instanceof Element) {
      setSelectSort(e.target.id);
    }
  };
  return (
    <MenuDiv>
      {localStorage.getItem('token') ? (
        <Link to="articles/write">
          <Button.WriteBtn>
            <HiPencil />
            작성하기
          </Button.WriteBtn>
        </Link>
      ) : (
        <Link to="../login">
          <Button.WriteBtn>
            <HiPencil />
            작성하기
          </Button.WriteBtn>
        </Link>
      )}

      {urlData === '/fre' ? (
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
      ) : null}
      {urlData === '/qna' ? (
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
              국어
            </Button.SubMenuBtn>
          ) : (
            <Button.SubMenuBtn id="2" onClick={menuSelectHandler}>
              국어
            </Button.SubMenuBtn>
          )}
          {selectMenu === '3' ? (
            <Button.SubMenuBtn
              id="3"
              className="selected"
              onClick={menuSelectHandler}
            >
              영어
            </Button.SubMenuBtn>
          ) : (
            <Button.SubMenuBtn id="3" onClick={menuSelectHandler}>
              영어
            </Button.SubMenuBtn>
          )}
          {selectMenu === '4' ? (
            <Button.SubMenuBtn
              id="4"
              className="selected"
              onClick={menuSelectHandler}
            >
              수학
            </Button.SubMenuBtn>
          ) : (
            <Button.SubMenuBtn id="4" onClick={menuSelectHandler}>
              수학
            </Button.SubMenuBtn>
          )}
          {selectMenu === '5' ? (
            <Button.SubMenuBtn
              id="5"
              className="selected"
              onClick={menuSelectHandler}
            >
              사탐
            </Button.SubMenuBtn>
          ) : (
            <Button.SubMenuBtn id="5" onClick={menuSelectHandler}>
              사탐
            </Button.SubMenuBtn>
          )}
          {selectMenu === '6' ? (
            <Button.SubMenuBtn
              id="6"
              className="selected"
              onClick={menuSelectHandler}
            >
              과탐
            </Button.SubMenuBtn>
          ) : (
            <Button.SubMenuBtn id="6" onClick={menuSelectHandler}>
              과탐
            </Button.SubMenuBtn>
          )}
          {selectMenu === '7' ? (
            <Button.SubMenuBtn
              id="7"
              className="selected"
              onClick={menuSelectHandler}
            >
              국사
            </Button.SubMenuBtn>
          ) : (
            <Button.SubMenuBtn id="7" onClick={menuSelectHandler}>
              국사
            </Button.SubMenuBtn>
          )}
          {selectMenu === '8' ? (
            <Button.SubMenuBtn
              id="8"
              className="selected"
              onClick={menuSelectHandler}
            >
              기타
            </Button.SubMenuBtn>
          ) : (
            <Button.SubMenuBtn id="8" onClick={menuSelectHandler}>
              기타
            </Button.SubMenuBtn>
          )}
        </Category>
      ) : null}
      <FilterDiv>
        <FilterOpenBtn
          onBlur={sortMenuOpenHandler}
          onClick={sortMenuOpenHandler}
        >
          <MdMenuOpen />
          <div>{selectSort}</div>
        </FilterOpenBtn>
        {sortMenuOpen ? (
          <SortModal>
            <FilterSelectBtn
              id="최신순"
              className={selectSort === '최신순' ? 'selected' : ''}
              onMouseDown={sortSelectHandler}
            >
              최신순
            </FilterSelectBtn>
            <FilterSelectBtn
              id="추천순"
              className={selectSort === '추천순' ? 'selected' : ''}
              onMouseDown={sortSelectHandler}
            >
              추천순
            </FilterSelectBtn>
            <FilterSelectBtn
              id="조회순"
              className={selectSort === '조회순' ? 'selected' : ''}
              onMouseDown={sortSelectHandler}
            >
              조회순
            </FilterSelectBtn>
          </SortModal>
        ) : null}
      </FilterDiv>
    </MenuDiv>
  );
}

const MenuDiv = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 100%;
  min-height: 100px;
  border-bottom: 1px solid ${theme.colors.gray};
`;

const Category = styled.div``;

const FilterDiv = styled.div`
  position: relative;
`;

const FilterOpenBtn = styled(Button.FilterBtn)`
  display: flex;
  justify-content: center;
  align-items: center;
`;

const FilterSelectBtn = styled(Button.DefaultBtn)`
  display: flex;
  justify-content: center;
  align-items: center;
  border-radius: 5px;
  padding: ${theme.gap.px10} ${theme.gap.px20};
  font-size: ${theme.fontSizes.sm};

  &.selected {
    color: ${theme.colors.pointColor};
  }

  &:hover {
    cursor: pointer;
    color: ${theme.colors.pointColor};
  }
`;

const SortModal = styled.div`
  position: absolute;
  z-index: 1;
  left: -70px;
  top: 50px;
  width: ${theme.gap.px150};
  background-color: ${theme.colors.white};
  border: 1px solid ${theme.colors.lightGray};
  border-radius: 5px;
  box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.05);
`;

export default FreeBoardMenu;

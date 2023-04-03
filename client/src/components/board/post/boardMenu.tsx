import { useState } from 'react';
import { Link, useLocation } from 'react-router-dom';

import { boardMenuStore } from 'stores/boardMenuStore';
import { boardSortStore } from 'stores/boardSortStore';

import styled from 'styled-components';
import theme from 'theme';
import Button from 'components/common/Button';
import { HiPencil } from 'react-icons/hi';
import { MdMenuOpen } from 'react-icons/md';

function FreeBoardMenu() {
  const { selectedMenuStore, setSelectedMenuStore } = boardMenuStore(
    state => state,
  );
  const [sortMenuOpen, setSortMenuOpen] = useState(false);
  const { selectedSortStore, setSelectedSortStore } = boardSortStore(
    state => state,
  );

  const urlData = useLocation().pathname.slice(0, 4);

  const menuSelectHandler = (e: React.MouseEvent<HTMLButtonElement>) => {
    if (e.target instanceof Element) {
      setSelectedMenuStore(e.target.id);
    }
  };
  const sortMenuOpenHandler = () => {
    setSortMenuOpen(!sortMenuOpen);
  };
  const sortSelectHandler = (e: React.MouseEvent<HTMLButtonElement>) => {
    if (e.target instanceof Element) {
      setSelectedSortStore(e.target.id);
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
          {selectedMenuStore === '전체' ? (
            <Button.SubMenuBtn
              id="전체"
              className="selected"
              onClick={menuSelectHandler}
            >
              전체
            </Button.SubMenuBtn>
          ) : (
            <Button.SubMenuBtn id="전체" onClick={menuSelectHandler}>
              전체
            </Button.SubMenuBtn>
          )}
          {selectedMenuStore === '공지' ? (
            <Button.SubMenuBtn
              id="공지"
              className="selected"
              onClick={menuSelectHandler}
            >
              공지
            </Button.SubMenuBtn>
          ) : (
            <Button.SubMenuBtn id="공지" onClick={menuSelectHandler}>
              공지
            </Button.SubMenuBtn>
          )}
          {selectedMenuStore === '일상' ? (
            <Button.SubMenuBtn
              id="일상"
              className="selected"
              onClick={menuSelectHandler}
            >
              일상
            </Button.SubMenuBtn>
          ) : (
            <Button.SubMenuBtn id="일상" onClick={menuSelectHandler}>
              일상
            </Button.SubMenuBtn>
          )}
          {selectedMenuStore === '정보' ? (
            <Button.SubMenuBtn
              id="정보"
              className="selected"
              onClick={menuSelectHandler}
            >
              정보
            </Button.SubMenuBtn>
          ) : (
            <Button.SubMenuBtn id="정보" onClick={menuSelectHandler}>
              정보
            </Button.SubMenuBtn>
          )}
          {selectedMenuStore === '유머' ? (
            <Button.SubMenuBtn
              id="유머"
              className="selected"
              onClick={menuSelectHandler}
            >
              유머
            </Button.SubMenuBtn>
          ) : (
            <Button.SubMenuBtn id="유머" onClick={menuSelectHandler}>
              유머
            </Button.SubMenuBtn>
          )}
        </Category>
      ) : null}
      {urlData === '/qna' ? (
        <Category>
          {selectedMenuStore === '전체' ? (
            <Button.SubMenuBtn
              id="전체"
              className="selected"
              onClick={menuSelectHandler}
            >
              전체
            </Button.SubMenuBtn>
          ) : (
            <Button.SubMenuBtn id="전체" onClick={menuSelectHandler}>
              전체
            </Button.SubMenuBtn>
          )}
          {selectedMenuStore === '공지' ? (
            <Button.SubMenuBtn
              id="공지"
              className="selected"
              onClick={menuSelectHandler}
            >
              공지
            </Button.SubMenuBtn>
          ) : (
            <Button.SubMenuBtn id="공지" onClick={menuSelectHandler}>
              공지
            </Button.SubMenuBtn>
          )}
          {selectedMenuStore === '국어' ? (
            <Button.SubMenuBtn
              id="국어"
              className="selected"
              onClick={menuSelectHandler}
            >
              국어
            </Button.SubMenuBtn>
          ) : (
            <Button.SubMenuBtn id="국어" onClick={menuSelectHandler}>
              국어
            </Button.SubMenuBtn>
          )}
          {selectedMenuStore === '영어' ? (
            <Button.SubMenuBtn
              id="영어"
              className="selected"
              onClick={menuSelectHandler}
            >
              영어
            </Button.SubMenuBtn>
          ) : (
            <Button.SubMenuBtn id="영어" onClick={menuSelectHandler}>
              영어
            </Button.SubMenuBtn>
          )}
          {selectedMenuStore === '수학' ? (
            <Button.SubMenuBtn
              id="수학"
              className="selected"
              onClick={menuSelectHandler}
            >
              수학
            </Button.SubMenuBtn>
          ) : (
            <Button.SubMenuBtn id="수학" onClick={menuSelectHandler}>
              수학
            </Button.SubMenuBtn>
          )}
          {selectedMenuStore === '사탐' ? (
            <Button.SubMenuBtn
              id="사탐"
              className="selected"
              onClick={menuSelectHandler}
            >
              사탐
            </Button.SubMenuBtn>
          ) : (
            <Button.SubMenuBtn id="사탐" onClick={menuSelectHandler}>
              사탐
            </Button.SubMenuBtn>
          )}
          {selectedMenuStore === '과탐' ? (
            <Button.SubMenuBtn
              id="과탐"
              className="selected"
              onClick={menuSelectHandler}
            >
              과탐
            </Button.SubMenuBtn>
          ) : (
            <Button.SubMenuBtn id="과탐" onClick={menuSelectHandler}>
              과탐
            </Button.SubMenuBtn>
          )}
          {selectedMenuStore === '국사' ? (
            <Button.SubMenuBtn
              id="국사"
              className="selected"
              onClick={menuSelectHandler}
            >
              국사
            </Button.SubMenuBtn>
          ) : (
            <Button.SubMenuBtn id="국사" onClick={menuSelectHandler}>
              국사
            </Button.SubMenuBtn>
          )}
          {selectedMenuStore === '기타' ? (
            <Button.SubMenuBtn
              id="기타"
              className="selected"
              onClick={menuSelectHandler}
            >
              기타
            </Button.SubMenuBtn>
          ) : (
            <Button.SubMenuBtn id="기타" onClick={menuSelectHandler}>
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
          <div>{selectedSortStore}</div>
        </FilterOpenBtn>
        {sortMenuOpen ? (
          <SortModal>
            <FilterSelectBtn
              id="최신순"
              className={selectedSortStore === '최신순' ? 'selected' : ''}
              onMouseDown={sortSelectHandler}
            >
              최신순
            </FilterSelectBtn>
            <FilterSelectBtn
              id="추천순"
              className={selectedSortStore === '추천순' ? 'selected' : ''}
              onMouseDown={sortSelectHandler}
            >
              추천순
            </FilterSelectBtn>
            <FilterSelectBtn
              id="조회순"
              className={selectedSortStore === '조회순' ? 'selected' : ''}
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

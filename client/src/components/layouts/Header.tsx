import { Link } from 'react-router-dom';
import styled from 'styled-components';
import Button from 'components/common/Button';
import isLogin from 'utils/isLogin';
import Toggle from '../common/Toggle';
import theme from '../../theme';

function Header() {
  return (
    <Container>
      <Left>
        <Link to="/">
          <Title>yanoljimalja</Title>
        </Link>
        <UL>
          <Link to="/ReviewPage">
            <LI>강사리뷰</LI>
          </Link>
          <LI>강의리뷰</LI>
          <Link to="/qna">
            <LI>질문게시판</LI>
          </Link>
          <Link to="/free">
            <LI>자유게시판</LI>
          </Link>

          <LI>이벤트</LI>
          <LI>스터디카페</LI>
        </UL>
      </Left>
      <Right>
        <ToggleDiv>
          <Toggle />
        </ToggleDiv>
        {isLogin() ? (
          <BtnDiv>
            <Button.WhiteBtn>내정보</Button.WhiteBtn>
            <Button.PointBtn>로그아웃</Button.PointBtn>
          </BtnDiv>
        ) : (
          <BtnDiv>
            <Link to="/login">
              <Button.WhiteBtn>로그인</Button.WhiteBtn>
            </Link>
            <Button.PointBtn>회원가입</Button.PointBtn>
          </BtnDiv>
        )}
      </Right>
    </Container>
  );
}

const Container = styled.div`
  display: flex;
  justify-content: space-between;
  position: sticky;
  z-index: 1;
  height: 74px;
  padding: 1.25rem 2.5rem 1.25rem 2.5rem;
  border-top: medium solid ${theme.colors.pointColor};
  border-bottom: thin solid ${theme.colors.pointColor};
`;

const Title = styled.h1`
  display: flex;
  align-items: center;
  font-weight: bold;
  color: ${theme.colors.pointColor};
  font-size: 35px;
`;

const UL = styled.ul`
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin: 0 ${theme.gap.px40} 0 ${theme.gap.px40};

  > a {
    display: flex;
  }
`;

const LI = styled.li`
  display: flex;
  justify-content: center;
  align-items: center;
  white-space: nowrap;
  width: 100%;
  height: 100%;
  margin: 0 0.425rem 0 0.425rem;
  color: ${theme.colors.pointColor};
  &:hover {
    cursor: pointer;
    color: ${theme.colors.pointColor_hover};
  }
`;

const BtnDiv = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 176px;
  > a {
    display: flex;
  }
`;

const Left = styled.div`
  display: flex;
`;

const Right = styled.div`
  display: flex;
  align-items: center;
`;

const ToggleDiv = styled.div`
  display: flex;
  margin: 0 ${theme.gap.px20} 0 ${theme.gap.px20};
`;
export default Header;

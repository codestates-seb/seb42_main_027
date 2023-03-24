import { Link, useNavigate } from 'react-router-dom';
import styled from 'styled-components';
import Button from 'components/common/Button';
import { useIsLoginStore } from 'stores/loginStore';
import Toggle from '../common/Toggle';
import theme from '../../theme';

function Header() {
  const { isLoginInStore, setIsLoginInStore } = useIsLoginStore(state => state);
  const navigate = useNavigate();
  const logOutHandler = () => {
    localStorage.removeItem('token');
    localStorage.removeItem('email');
    setIsLoginInStore(false);
  };

  const handleClickMypage = () => {
    navigate('/mypage');
  };
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
          <Link to="/lectureslist">
            <LI>강의리뷰</LI>
          </Link>
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
        {isLoginInStore ? (
          <BtnDiv>
            <Button.WhiteBtn onClick={handleClickMypage}>
              내정보
            </Button.WhiteBtn>
            <Button.PointBtn onClick={logOutHandler}>로그아웃</Button.PointBtn>
          </BtnDiv>
        ) : (
          <BtnDiv>
            <Link to="/login">
              <Button.WhiteBtn>로그인</Button.WhiteBtn>
            </Link>
            <Link to="/signup">
              <Button.PointBtn>회원가입</Button.PointBtn>
            </Link>
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
  top: 0;
  z-index: 10;
  height: 74px;
  padding: 1.25rem 2.5rem 1.25rem 2.5rem;
  border-top: medium solid ${theme.colors.pointColor};
  border-bottom: thin solid ${theme.colors.pointColor};
  background-color: ${theme.colors.white};
`;

const Title = styled.h1`
  display: flex;
  align-items: center;
  font-weight: bold;
  color: ${theme.colors.pointColor};
  font-size: 35px;
  margin-top: -0.1953125rem;
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

import styled from 'styled-components';
import Button from 'components/UI/Button';
import theme from '../../theme';

function Header() {
  return (
    <Container>
      <h1>yanoljimalja</h1>
      <ul>
        <li>자유</li>
        <li>리뷰</li>
        <li>질문</li>
        <li>이벤트</li>
      </ul>
      <Button.DefaultBtn>토글</Button.DefaultBtn>
      <Button.WhiteBtn>로그인</Button.WhiteBtn>
      <Button.PointBtn>회원가입</Button.PointBtn>
    </Container>
  );
}

const Container = styled.div`
  display: flex;
  justify-content: space-between;
  position: sticky;
  z-index: 1;
`;

export default Header;

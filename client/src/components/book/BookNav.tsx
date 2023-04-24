import { FlexContainer } from 'pages/review/TeacherList/ReviewPage';
import { Link } from 'react-router-dom';
import styled from 'styled-components';

function BookNav() {
  return (
    <FlexContainer width="100%" justify="end">
      <Link to="/booklists">
        <NavSpan>도서리스트</NavSpan>
      </Link>
      <Link to="/bookcart">
        <NavSpan>장바구니</NavSpan>
      </Link>
    </FlexContainer>
  );
}

export default BookNav;

const NavSpan = styled.span`
  :hover {
    color: pink;
  }
  cursor: pointer;
`;

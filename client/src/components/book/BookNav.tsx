import { FlexContainer } from 'pages/review/TeacherList/ReviewPage';
import { Link } from 'react-router-dom';
import styled from 'styled-components';

function BookNav() {
  return (
    <FlexContainer width="100%" justify="end" padding="0 0 1rem 0">
      <Link to="/booklists">
        <NavButton>쇼핑</NavButton>
      </Link>
      <Link to="/bookcart">
        <NavButton>장바구니</NavButton>
      </Link>
    </FlexContainer>
  );
}

export default BookNav;

const NavButton = styled.button`
  :hover {
    color: white;
    background-color: #6667ab;
  }
  border-radius: 15px;
  padding: 0.7rem;
  transition: 0.3s;
  cursor: pointer;
`;

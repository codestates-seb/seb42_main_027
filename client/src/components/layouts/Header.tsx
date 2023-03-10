import Button from 'components/UI/Button';

function Header() {
  return (
    <>
      <h1>yanoljimalja</h1>
      <ul>
        <li>자유</li>
        <li>리뷰</li>
        <li>질문</li>
        <li>이벤트</li>
      </ul>
      <Button>토글</Button>
      <Button>로그인</Button>
      <Button>로그아웃</Button>
    </>
  );
}

export default Header;

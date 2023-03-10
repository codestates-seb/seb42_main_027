import styled from 'styled-components';

function Footer() {
  return (
    <Container>
      <h1>야놀지말자</h1>
      <section>
        <ul>
          <li>[FE] 김기은, 노지용, 이채욱</li>
          <li>[BE] 김다빈, 김민호, 노석준</li>
          <li>
            <a href="https://github.com/codestates-seb/seb42_main_027">
              GitHub Repository Link
            </a>
          </li>
        </ul>
      </section>
      <div>Copyright (c) 2023. 야놀지말자. All rights reserved.</div>
    </Container>
  );
}

const Container = styled.div`
  padding: 20px;
  background-color: #6667ab;
`;

export default Footer;

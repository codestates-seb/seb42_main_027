import styled from 'styled-components';
import theme from '../../theme';
import { ReactComponent as GithubLogo } from '../../assets/images/github-mark-white.svg';

function Footer() {
  return (
    <Container>
      <Title>야놀지말자</Title>
      <Section>
        <ul>
          <LI>[FE] 김기은, 노지용, 이채욱</LI>
          <LI>[BE] 김다빈, 김민호, 노석준</LI>
          <LI className="gh-link">
            <a href="https://github.com/codestates-seb/seb42_main_027">
              <GitHubLink>
                <div>
                  <GithubLogo width="16" height="16" />
                </div>
                <div>GitHub Repository Link</div>
              </GitHubLink>
            </a>
          </LI>
        </ul>
      </Section>
      <CopyRightDiv>
        <div>Copyright (c) 2023. 야놀지말자. All rights reserved.</div>
      </CopyRightDiv>
      <div>
        <a href="https://www.flaticon.com/icons" title="icons">
          Icons created by Freepik - Flaticon
        </a>
      </div>
    </Container>
  );
}

const Container = styled.div`
  height: 207px;
  padding: 1.25rem;
  color: ${theme.colors.white};
  background-color: ${theme.colors.pointColor};
`;

const Title = styled.h1`
  display: flex;
  align-items: center;
  font-weight: bold;
  color: ${theme.colors.white};
  font-size: 32px;
  margin-bottom: ${theme.gap.px20};
`;

const Section = styled.section`
  margin-bottom: ${theme.gap.px20};
`;

const LI = styled.li`
  margin-bottom: calc(${theme.gap.px20} / 5);

  &.gh-link {
    margin: 0;
  }
`;

const GitHubLink = styled.div`
  display: flex;
  align-items: center;

  > div {
    padding: 0 2px 0 2px;
  }
`;

const CopyRightDiv = styled.div`
  margin-bottom: calc(${theme.gap.px20} / 5);
`;
export default Footer;

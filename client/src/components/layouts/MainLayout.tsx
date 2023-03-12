import styled from 'styled-components';

import Footer from './Footer';
import Header from './Header';

const Container = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  height: 100vh;
`;

type MainLayoutProps = {
  children: React.ReactNode;
};

function MainLayout({ children }: MainLayoutProps) {
  return (
    <Container>
      <Header />
      {children}
      <Footer />
    </Container>
  );
}

export default MainLayout;

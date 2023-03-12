import Header from 'components/layouts/Header';
import Footer from 'components/layouts/Footer';
import MainLayout from 'components/layouts/MainLayout';
import GlobalStyle from './GlobalStyles';
import Router from './Router';

function App() {
  return (
    <>
      <GlobalStyle />
      {/* <Header /> */}
      <MainLayout>
        <Router />
      </MainLayout>
      {/* <Footer /> */}
    </>
  );
}

export default App;

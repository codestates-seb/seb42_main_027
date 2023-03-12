import Header from 'components/layouts/Header';
import Footer from 'components/layouts/Footer';
import GlobalStyle from './GlobalStyles';
import Router from './Router';

function App() {
  return (
    <>
      <GlobalStyle />
      <Header />
      <Router />
      <Footer />
    </>
  );
}

export default App;

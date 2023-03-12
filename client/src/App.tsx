import MainLayout from 'components/layouts/MainLayout';
import GlobalStyle from './GlobalStyles';
import Router from './Router';

function App() {
  return (
    <>
      <GlobalStyle />
      <MainLayout>
        <Router />
      </MainLayout>
    </>
  );
}

export default App;

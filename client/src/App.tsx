import MainLayout from 'components/layouts/MainLayout';
import { useEffect } from 'react';
import { useIsLoginStore } from 'stores/loginStore';
import isLogin from 'utils/isLogin';
import GlobalStyle from './GlobalStyles';
import Router from './Router';

function App() {
  const { setIsLogin } = useIsLoginStore();

  useEffect(() => {
    if (isLogin()) {
      setIsLogin(true);
    }
  }, []);

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

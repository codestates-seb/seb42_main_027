import MainLayout from 'components/layouts/MainLayout';
import { useEffect } from 'react';
import { useIsLoginStore } from 'stores/loginStore';
import isLogin from 'utils/isLogin';
import getUserInfo from 'apis/getUserInfo';
import useUserInfoStore from 'stores/userInfoStore';
import GlobalStyle from './GlobalStyles';
import Router from './Router';

function App() {
  const { setIsLoginInStore } = useIsLoginStore();
  const { setUserInfo } = useUserInfoStore(state => state);

  const email = localStorage.getItem('email');

  const fetchUserInfo = async () => {
    try {
      const response = await getUserInfo(email);
      setUserInfo(response);
    } catch (error) {
      console.error(error);
      console.log('정보를 가져오지 못했습니다.');
    }
  };

  useEffect(() => {
    if (isLogin()) {
      setIsLoginInStore(true);
      fetchUserInfo();
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

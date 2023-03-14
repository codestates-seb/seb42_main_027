const isLogin = () => {
  if (localStorage.getItem('token')) {
    return true;
  }
  return false;
};

export default isLogin;

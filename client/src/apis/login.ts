import axios from 'axios';

type LoginParams = {
  email: string;
  password: string;
};

type AuthResponse = {
  token: string;
};

const login = async (pathData: LoginParams): Promise<void> => {
  const response = await axios.post<AuthResponse>(
    'http://13.125.1.215:8080/auth/login',
    pathData,
  );
  localStorage.setItem('token', response.data.token);
};

export default login;

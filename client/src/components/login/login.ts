import axios from 'axios';

type LoginParams = {
  email: string;
  password: string;
};

type AuthResponse = {
  token: string;
};

const login = async (pathData: LoginParams): Promise<void> => {
  const response = await axios.post<AuthResponse>('/api/login', {
    pathData,
  });
  localStorage.setItem('token', response.data.token);
};

export default login;

import axios from 'axios';

const apiUrl = process.env.REACT_APP_API_URL;
type LoginParams = {
  email: string;
  password: string;
};

type AuthResponse = {
  token: string;
};

const login = async (pathData: LoginParams) => {
  const response = await axios.post<AuthResponse>(
    `${apiUrl}/auth/login`,
    pathData,
  );
  const token = response.headers.authorization;
  localStorage.setItem('token', token);
};

export default login;

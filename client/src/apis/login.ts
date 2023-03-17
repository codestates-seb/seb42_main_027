import axios from 'axios';

const apiUrl = process.env.REACT_APP_API_URL;
type LoginParams = {
  email: string;
  password: string;
};

type AuthResponse = {
  token: string;
};

const login = async (pathData: LoginParams): Promise<void> => {
  const response = await axios.post<AuthResponse>(
    `${apiUrl}/auth/login`,
    pathData,
  );
  localStorage.setItem('token', response.data.token);
};

export default login;

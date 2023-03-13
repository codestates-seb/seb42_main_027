import axios from 'axios';

type LoginParams = {
  email: string;
  password: string;
};

type AuthResponse = {
  token: string;
};

const login = async ({ email, password }: LoginParams): Promise<void> => {
  try {
    const response = await axios.post<AuthResponse>('/api/login', {
      email,
      password,
    });
    localStorage.setItem('token', response.data.token);
  } catch (error) {
    console.error(error);
  }
};

export default login;

import axios from 'axios';

type LoginInfo = {
  email: string;
  password: string;
};

export const fetchLogin = async (pathData: LoginInfo) => {
  try {
    const response = await axios.post(`api/login`, pathData, {
      withCredentials: true,
    });
    return response.data;
  } catch (error) {
    return console.error(error);
  }
};

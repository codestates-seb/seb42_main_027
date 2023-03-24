import axios from 'axios';

const apiUrl = process.env.REACT_APP_API_URL;

type SignUpParams = {
  username: string;
  phoneNumber: string;
  displayName: string;
  email: string;
  password: string;
  confirmPassword: string;
  createdAt: string;
  state: string;
};

const postSignUp = async (pathData: SignUpParams) => {
  await axios.post(`${apiUrl}/members`, pathData);
};

export default postSignUp;

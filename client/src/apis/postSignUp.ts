import axios from 'axios';

type SignUpParams = {
  userName: string;
  phoneNum: string;
  displayName: string;
  email: string;
  password: string;
  confirmPassword: string;
  createdAt: string;
  userState: string;
};

const postSignUp = async (pathData: SignUpParams) => {
  await axios.post('http://13.125.1.215:8080/members', pathData);
};

export default postSignUp;

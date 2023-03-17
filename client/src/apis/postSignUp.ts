import axios from 'axios';

type SignUpParams = {
  username: string;
  phoneNumber: string;
  displayName: string;
  email: string;
  password: string;
  confirmPassword: string;
  // createdAt: string;
  // userState: string;
};

const postSignUp = async (pathData: SignUpParams) => {
  await axios.post('https://65e1-119-65-189-55.jp.ngrok.io/members', pathData);
};

export default postSignUp;

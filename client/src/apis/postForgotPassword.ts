import axios from 'axios';

const apiUrl = process.env.REACT_APP_API_URL;

type postForgotPasswordParams = {
  username: string;
  email: string;
  phoneNumber: string;
};

const postForgotPassword = async (pathData: postForgotPasswordParams) => {
  await axios.post(`${apiUrl}/members/finds/findpasswords`, pathData, {
    headers: {
      'ngrok-skip-browser-warning': '69420',
    },
  });
};

export default postForgotPassword;

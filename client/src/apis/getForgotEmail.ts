import axios from 'axios';

const apiUrl = process.env.REACT_APP_API_URL;

type SignUpParams = {
  username: string;
  phoneNumber: string;
};

const getForgotEmail = async (pathData: SignUpParams) => {
  const response = await axios.post(`${apiUrl}/members/findemails`, pathData, {
    headers: {
      'ngrok-skip-browser-warning': '69420',
    },
  });
  return response.data;
};

export default getForgotEmail;

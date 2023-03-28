import axios from 'axios';

const apiUrl = process.env.REACT_APP_API_URL;

const postSendEmail = async (email: string) => {
  await axios.post(`${apiUrl}/members/sendEmail?memberEmail=${email}`, {
    headers: {
      'ngrok-skip-browser-warning': '69420',
    },
  });
};

export default postSendEmail;

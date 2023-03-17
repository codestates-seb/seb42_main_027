import axios from 'axios';

// http://13.125.1.215:8080/members/2

const getUserInfo = async (email: string | null) => {
  const response = await axios.get(
    `https://65e1-119-65-189-55.jp.ngrok.io/members/${email}`,
    {
      headers: {
        Authorization: `Bearer ${localStorage.getItem('token')}`,
        'ngrok-skip-browser-warning': '69420',
      },
    },
  );
  return response.data;
};

export default getUserInfo;

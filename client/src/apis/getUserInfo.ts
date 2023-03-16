import axios from 'axios';

// http://13.125.1.215:8080/members/2
// https://05ee-112-156-175-230.jp.ngrok.io/
const getUserInfo = async () => {
  const response = await axios.get(
    'https://05ee-112-156-175-230.jp.ngrok.io/members/2',
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

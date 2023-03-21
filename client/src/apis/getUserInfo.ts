import axios from 'axios';

const apiUrl = process.env.REACT_APP_API_URL;

const getUserInfo = async (email: string | null) => {
  const response = await axios.get(`${apiUrl}/members/${email}`, {
    headers: {
      Authorization: `Bearer ${localStorage.getItem('token')}`,
      'ngrok-skip-browser-warning': '69420',
    },
  });
  return response.data.data;
};

export default getUserInfo;

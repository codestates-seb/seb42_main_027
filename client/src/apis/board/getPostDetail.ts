import axios from 'axios';

const apiUrl = process.env.REACT_APP_API_URL;

const getPostDetail = async (board: string, id: number) => {
  const response = await axios.get(`${apiUrl}/boards/${board}/${id}`, {
    headers: {
      Authorization: `Bearer ${localStorage.getItem('token')}`,
      'ngrok-skip-browser-warning': '69420',
    },
  });
  return response.data;
};

export default getPostDetail;

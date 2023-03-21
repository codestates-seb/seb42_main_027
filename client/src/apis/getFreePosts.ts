import axios from 'axios';

const apiUrl = process.env.REACT_APP_API_URL;

const getFreePosts = async (id: number | null) => {
  const response = await axios.get(`${apiUrl}/members/${id}/frees`, {
    headers: {
      'ngrok-skip-browser-warning': '69420',
    },
  });
  return response.data.data;
};

export default getFreePosts;

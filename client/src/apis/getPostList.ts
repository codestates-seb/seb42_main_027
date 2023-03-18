import axios from 'axios';

const apiUrl = process.env.REACT_APP_API_URL;

const getPostList = async (board: string, page: number) => {
  const response = await axios.get(`${apiUrl}/boards/${board}?page=${page}`, {
    headers: {
      Authorization: `Bearer ${localStorage.getItem('token')}`,
    },
  });
  return response.data;
};

export default getPostList;

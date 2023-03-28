import axios from 'axios';

const apiUrl = process.env.REACT_APP_API_URL;

const getPostList = async (
  board: string,
  category: string | null,
  sort: string | null,
  page: number,
) => {
  const response = await axios.get(
    `${apiUrl}/boards/${board}?category=${category}&sort=${sort}&page=${page}`,
    {
      headers: {
        Authorization: `${localStorage.getItem('token')}`,
        'ngrok-skip-browser-warning': '69420',
      },
    },
  );
  return response.data;
};

export default getPostList;

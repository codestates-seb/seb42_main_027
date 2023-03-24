import axios from 'axios';

const apiUrl = process.env.REACT_APP_API_URL;

type PostData = {
  title: string;
  content: string;
  category: string;
  createdAt: string;
};

const PostData = async (data: PostData, board: string) => {
  const response = await axios.post(`${apiUrl}/boards/${board}`, data, {
    headers: {
      Authorization: `Bearer ${localStorage.getItem('token')}`,
      'ngrok-skip-browser-warning': '69420',
    },
  });
  return response.data;
};

export default PostData;

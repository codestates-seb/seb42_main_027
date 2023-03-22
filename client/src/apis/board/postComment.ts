import axios from 'axios';

const apiUrl = process.env.REACT_APP_API_URL;

type PostData = {
  content: string;
  createdAt: string;
};

const PostComment = async (data: PostData, board: string, id: number) => {
  const response = await axios.post(`${apiUrl}/comments/${board}/${id}`, data, {
    headers: {
      Authorization: `Bearer ${localStorage.getItem('token')}`,
      'ngrok-skip-browser-warning': '69420',
    },
  });
  return response.data;
};

export default PostComment;

import axios from 'axios';

const apiUrl = process.env.REACT_APP_API_URL;

const deleteComment = async (board: string, commentId: number) => {
  const response = await axios.delete(
    `${apiUrl}/comments/${board}/${commentId}`,
    {
      headers: {
        Authorization: `Bearer ${localStorage.getItem('token')}`,
        'ngrok-skip-browser-warning': '69420',
      },
    },
  );
  return response.data;
};

export default deleteComment;

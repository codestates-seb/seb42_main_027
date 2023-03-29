import axios from 'axios';

const apiUrl = process.env.REACT_APP_API_URL;

const PostAdopt = async (questionId: number, answerId: number) => {
  const response = await axios.post(
    `${apiUrl}/boards/qnas/questions/${questionId}/adopt-answer/${answerId}`,
    {},
    {
      headers: {
        Authorization: `${localStorage.getItem('token')}`,
        'ngrok-skip-browser-warning': '69420',
      },
    },
  );
  return response.data;
};

export default PostAdopt;

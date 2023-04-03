import axios from 'axios';

const apiUrl = process.env.REACT_APP_API_URL;

type PostData = {
  content: string;
  uploadImages: string[] | [];
  createdAt: string;
  questionId: number;
};

const PostAnswer = async (data: PostData) => {
  const response = await axios.post(`${apiUrl}/boards/qnas/answers`, data, {
    headers: {
      Authorization: `${localStorage.getItem('token')}`,
      'ngrok-skip-browser-warning': '69420',
    },
  });
  return response.data;
};

export default PostAnswer;

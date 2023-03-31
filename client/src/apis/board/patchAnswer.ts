import axios from 'axios';

const apiUrl = process.env.REACT_APP_API_URL;

type PostData = {
  content: string;
  uploadImages: string[] | [];
  modifiedAt: string;
};

const PatchAnswer = async (data: PostData, answerId: number) => {
  const response = await axios.patch(
    `${apiUrl}/boards/qnas/answers/${answerId}`,
    data,
    {
      headers: {
        Authorization: `${localStorage.getItem('token')}`,
        'ngrok-skip-browser-warning': '69420',
      },
    },
  );
  return response.data;
};

export default PatchAnswer;

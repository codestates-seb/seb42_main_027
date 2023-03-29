import axios from 'axios';

const apiUrl = process.env.REACT_APP_API_URL;

type PatchData = {
  content: string;
  modifiedAt: string;
};

const PatchComment = async (
  data: PatchData,
  board: string,
  commentId: number,
) => {
  const response = await axios.patch(
    `${apiUrl}/comments/${board}/${commentId}`,
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

export default PatchComment;

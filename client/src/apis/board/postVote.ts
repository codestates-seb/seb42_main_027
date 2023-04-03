import axios from 'axios';

const apiUrl = process.env.REACT_APP_API_URL;

const PostVote = async (board: string, id: number, feat: string) => {
  const response = await axios.post(
    `${apiUrl}/votes/${board}/${id}/${feat}`,
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

export default PostVote;

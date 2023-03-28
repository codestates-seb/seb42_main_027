import axios from 'axios';

const apiUrl = process.env.REACT_APP_API_URL;

type PatchData = {
  title: string;
  content: string;
  category: string;
  // uploadImages: string[];
  modifiedAt: string;
};

const PatchData = async (data: PatchData, board: string, id: number) => {
  const response = await axios.patch(`${apiUrl}/boards/${board}/${id}`, data, {
    headers: {
      Authorization: `${localStorage.getItem('token')}`,
      'ngrok-skip-browser-warning': '69420',
    },
  });
  return response.data;
};

export default PatchData;

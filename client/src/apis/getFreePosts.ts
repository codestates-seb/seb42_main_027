import axios from 'axios';

const apiUrl = process.env.REACT_APP_API_URL;

const getFreePosts = async (id: number | null, select: string) => {
  let boardType;
  let endPoint;
  if (select === '자유 게시판') {
    boardType = 'frees';
    endPoint = 'members';
  }
  if (select === '질문 게시판') {
    boardType = 'questions';
    endPoint = 'members';
  }
  if (select === '강의 리뷰') {
    boardType = 'lectures';
    endPoint = 'boards';
  }
  //! 이부분은 나중에
  const response = await axios.get(`${apiUrl}/${endPoint}/${id}/${boardType}`, {
    headers: {
      'ngrok-skip-browser-warning': '69420',
    },
  });
  console.log(`update: ${boardType}`);
  return response.data.data;
};

export default getFreePosts;

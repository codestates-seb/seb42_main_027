import axios from 'axios';

const apiUrl = process.env.REACT_APP_API_URL;

const getComment = async (id: number | null, select: string) => {
  let boardType;
  if (select === '자유 게시판') {
    boardType = 'comments/frees';
  }
  if (select === '답변 게시글') {
    boardType = 'comments/qnas';
  }
  if (select === '강의 리뷰') {
    boardType = 'comments/reviews';
  }
  if (select === '대댓글') {
    boardType = 'recomments/qnas';
  }
  const response = await axios.get(`${apiUrl}/members/${id}/${boardType}`, {
    headers: {
      'ngrok-skip-browser-warning': '69420',
    },
  });
  return response.data.data;
};

export default getComment;

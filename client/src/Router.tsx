import Main from 'pages/MainPage';
import Login from 'pages/Login';
import QnABoard from 'pages/QnABoard/QnABoard';
import FreeBoard from 'pages/FreeBoard/FreeBoard';
import { BrowserRouter, Routes, Route } from 'react-router-dom';

function Router() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Main />} />
        <Route path="/login" element={<Login />} />
        <Route path="/qna" element={<QnABoard />} />
        <Route path="/free" element={<FreeBoard />} />
      </Routes>
    </BrowserRouter>
  );
}

export default Router;

import Header from 'components/layouts/Header';
import Footer from 'components/layouts/Footer';
import Main from 'pages/MainPage';
import Login from 'pages/Login';
import QnABoard from 'pages/QnABoard/QnABoard';
import FreeBoard from 'pages/FreeBoard/FreeBoard';
import { BrowserRouter, Routes, Route } from 'react-router-dom';

function Router() {
  return (
    <BrowserRouter>
      <Header />
      <Routes>
        <Route path="/" element={<Main />} />
        <Route path="/login" element={<Login />} />
        <Route path="/qna" element={<QnABoard />} />
        <Route path="/free" element={<FreeBoard />} />
      </Routes>
      <Footer />
    </BrowserRouter>
  );
}

export default Router;

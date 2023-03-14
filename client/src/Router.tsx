import Header from 'components/layouts/Header';
import Footer from 'components/layouts/Footer';
import Main from 'pages/MainPage';
import Login from 'pages/Login';
import QnABoard from 'pages/QnABoard/QnABoard';
import FreeBoard from 'pages/FreeBoard/FreeBoard';
import ReviewPage from 'pages/Review/ReviewPage';
import ReviewPageDetail from 'pages/Review/ReviewPageDetail';
import CreateTeacher from 'pages/Review/CreateTeacher';

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
        <Route path="/ReviewPage" element={<ReviewPage />} />
        <Route
          path="/ReviewPageDetail/:teacherId"
          element={<ReviewPageDetail />}
        />
        <Route path="/ReviewPage/createTeacher" element={<CreateTeacher />} />
      </Routes>
      <Footer />
    </BrowserRouter>
  );
}

export default Router;

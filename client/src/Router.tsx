import Header from 'components/layouts/Header';
import Footer from 'components/layouts/Footer';
import Main from 'pages/MainPage';
import Login from 'pages/Login';
import QnABoard from 'pages/qnABoard/QnABoard';
import FreeBoard from 'pages/freeBoard/FreeBoard';
import ReviewPage from 'pages/review/ReviewPage';
import ReviewPageDetail from 'pages/review/ReviewPageDetail';
import CreateTeacher from 'pages/review/CreateTeacher';
import StudentSignUpForm from 'components/member/signup/StudentSignUpForm';
import TeacherSignUpForm from 'components/member/signup/TeacherSignUpForm';

import { BrowserRouter, Routes, Route } from 'react-router-dom';
import SignUp from 'pages/SignUp';

function Router() {
  return (
    <BrowserRouter>
      <Header />
      <Routes>
        <Route path="/" element={<Main />} />
        <Route path="/login" element={<Login />} />
        <Route path="/signup" element={<SignUp />}>
          <Route path="/signup/student" element={<StudentSignUpForm />} />
          <Route path="/signup/teacher" element={<TeacherSignUpForm />} />
        </Route>
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

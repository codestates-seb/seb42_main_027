import Login from 'pages/Login';
import ReviewPage from 'pages/Review/ReviewPage';
import ReviewPageDetail from 'pages/Review/ReviewPageDetail';
import CreateTeacher from 'pages/Review/CreateTeacher';
import { BrowserRouter, Routes, Route } from 'react-router-dom';

function Router() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="login" element={<Login />} />
        <Route path="/" element={<ReviewPage />} />
        <Route
          path="/ReviewPageDetail/:teacherId"
          element={<ReviewPageDetail />}
        />
        <Route path="/createTeacher" element={<CreateTeacher />} />
      </Routes>
    </BrowserRouter>
  );
}

export default Router;

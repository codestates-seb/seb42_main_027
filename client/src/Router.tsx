import Login from 'pages/Login';
import ReviewPage from 'pages/Review/ReviewPage';
import { BrowserRouter, Routes, Route } from 'react-router-dom';

function Router() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="login" element={<Login />} />
        <Route path="/" element={<ReviewPage />} />
      </Routes>
    </BrowserRouter>
  );
}

export default Router;

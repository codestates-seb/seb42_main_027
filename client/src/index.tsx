import ReactDOM from 'react-dom/client';
import { RecoilRoot } from 'recoil';
// 리코일 사용을 위해 루트를 감싸줌
import App from './App';

const root = ReactDOM.createRoot(
  document.getElementById('root') as HTMLElement,
);
root.render(
  <RecoilRoot>
    <App />
  </RecoilRoot>,
);

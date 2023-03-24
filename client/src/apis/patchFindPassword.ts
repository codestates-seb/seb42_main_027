import axios from 'axios';

const apiUrl = process.env.REACT_APP_API_URL;

type patchFindPasswordProps = {
  newPassword: string;
  confirmPassword: string;
};

const patchFindPassword = async (pathData: patchFindPasswordProps) => {
  //! endPoint 협의 필요
  await axios.patch(`${apiUrl}/members/changepassword`, pathData, {
    headers: {
      'ngrok-skip-browser-warning': '69420',
    },
  });
};

export default patchFindPassword;

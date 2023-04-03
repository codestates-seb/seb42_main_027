import axios from 'axios';

const apiUrl = process.env.REACT_APP_API_URL;

type UserPasswordProps = {
  nowPassword: string;
  newPassword: string;
  confirmPassword: string;
};

const patchUserPassword = async (
  pathData: UserPasswordProps,
  id: number | null,
) => {
  await axios.patch(`${apiUrl}/members/${id}/changepasswords`, pathData, {
    headers: {
      'ngrok-skip-browser-warning': '69420',
    },
  });
};

export default patchUserPassword;

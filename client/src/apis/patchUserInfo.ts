import axios from 'axios';

const apiUrl = process.env.REACT_APP_API_URL;

type UserInfoProps = {
  phoneNumber: string;
  password: string;
  displayName: string;
};

const patchUserInfo = async (pathData: UserInfoProps, id: number | null) => {
  await axios.patch(`${apiUrl}/members/${id}`, pathData);
};

export default patchUserInfo;

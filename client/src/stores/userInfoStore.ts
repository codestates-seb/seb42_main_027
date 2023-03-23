import { create } from 'zustand';

type loginInfoPrams = {
  username: string;
  phoneNumber: string;
  memberId: number | null;
  email: string;
  password: string;
  displayName: string;
  state: string;
  createdAt: string;
  iconImageUrl: string | null;
};
type userInfoPrams = {
  userInfo: loginInfoPrams;
  setUserInfo: (state: loginInfoPrams) => void;
};

const useUserInfoStore = create<userInfoPrams>(set => ({
  userInfo: {
    username: '',
    phoneNumber: '',
    memberId: null,
    email: '',
    password: '',
    displayName: '',
    state: '',
    createdAt: '',
    iconImageUrl: null,
  },
  setUserInfo: state => set(() => ({ userInfo: state })),
}));

export default useUserInfoStore;

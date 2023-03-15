import { create } from 'zustand';

type useIsLoginStoreProps = {
  isLogin: boolean;
  setIsLogin: (state: boolean) => void;
};

export const useIsLoginStore = create<useIsLoginStoreProps>(set => ({
  isLogin: false,
  setIsLogin: state => set(() => ({ isLogin: state })),
}));

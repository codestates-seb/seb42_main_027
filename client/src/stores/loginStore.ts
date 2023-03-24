import { create } from 'zustand';

type useIsLoginStoreProps = {
  isLoginInStore: boolean;
  setIsLoginInStore: (state: boolean) => void;
};

export const useIsLoginStore = create<useIsLoginStoreProps>(set => ({
  isLoginInStore: false,
  setIsLoginInStore: state => set(() => ({ isLoginInStore: state })),
}));

import { create } from 'zustand';

type activeLoginStoreProps = {
  isActiveLogin: boolean;
  setIsActiveLogin: (state: boolean) => void;
};

export const activeLoginStore = create<activeLoginStoreProps>(set => ({
  isActiveLogin: false,
  setIsActiveLogin: state => set(() => ({ isActiveLogin: state })),
}));

import { create } from 'zustand';

type useEditInfoStoreProps = {
  isEditInfo: boolean;
  setIsEditInfo: (state: boolean) => void;
};

export const useEditInfoStore = create<useEditInfoStoreProps>(set => ({
  isEditInfo: false,
  setIsEditInfo: state => set(() => ({ isEditInfo: state })),
}));

import { create } from 'zustand';

type useboardSortStoreProps = {
  selectedSortStore: string;
  setSelectedSortStore: (state: string) => void;
};

export const boardSortStore = create<useboardSortStoreProps>(set => ({
  selectedSortStore: '최신순',
  setSelectedSortStore: state => set(() => ({ selectedSortStore: state })),
}));

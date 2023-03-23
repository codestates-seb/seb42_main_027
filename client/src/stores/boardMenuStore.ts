import { create } from 'zustand';

type useBoardMenuStoreProps = {
  selectedMenuStore: string;
  setSelectedMenuStore: (state: string) => void;
};

export const boardMenuStore = create<useBoardMenuStoreProps>(set => ({
  selectedMenuStore: '0',
  setSelectedMenuStore: state => set(() => ({ selectedMenuStore: state })),
}));

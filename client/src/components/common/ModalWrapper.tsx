import { ReactNode } from 'react';
import Modal from 'react-modal';

type ModalWrapperProps = {
  isOpen: boolean;
  onRequestClose: () => void;
  children: ReactNode;
};

const customStyles = {
  content: {
    top: '50%',
    left: '50%',
    transform: 'translate(-50%, -50%)',
    width: '400px',
    maxHeight: '90%',
    overflow: 'auto',
    background: '#fff',
    borderRadius: '5px',
    padding: '20px',
    boxShadow: '0 0 20px rgba(0, 0, 0, 0.3)',
    border: 'none',
  },
  overlay: {
    backgroundColor: 'rgba(0, 0, 0, 0.5)',
    zIndex: 999,
  },
};

Modal.setAppElement('#root');

function ModalWrapper({ isOpen, onRequestClose, children }: ModalWrapperProps) {
  return (
    <Modal
      isOpen={isOpen}
      onRequestClose={onRequestClose}
      style={customStyles}
      shouldCloseOnOverlayClick={false}
      contentLabel="Modal"
    >
      {children}
    </Modal>
  );
}

export default ModalWrapper;

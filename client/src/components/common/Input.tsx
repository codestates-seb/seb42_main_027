import styled from 'styled-components';
import theme from 'theme';

const { colors } = theme;

const Label = styled.label`
  display: flex;
  flex-direction: column;
  color: ${colors.pointColor};
`;

const StyleInput = styled.input`
  border: 1px solid ${colors.gray};
  padding: 0.4rem 1rem;
  border-radius: 0.5rem;
  width: 20rem;
  margin-bottom: 1rem;
  color: ${colors.fontColor};
`;

type InputProps = {
  type: string;
  label: string;
  id: string;
  onChange: React.ChangeEventHandler<HTMLInputElement>;
};

function Input({ label, type, id, onChange }: InputProps) {
  return (
    <Label htmlFor={id}>
      {label}
      <StyleInput onChange={onChange} type={type} id={id} />
    </Label>
  );
}

export default Input;
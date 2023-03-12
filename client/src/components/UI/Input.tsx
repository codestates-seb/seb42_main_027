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
  padding: 0.25rem 1rem;
  border-radius: 0.5rem;
`;

type InputProps = {
  type: string;
  label: string;
  htmlFor: string;
  id: string;
};

function Input({ label, type, htmlFor, id }: InputProps) {
  return (
    <Label htmlFor={htmlFor}>
      {label}
      <StyleInput type={type} id={id} />
    </Label>
  );
}

export default Input;

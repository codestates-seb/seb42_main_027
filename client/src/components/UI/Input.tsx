import styled from 'styled-components';
import theme from 'theme';

const { colors } = theme;

const Label = styled.label`
  display: flex;
  flex-direction: column;
  color: ${colors.pointColor};
`;

const StyleInput = styled.input``;

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
      <input type={type} id={id} />
    </Label>
  );
}

export default Input;

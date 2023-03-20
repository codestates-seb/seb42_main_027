import styled from 'styled-components';
import theme from 'theme';

const { danger, success, pointColor, gray } = theme.colors;
const dangerRGB = '236, 60, 60';
const successRGB = '101, 226, 113';

const Label = styled.label`
  display: flex;
  flex-direction: column;
  color: ${pointColor};
`;

const StyleInput = styled.input`
  padding: 0.4rem 1rem;
  border-radius: 0.5rem;
  margin-bottom: 0.5rem;
  background-color: ${({ color }) => {
    switch (color) {
      case 'danger':
        return `rgba(${dangerRGB}, 0.2)`;
      case 'success':
        return `rgba(${successRGB}, 0.2)`;
      default:
        return 'white';
    }
  }};

  border: 1px solid
    ${({ color }) => {
      switch (color) {
        case 'danger':
          return danger;
        case 'success':
          return success;
        default:
          return gray;
      }
    }};

  :-webkit-autofill {
    -webkit-box-shadow: 0 0 0 1000px inset
      ${({ color }) => {
        switch (color) {
          case 'danger':
            return `rgba(${dangerRGB}, 0.2)`;
          case 'success':
            return `rgba(${successRGB}, 0.2)`;
          default:
            return 'white';
        }
      }};
  }
`;

const StyleErrorMessage = styled.p`
  margin-bottom: 1rem;
  color: ${danger};
`;

type InputProps = {
  placeholder?: string;
  errorMessage?: string;
  color?: 'danger' | 'success';
  label: string;
  type: string;
  id: string;
  onChange: React.ChangeEventHandler<HTMLInputElement>;
  value: string;
};

function Input({
  placeholder,
  errorMessage,
  color,
  label,
  type,
  id,
  onChange,
  value,
}: InputProps) {
  return (
    <Label htmlFor={id}>
      {label}
      <StyleInput
        color={color}
        placeholder={placeholder}
        onChange={onChange}
        type={type}
        id={id}
        value={value}
      />
      {color === 'danger' ? (
        <StyleErrorMessage>{errorMessage}</StyleErrorMessage>
      ) : null}
    </Label>
  );
}

Input.defaultProps = {
  errorMessage: undefined,
  color: undefined,
  placeholder: undefined,
};

export default Input;

import styled from 'styled-components';
import theme from 'theme';

const { danger, success, pointColor, gray } = theme.colors;
const dangerRGB = '236, 60, 60';
const successRGB = '101, 226, 113';

const EditUserInfo = styled.input`
  padding: 0.1rem 0.2rem;
  border-radius: 0.2rem;
  margin-bottom: 0.2rem;
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
  color: ${danger};
`;

type EditUserInfoInputProps = {
  placeholder: string;
  onChange: React.ChangeEventHandler<HTMLInputElement>;
  errorMessage: string;
  value: string;
  color?: 'danger' | 'success';
};

function EditUserInfoInput({
  placeholder,
  onChange,
  errorMessage,
  value,
  color,
}: EditUserInfoInputProps) {
  return (
    <>
      <EditUserInfo
        placeholder={placeholder}
        onChange={onChange}
        value={value}
        color={color}
      />
      <StyleErrorMessage>{errorMessage}</StyleErrorMessage>
    </>
  );
}

EditUserInfoInput.defaultProps = {
  color: undefined,
};

export default EditUserInfoInput;

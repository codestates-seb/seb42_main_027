import styled from 'styled-components';
import theme from 'theme';

type ButtonProps = {
  size: 'sm' | 'md' | 'lg';
  onClick: () => void;
  disabled: boolean;
  children: string;
};

const StyledButton = styled.button<ButtonProps>`
  background-color: ${theme.colors.pointColor};
  color: white;
  font-size: ${props => {
    switch (props.size) {
      case 'sm':
        return '0.875rem';
      case 'md':
        return '1rem';
      case 'lg':
        return '1.5rem';
      default:
        return '1rem';
    }
  }};
  padding: ${props => {
    switch (props.size) {
      case 'sm':
        return '0.25rem 0.5rem';
      case 'md':
        return '0.5rem 0.75rem';
      case 'lg':
        return '0.75rem 1rem';
      default:
        return '0.5rem 0.75rem';
    }
  }};
  border-radius: 5px;
`;

function Button({ size, onClick, disabled, children }: ButtonProps) {
  return (
    <StyledButton
      type="button"
      onClick={onClick}
      disabled={disabled}
      size={size}
    >
      {children}
    </StyledButton>
  );
}

function Login() {
  return (
    <div>
      <h1>로그인</h1>
      <form>
        <label htmlFor="email">
          Email
          <input type="text" id="email" />
        </label>
        <Button
          size="lg"
          onClick={() => console.log('clicked')}
          disabled={false}
        >
          다음
        </Button>
        <Button
          size="md"
          onClick={() => console.log('clicked')}
          disabled={false}
        >
          Google 로그인
        </Button>
        <button type="button">dd</button>
      </form>
    </div>
  );
}

export default Login;

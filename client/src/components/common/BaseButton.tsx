import styled from 'styled-components';
import theme from '../../theme';

const { pointColor, gray } = theme.colors;

type ButtonProps = {
  size: 'sm' | 'md' | 'lg';
  color: 'pointColor' | 'white';
  onClick?: React.MouseEventHandler<HTMLButtonElement>;
  disabled: boolean;
  children: string;
};

const StyledButton = styled.button<ButtonProps>`
  background-color: ${({ color }) =>
    color === 'pointColor' ? pointColor : 'white'};
  border: ${({ color }) =>
    color === 'pointColor' ? `1px solid ${gray}` : `1px solid ${pointColor}`};
  color: ${({ color }) => (color === 'pointColor' ? 'white' : pointColor)};
  font-size: ${props => {
    switch (props.size) {
      case 'sm':
        return '0.874rem';
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
  border-radius: 0.5rem;
  cursor: pointer;
  &:disabled {
    cursor: default;
    opacity: 0.5;
  }
`;

function BaseButton({ size, color, onClick, disabled, children }: ButtonProps) {
  return (
    <StyledButton
      color={color}
      onClick={onClick}
      disabled={disabled}
      size={size}
      type="button"
    >
      {children}
    </StyledButton>
  );
}

BaseButton.defaultProps = {
  onClick: undefined,
};

export default BaseButton;

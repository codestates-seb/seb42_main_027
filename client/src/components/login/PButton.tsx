import styled from 'styled-components';
import theme from 'theme';

const { colors } = theme;
const StyleP = styled.p`
  color: ${colors.pointColor};
`;
type PButtonProps = {
  children: string;
};
function PButton({ children }: PButtonProps) {
  return <StyleP>{children}</StyleP>;
}

export default PButton;

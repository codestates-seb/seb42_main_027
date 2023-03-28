import styled from 'styled-components';
import theme from 'theme';
import { BsStarFill, BsFillGearFill } from 'react-icons/bs';

const Teacher = styled(BsStarFill)`
  color: ${theme.colors.yellow};
  width: 1rem;
  height: 1rem;
  margin-left: 3px;
`;

const Admin = styled(BsFillGearFill)`
  color: ${theme.colors.pointColor};
  width: 1rem;
  height: 1rem;
  margin-left: 3px;
`;

const StateIcon = {
  Teacher,
  Admin,
};

export default StateIcon;

import styled from 'styled-components';
import theme from 'theme';
import { FaUserCircle } from 'react-icons/fa';

const Default = styled(FaUserCircle)`
  color: ${theme.colors.pointColor};
  width: 2rem;
  height: 2rem;
`;

const Mini = styled(Default)`
  width: 1rem;
  height: 1rem;
`;

const ProfileIcon = {
  Default,
  Mini,
};

export default ProfileIcon;

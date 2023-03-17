import styled from 'styled-components';
import theme from 'theme';
import { FaUserCircle } from 'react-icons/fa';

const Default = styled(FaUserCircle)`
  color: ${theme.colors.pointColor};
  width: 30px;
  height: 30px;
`;

const Mini = styled(Default)`
  width: 13.33px;
  height: 13.33px;
`;

const ProfileIcon = {
  Default,
  Mini,
};

export default ProfileIcon;

import { useState } from 'react';
import styled from 'styled-components';
import theme from '../../theme';

function Toggle() {
  const [isOn, setisOn] = useState(false);

  const toggleHandler = () => {
    setisOn(!isOn);
  };

  return (
    <ToggleContainer onClick={toggleHandler}>
      <div className={`toggle-container ${isOn ? 'toggle--checked' : ''}`} />
      <div className={`toggle-circle ${isOn ? 'toggle--checked' : ''}`} />
    </ToggleContainer>
  );
}

const ToggleContainer = styled.div`
  position: relative;
  cursor: pointer;

  > .toggle-container {
    width: 50px;
    height: 24px;
    border-radius: 30px;
    background-color: ${theme.colors.pointColor};
    transition: 0.5s;
  }
  > .toggle--checked {
    background-color: ${theme.colors.gray};
    transition: 0.5s;
  }

  > .toggle-circle {
    position: absolute;
    top: 2px;
    left: 2px;
    width: 20px;
    height: 20px;
    border-radius: 50%;
    background-color: ${theme.colors.white};
    transition: 0.5s;
    &.toggle--checked {
      left: 28px;
      transition: 0.5;
    }
  }
`;

export default Toggle;

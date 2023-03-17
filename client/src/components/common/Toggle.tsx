import { useState } from 'react';
import styled from 'styled-components';
import theme from '../../theme';
import sunIcon from '../../assets/images/sun.png';
import MoonIcon from '../../assets/images/moon.png';

function Toggle() {
  const [isOn, setisOn] = useState(false);

  const toggleHandler = () => {
    setisOn(!isOn);
  };

  return (
    <ToggleContainer onClick={toggleHandler}>
      <div className={`toggle-container ${isOn ? 'toggle--checked' : ''}`} />
      <div className={`toggle-circle ${isOn ? 'toggle--checked' : ''}`} />
      <SunIconDiv>
        <img src={sunIcon} alt="light mode" />
      </SunIconDiv>

      <MoonIconDiv>
        <img src={MoonIcon} alt="dark mode" />
      </MoonIconDiv>
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
    z-index: 10;
    top: calc(100% / 12);
    left: calc(4%);
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

const SunIconDiv = styled.div`
  position: absolute;
  top: calc(100% / 6);
  left: 30px;

  > img {
    width: 16px;
    filter: invert(100%);
  }
`;

const MoonIconDiv = styled.div`
  position: absolute;
  top: calc(100% / 5);
  left: 5px;
  > img {
    width: 14px;
    filter: invert(100%);
  }
`;

export default Toggle;

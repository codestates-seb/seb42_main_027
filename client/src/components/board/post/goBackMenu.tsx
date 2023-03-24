import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';
import theme from 'theme';
import { AiOutlineArrowLeft } from 'react-icons/ai';

function GoBackMenu() {
  const navigate = useNavigate();

  return (
    <Container>
      <AiOutlineArrowLeft className="arrow-left" onClick={() => navigate(-1)} />
    </Container>
  );
}

const Container = styled.div`
  display: flex;
  align-items: center;
  width: 100%;
  height: 60px;
  padding: ${theme.gap.px20};
  border-bottom: 1px solid ${theme.colors.gray};

  > .arrow-left {
    width: 17.33px;
    height: 17.33px;

    &:hover {
      filter: ${theme.filterColors.pointColor};
      cursor: pointer;
    }
  }
`;

export default GoBackMenu;

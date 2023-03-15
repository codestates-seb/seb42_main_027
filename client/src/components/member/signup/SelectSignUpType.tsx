import styled from 'styled-components';
import { BiUser } from 'react-icons/bi';
import { FaChalkboardTeacher } from 'react-icons/fa';
import theme from 'theme';
import { Link, useNavigate } from 'react-router-dom';

const { colors } = theme;

const TypeButton = styled.button`
  display: flex;
  justify-content: flex-start;
  align-items: center;
  padding: 0.8rem;
  :hover {
    opacity: 0.8;
    cursor: pointer;
  }
`;

const IconBiUser = styled(BiUser)`
  color: ${colors.pointColor};
  width: 2.5rem;
  height: 2.5rem;
  margin-right: 1rem;
`;

const IconFaChalkboardTeacher = styled(FaChalkboardTeacher)`
  color: ${colors.pointColor};
  width: 2.5rem;
  height: 2.5rem;
  margin-right: 1rem;
`;

const ButtonGroup = styled.div`
  display: flex;
  flex-direction: column;
  margin-bottom: 1rem;
  width: 100%;
  & button:first-child {
    border-bottom: 0.1rem solid ${colors.gray};
  }
`;

const ButtonInfoContainer = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: flex-start;
`;

const ButtonInfoTitle = styled.span`
  color: ${colors.pointColor};
  margin-bottom: 0.2rem;
`;

const ButtonInfoSubTitle = styled.span`
  color: ${colors.gray};
  font-size: 0.8rem;
`;

function SelectSignUpType() {
  const navigate = useNavigate();

  const handleStudentBtnClick = () => {
    navigate('student');
  };

  const handleTeacherBtnClick = () => {
    navigate('teacher');
  };

  return (
    <ButtonGroup>
      <TypeButton onClick={handleStudentBtnClick}>
        <IconBiUser />
        <ButtonInfoContainer>
          <ButtonInfoTitle>학생</ButtonInfoTitle>
          <ButtonInfoSubTitle>학생 회원</ButtonInfoSubTitle>
        </ButtonInfoContainer>
      </TypeButton>

      <TypeButton onClick={handleTeacherBtnClick}>
        <IconFaChalkboardTeacher />
        <ButtonInfoContainer>
          <ButtonInfoTitle>교/강사 회원</ButtonInfoTitle>
          <ButtonInfoSubTitle>학교/학원 등 선생님 회원</ButtonInfoSubTitle>
        </ButtonInfoContainer>
      </TypeButton>
    </ButtonGroup>
  );
}

export default SelectSignUpType;

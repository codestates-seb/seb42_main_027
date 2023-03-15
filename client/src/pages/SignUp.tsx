import styled from 'styled-components';
import { AiOutlineUser } from 'react-icons/ai';
import { FaChalkboardTeacher, FaUserAlt } from 'react-icons/fa';
import theme from 'theme';

const { colors } = theme;

const Container = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 25%;
  margin: 0 auto;
`;

const Title = styled.h1`
  color: ${colors.pointColor};
  font-size: 2rem;
  margin-bottom: 1rem;
`;

const SubTitle = styled.p`
  color: ${colors.pointColor};
  font-size: 1rem;
  margin-bottom: 0.5rem;
`;

const ButtonGroup = styled.div`
  display: flex;
  margin-bottom: 1rem;
`;

const TypeButton = styled.button`
  display: flex;
`;

const ButtonInfo = styled.div``;

function SignUp() {
  return (
    <div>
      <Container>
        <Title>시작하기</Title>
        <SubTitle>지금 가입하고 국내 모든 인강 정보와 후기를 한눈에</SubTitle>
        <SubTitle>확인하세요.</SubTitle>
        <ButtonGroup>
          <TypeButton>
            <FaUserAlt />
            <span>학생</span>
            <span>학생 회원</span>
          </TypeButton>
          <FaChalkboardTeacher />
        </ButtonGroup>
      </Container>
    </div>
  );
}

export default SignUp;

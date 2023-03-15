import styled from 'styled-components';
import { BiUser } from 'react-icons/bi';
import { FaChalkboardTeacher, FaUserAlt } from 'react-icons/fa';
import theme from 'theme';

const { colors } = theme;

const Container = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 30%;
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
  flex-direction: column;
  margin-bottom: 1rem;
  width: 100%;
  & button:first-child {
    border-bottom: 0.1rem solid ${colors.gray};
  }
`;

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

function SignUp() {
  return (
    <div>
      <Container>
        <Title>시작하기</Title>
        <SubTitle>지금 가입하고 국내 모든 인강 정보와 후기를 한눈에</SubTitle>
        <SubTitle>확인하세요.</SubTitle>
        <ButtonGroup>
          {/* 타입버튼 */}
          <TypeButton>
            <IconBiUser />
            <ButtonInfoContainer>
              <ButtonInfoTitle>학생</ButtonInfoTitle>
              <ButtonInfoSubTitle>학생 회원</ButtonInfoSubTitle>
            </ButtonInfoContainer>
          </TypeButton>
          <TypeButton>
            <IconFaChalkboardTeacher />
            <ButtonInfoContainer>
              <ButtonInfoTitle>교/강사 회원</ButtonInfoTitle>
              <ButtonInfoSubTitle>학교/학원 등 선생님 회원</ButtonInfoSubTitle>
            </ButtonInfoContainer>
          </TypeButton>
        </ButtonGroup>
      </Container>
    </div>
  );
}

export default SignUp;

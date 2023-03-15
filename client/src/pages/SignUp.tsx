import styled from 'styled-components';
import theme from 'theme';
import { Container, Title } from 'components/member/memberStyledComponents';
import { Routes, Route } from 'react-router-dom';
import SelectSignUpType from 'components/member/signup/SelectSignUpType';
import StudentSignUpForm from 'components/member/signup/StudentSignUpForm';
import TeacherSignUpForm from 'components/member/signup/TeacherSignUpForm';

const { colors } = theme;

const SubTitle = styled.p`
  color: ${colors.pointColor};
  font-size: 1rem;
  margin-bottom: 0.5rem;
`;

function SignUp() {
  return (
    <div>
      <Container>
        <Title>시작하기</Title>
        <SubTitle>지금 가입하고 국내 모든 인강 정보와 후기를 한눈에</SubTitle>
        <SubTitle>확인하세요.</SubTitle>
        <Routes>
          <Route path="/" element={<SelectSignUpType />} />
          <Route path="student" element={<StudentSignUpForm />} />
          <Route path="teacher" element={<TeacherSignUpForm />} />
        </Routes>
      </Container>
    </div>
  );
}

export default SignUp;

import styled from 'styled-components';
import { AiOutlineUser } from 'react-icons/ai';
import theme from 'theme';

const { colors } = theme;

function SignUp() {
  return (
    <div>
      <h1>
        <AiOutlineUser />
        회원가입
      </h1>
    </div>
  );
}

export default SignUp;

import BaseButton from '../components/UI/BaseButton';

function Login() {
  return (
    <div>
      <h1>로그인</h1>
      <form>
        <label htmlFor="email">
          Email
          <input type="text" id="email" />
        </label>
        <BaseButton
          color="pointColor"
          size="md"
          onClick={() => console.log('clicked')}
          disabled={false}
        >
          다음
        </BaseButton>
        <BaseButton
          color="white"
          size="md"
          onClick={() => console.log('clicked')}
          disabled={false}
        >
          Google 로그인
        </BaseButton>
      </form>
    </div>
  );
}

export default Login;

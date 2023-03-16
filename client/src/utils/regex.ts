const phoneNumRegex = /^\d{10,11}$/;

export const validatePhoneNum = (phoneNum: string) => {
  return phoneNumRegex.test(phoneNum);
};

const emailRegex = /^(.+[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6})*$/;
// 이메일 주소는 @ 기호를 포함해야 한다.
// 이메일 주소는 영문자, 숫자, 밑줄, 하이픈, 마침표를 사용할 수 있다.
// 이메일 주소는 @ 기호를 기준으로 앞쪽에는 영문자, 숫자, 밑줄, 하이픈, 마침표가 사용될 수 있고, 뒷쪽에는 영문자와 마침표가 사용될 수 있으며, 마침표로 구분된 문자열 중 마지막 부분은 반드시 영문자로 끝나야 한다.
export const validateEmail = (email: string): boolean => {
  return emailRegex.test(email);
};

const passwordRegex =
  /^(?=.*[A-Za-z])(?=.*\d)(?=.*[~!@#$%^&*()_+])[A-Za-z\d~!@#$%^&*()_+]{8,16}$/;
// 이메일 주소는 @ 기호를 포함해야 한다.
// 이메일 주소는 영문자, 숫자, 밑줄, 하이픈, 마침표를 사용할 수 있다.
// 이메일 주소는 @ 기호를 기준으로 앞쪽에는 영문자, 숫자, 밑줄, 하이픈, 마침표가 사용될 수 있고, 뒷쪽에는 영문자와 마침표가 사용될 수 있으며, 마침표로 구분된 문자열 중 마지막 부분은 반드시 영문자로 끝나야 한다.
export const validatePassword = (email: string): boolean => {
  return passwordRegex.test(email);
};

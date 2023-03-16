const phoneNumRegex = /^\d{10,11}$/;

export const validatePhoneNum = (phoneNum: string) => {
  return phoneNumRegex.test(phoneNum);
};

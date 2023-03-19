import styled from 'styled-components';
import theme from '../theme';

const { colors } = theme;
const { fontSizes } = theme;
const { radius } = theme;

const Container = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  width: 30%;
  margin: 0 auto;
`;

const ProfileImage = styled.img`
  width: 14rem;
  height: 14rem;
  object-fit: cover;
  border-radius: 50%;
  margin-top: 2rem;
  margin-right: 5rem;
`;

const UserInfo = styled.div`
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  margin-top: 1rem;
`;

const Name = styled.h2`
  font-size: 2rem;
  margin: 1rem 0;
  margin-right: 1rem;
`;

const Email = styled.p`
  font-size: ${fontSizes.md};
  margin: 0.3rem 0;
`;

const NickName = styled.p`
  font-size: ${fontSizes.md};
  margin: 0.3rem 0;
`;

const StudentTag = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 0.2rem 0.5rem;
  color: ${colors.pointColor};
  font-size: ${fontSizes.md};
  border: solid 0.2rem ${colors.pointColor};
  border-radius: 0.8rem;
`;

const TeacherTag = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 0.2rem 0rem;
  color: ${colors.pointColor};
  font-size: ${fontSizes.md};
  border: solid 0.2rem ${colors.pointColor};
  border-radius: 0.8rem;
`;

const NameTagContainer = styled.div`
  display: flex;
  align-items: center;
`;

const EditBtn = styled.span`
  width: 100%;
  text-align: end;
  color: ${colors.gray};
`;

function MyPage() {
  // dummy data
  const userData = {
    profileImage: 'https://i.pravatar.cc/150?img=7',
    name: '이채욱',
    email: 'johndoe@example.com',
    nickname: 'codnr',
  };

  return (
    <Container>
      <ProfileImage src={userData.profileImage} />
      <UserInfo>
        <NameTagContainer>
          <Name>{userData.name}</Name>
          <StudentTag>학생</StudentTag>
        </NameTagContainer>
        <Email>{userData.email}</Email>
        <NickName>{userData.nickname}</NickName>
        <EditBtn>수정</EditBtn>
      </UserInfo>
    </Container>
  );
}

export default MyPage;

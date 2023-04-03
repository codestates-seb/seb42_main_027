/* eslint-disable react/no-unused-prop-types */
/* eslint-disable react/no-array-index-key */
import GlobalStyle from 'GlobalStyles';
import styled from 'styled-components';
import { FlexContainer } from 'pages/review/TeacherList/ReviewPage';
import useUserInfoStore from 'stores/userInfoStore';
import { Link } from 'react-router-dom';
import Button from 'components/common/Button';

type Props = {
  lecture: {
    lectureId: number;
    starPointAverage: number;
    title: string;
  };
  teacher: {
    name: string;
    starPointAverage: number;
    teacherId: number;
  };
};

function LectureIntro({ lecture, teacher }: Props) {
  const { userInfo } = useUserInfoStore(state => state);
  const Authorization = localStorage.getItem('token');
  return (
    <FlexContainer
      width="100%"
      gap="1rem"
      padding="1rem 1rem"
      borderRadius="1rem"
      justify="space-between"
    >
      <BigFont>
        <MidGrayFont>{teacher.name}</MidGrayFont>
        {lecture.title}
      </BigFont>
      <MidGrayFont />
      <FlexContainer display={Authorization ? 'flex' : 'none'}>
        <Link to="create">
          <PButton>리뷰 등록</PButton>
        </Link>
      </FlexContainer>
    </FlexContainer>
  );
}

export default LectureIntro;

const PButton = Button.PointBtn;

const BigFont = styled.div`
  font-size: large;
  font-weight: bold;
  color: black;
`;

const MidGrayFont = styled.div`
  font-weight: 500;
  color: gray;
  margin-bottom: 0.5rem;
`;

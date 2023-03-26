/* eslint-disable react/no-unused-prop-types */
/* eslint-disable react/no-array-index-key */
import GlobalStyle from 'GlobalStyles';
import styled from 'styled-components';
import { FlexContainer } from 'pages/review/TeacherList/ReviewPage';

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
  return (
    <FlexContainer dir="col" width="90%" gap="1rem">
      <BigFont>{teacher.name}</BigFont>
      <BigFont>{lecture.title}</BigFont>
    </FlexContainer>
  );
}

export default LectureIntro;

const BigFont = styled.div`
  font-size: large;
  font-weight: bold;
`;

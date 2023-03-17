import GlobalStyle from 'GlobalStyles';
import { useEffect, useState } from 'react';
import axios from 'axios';
import { useParams } from 'react-router';
import styled from 'styled-components';
import { FlexContainer } from '../ReviewPage';
import { Container } from './Information';

const defaultData = {
  gradeTags: ['1'],
  imageUrl: 'string;',
  introduction: 'string;',
  name: 'string;',
  platformTags: [{ platformTag: 's' }],
  starPointAverage: 5,
  subjectTags: [{ subjectTag: 'string' }],
  teacherId: 5,
  totalReviewCount: 5,
  lectures: 'string',
  analects: ['1'],
  profile: ['1'],
};
function TeacherReview() {
  const [data, setData] = useState(defaultData);
  const [isPending, setIsPending] = useState<boolean>(true);
  const { teacherId } = useParams();
  useEffect(() => {
    axios
      .get(`http://13.125.1.215:8080/teachers/${teacherId}`)
      .then((res: any) => {
        return res.data.data;
      })
      .then(data => {
        console.log(data);
        setData(data);
        setIsPending(false);
      });
  }, []);

  return (
    <Container>
      {isPending ? null : (
        <div>
          <GlobalStyle />
          수강 후기 페이지 입니다
        </div>
      )}
    </Container>
  );
}

export default TeacherReview;

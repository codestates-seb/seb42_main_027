/* eslint-disable react/no-array-index-key */
import GlobalStyle from 'GlobalStyles';
import { useEffect, useState } from 'react';
import axios from 'axios';
import { useParams } from 'react-router';
import styled from 'styled-components';
import { FlexContainer } from '../ReviewPage';

type Data = {
  gradeTags: string[];
  imageUrl: string;
  introduction: string;
  name: string; // 강사명
  platformTags: { platformTag: string }[];
  starPointAverage: number;
  subjectTags: { subjectTag: string }[];
  teacherId: number;
  totalReviewCount: number;
  lectures: string[];
  analects: string[];
  profile: string[];
};

const defaultData = {
  gradeTags: ['string'],
  imageUrl: 'string;',
  introduction: 'string;',
  name: 'string;',
  platformTags: [{ platformTag: 'string' }],
  starPointAverage: 5,
  subjectTags: [{ subjectTag: 'string' }],
  teacherId: 5,
  totalReviewCount: 5,
  lectures: [],
  analects: ['string'],
  profile: ['string'],
};

function Information() {
  const [data, setData] = useState(defaultData);
  const [isPending, setIsPending] = useState<boolean>(true);
  const { teacherId } = useParams();
  useEffect(() => {
    axios
      .get(`${process.env.REACT_APP_API_URL}/teachers/${teacherId}`, {
        headers: { 'ngrok-skip-browser-warning': '69420' },
      })
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
      {isPending ? (
        <FlexContainer height="100vh" />
      ) : (
        <div>
          <GlobalStyle />
          <FlexContainer width="100vw">
            <FlexContainer width="100%" height="100%" dir="col" grow={1}>
              <img src="http://placehold.it/300X350" alt="dummyImage" />
              <span>프로필 사진</span>

              <img src="http://placehold.it/300X350" alt="dummyImage" />
              <span>실제 사진</span>
            </FlexContainer>

            <FlexContainer
              width="100%"
              height="100%"
              dir="col"
              grow={1}
              align="start"
            >
              <TitleBox>
                {data.subjectTags.map((el, index) => {
                  return (
                    <SmallFont key={index}>{`${el.subjectTag} 영역`}</SmallFont>
                  );
                })}
                <BigFont>{data.name} 선생님</BigFont>
              </TitleBox>

              <ContentBox>
                <MiddleFont>프로필</MiddleFont>
                <FlexContainer dir="col" gap="0.5rem" align="start">
                  {data.profile.map((el, index) => {
                    return <SmallFont key={index}>{`· ${el}`}</SmallFont>;
                  })}
                </FlexContainer>
              </ContentBox>

              <ContentBox>
                <MiddleFont>강의 이력</MiddleFont>
                <FlexContainer dir="col" gap="0.5rem" align="start">
                  {data.platformTags.map((el, index) => {
                    return (
                      <SmallFont key={index}>{`· ${el.platformTag}`}</SmallFont>
                    );
                  })}
                </FlexContainer>
              </ContentBox>

              <ContentBox>
                <MiddleFont>강사 소개</MiddleFont>
                <FlexContainer dir="col" gap="0.5rem" align="start">
                  {data.introduction}
                </FlexContainer>
              </ContentBox>

              <ContentBox>
                <MiddleFont>어록</MiddleFont>
                <FlexContainer dir="col" gap="0.5rem" align="start">
                  {data.analects.map((el, index) => {
                    return <SmallFont key={index}>{`· ${el}`}</SmallFont>;
                  })}
                </FlexContainer>
              </ContentBox>
            </FlexContainer>
          </FlexContainer>
        </div>
      )}
    </Container>
  );
}

export default Information;

export const Container = styled.div`
  padding: 3rem;
`;

const TitleBox = styled.div`
  border-left: 0.3rem solid #6667ab;
  padding: 0.7rem 2rem;
  display: flex;
  flex-direction: column;
  gap: 1rem;
`;

const ContentBox = styled.div`
  padding: 1.5rem;
  display: flex;
  flex-direction: column;
  gap: 1rem;
`;

export const BigFont = styled.div`
  font-size: 2rem;
  font-weight: bold;
`;

export const MiddleFont = styled.div`
  font-size: 1.5rem;
  font-weight: bold;
`;
export const SmallFont = styled.div`
  font-size: 1.1rem;
`;

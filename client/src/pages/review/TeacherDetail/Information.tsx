/* eslint-disable react/no-array-index-key */
import GlobalStyle from 'GlobalStyles';
import { useEffect, useState } from 'react';
import axios from 'axios';
import { useParams } from 'react-router';
import styled from 'styled-components';
import { FlexContainer } from '../TeacherList/ReviewPage';

type Data = {
  gradeTags: string[];
  profileImageUrl: string;
  realImageUrl: string;
  introduction: string;
  name: string;
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
  profileImageUrl: 'string;',
  realImageUrl: 'string;',
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
  const [data, setData] = useState<Data>(defaultData);
  const [isPending, setIsPending] = useState<boolean>(true);
  const { teacherId } = useParams();
  useEffect(() => {
    window.scrollTo(0, 0);
    axios
      .get(`${process.env.REACT_APP_API_URL}/boards/teachers/${teacherId}`, {
        headers: { 'ngrok-skip-browser-warning': '69420' },
      })
      .then((res: any) => {
        return res.data.data;
      })
      .then(data => {
        setData(data);
        setIsPending(false);
      });
  }, []);

  return (
    <Container>
      {isPending ? (
        <FlexContainer height="100vh" />
      ) : (
        <FlexContainer width="100%">
          <GlobalStyle />
          <FlexContainer
            width="100%"
            height="100%"
            dir="col"
            gap="3rem"
            grow={1}
          >
            <FlexContainer dir="col">
              <Img
                src={
                  data.profileImageUrl ||
                  'https://web.yonsei.ac.kr/_ezaid/board/_skin/albumRecent/1/no_image.gif'
                }
                alt="profileImage"
              />
              <span>프로필 사진</span>
            </FlexContainer>

            <FlexContainer dir="col">
              <Img
                src={
                  data.realImageUrl ||
                  'https://web.yonsei.ac.kr/_ezaid/board/_skin/albumRecent/1/no_image.gif'
                }
                alt="realImage"
              />
              <span>실제 사진</span>
            </FlexContainer>
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
      )}
    </Container>
  );
}

export default Information;

export const Container = styled.div`
  width: 98vw;
  padding: 3rem 0;
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

const Img = styled.img`
  width: 340px;
  height: 350px;
  border-radius: 0.5rem;
  background-color: #b8b8b8;
`;

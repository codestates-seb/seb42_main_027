/* eslint-disable react/no-array-index-key */
/* eslint-disable jsx-a11y/label-has-associated-control */
import GlobalStyle from 'GlobalStyles';
import { useState, useEffect } from 'react';
import { useNavigate } from 'react-router';
import styled from 'styled-components';
import axios from 'axios';
import { FlexContainer } from './ReviewPage';

function CreateTeacher() {
  const [name, setName] = useState<string>('');
  const [platformTag, setPlatformTag] = useState<string[]>([]);
  const [subjectTag, setSubjectTag] = useState<string[]>([]);
  const [gradeTag, setGradeTag] = useState<string[]>([]);
  const [introduction, setIntroduction] = useState<string>('');
  const [profile, setProfile] = useState<string>('');
  const [analects, setAnalects] = useState<string>('');
  const [imageUrl, setImageUrl] = useState<string>('none');

  const navigate = useNavigate();

  const subjectArr: string[][] = [
    ['국어', '영어', '수학', '한국사'],
    ['지리', '일반사회', '윤리', '역사', '통합사회'],
    ['물리학', '지구과학', '화학', '생명과학', '통합과학'],
    ['제2외국어', '대학별고사'],
  ];

  const gradeArr: string[][] = [
    ['예비중1', '중1', '예비중2', '중2', '예비중3', '중3'],
    ['예비고1', '고1', '예비고2', '고2', '예비고3', '고3'],
  ];

  const platformArr: string[][] = [
    ['이투스', '메가스터디', 'EBS', '에듀윌', '기타'],
  ];

  const subjectClickHandler = (e: any) => {
    if (subjectTag.indexOf(e.target.value) > -1) {
      setSubjectTag(subjectTag.filter(el => el !== e.target.value));
    } else setSubjectTag([...subjectTag, e.target.value]);
  };

  const gradeClickHandler = (e: any) => {
    if (gradeTag.indexOf(e.target.value) > -1) {
      setGradeTag(gradeTag.filter(el => el !== e.target.value));
    } else setGradeTag([...gradeTag, e.target.value]);
  };

  const platformClickHandler = (e: any) => {
    if (platformTag.indexOf(e.target.value) > -1) {
      setPlatformTag(platformTag.filter(el => el !== e.target.value));
    } else setPlatformTag([...platformTag, e.target.value]);
  };

  useEffect(() => {
    console.log('Rerendering!');
  }, []);

  const createHandler = () => {
    if (
      !name ||
      !subjectTag.length ||
      !gradeTag.length ||
      !platformTag.length ||
      !introduction ||
      !profile.length ||
      !analects.length ||
      !imageUrl
    ) {
      alert('빈 곳을 채워주세요!');
    } else {
      const data = {
        name,
        subjectTag,
        gradeTag,
        platformTag,
        introduction,
        profile: profile.split('\n'),
        analects: analects.split('\n'),
        imageUrl,
      };

      axios.post(`http://13.125.1.215:8080/teachers`, data).then(res => {
        navigate(-1);
      });
    }
  };

  return (
    <UpdateContainer>
      <GlobalStyle />
      <FlexContainer dir="col">
        <CardContainer>
          <Img src="http://placehold.it/200X200" alt="dummyImage" />
          <ColumDiv>
            <label htmlFor="name">강사명</label>
            <Input
              id="name"
              value={name}
              onChange={e => setName(e.target.value)}
            />
          </ColumDiv>

          <ColumDiv>
            <label htmlFor="subject">과목</label>
            {subjectArr.map((row, index) => {
              return (
                <FlexContainer
                  wrap="wrap"
                  gap="0.3rem"
                  justify="start"
                  key={index}
                >
                  {row.map((el, index) => {
                    return (
                      <FlexContainer key={index} gap="0.1rem">
                        <input
                          id={el}
                          value={el}
                          type="checkbox"
                          onClick={subjectClickHandler}
                        />
                        <label htmlFor={el}>{el}</label>
                      </FlexContainer>
                    );
                  })}
                </FlexContainer>
              );
            })}
          </ColumDiv>

          <ColumDiv>
            <label htmlFor="subject">학년</label>
            {gradeArr.map((row, index) => {
              return (
                <FlexContainer
                  wrap="wrap"
                  gap="0.3rem"
                  justify="start"
                  key={index}
                >
                  {row.map((el, index) => {
                    return (
                      <FlexContainer key={index} gap="0.1rem">
                        <input
                          id={el}
                          value={el}
                          type="checkbox"
                          onClick={gradeClickHandler}
                        />
                        <label htmlFor={el}>{el}</label>
                      </FlexContainer>
                    );
                  })}
                </FlexContainer>
              );
            })}
          </ColumDiv>

          <ColumDiv>
            <label htmlFor="subject">플랫폼</label>
            {platformArr.map((row, index) => {
              return (
                <FlexContainer
                  wrap="wrap"
                  gap="0.3rem"
                  justify="start"
                  key={index}
                >
                  {row.map((el, index) => {
                    return (
                      <FlexContainer key={index} gap="0.1rem">
                        <input
                          id={el}
                          value={el}
                          readOnly
                          type="checkbox"
                          onClick={platformClickHandler}
                        />
                        <label htmlFor={el}>{el}</label>
                      </FlexContainer>
                    );
                  })}
                </FlexContainer>
              );
            })}
          </ColumDiv>

          <ColumDiv>
            <label htmlFor="profile">프로필</label>
            <Textarea
              id="profile"
              value={profile}
              onChange={e => {
                setProfile(e.target.value);
              }}
            />
          </ColumDiv>
          <ColumDiv>
            <label htmlFor="analects">어록</label>
            <Textarea
              id="analects"
              value={analects}
              onChange={e => {
                setAnalects(e.target.value);
              }}
            />
          </ColumDiv>
          <ColumDiv>
            <label htmlFor="expectingContent">강사 소개글</label>
            <Textarea
              id="expectingContent"
              value={introduction}
              onChange={e => {
                setIntroduction(e.target.value);
              }}
            />
          </ColumDiv>
          <Span>⭐️ 신입</Span>
        </CardContainer>
      </FlexContainer>
      <FlexContainer>
        <UploadButton onClick={createHandler}>강사 등록</UploadButton>
        <UploadButton
          onClick={() => {
            navigate(-1);
          }}
        >
          등록 취소
        </UploadButton>
      </FlexContainer>
    </UpdateContainer>
  );
}

export default CreateTeacher;

export const UpdateContainer = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding: 1rem;
  gap: 2rem;
`;

export const ColumDiv = styled.div`
  width: 80%;
  display: flex;
  flex-direction: column;
  align-items: left;
  gap: 0.5rem;
`;

export const Input = styled.input`
  width: 100%;
  padding: 0.5rem;
  margin-bottom: 1rem;
  border: 1px solid black;
`;
export const Textarea = styled.textarea`
  width: 100%;
  padding: 0.5rem;
  height: 7rem;
  margin-bottom: 1rem;
`;

export const UploadButton = styled.button`
  width: fit-content;
  height: 2.5rem;
  padding: 0 0.6rem;
  background-color: #1295ff;
  color: white;
  border: none;
  cursor: pointer;
  :hover {
    background-color: #0088ff;
  }
`;

export const CardContainer = styled.div`
  width: 100%;
  padding: 2rem 0.5rem;
  background-color: white;
  border: 0.3rem solid #6667ab;
  border-radius: 1.2rem;

  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  gap: 1rem;
`;

export const Img = styled.img`
  width: 25rem;
  height: 15rem;
  border-radius: 0.5rem;
  background-color: #b8b8b8;
`;

export const Span = styled.span`
  font-weight: bold;
`;

export const LargeSpan = styled.div`
  width: 100%;
  margin: 1rem 0;
  font-size: large;
  font-weight: bold;
`;

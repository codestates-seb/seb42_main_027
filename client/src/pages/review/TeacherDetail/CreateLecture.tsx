/* eslint-disable react/no-array-index-key */
/* eslint-disable jsx-a11y/label-has-associated-control */
import GlobalStyle from 'GlobalStyles';
import { useState, useEffect } from 'react';
import axios from 'axios';
import { useParams, useNavigate } from 'react-router';
import styled from 'styled-components';
import { FlexContainer } from '../ReviewPage';

import {
  UpdateContainer,
  ColumDiv,
  Input,
  Textarea,
  CardContainer,
  UploadButton,
} from '../CreateTeacher';

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

function CreateLecture() {
  const { teacherId } = useParams();

  const [title, setTitle] = useState<string>('');
  const [status, setStatus] = useState<string>('');
  const [platformTag, setPlatformTag] = useState<string[]>([]);
  const [subjectTag, setSubjectTag] = useState<string[]>([]);
  const [gradeTag, setGradeTag] = useState<string[]>([]);
  const [introduction, setIntroduction] = useState<string>('');

  const navigate = useNavigate();

  useEffect(() => {
    console.log(subjectTag);
  }, [subjectTag]);

  const subjectArr: string[] = [
    '국어',
    '영어',
    '수학',
    '한국사',
    '경제/정.법',
    '지리',
    '윤리',
    '역사',
    '일반사회',
    '물리학',
    '화학',
    '생명과학',
    '지구과학',
    '일반과학',
    '제2외국어',
    '대학별고사',
    '그외',
  ];

  const gradeArr: string[] = ['중1', '중2', '중3', '고1', '고2', '고3'];

  const platformArr: string[] = [
    '이투스',
    '메가스터디',
    'EBS',
    '에듀윌',
    '기타',
  ];

  const statusArr: string[] = ['예정', '진행중', '완강'];

  const subjectClickHandler = (e: any) => {
    if (!e.target.value) {
      setSubjectTag([]);
    } else setSubjectTag([e.target.value]);
  };

  const gradeClickHandler = (e: any) => {
    if (!e.target.value) {
      setGradeTag([]);
    } else setGradeTag([e.target.value]);
  };

  const platformClickHandler = (e: any) => {
    if (!e.target.value) {
      setPlatformTag([]);
    } else setPlatformTag([e.target.value]);
  };

  const createHandler = () => {
    if (
      !title ||
      !subjectTag.length ||
      !gradeTag.length ||
      !platformTag.length ||
      !introduction ||
      !status
    ) {
      alert('빈 곳을 채워주세요!');
    } else {
      const data = {
        status,
        title,
        introduction,
        gradeTag,
        subjectTag,
        platformTag,
        teacherId: Number(teacherId),
      };

      axios
        .post(`${process.env.REACT_APP_API_URL}/lectures`, data, {
          headers: {
            'ngrok-skip-browser-warning': '69420',
          },
        })
        .then(res => {
          navigate(-1);
        });
    }
  };

  return (
    <UpdateContainer>
      <GlobalStyle />
      <FlexContainer dir="col" width="30vw">
        <CardContainer>
          <ColumDiv>
            <label htmlFor="name">강의 제목</label>
            <Input
              id="name"
              value={title}
              onChange={e => setTitle(e.target.value)}
            />
          </ColumDiv>

          <ColumDiv>
            <label htmlFor="subject">강의 상태</label>
            <FlexContainer justify="start" gap="0.5rem">
              {statusArr.map((el, index) => {
                return (
                  <FlexContainer key={index} gap="0.1rem">
                    <input
                      name="status"
                      id={el}
                      value={el}
                      type="radio"
                      onClick={(e: any) => {
                        setStatus(e.target.value);
                      }}
                    />
                    <label htmlFor={el}>{el}</label>
                  </FlexContainer>
                );
              })}
            </FlexContainer>
          </ColumDiv>

          <ColumDiv>
            <label htmlFor="subject">과목</label>
            <select onChange={subjectClickHandler}>
              <option value="">과목 선택</option>
              {subjectArr.map((el, index) => {
                return (
                  <option key={index} id={el} value={el}>
                    {el}
                  </option>
                );
              })}
            </select>
          </ColumDiv>

          <ColumDiv>
            <label htmlFor="grade">학년</label>
            <select onChange={gradeClickHandler}>
              <option value="">학년 선택</option>
              {gradeArr.map((el, index) => {
                return (
                  <option key={index} id={el} value={el}>
                    {el}
                  </option>
                );
              })}
            </select>
          </ColumDiv>

          <ColumDiv>
            <label htmlFor="platform">플랫폼</label>
            <select onChange={platformClickHandler}>
              <option value="">플랫폼 선택</option>
              {platformArr.map((el, index) => {
                return (
                  <option key={index} id={el} value={el}>
                    {el}
                  </option>
                );
              })}
            </select>
          </ColumDiv>

          <ColumDiv>
            <label htmlFor="introduction">강의 소개</label>
            <Textarea
              id="introduction"
              value={introduction}
              onChange={e => {
                setIntroduction(e.target.value);
              }}
            />
          </ColumDiv>
        </CardContainer>
      </FlexContainer>
      <FlexContainer>
        <UploadButton onClick={createHandler}>강의 등록</UploadButton>
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

export default CreateLecture;

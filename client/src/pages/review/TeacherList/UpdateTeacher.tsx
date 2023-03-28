/* eslint-disable react/no-array-index-key */
/* eslint-disable jsx-a11y/label-has-associated-control */
import GlobalStyle from 'GlobalStyles';
import { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router';
import styled from 'styled-components';
import axios from 'axios';
import { FlexContainer } from './ReviewPage';
import {
  UpdateContainer,
  ColumDiv,
  Input,
  Textarea,
  CardContainer,
  UploadButton,
  Img,
  Span,
} from './CreateTeacher';

function UpdateTeacher() {
  const [name, setName] = useState<string>('');
  const [platformTag, setPlatformTag] = useState<string[]>([]);
  const [subjectTag, setSubjectTag] = useState<string[]>([]);
  const [gradeTag, setGradeTag] = useState<string[]>([]);
  const [introduction, setIntroduction] = useState<string>('');
  const [profile, setProfile] = useState<string>('');
  const [analects, setAnalects] = useState<string>('');
  const [starPointAverage, setStarPointAverage] = useState<number>(4.2);
  const [profileImage, setProfileImage] = useState<string>();
  const [profilePreview, setProfilePreview] = useState(
    'http://placehold.it/340X240',
  );
  const [realImage, setRealImage] = useState<string>();
  const [realPreview, setRealPreview] = useState('http://placehold.it/340X240');

  const navigate = useNavigate();
  const { teacherId } = useParams();

  const subjectArr: string[][] = [
    ['국어', '영어', '수학', '한국사'],
    ['경제/정.법', '지리', '윤리', '역사', '일반사회'],
    ['물리학', '화학', '생명과학', '지구과학', '일반과학'],
    ['제2외국어', '대학별고사', '그외'],
  ];

  const gradeArr: string[][] = [
    ['중1', '중2', '중3'],
    ['고1', '고2', '고3'],
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
    axios
      .get(`${process.env.REACT_APP_API_URL}/boards/teachers/${teacherId}`, {
        headers: {
          'ngrok-skip-browser-warning': 'asdasdas',
        },
      })
      .then((res: any) => {
        console.log(res.data.data);
        return res.data.data;
      })
      .then(data => {
        setName(data.name);
        setIntroduction(data.introduction);
        setStarPointAverage(data.starPointAverage);
        setProfileImage(data.profileImageUrl);
        setRealImage(data.realImageUrl);
        setProfile(data.profile.join('\n'));
        setAnalects(data.analects.join('\n'));
        setGradeTag(
          data.gradeTags.map((el: any) => {
            return el.gradeTag;
          }),
        );
        setSubjectTag(
          data.subjectTags.map((el: any) => {
            return el.subjectTag;
          }),
        );
        setPlatformTag(
          data.platformTags.map((el: any) => {
            return el.platformTag;
          }),
        );
      });
  }, []);

  const updateHandler = () => {
    if (
      !name ||
      !subjectTag.length ||
      !gradeTag.length ||
      !platformTag.length ||
      !introduction ||
      !profile.length ||
      !analects.length
    ) {
      alert('빈 곳을 채워주세요!');
    } else {
      const data = {
        name,
        introduction,
        profile: profile.split('\n'),
        analects: analects.split('\n'),
        profileImageUrl: profileImage,
        realImageUrl: realImage,
        subjectTag,
        gradeTag,
        platformTag,
      };

      axios
        .patch(
          `${process.env.REACT_APP_API_URL}/boards/teachers/${teacherId}`,
          data,
          {
            headers: {
              'ngrok-skip-browser-warning': 'asdasdas',
            },
          },
        )
        .then(res => {
          console.log(res);
          navigate(-1);
        });
    }
  };

  return (
    <UpdateContainer>
      <GlobalStyle />
      <FlexContainer dir="col">
        <CardContainer>
          {/* 프로필 사진 */}
          <FlexContainer dir="col" align="start">
            <label htmlFor="profile">프로필 사진</label>
            <input
              id="profile"
              type="file"
              accept="image/*"
              onChange={(e: any) => {
                if (e.target.files.length) {
                  console.log(e.target.files[0]);
                  const formData = new FormData();
                  formData.append('image', e.target.files[0]);
                  formData.append('filePath', 'boards/teachers/profile-images');

                  axios
                    .post(`${process.env.REACT_APP_API_URL}/upload`, formData, {
                      headers: {
                        'ngrok-skip-browser-warning': 'asdasdas',
                      },
                    })
                    .then(res => res.data.data)
                    .then((data: string) => {
                      setProfileImage(data);
                    });

                  const fileReader = new FileReader();
                  fileReader.readAsDataURL(e.target.files[0]);
                  fileReader.onload = (e: any) => {
                    setProfilePreview(e.target.result);
                  };
                } else {
                  setProfileImage('');
                  setProfilePreview('http://placehold.it/340X240');
                }
              }}
            />
            <Img src={profilePreview} />
          </FlexContainer>
          {/* 실제 사진 */}
          <FlexContainer dir="col" align="start">
            <label htmlFor="real">실제 사진</label>
            <input
              id="real"
              type="file"
              accept="image/*"
              onChange={(e: any) => {
                if (e.target.files.length) {
                  const formData = new FormData();
                  formData.append('image', e.target.files[0]);
                  formData.append('filePath', 'boards/teachers/real-images');
                  axios
                    .post(`${process.env.REACT_APP_API_URL}/upload`, formData, {
                      headers: {
                        'ngrok-skip-browser-warning': 'asdasdas',
                      },
                    })
                    .then(res => res.data.data)
                    .then((data: any) => {
                      setRealImage(data);
                    });

                  const fileReader = new FileReader();
                  fileReader.readAsDataURL(e.target.files[0]);
                  fileReader.onload = (e: any) => {
                    setRealPreview(e.target.result);
                  };
                } else {
                  setRealImage('');
                  setRealPreview('http://placehold.it/340X240');
                }
              }}
            />
            <Img src={realPreview} />
          </FlexContainer>
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
                          readOnly
                          checked={subjectTag.includes(el)}
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
                          readOnly
                          checked={gradeTag.includes(el)}
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
                          type="checkbox"
                          readOnly
                          checked={platformTag.includes(el)}
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
            <label htmlFor="profile">경력</label>
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
          <Span>⭐️ {starPointAverage}</Span>
        </CardContainer>
      </FlexContainer>
      <FlexContainer>
        <UploadButton onClick={updateHandler}>강사 수정</UploadButton>
        <UploadButton
          onClick={() => {
            navigate(-1);
          }}
        >
          수정 취소
        </UploadButton>
      </FlexContainer>
    </UpdateContainer>
  );
}

export default UpdateTeacher;

/* eslint-disable react/no-array-index-key */
import GlobalStyle from 'GlobalStyles';
import styled from 'styled-components';
import SortBar from 'components/review/SortBar';
import SearchBar from 'components/review/SearchBar';
import Pagenation from 'components/review/Pagenation';
import Lecture from 'components/review/Lecture';
import Carousel from 'components/review/Carousel';
import SubjectMenu from 'components/review/SubjectMenu';
import { useEffect, useState } from 'react';
import axios from 'axios';
import Button from 'components/common/Button';
import isLogin from 'utils/isLogin';
import { Link } from 'react-router-dom';

import { FlexContainer } from './ReviewPage';

type LectureType = {
  lectureId: number;
  title: string;
  introduction: string;
  status: string;
  starPointAverage: number;
  totalReviewCount: number;
  gradeTags: { gradeTag: string }[];
  subjectTags: { subjectTag: string }[];
  platformTags: { platformTag: string }[];
  teacher: {
    teacherId: number;
    name: string;
    starPointAverage: number;
  };
};

type PageInfo = {
  page: number;
  size: number;
  totalElements: number;
  totalPages: number;
};

const defaultPageInfo = {
  page: 1,
  size: 6,
  totalElements: 6,
  totalPages: 1,
};

function LectureReviewPage() {
  const [buttonOpen, setButtonOpen] = useState<boolean>(false);
  const [subject, setSubject] = useState<string>('전체');
  const [grade, setGrade] = useState<string>('전체');
  const [platform, setPlatform] = useState<string>('전체');
  const [sortTag, setSortTag] = useState<string>('최신순');
  const [search, setSearch] = useState<string>('');
  const [reverse, setReverse] = useState<string>('정순');
  const [lectures, setLectures] = useState<LectureType[]>([]); // 서버에서 받아올 선생 정보
  const [pageInfo, setPageInfo] = useState<PageInfo>(defaultPageInfo);
  const [curPage, setCurPage] = useState<number>(1);
  const [pageSize, setPageSize] = useState<number>(6);

  useEffect(() => {
    console.log('Rerendering!!');

    axios
      .get(
        `${process.env.REACT_APP_API_URL}/lectures?${
          subject !== '전체' ? `subject=${subject}&` : ''
        }${sortTag !== '최신순' ? `sort=${sortTag}&` : ''}${
          grade !== '전체' ? `grade=${grade}&` : ''
        }${search !== '' ? `title=${search}&` : ''}${
          platform !== '전체' ? `platform=${platform}&` : ''
        }${
          reverse !== '정순' ? `reverse=on&` : ''
        }page=${curPage}&size=${pageSize}`,
        {
          headers: { 'ngrok-skip-browser-warning': '69420' },
        },
      )
      .then((res: any) => {
        console.log(res.data.data);
        console.log(res.data.pageInfo);
        setLectures(res.data.data);
        setPageInfo(res.data.pageInfo);
      });
  }, [subject, sortTag, search, curPage, grade, platform, reverse]);

  return (
    <FlexContainer dir="col">
      <GlobalStyle />
      <Carousel />
      <FlexContainer
        display={isLogin() ? 'flex' : 'none'}
        width="80%"
        justify="right"
      >
        <Link to="createTeacher">
          <PButton>강사 등록</PButton>
        </Link>
      </FlexContainer>
      <SortBar
        reverse={reverse}
        setReverse={setReverse}
        sortTag={sortTag}
        setSortTag={setSortTag}
        buttonOpen={buttonOpen}
        setButtonOpen={setButtonOpen}
      />
      <SubjectMenu
        buttonOpen={buttonOpen}
        subject={subject}
        grade={grade}
        platform={platform}
        setGrade={setGrade}
        setSubject={setSubject}
        setPlatform={setPlatform}
      />
      <SearchBar search={search} setSearch={setSearch} />

      {!lectures.length ? (
        <FlexContainer>등록된 강의가 없습니다</FlexContainer>
      ) : (
        <FlexContainer dir="col">
          {lectures.map((el, index) => {
            return (
              <Lecture
                key={index}
                lecture={{
                  lectureId: el.lectureId,
                  title: el.title,
                  introduction: el.introduction,
                  status: el.status,
                  starPointAverage: el.starPointAverage,
                  totalReviewCount: el.totalReviewCount,
                  gradeTags: el.gradeTags,
                  subjectTags: el.subjectTags,

                  teacher: el.teacher,
                }}
                first={index === 0}
              />
            );
          })}
        </FlexContainer>
      )}

      <Pagenation
        size={pageInfo.totalPages}
        currentPage={curPage}
        pageSize={pageSize}
        setCurPage={setCurPage}
      />
    </FlexContainer>
  );
}

export default LectureReviewPage;

type SubjectSelectButton = {
  isOpen: boolean;
};

const SubjectSelectButton = styled.div<SubjectSelectButton>`
  width: 5.25rem;
  height: 2.125rem;
  text-align: center;
  color: white;
  background-color: ${props => (props.isOpen ? '#6667ab' : '#b8b8b8')};
  border: none;
  border-radius: 13px;
  font-weight: bold;

  position: relative;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  cursor: pointer;
`;

const PButton = Button.PointBtn;

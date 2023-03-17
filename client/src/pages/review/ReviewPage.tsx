import GlobalStyle from 'GlobalStyles';
import styled from 'styled-components';
import SortBar from 'components/review/SortBar';
import SearchBar from 'components/review/SearchBar';
import Pagenation from 'components/review/Pagenation';
import CharacterCard from 'components/review/CharacterCard';
import Carousel from 'components/review/Carousel';
import SubjectMenu from 'components/review/SubjectMenu';

import { useEffect, useState } from 'react';
import axios from 'axios';
import Button from 'components/common/Button';
import isLogin from 'utils/isLogin';
import { Link } from 'react-router-dom';

type Teachers = {
  gradeTags: string[];
  imageUrl: string;
  introduction: string;
  name: string; // 강사명
  platformTags: { platformTag: string }[];
  starPointAverage: number;
  subjectTags: { subjectTag: string }[];
  teacherId: number;
  totalReviewCount: number;
};

function ReviewPage() {
  const [buttonOpen, setButtonOpen] = useState<boolean>(false);
  const [subject, setSubject] = useState<string>('전체');
  const [grade, setGrade] = useState<string>('전체');
  const [platform, setPlatform] = useState<string>('전체');
  const [sortTag, setSortTag] = useState<string>('최신순');
  const [search, setSearch] = useState<string>('');
  const [reverse, setReverse] = useState<string>('정순');
  const [teachers, setTeachers] = useState<Teachers[]>([]); // 서버에서 받아올 선생 정보

  const [curPage, setCurPage] = useState<number>(1);
  const [pageSize, setPageSize] = useState<number>(6);

  useEffect(() => {
    console.log('Rerendering!!');

    axios
      .get(
        `http://13.125.1.215:8080/teachers?${
          subject !== '전체' ? `subject=${subject}&` : ''
        }${sortTag !== '최신순' ? `sort=${sortTag}&` : ''}${
          grade !== '전체' ? `grade=${grade}&` : ''
        }${search !== '' ? `name=${search}&` : ''}${
          platform !== '전체' ? `platform=${platform}&` : ''
        }${
          reverse !== '정순' ? `reverse=on&` : ''
        }page=${curPage}&size=${pageSize}`,
      )
      .then((res: any) => {
        console.log(res.data.data);
        setTeachers(res.data.data);
      });

    // fetch(
    //   `http://13.125.1.215:8080/teachers?page=${curPage}&size=${pageSize}`,
    //   {
    //     method: 'GET',
    //     headers: {
    //       'Content-Type': 'application/json',
    //       'User-Agent': 'ngrok-skip-browser-warning',
    //       Accept: 'application/json',
    //     },
    //   },
    // )
    //   .then((res: any) => {
    //     return res.json();
    //   })
    //   .then((data: any) => {
    //     console.log(data);
    //     setTeachers(data.data);
    //   });
  }, [subject, sortTag, search, curPage, grade, platform, reverse]);

  return (
    <FlexContainer dir="col">
      <GlobalStyle />
      <Carousel />
      <FlexContainer
        display={!isLogin() ? 'flex' : 'none'}
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

      <CharacterCard teachers={teachers} />
      <Pagenation
        size={teachers.length}
        currentPage={curPage}
        pageSize={pageSize}
        setCurPage={setCurPage}
      />
    </FlexContainer>
  );
}

export default ReviewPage;

type Container = {
  dir?: string;
  gap?: string;
  justify?: string;
  align?: string;
  width?: string;
  height?: string;
  backColor?: string;
  borderRadius?: string;
  display?: string;
  wrap?: string;
  grow?: number;
};

type SubjectSelectButton = {
  isOpen: boolean;
};

export const FlexContainer = styled.div<Container>`
  width: ${props => props.width};
  height: ${props => props.height};
  display: ${props => props.display || 'flex'};
  flex-direction: ${props => (props.dir === 'col' ? 'column' : 'row')};
  flex-wrap: ${props => props.wrap};
  justify-content: ${props => props.justify || 'center'};
  align-items: ${props => props.align || 'center'};
  gap: ${props => props.gap || '1rem'};
  background-color: ${props => props.backColor || 'none'};
  border-radius: ${props => props.borderRadius || 'none'};
  flex-grow: ${props => props.grow};
`;

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

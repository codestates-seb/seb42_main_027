import GlobalStyle from 'GlobalStyles';
import styled from 'styled-components';
import SortBar from 'components/review/SortBar';
import SearchBar from 'components/review/SearchBar';
import Pagenation from 'components/review/Pagenation';
import CharacterCard from 'components/review/CharacterCard';
import Carousel from 'components/review/Carousel';
import SubjectMenu from 'components/review/SubjectMenu';
import Loading from 'components/review/Loading';

import { useEffect, useState } from 'react';
import axios from 'axios';
import Button from 'components/common/Button';
import isLogin from 'utils/isLogin';
import { Link } from 'react-router-dom';
import { type } from 'os';

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

function ReviewPage() {
  const [buttonOpen, setButtonOpen] = useState<boolean>(false);
  const [subject, setSubject] = useState<string>('전체');
  const [grade, setGrade] = useState<string>('전체');
  const [platform, setPlatform] = useState<string>('전체');
  const [sortTag, setSortTag] = useState<string>('최신순');
  const [search, setSearch] = useState<string>('');
  const [reverse, setReverse] = useState<string>('정순');
  const [teachers, setTeachers] = useState<Teachers[]>([]); // 서버에서 받아올 선생 정보
  const [pageInfo, setPageInfo] = useState<PageInfo>(defaultPageInfo);
  const [curPage, setCurPage] = useState<number>(1);
  const [pageSize, setPageSize] = useState<number>(6);
  const [isPending, setIsPending] = useState(true);

  useEffect(() => {
    setIsPending(true);
    axios
      .get(
        `${process.env.REACT_APP_API_URL}/teachers?${
          subject !== '전체' ? `subject=${subject}&` : ''
        }${sortTag !== '최신순' ? `sort=${sortTag}&` : ''}${
          grade !== '전체' ? `grade=${grade}&` : ''
        }${search !== '' ? `name=${search}&` : ''}${
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
        setTeachers(res.data.data);
        setPageInfo(res.data.pageInfo);
        setCurPage(res.data.pageInfo.page);
        setIsPending(false);
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
        setCurPage={setCurPage}
      />
      <SubjectMenu
        buttonOpen={buttonOpen}
        subject={subject}
        grade={grade}
        platform={platform}
        setGrade={setGrade}
        setSubject={setSubject}
        setPlatform={setPlatform}
        setCurPage={setCurPage}
      />
      <SearchBar
        search={search}
        setSearch={setSearch}
        setCurPage={setCurPage}
      />

      {isPending ? (
        <Loading />
      ) : (
        <FlexContainer dir="col">
          {!teachers.length ? (
            <FlexContainer height="50vh">등록된 강사가 없습니다</FlexContainer>
          ) : (
            <CharacterCard teachers={teachers} />
          )}
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
  borderTop?: string;
  borderBottom?: string;
  padding?: string;
  overflow?: string;
  top?: string;
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
  border-top: ${props => props.borderTop};
  border-bottom: ${props => props.borderBottom};
  padding: ${props => props.padding};
  overflow: ${props => props.overflow};
  top: ${props => props.top};
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

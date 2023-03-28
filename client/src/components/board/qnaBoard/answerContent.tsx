import { useEffect, useState } from 'react';
import { Link, useLocation, useParams, useNavigate } from 'react-router-dom';
import useUserInfoStore from 'stores/userInfoStore';

import styled from 'styled-components';
import theme from 'theme';
import Button from 'components/common/Button';
import ProfileIcon from 'assets/icons/defaultProfileIcon';
import CountIcon from 'assets/icons/countIcon';

import DeletePost from 'apis/board/deletePost';
import getPostDetail from 'apis/board/getPostDetail';
import PostVote from 'apis/board/postVote';
import CalElapsedTime from '../post/calElapsedTime';

// import WriteComment from '../comment/writeComment';

interface AnswerData {
  answerId: number;
  content: 'string';
  voteCount: number;
  createdAt: string;
  modifiedAt: string | null;
  adoptStatus: string;
  answerCount: number;
  member: {
    memberId: number;
    iconImageUrl?: string;
    displayName: string;
    state: string;
  };
  comments: [Comment];
}

interface Comment {
  qnaCommentId: number;
  content: string;
  createdAt: string;
  modifiedAt: string | null;
  voteCount: number;
  member: {
    memberId: number;
    iconImageUrl?: string;
    displayName: string;
    state: string;
  };
}

type Props = {
  data: AnswerData;
};

function AnswerContent({ data }: Props) {
  const { userInfo } = useUserInfoStore(state => state);
  const [isPending, setIsPending] = useState(true);
  const idData = Number(useParams().id);

  let calTime = '';
  if (!isPending) {
    calTime = CalElapsedTime(data.createdAt);
  }
  console.log(data);
  console.log('userInfo memeberId', userInfo.memberId);

  return (
    <Container>
      <TitleDiv>
        <Top>
          <CategoryDiv>
            <Category>답변</Category>
            {data.adoptStatus === 'TRUE' ? (
              <Category className="seleted">답변채택</Category>
            ) : null}
          </CategoryDiv>
          {data.member.memberId === userInfo.memberId ? (
            <UDBtnDiv>
              <Link to="edit">
                <Button.UDWhiteBtn>수정</Button.UDWhiteBtn>
              </Link>
              <Button.UDWhiteBtn>삭제</Button.UDWhiteBtn>
            </UDBtnDiv>
          ) : null}
        </Top>
        <Writer>
          <ProfileIcon.Default />
          <div>{data.member.displayName}</div>
          <div> · {calTime}</div>
        </Writer>
      </TitleDiv>
      <MainDiv>
        <TextDiv dangerouslySetInnerHTML={{ __html: data.content }} />
        <div>
          {data.adoptStatus === 'TRUE' ? (
            <BtnSelect className="selected">채택하기</BtnSelect>
          ) : (
            <BtnSelect>채택하기</BtnSelect>
          )}
        </div>
        <VoteDiv>
          <Button.VoteDownBtn>
            <CountIcon.VoteDown />
          </Button.VoteDownBtn>
          <VoteCount>{data.voteCount}</VoteCount>
          <Button.VoteUpBtn>
            <CountIcon.VoteUp />
          </Button.VoteUpBtn>
        </VoteDiv>
      </MainDiv>
      {/* <CommentCnt>{listData.commentsListNum}개의 댓글</CommentCnt>
          <CommentContainer>
            <WriteCommentDiv>
              <WriteComment />
            </WriteCommentDiv>
            {listData.commentsListNum === 0 ? null : (
              <div>
                {listData.comments.map((ele: Comment) => {
                  return <CommentBlock key={ele.freeCommentId} data={ele} />;
                })}
              </div>
            )}
          </CommentContainer> */}
    </Container>
  );
}

const Container = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
`;

const NoData = styled.div`
  display: flex;
  width: 100%;
  justify-content: center;
  align-items: center;
  padding-top: ${theme.gap.px60};
`;

const TitleDiv = styled.div`
  display: flex;
  flex-direction: column;
  padding: ${theme.gap.px20};
  border-bottom: 1px solid ${theme.colors.gray};
`;

const Top = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: ${theme.gap.px10};
`;

const CategoryDiv = styled.div`
  display: flex;
  align-items: center;
`;

const Writer = styled.div`
  display: flex;
  align-items: center;
  position: relative;
`;

const Category = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  min-width: 40px;
  height: 18px;
  padding: 0 calc(${theme.gap.px10} / 2);
  border: 1px solid ${theme.colors.pointColor};
  border-radius: 5px;
  font-size: ${theme.fontSizes.sm};
  font-weight: bold;
  color: ${theme.colors.pointColor};
  background-color: ${theme.colors.white};
  margin-bottom: 4px;
  margin-right: 5px;

  &.seleted {
    color: ${theme.colors.white};
    background-color: ${theme.colors.pointColor};
  }
`;

const UDBtnDiv = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: calc(${theme.gap.px40} * 2 + 6px);
`;

const MainDiv = styled.div`
  display: flex;
  flex-direction: column;
  padding: ${theme.gap.px20};
  border-bottom: 1px solid ${theme.colors.gray};
`;

const BtnSelect = styled(Button.UDWhiteBtn)`
  width: 3.375rem;
`;

const VoteDiv = styled.div`
  display: flex;
  justify-content: right;
  margin-bottom: ${theme.gap.px120};
`;

const VoteCount = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  width: 1.875rem;
  height: 1.25rem;
  font-size: ${theme.fontSizes.xs};
  border-top: 1px solid ${theme.colors.gray};
  border-bottom: 1px solid ${theme.colors.gray};
`;

const AnswerCnt = styled.div`
  padding: ${theme.gap.px20};
  border-bottom: 1px solid ${theme.colors.gray};
`;

const CommentContainer = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  min-height: 253px;
`;

const WriteCommentDiv = styled.div`
  display: flex;
  width: 100%;
  padding-bottom: calc(${theme.gap.px60} + 7px);
`;

const TextDiv = styled.div`
  font-size: 1rem;
  line-height: 2rem;
  margin-bottom: ${theme.gap.px20};
  white-space: pre-wrap;

  .ql-size-small {
    font-size: ${theme.fontSizes.sm};
  }
  .ql-size-large {
    font-size: ${theme.fontSizes.md};
  }
  .ql-size-huge {
    font-size: ${theme.fontSizes.lg};
  }
  strong {
    font-weight: bold;
  }
  em {
    font-style: italic;
  }
  blockquote {
    border-left: 4px solid #ccc;
    margin-bottom: 5px;
    margin-top: 5px;
    padding-left: 16px;
  }
  ol {
    padding-left: 3em;
  }
  ol > li {
    list-style: decimal;
    &.ql-indent-1 {
      margin-left: 3em;
    }
  }
  ul {
    padding-left: 3em;
  }
  ul > li {
    list-style: disc;
    &.ql-indent-1 {
      margin-left: 3em;
    }
  }
  a {
    text-decoration: underline;
  }
  img {
    max-width: 50rem;
    height: auto;
  }
`;
export default AnswerContent;

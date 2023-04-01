/* eslint-disable no-unused-expressions */
/* eslint-disable react/button-has-type */
/* eslint-disable react/require-default-props */
import styled from 'styled-components';
import { FlexContainer } from 'pages/review/TeacherList/ReviewPage';
import { useParams } from 'react-router-dom';
import ProfileIcon from 'assets/icons/defaultProfileIcon';
import { useEffect, useState } from 'react';
import axios from 'axios';
import useUserInfoStore from 'stores/userInfoStore';
import theme from 'theme';
import Button from 'components/common/Button';
import CountIcon from 'assets/icons/countIcon';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import Swal from 'sweetalert2';

type Props = {
  lectureReviewCommentId: number;
  content: string;
  createdAt: string;
  modifiedAt: string;
  voteCount: number;
  vStatus: { lectureReviewCommentId: number; voteStatus: string }[];
  member: {
    memberId: number;
    displayName: string;
    IconImageUrl: string;
    state: string; // 강사, 학생, 관리자
  };
};

function LectureReviewComment({
  lectureReviewCommentId,
  content,
  createdAt,
  vStatus,
  modifiedAt,
  voteCount,
  member,
}: Props) {
  const [commentVote, setCommentVote] = useState(voteCount);
  const [updateContent, setUpdateContent] = useState<string>('');
  const [isOpen, setIsOpen] = useState<boolean>(false);
  const { teacherId } = useParams();
  const { userInfo } = useUserInfoStore(state => state);

  const voStatus = vStatus.filter(
    el => el.lectureReviewCommentId === lectureReviewCommentId,
  );
  let tmp: string | (() => string);
  !voStatus.length ? (tmp = 'NONE') : (tmp = voStatus[0].voteStatus);

  const [voteStatus, setVoteStatus] = useState(tmp);
  const Authorization = localStorage.getItem('token');

  const up = () => toast.success('Comment UP!');
  const down = () => toast.error('Comment DOWN!');
  const cancle = () => toast.info('Comment Cancel!');
  const login = () => toast.info('Login!');

  useEffect(() => {
    console.log(commentVote);
  }, [commentVote]);

  const updateOpenHandler = () => {
    setIsOpen(!isOpen);
  };
  const updateHandler = () => {
    if (!updateContent) {
      Swal.fire({
        title: '내용을 입력해주세요',
        icon: 'warning',

        confirmButtonColor: '#d33', // confrim 버튼 색깔 지정
        confirmButtonText: '승인', // confirm 버튼 텍스트 지정
      });
    } else {
      Swal.fire({
        title: '수정이 완료되었습니다',
        icon: 'success',

        confirmButtonColor: '#6667ab', // confrim 버튼 색깔 지정
        confirmButtonText: '확인', // confirm 버튼 텍스트 지정
      }).then(() => {
        axios
          .patch(
            `${process.env.REACT_APP_API_URL}/comments/reviews/lectures/${lectureReviewCommentId}`,
            {
              content: updateContent,
              createdAt: new Date(),
              lectureReviewCommentId,
            },
            {
              headers: {
                Authorization,
                'ngrok-skip-browser-warning': '69420',
              },
            },
          )
          .then(() => {
            window.location.reload();
          });
      });
    }
  };
  const commentUpHandler = () => {
    axios
      .post(
        `${process.env.REACT_APP_API_URL}/votes/reviews/lectures/comments/${lectureReviewCommentId}/up`,
        {},
        {
          headers: { Authorization, 'ngrok-skip-browser-warning': '69420' },
        },
      )
      .then(res => res.data.data)
      .then(data => {
        console.log(data);
        setCommentVote(data.lectureReviewCommentVoteTotalCount);
        setVoteStatus(data.status);
        if (data.status === 'UP') up();
        else if (data.status === 'NONE') cancle();
      })
      .catch(() => {
        login();
      });
  };
  const commentDownHandler = () => {
    axios
      .post(
        `${process.env.REACT_APP_API_URL}/votes/reviews/lectures/comments/${lectureReviewCommentId}/down`,
        {},
        {
          headers: { Authorization, 'ngrok-skip-browser-warning': '69420' },
        },
      )
      .then(res => res.data.data)
      .then(data => {
        console.log(data);
        setCommentVote(data.lectureReviewCommentVoteTotalCount);
        setVoteStatus(data.status);
        if (data.status === 'DOWN') down();
        else if (data.status === 'NONE') cancle();
      })
      .catch(() => {
        login();
      });
  };

  return (
    <Container>
      <FlexContainer width="100%" align="start" dir="col" gap="0.2rem">
        <FlexContainer width="100%" justify="space-between" padding="0 0.2rem">
          <VerySmallGrayFont>
            <ProfileIcon.Default />
            {member.displayName}
          </VerySmallGrayFont>
          <FlexContainer gap="0">
            <DownButton voteStatus={voteStatus} onClick={commentDownHandler}>
              <Button.VoteDownBtn>
                <CountIcon.VoteDown />
              </Button.VoteDownBtn>
            </DownButton>
            <VoteCount>{commentVote}</VoteCount>
            <UpButton voteStatus={voteStatus} onClick={commentUpHandler}>
              <Button.VoteUpBtn>
                <CountIcon.VoteUp />
              </Button.VoteUpBtn>
            </UpButton>
          </FlexContainer>
        </FlexContainer>
        {isOpen ? (
          <Textarea
            value={updateContent}
            placeholder="수정 내용 입력"
            onChange={e => {
              setUpdateContent(e.target.value);
            }}
          />
        ) : (
          <ContentBox>{content}</ContentBox>
        )}
        <FlexContainer
          width="100%"
          justify="space-between"
          align="end"
          padding="1rem 0"
        >
          <VerySmallGrayFont>{createdAt.slice(0, 10)}</VerySmallGrayFont>
          {!teacherId && userInfo.memberId === member.memberId ? (
            <FlexContainer justify="right">
              {isOpen ? (
                <FlexContainer gap="0.8rem">
                  <Ubutton onClick={updateHandler}>확인</Ubutton>
                  <Ubutton
                    onClick={() => {
                      setIsOpen(!isOpen);
                    }}
                  >
                    취소
                  </Ubutton>
                </FlexContainer>
              ) : (
                <FlexContainer gap="0.8rem">
                  <Ubutton onClick={updateOpenHandler}>수정</Ubutton>
                  <Ubutton
                    onClick={() => {
                      axios
                        .delete(
                          `${process.env.REACT_APP_API_URL}/comments/reviews/lectures/${lectureReviewCommentId}`,
                          {
                            headers: {
                              Authorization,
                              'ngrok-skip-browser-warning': '69420',
                            },
                          },
                        )
                        .then(() => {
                          window.location.reload();
                        });
                    }}
                  >
                    삭제
                  </Ubutton>
                </FlexContainer>
              )}
            </FlexContainer>
          ) : null}
        </FlexContainer>
      </FlexContainer>
    </Container>
  );
}

export default LectureReviewComment;

type Container = {
  first?: boolean;
};

type Button = {
  voteStatus?: string;
};

const Container = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  padding: ${theme.gap.px20};
  padding-bottom: 0;
  border-bottom: 1px solid ${theme.colors.gray};
`;

const VerySmallGrayFont = styled.div`
  width: 5rem;
  font-size: small;
  color: gray;
  font-weight: bold;
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 0.3rem;
`;

const UpButton = styled.button<Button>`
  border: none;
  pointer-events: ${props => (props.voteStatus === 'DOWN' ? 'none' : 'all')};
  background-color: white;
  color: ${props => (props.voteStatus === 'UP' ? '#f48224' : 'black')};
`;

const DownButton = styled.button<Button>`
  border: none;
  pointer-events: ${props => (props.voteStatus === 'UP' ? 'none' : 'all')};
  background-color: white;
  color: ${props => (props.voteStatus === 'DOWN' ? '#f48224' : 'black')};
`;

const ContentBox = styled.div`
  width: 100%;
  padding: 1rem 0;
`;

const Textarea = styled.textarea`
  width: 100%;
  height: 6rem;
  padding: 1rem;
`;

const Ubutton = styled.button`
  padding: 0.4rem;
  background-color: white;
  color: #afafaf;
  border-radius: 0.4rem;
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

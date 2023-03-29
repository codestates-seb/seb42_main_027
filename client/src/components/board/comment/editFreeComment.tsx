import React, { useState } from 'react';
import { Link, useLocation, useParams } from 'react-router-dom';

import styled from 'styled-components';
import theme from 'theme';
import Button from 'components/common/Button';
import ProfileIcon from 'assets/icons/defaultProfileIcon';
import { AiOutlineExclamationCircle } from 'react-icons/ai';

import { useIsLoginStore } from 'stores/loginStore';
import useUserInfoStore from 'stores/userInfoStore';
import PostComment from 'apis/board/postComment';

interface Comment {
  freeCommentId: number;
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
  data: Comment;
  checkState: boolean;
  setCheckState: React.Dispatch<React.SetStateAction<boolean>>;
  editData: string;
  setEditData: React.Dispatch<React.SetStateAction<string>>;
};

function EditFreeComment({
  data,
  checkState,
  setCheckState,
  editData,
  setEditData,
}: Props) {
  const { isLoginInStore } = useIsLoginStore(state => state);

  return (
    <Container>
      {isLoginInStore ? (
        <Textarea
          value={editData}
          placeholder="댓글을 입력해 주세요."
          onChange={e => setEditData(e.target.value)}
        />
      ) : (
        <GuideDiv>
          <AiOutlineExclamationCircle />
          <div>
            댓글을 쓰려면 <Link to="/login">로그인</Link>이 필요합니다.
          </div>
        </GuideDiv>
      )}
    </Container>
  );
}

const Container = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  margin-bottom: ${theme.gap.px20};
`;

const Textarea = styled.textarea`
  display: flex;
  width: 100%;
  height: 100%;
  resize: none;
  padding: ${theme.gap.px10};
  border: 1px solid ${theme.colors.gray};
  border-radius: 5px;
`;

const GuideDiv = styled.div`
  display: flex;
  width: 100%;
  height: 73px;
  padding: ${theme.gap.px10};
  border: 1px solid ${theme.colors.gray};
  border-radius: 5px;
  color: ${theme.colors.gray};
  margin-left: ${theme.gap.px10};
  a {
    font-weight: bold;
    text-decoration: underline;
    color: ${theme.colors.pointColor};
  }
  > div {
    margin-left: 6px;
  }
`;

export default EditFreeComment;

import styled from 'styled-components';
import theme from 'theme';
import { AiOutlineEye } from 'react-icons/ai';
import { BiCommentDetail, BiLike } from 'react-icons/bi';
import {
  MdOutlineKeyboardArrowUp,
  MdOutlineKeyboardArrowDown,
} from 'react-icons/md';

const View = styled(AiOutlineEye)`
  color: ${theme.colors.gray};
  width: 15px;
  height: 15px;
`;

const Comment = styled(BiCommentDetail)`
  color: ${theme.colors.gray};
  width: 15px;
  height: 15px;
`;

const Vote = styled(BiLike)`
  color: ${theme.colors.gray};
  width: 15px;
  height: 15px;
`;

const VoteUp = styled(MdOutlineKeyboardArrowUp)``;

const VoteDown = styled(MdOutlineKeyboardArrowDown)``;

const CountIcon = {
  View,
  Comment,
  Vote,
  VoteUp,
  VoteDown,
};

export default CountIcon;

package ynzmz.server.vote.qna.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ynzmz.server.vote.Vote;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginUserQnaReCommentVoteResponseDto {
    private Long qnaReCommentVoteId;
    private Vote.Status voteStatus;
}
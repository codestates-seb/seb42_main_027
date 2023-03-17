package ynzmz.server.vote.qna.question.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ynzmz.server.vote.qna.question.entity.QuestionVote;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class QuestionVoteResponseDto {
    private QuestionVote.VoteStatus voteStatus;
    private Long questionId;
    private Long memberId;
    private Long questionVoteTotalCount;

}

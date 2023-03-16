package ynzmz.server.vote.question.question.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ynzmz.server.vote.question.question.entity.QuestionVote;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class QuestionVoteResponseDto {
    private QuestionVote.VoteStatus voteStatus;
    private Long questionId;
    private Long memberId;
    private Long questionVoteTotalCount;

}

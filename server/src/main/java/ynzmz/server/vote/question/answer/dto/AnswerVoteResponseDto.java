package ynzmz.server.vote.question.answer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ynzmz.server.vote.question.answer.entity.AnswerVote;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class AnswerVoteResponseDto {
        private AnswerVote.VoteStatus voteStatus;
        private Long answerId;
        private Long memberId;
        private Long answerVoteTotalCount;
    }

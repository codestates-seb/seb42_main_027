package ynzmz.server.vote.question.answer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ynzmz.server.vote.question.answer.entity.AnswerVote;

/**
 *  질문글 상세페이지용
 *
 *  로그인한 유저의 답변 추천상태 확인
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginUserAnswerVoteResponseDto {
    private Long answerId;
    private AnswerVote.VoteStatus voteStatus;
}

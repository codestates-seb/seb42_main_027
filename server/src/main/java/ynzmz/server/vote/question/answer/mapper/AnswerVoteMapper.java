package ynzmz.server.vote.question.answer.mapper;

import org.mapstruct.Mapper;
import ynzmz.server.question.answer.entity.Answer;
import ynzmz.server.vote.question.answer.dto.AnswerVoteResponseDto;
import ynzmz.server.vote.question.answer.entity.AnswerVote;

@Mapper(componentModel = "spring")
public interface AnswerVoteMapper {
    default AnswerVoteResponseDto answerVoteToAnswerResponseDto(AnswerVote answerVote, Answer answer){

        AnswerVoteResponseDto answerVoteResponseDto = new AnswerVoteResponseDto();

        answerVoteResponseDto.setAnswerId(answerVote.getAnswer().getAnswerId());
        answerVoteResponseDto.setVoteStatus(answerVote.getVoteStatus());
        answerVoteResponseDto.setMemberId(answer.getMember().getMemberId());
        answerVoteResponseDto.setAnswerVoteTotalCount(answer.getVoteCount());

        return answerVoteResponseDto;
    }
}

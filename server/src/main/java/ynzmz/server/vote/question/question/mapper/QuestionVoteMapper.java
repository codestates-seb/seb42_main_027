package ynzmz.server.vote.question.question.mapper;

import org.mapstruct.Mapper;
import ynzmz.server.question.question.entity.Question;
import ynzmz.server.vote.question.question.dto.QuestionVoteResponseDto;
import ynzmz.server.vote.question.question.entity.QuestionVote;

@Mapper(componentModel = "spring")
public interface QuestionVoteMapper {
    default QuestionVoteResponseDto questionVoteToQuestionResponseDto(QuestionVote questionVote, Question question){

        QuestionVoteResponseDto questionVoteResponseDto = new QuestionVoteResponseDto();

        questionVoteResponseDto.setVoteStatus(questionVote.getVoteStatus());
        questionVoteResponseDto.setQuestionId(question.getQuestionId());
        questionVoteResponseDto.setMemberId(questionVote.getMember().getMemberId());
        questionVoteResponseDto.setQuestionVoteTotalCount(question.getVoteCount());

        return questionVoteResponseDto;
    }
}

package ynzmz.server.vote.qna.question.mapper;

import org.mapstruct.Mapper;
import ynzmz.server.board.qna.question.entity.Question;
import ynzmz.server.vote.qna.question.dto.QuestionVoteResponseDto;
import ynzmz.server.vote.qna.question.entity.QuestionVote;

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

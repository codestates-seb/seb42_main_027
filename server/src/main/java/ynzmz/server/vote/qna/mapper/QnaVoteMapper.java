package ynzmz.server.vote.qna.mapper;

import org.mapstruct.Mapper;
import ynzmz.server.vote.qna.dto.QnaVoteDto;
import ynzmz.server.vote.qna.entity.QnaVote;

@Mapper(componentModel = "spring")
public interface QnaVoteMapper {
    default QnaVoteDto.Response qnaVoteToQuestionResponse(QnaVote qnaVote){

        QnaVoteDto.Response qnaVoteDto = new QnaVoteDto.Response();

        qnaVoteDto.setTarget(qnaVote.getTarget());
        qnaVoteDto.setStatus(qnaVote.getVoteStatus());
        qnaVoteDto.setQuestionId(qnaVote.getQuestion().getQuestionId());
        qnaVoteDto.setAnswerId(qnaVote.getAnswer().getAnswerId());
        qnaVoteDto.setMemberId(qnaVote.getMember().getMemberId());
        qnaVoteDto.setQuestionVoteTotalCount(qnaVote.getQuestion().getVoteCount());
        qnaVoteDto.setAnswerVoteTotalCount(qnaVote.getAnswer().getVoteCount());

        return qnaVoteDto;
    }
}

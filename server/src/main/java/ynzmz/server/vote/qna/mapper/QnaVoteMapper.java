package ynzmz.server.vote.qna.mapper;

import org.mapstruct.Mapper;
import ynzmz.server.vote.qna.dto.QnaVoteDto;
import ynzmz.server.vote.qna.entity.QnaVote;

@Mapper(componentModel = "spring")
public interface QnaVoteMapper {
    default QnaVoteDto.QuestionResponse qnaVoteToQuestionResponse(QnaVote qnaVote){

        QnaVoteDto.QuestionResponse qnaVoteDto = new QnaVoteDto.QuestionResponse();

        qnaVoteDto.setTarget(qnaVote.getTarget());
        qnaVoteDto.setStatus(qnaVote.getVoteStatus());
        qnaVoteDto.setMemberId(qnaVote.getMember().getMemberId());
        if( qnaVote.getQuestion() != null ) {
            qnaVoteDto.setQuestionId(qnaVote.getQuestion().getQuestionId());
            qnaVoteDto.setQuestionVoteTotalCount(qnaVote.getQuestion().getVoteCount());
        }

        return qnaVoteDto;
    }
    default QnaVoteDto.AnswerResponse qnaVoteToAnswerResponse(QnaVote qnaVote){

        QnaVoteDto.AnswerResponse qnaVoteDto = new QnaVoteDto.AnswerResponse();

        qnaVoteDto.setTarget(qnaVote.getTarget());
        qnaVoteDto.setStatus(qnaVote.getVoteStatus());
        qnaVoteDto.setMemberId(qnaVote.getMember().getMemberId());
        if( qnaVote.getAnswer() != null ) {
            qnaVoteDto.setAnswerId(qnaVote.getAnswer().getAnswerId());
            qnaVoteDto.setAnswerVoteTotalCount(qnaVote.getAnswer().getVoteCount());
        }

        return qnaVoteDto;
    }

    default QnaVoteDto.CommentResponse qnaVoteToCommentResponse(QnaVote qnaVote){

        QnaVoteDto.CommentResponse qnaVoteDto = new QnaVoteDto.CommentResponse();

        qnaVoteDto.setTarget(qnaVote.getTarget());
        qnaVoteDto.setStatus(qnaVote.getVoteStatus());
        qnaVoteDto.setMemberId(qnaVote.getMember().getMemberId());
        if( qnaVote.getQnaComment() != null ) {
            qnaVoteDto.setCommentId(qnaVote.getQnaComment().getQnaCommentId());
            qnaVoteDto.setCommentVoteTotalCount(qnaVote.getQnaComment().getVoteCount());
        }

        return qnaVoteDto;
    }

    default QnaVoteDto.ReCommentResponse qnaVoteToReCommentResponse(QnaVote qnaVote){

        QnaVoteDto.ReCommentResponse qnaVoteDto = new QnaVoteDto.ReCommentResponse();

        qnaVoteDto.setTarget(qnaVote.getTarget());
        qnaVoteDto.setStatus(qnaVote.getVoteStatus());
        qnaVoteDto.setMemberId(qnaVote.getMember().getMemberId());
        if( qnaVote.getQnaReComment() != null ) {
            qnaVoteDto.setRecommentId(qnaVote.getQnaReComment().getQnaReCommentId());
            qnaVoteDto.setRecommentVoteTotalCount(qnaVote.getQnaReComment().getVoteCount());
        }

        return qnaVoteDto;
    }
}

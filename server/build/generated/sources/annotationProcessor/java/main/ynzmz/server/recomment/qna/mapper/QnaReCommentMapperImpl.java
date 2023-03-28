package ynzmz.server.recomment.qna.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ynzmz.server.board.qna.answer.dto.AnswerDto;
import ynzmz.server.board.qna.answer.entity.Answer;
import ynzmz.server.board.qna.question.dto.QuestionDto;
import ynzmz.server.board.qna.question.entity.Question;
import ynzmz.server.comment.qna.dto.QnaCommentDto;
import ynzmz.server.comment.qna.entity.QnaComment;
import ynzmz.server.member.dto.MemberDto;
import ynzmz.server.member.entity.Member;
import ynzmz.server.recomment.qna.dto.QnaReCommentDto;
import ynzmz.server.recomment.qna.entity.QnaReComment;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-29T02:24:49+0900",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.1.jar, environment: Java 11.0.17 (Azul Systems, Inc.)"
)
@Component
public class QnaReCommentMapperImpl implements QnaReCommentMapper {

    @Override
    public QnaReComment qnaReCommentPostToQnaReComment(QnaReCommentDto.Post qnaReCommentPostDto) {
        if ( qnaReCommentPostDto == null ) {
            return null;
        }

        QnaReComment qnaReComment = new QnaReComment();

        qnaReComment.setContent( qnaReCommentPostDto.getContent() );
        qnaReComment.setCreatedAt( qnaReCommentPostDto.getCreatedAt() );

        return qnaReComment;
    }

    @Override
    public QnaReComment qnaReCommentPatchToQnaReComment(QnaReCommentDto.Patch qnaReCommentPatchDto) {
        if ( qnaReCommentPatchDto == null ) {
            return null;
        }

        QnaReComment qnaReComment = new QnaReComment();

        qnaReComment.setContent( qnaReCommentPatchDto.getContent() );
        qnaReComment.setModifiedAt( qnaReCommentPatchDto.getModifiedAt() );

        return qnaReComment;
    }

    @Override
    public QnaReCommentDto.Response qnaReCommentToQnaReCommentResponse(QnaReComment qnaReComment) {
        if ( qnaReComment == null ) {
            return null;
        }

        QnaReCommentDto.Response response = new QnaReCommentDto.Response();

        if ( qnaReComment.getQnaReCommentId() != null ) {
            response.setQnaReCommentId( qnaReComment.getQnaReCommentId() );
        }
        response.setContent( qnaReComment.getContent() );
        response.setCreatedAt( qnaReComment.getCreatedAt() );
        response.setModifiedAt( qnaReComment.getModifiedAt() );
        response.setVoteCount( qnaReComment.getVoteCount() );
        response.setMember( memberToSimpleInfoResponse( qnaReComment.getMember() ) );

        return response;
    }

    @Override
    public List<QnaReCommentDto.SimpleResponse> qnaReCommentToQnaReCommentSimpleResponse(List<QnaReComment> qnaReComments) {
        if ( qnaReComments == null ) {
            return null;
        }

        List<QnaReCommentDto.SimpleResponse> list = new ArrayList<QnaReCommentDto.SimpleResponse>( qnaReComments.size() );
        for ( QnaReComment qnaReComment : qnaReComments ) {
            list.add( qnaReCommentToSimpleResponse( qnaReComment ) );
        }

        return list;
    }

    protected MemberDto.SimpleInfoResponse memberToSimpleInfoResponse(Member member) {
        if ( member == null ) {
            return null;
        }

        Long memberId = null;
        String displayName = null;
        String iconImageUrl = null;
        String state = null;

        memberId = member.getMemberId();
        displayName = member.getDisplayName();
        iconImageUrl = member.getIconImageUrl();
        if ( member.getState() != null ) {
            state = member.getState().name();
        }

        MemberDto.SimpleInfoResponse simpleInfoResponse = new MemberDto.SimpleInfoResponse( memberId, displayName, iconImageUrl, state );

        return simpleInfoResponse;
    }

    protected QuestionDto.SimpleResponse questionToSimpleResponse(Question question) {
        if ( question == null ) {
            return null;
        }

        QuestionDto.SimpleResponse simpleResponse = new QuestionDto.SimpleResponse();

        simpleResponse.setQuestionId( question.getQuestionId() );
        simpleResponse.setTitle( question.getTitle() );

        return simpleResponse;
    }

    protected AnswerDto.SimpleResponse answerToSimpleResponse(Answer answer) {
        if ( answer == null ) {
            return null;
        }

        AnswerDto.SimpleResponse simpleResponse = new AnswerDto.SimpleResponse();

        simpleResponse.setAnswerId( answer.getAnswerId() );
        simpleResponse.setContent( answer.getContent() );

        return simpleResponse;
    }

    protected QnaCommentDto.SimpleResponse qnaCommentToSimpleResponse(QnaComment qnaComment) {
        if ( qnaComment == null ) {
            return null;
        }

        Long qnaCommentId = null;
        String content = null;
        String createdAt = null;
        String modifiedAt = null;
        long voteCount = 0L;
        QuestionDto.SimpleResponse question = null;
        AnswerDto.SimpleResponse answer = null;

        qnaCommentId = qnaComment.getQnaCommentId();
        content = qnaComment.getContent();
        createdAt = qnaComment.getCreatedAt();
        modifiedAt = qnaComment.getModifiedAt();
        voteCount = qnaComment.getVoteCount();
        question = questionToSimpleResponse( qnaComment.getQuestion() );
        answer = answerToSimpleResponse( qnaComment.getAnswer() );

        QnaCommentDto.SimpleResponse simpleResponse = new QnaCommentDto.SimpleResponse( qnaCommentId, content, createdAt, modifiedAt, voteCount, question, answer );

        return simpleResponse;
    }

    protected QnaReCommentDto.SimpleResponse qnaReCommentToSimpleResponse(QnaReComment qnaReComment) {
        if ( qnaReComment == null ) {
            return null;
        }

        QnaReCommentDto.SimpleResponse simpleResponse = new QnaReCommentDto.SimpleResponse();

        if ( qnaReComment.getQnaReCommentId() != null ) {
            simpleResponse.setQnaReCommentId( qnaReComment.getQnaReCommentId() );
        }
        simpleResponse.setContent( qnaReComment.getContent() );
        simpleResponse.setCreatedAt( qnaReComment.getCreatedAt() );
        simpleResponse.setModifiedAt( qnaReComment.getModifiedAt() );
        simpleResponse.setVoteCount( qnaReComment.getVoteCount() );
        simpleResponse.setQnaComment( qnaCommentToSimpleResponse( qnaReComment.getQnaComment() ) );

        return simpleResponse;
    }
}

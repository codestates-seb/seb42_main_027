package ynzmz.server.comment.qna.mapper;

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
public class QnaCommentMapperImpl implements QnaCommentMapper {

    @Override
    public QnaComment qnaCommentPostToQnaComment(QnaCommentDto.Post QnaCommentPostDto) {
        if ( QnaCommentPostDto == null ) {
            return null;
        }

        QnaComment qnaComment = new QnaComment();

        qnaComment.setContent( QnaCommentPostDto.getContent() );
        qnaComment.setCreatedAt( QnaCommentPostDto.getCreatedAt() );

        return qnaComment;
    }

    @Override
    public QnaComment qnaCommentPatchToQnaComment(QnaCommentDto.Patch QnaCommentPatchDto) {
        if ( QnaCommentPatchDto == null ) {
            return null;
        }

        QnaComment qnaComment = new QnaComment();

        qnaComment.setContent( QnaCommentPatchDto.getContent() );
        qnaComment.setModifiedAt( QnaCommentPatchDto.getModifiedAt() );

        return qnaComment;
    }

    @Override
    public QnaCommentDto.Response qnaCommentToQnaCommentResponse(QnaComment QnaComment) {
        if ( QnaComment == null ) {
            return null;
        }

        QnaCommentDto.Response response = new QnaCommentDto.Response();

        response.setQnaCommentId( QnaComment.getQnaCommentId() );
        response.setContent( QnaComment.getContent() );
        response.setCreatedAt( QnaComment.getCreatedAt() );
        response.setModifiedAt( QnaComment.getModifiedAt() );
        response.setVoteCount( QnaComment.getVoteCount() );
        response.setReCommentCount( QnaComment.getReCommentCount() );
        response.setMember( memberToSimpleInfoResponse( QnaComment.getMember() ) );
        response.setQnaReComments( qnaReCommentListToResponseList( QnaComment.getQnaReComments() ) );

        return response;
    }

    @Override
    public List<QnaCommentDto.SimpleResponse> qnaCommentsToQnaCommentSimpleResponses(List<QnaComment> qnaComments) {
        if ( qnaComments == null ) {
            return null;
        }

        List<QnaCommentDto.SimpleResponse> list = new ArrayList<QnaCommentDto.SimpleResponse>( qnaComments.size() );
        for ( QnaComment qnaComment : qnaComments ) {
            list.add( qnaCommentToSimpleResponse( qnaComment ) );
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

    protected QnaReCommentDto.Response qnaReCommentToResponse(QnaReComment qnaReComment) {
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

    protected List<QnaReCommentDto.Response> qnaReCommentListToResponseList(List<QnaReComment> list) {
        if ( list == null ) {
            return null;
        }

        List<QnaReCommentDto.Response> list1 = new ArrayList<QnaReCommentDto.Response>( list.size() );
        for ( QnaReComment qnaReComment : list ) {
            list1.add( qnaReCommentToResponse( qnaReComment ) );
        }

        return list1;
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
}

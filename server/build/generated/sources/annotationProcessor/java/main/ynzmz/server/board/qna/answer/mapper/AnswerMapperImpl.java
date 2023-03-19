package ynzmz.server.board.qna.answer.mapper;

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
import ynzmz.server.tag.dto.SubjectTagDto;
import ynzmz.server.tag.mappingtable.question.QuestionSubjectTag;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-18T14:09:58+0900",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.1.jar, environment: Java 11.0.17 (Azul Systems, Inc.)"
)
@Component
public class AnswerMapperImpl implements AnswerMapper {

    @Override
    public Answer answerPostDtoToAnswer(AnswerDto.Post answerPostDto) {
        if ( answerPostDto == null ) {
            return null;
        }

        Answer answer = new Answer();

        answer.setContent( answerPostDto.getContent() );
        answer.setCreatedAt( answerPostDto.getCreatedAt() );

        return answer;
    }

    @Override
    public Answer answerPatchDtoToAnswer(AnswerDto.Patch answerPatchDto) {
        if ( answerPatchDto == null ) {
            return null;
        }

        Answer answer = new Answer();

        answer.setContent( answerPatchDto.getContent() );
        answer.setModifiedAt( answerPatchDto.getModifiedAt() );

        return answer;
    }

    @Override
    public AnswerDto.SimpleInfoResponse answerToAnswerInfoResponse(Answer answer) {
        if ( answer == null ) {
            return null;
        }

        AnswerDto.SimpleInfoResponse simpleInfoResponse = new AnswerDto.SimpleInfoResponse();

        if ( answer.getAnswerId() != null ) {
            simpleInfoResponse.setAnswerId( answer.getAnswerId() );
        }
        simpleInfoResponse.setQuestion( questionToSimpleInfoResponse( answer.getQuestion() ) );
        simpleInfoResponse.setMember( memberToSimpleInfoResponse( answer.getMember() ) );
        simpleInfoResponse.setContent( answer.getContent() );
        simpleInfoResponse.setVoteCount( answer.getVoteCount() );
        simpleInfoResponse.setCreatedAt( answer.getCreatedAt() );
        simpleInfoResponse.setModifiedAt( answer.getModifiedAt() );
        simpleInfoResponse.setAdoptStatus( answer.getAdoptStatus() );

        return simpleInfoResponse;
    }

    @Override
    public List<AnswerDto.Response> answersToAnswerResponses(List<Answer> answers) {
        if ( answers == null ) {
            return null;
        }

        List<AnswerDto.Response> list = new ArrayList<AnswerDto.Response>( answers.size() );
        for ( Answer answer : answers ) {
            list.add( answerToResponse( answer ) );
        }

        return list;
    }

    @Override
    public List<AnswerDto.SimpleInfoResponse> answersToAnswerInfoResponses(List<Answer> answers) {
        if ( answers == null ) {
            return null;
        }

        List<AnswerDto.SimpleInfoResponse> list = new ArrayList<AnswerDto.SimpleInfoResponse>( answers.size() );
        for ( Answer answer : answers ) {
            list.add( answerToAnswerInfoResponse( answer ) );
        }

        return list;
    }

    protected MemberDto.SimpleInfoResponse memberToSimpleInfoResponse(Member member) {
        if ( member == null ) {
            return null;
        }

        Long memberId = null;
        String displayName = null;
        String state = null;

        memberId = member.getMemberId();
        displayName = member.getDisplayName();
        if ( member.getState() != null ) {
            state = member.getState().name();
        }

        String iconImageUrl = null;

        MemberDto.SimpleInfoResponse simpleInfoResponse = new MemberDto.SimpleInfoResponse( memberId, displayName, iconImageUrl, state );

        simpleInfoResponse.setIconImageUrl( member.getIconImageUrl() );

        return simpleInfoResponse;
    }

    protected SubjectTagDto.Response questionSubjectTagToResponse(QuestionSubjectTag questionSubjectTag) {
        if ( questionSubjectTag == null ) {
            return null;
        }

        SubjectTagDto.Response response = new SubjectTagDto.Response();

        response.setSubjectTag( map( questionSubjectTag.getSubjectTag() ) );

        return response;
    }

    protected List<SubjectTagDto.Response> questionSubjectTagListToResponseList(List<QuestionSubjectTag> list) {
        if ( list == null ) {
            return null;
        }

        List<SubjectTagDto.Response> list1 = new ArrayList<SubjectTagDto.Response>( list.size() );
        for ( QuestionSubjectTag questionSubjectTag : list ) {
            list1.add( questionSubjectTagToResponse( questionSubjectTag ) );
        }

        return list1;
    }

    protected QuestionDto.SimpleInfoResponse questionToSimpleInfoResponse(Question question) {
        if ( question == null ) {
            return null;
        }

        QuestionDto.SimpleInfoResponse simpleInfoResponse = new QuestionDto.SimpleInfoResponse();

        simpleInfoResponse.setQuestionId( question.getQuestionId() );
        simpleInfoResponse.setTitle( question.getTitle() );
        simpleInfoResponse.setMember( memberToSimpleInfoResponse( question.getMember() ) );
        simpleInfoResponse.setCreatedAt( question.getCreatedAt() );
        simpleInfoResponse.setModifiedAt( question.getModifiedAt() );
        simpleInfoResponse.setViewCount( question.getViewCount() );
        simpleInfoResponse.setVoteCount( question.getVoteCount() );
        simpleInfoResponse.setSubjectTags( questionSubjectTagListToResponseList( question.getSubjectTags() ) );

        return simpleInfoResponse;
    }

    protected QnaCommentDto.Response qnaCommentToResponse(QnaComment qnaComment) {
        if ( qnaComment == null ) {
            return null;
        }

        QnaCommentDto.Response response = new QnaCommentDto.Response();

        response.setQnaCommentId( qnaComment.getQnaCommentId() );
        response.setContent( qnaComment.getContent() );
        response.setCreatedAt( qnaComment.getCreatedAt() );
        response.setModifiedAt( qnaComment.getModifiedAt() );
        response.setVoteCount( qnaComment.getVoteCount() );
        response.setMember( memberToSimpleInfoResponse( qnaComment.getMember() ) );

        return response;
    }

    protected List<QnaCommentDto.Response> qnaCommentListToResponseList(List<QnaComment> list) {
        if ( list == null ) {
            return null;
        }

        List<QnaCommentDto.Response> list1 = new ArrayList<QnaCommentDto.Response>( list.size() );
        for ( QnaComment qnaComment : list ) {
            list1.add( qnaCommentToResponse( qnaComment ) );
        }

        return list1;
    }

    protected AnswerDto.Response answerToResponse(Answer answer) {
        if ( answer == null ) {
            return null;
        }

        AnswerDto.Response response = new AnswerDto.Response();

        response.setAnswerId( answer.getAnswerId() );
        response.setContent( answer.getContent() );
        response.setVoteCount( answer.getVoteCount() );
        response.setCreatedAt( answer.getCreatedAt() );
        response.setModifiedAt( answer.getModifiedAt() );
        response.setAdoptStatus( answer.getAdoptStatus() );
        response.setMember( memberToSimpleInfoResponse( answer.getMember() ) );
        response.setComments( qnaCommentListToResponseList( answer.getComments() ) );

        return response;
    }
}

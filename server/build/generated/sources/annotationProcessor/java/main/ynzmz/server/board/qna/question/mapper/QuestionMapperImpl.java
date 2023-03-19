package ynzmz.server.board.qna.question.mapper;

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
import ynzmz.server.tag.dto.SubjectTagDto;
import ynzmz.server.tag.mappingtable.question.QuestionSubjectTag;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-19T19:18:30+0900",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.1.jar, environment: Java 11.0.17 (Azul Systems, Inc.)"
)
@Component
public class QuestionMapperImpl implements QuestionMapper {

    @Override
    public Question questionPostToQuestion(QuestionDto.Post questionPostDto) {
        if ( questionPostDto == null ) {
            return null;
        }

        Question question = new Question();

        question.setQuestionId( questionPostDto.getQuestionId() );
        question.setTitle( questionPostDto.getTitle() );
        question.setContent( questionPostDto.getContent() );
        question.setCreatedAt( questionPostDto.getCreatedAt() );

        return question;
    }

    @Override
    public Question questionPatcToQuestion(QuestionDto.Patch questionPatchDto) {
        if ( questionPatchDto == null ) {
            return null;
        }

        Question question = new Question();

        question.setQuestionId( questionPatchDto.getQuestionId() );
        question.setTitle( questionPatchDto.getTitle() );
        question.setContent( questionPatchDto.getContent() );
        question.setModifiedAt( questionPatchDto.getModifiedAt() );

        return question;
    }

    @Override
    public QuestionDto.InfoResponse questionToQuestionInfoResponse(Question question) {
        if ( question == null ) {
            return null;
        }

        QuestionDto.InfoResponse infoResponse = new QuestionDto.InfoResponse();

        infoResponse.setQuestionId( question.getQuestionId() );
        infoResponse.setMember( memberToSimpleInfoResponse( question.getMember() ) );
        infoResponse.setTitle( question.getTitle() );
        infoResponse.setContent( question.getContent() );
        infoResponse.setCreatedAt( question.getCreatedAt() );
        infoResponse.setModifiedAt( question.getModifiedAt() );
        infoResponse.setViewCount( question.getViewCount() );
        infoResponse.setVoteCount( question.getVoteCount() );
        infoResponse.setSubjectTags( questionSubjectTagListToResponseList( question.getSubjectTags() ) );

        return infoResponse;
    }

    @Override
    public QuestionDto.DetailPageResponse questionToQuestionDetailPageResponse(Question question) {
        if ( question == null ) {
            return null;
        }

        QuestionDto.DetailPageResponse detailPageResponse = new QuestionDto.DetailPageResponse();

        detailPageResponse.setAnswerCount( countAnswers( question.getAnswers() ) );
        detailPageResponse.setQuestionId( question.getQuestionId() );
        detailPageResponse.setMember( memberToSimpleInfoResponse( question.getMember() ) );
        detailPageResponse.setTitle( question.getTitle() );
        detailPageResponse.setContent( question.getContent() );
        detailPageResponse.setCreatedAt( question.getCreatedAt() );
        detailPageResponse.setModifiedAt( question.getModifiedAt() );
        detailPageResponse.setViewCount( question.getViewCount() );
        detailPageResponse.setVoteCount( question.getVoteCount() );
        detailPageResponse.setSubjectTags( questionSubjectTagListToResponseList( question.getSubjectTags() ) );
        detailPageResponse.setAnswers( answerListToResponseList( question.getAnswers() ) );
        detailPageResponse.setComments( qnaCommentListToResponseList( question.getComments() ) );

        return detailPageResponse;
    }

    @Override
    public QuestionDto.ListPageResponse questionToQuestionListPageResponse(Question question) {
        if ( question == null ) {
            return null;
        }

        QuestionDto.ListPageResponse listPageResponse = new QuestionDto.ListPageResponse();

        listPageResponse.setAnswerCount( countAnswers( question.getAnswers() ) );
        listPageResponse.setQuestionId( question.getQuestionId() );
        listPageResponse.setTitle( question.getTitle() );
        listPageResponse.setContent( question.getContent() );
        listPageResponse.setCreatedAt( question.getCreatedAt() );
        listPageResponse.setModifiedAt( question.getModifiedAt() );
        listPageResponse.setViewCount( question.getViewCount() );
        listPageResponse.setVoteCount( question.getVoteCount() );
        listPageResponse.setAdoptAnswerId( question.getAdoptAnswerId() );
        listPageResponse.setMember( memberToSimpleInfoResponse( question.getMember() ) );
        listPageResponse.setSubjectTags( questionSubjectTagListToResponseList( question.getSubjectTags() ) );

        return listPageResponse;
    }

    @Override
    public List<QuestionDto.ListPageResponse> questionToQuestionListPageResponses(List<Question> questions) {
        if ( questions == null ) {
            return null;
        }

        List<QuestionDto.ListPageResponse> list = new ArrayList<QuestionDto.ListPageResponse>( questions.size() );
        for ( Question question : questions ) {
            list.add( questionToQuestionListPageResponse( question ) );
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

    protected List<SubjectTagDto.Response> questionSubjectTagListToResponseList(List<QuestionSubjectTag> list) {
        if ( list == null ) {
            return null;
        }

        List<SubjectTagDto.Response> list1 = new ArrayList<SubjectTagDto.Response>( list.size() );
        for ( QuestionSubjectTag questionSubjectTag : list ) {
            list1.add( subjectTagResponseToQuestionSubjectTag( questionSubjectTag ) );
        }

        return list1;
    }

    protected QnaReCommentDto.Response qnaReCommentToResponse(QnaReComment qnaReComment) {
        if ( qnaReComment == null ) {
            return null;
        }

        QnaReCommentDto.Response response = new QnaReCommentDto.Response();

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
        response.setQnaReComments( qnaReCommentListToResponseList( qnaComment.getQnaReComments() ) );

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

    protected List<AnswerDto.Response> answerListToResponseList(List<Answer> list) {
        if ( list == null ) {
            return null;
        }

        List<AnswerDto.Response> list1 = new ArrayList<AnswerDto.Response>( list.size() );
        for ( Answer answer : list ) {
            list1.add( answerToResponse( answer ) );
        }

        return list1;
    }
}

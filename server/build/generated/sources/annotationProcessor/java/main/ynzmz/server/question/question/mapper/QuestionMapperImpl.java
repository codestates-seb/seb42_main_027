package ynzmz.server.question.question.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ynzmz.server.question.answer.dto.AnswerDto;
import ynzmz.server.question.answer.entity.Answer;
import ynzmz.server.question.question.dto.QuestionDto;
import ynzmz.server.question.question.entity.Question;
import ynzmz.server.tag.dto.SubjectTagDto;
import ynzmz.server.tag.mappingtable.question.QuestionSubjectTag;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-17T14:08:15+0900",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.1.jar, environment: Java 11.0.17 (Azul Systems, Inc.)"
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
        question.setCreatedAt( questionPatchDto.getCreatedAt() );

        return question;
    }

    @Override
    public QuestionDto.InfoResponse questionToQuestionInfoResponse(Question question) {
        if ( question == null ) {
            return null;
        }

        QuestionDto.InfoResponse infoResponse = new QuestionDto.InfoResponse();

        infoResponse.setQuestionId( question.getQuestionId() );
        infoResponse.setMember( question.getMember() );
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
        detailPageResponse.setMember( question.getMember() );
        detailPageResponse.setTitle( question.getTitle() );
        detailPageResponse.setContent( question.getContent() );
        detailPageResponse.setCreatedAt( question.getCreatedAt() );
        detailPageResponse.setModifiedAt( question.getModifiedAt() );
        detailPageResponse.setViewCount( question.getViewCount() );
        detailPageResponse.setVoteCount( question.getVoteCount() );
        detailPageResponse.setSubjectTags( questionSubjectTagListToResponseList( question.getSubjectTags() ) );
        detailPageResponse.setAnswers( answerListToInfoResponseList( question.getAnswers() ) );

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
        listPageResponse.setMember( question.getMember() );
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

    protected AnswerDto.InfoResponse answerToInfoResponse(Answer answer) {
        if ( answer == null ) {
            return null;
        }

        AnswerDto.InfoResponse infoResponse = new AnswerDto.InfoResponse();

        infoResponse.setAnswerId( answer.getAnswerId() );
        infoResponse.setMember( answer.getMember() );
        infoResponse.setContent( answer.getContent() );
        infoResponse.setVoteCount( (int) answer.getVoteCount() );
        infoResponse.setCreatedAt( answer.getCreatedAt() );
        infoResponse.setModifiedAt( answer.getModifiedAt() );
        infoResponse.setAdoptStatus( answer.getAdoptStatus() );

        return infoResponse;
    }

    protected List<AnswerDto.InfoResponse> answerListToInfoResponseList(List<Answer> list) {
        if ( list == null ) {
            return null;
        }

        List<AnswerDto.InfoResponse> list1 = new ArrayList<AnswerDto.InfoResponse>( list.size() );
        for ( Answer answer : list ) {
            list1.add( answerToInfoResponse( answer ) );
        }

        return list1;
    }
}

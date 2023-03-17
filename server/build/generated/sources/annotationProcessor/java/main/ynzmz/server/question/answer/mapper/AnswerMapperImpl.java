package ynzmz.server.question.answer.mapper;

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

        answer.setAnswerId( answerPatchDto.getAnswerId() );
        answer.setContent( answerPatchDto.getContent() );

        return answer;
    }

    @Override
    public AnswerDto.InfoResponse answerToAnswerInfoResponse(Answer answer) {
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
    public List<AnswerDto.InfoResponse> answersToAnswerInfoResponses(List<Answer> answers) {
        if ( answers == null ) {
            return null;
        }

        List<AnswerDto.InfoResponse> list = new ArrayList<AnswerDto.InfoResponse>( answers.size() );
        for ( Answer answer : answers ) {
            list.add( answerToAnswerInfoResponse( answer ) );
        }

        return list;
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

    protected QuestionDto.ListPageResponse questionToListPageResponse(Question question) {
        if ( question == null ) {
            return null;
        }

        QuestionDto.ListPageResponse listPageResponse = new QuestionDto.ListPageResponse();

        listPageResponse.setQuestionId( question.getQuestionId() );
        listPageResponse.setTitle( question.getTitle() );
        listPageResponse.setContent( question.getContent() );
        listPageResponse.setCreatedAt( question.getCreatedAt() );
        listPageResponse.setModifiedAt( question.getModifiedAt() );
        listPageResponse.setViewCount( question.getViewCount() );
        listPageResponse.setVoteCount( question.getVoteCount() );
        listPageResponse.setAnswerCount( question.getAnswerCount() );
        listPageResponse.setAdoptAnswerId( question.getAdoptAnswerId() );
        listPageResponse.setMember( question.getMember() );
        listPageResponse.setSubjectTags( questionSubjectTagListToResponseList( question.getSubjectTags() ) );

        return listPageResponse;
    }

    protected AnswerDto.Response answerToResponse(Answer answer) {
        if ( answer == null ) {
            return null;
        }

        AnswerDto.Response response = new AnswerDto.Response();

        response.setAnswerId( answer.getAnswerId() );
        response.setContent( answer.getContent() );
        response.setVoteCount( (int) answer.getVoteCount() );
        response.setCreatedAt( answer.getCreatedAt() );
        response.setModifiedAt( answer.getModifiedAt() );
        response.setAdoptStatus( answer.getAdoptStatus() );
        response.setMember( answer.getMember() );
        response.setQuestion( questionToListPageResponse( answer.getQuestion() ) );

        return response;
    }
}

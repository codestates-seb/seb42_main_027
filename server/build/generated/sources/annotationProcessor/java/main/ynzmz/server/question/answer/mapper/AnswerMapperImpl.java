package ynzmz.server.question.answer.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ynzmz.server.question.answer.dto.AnswerInfoResponseDto;
import ynzmz.server.question.answer.dto.AnswerPatchDto;
import ynzmz.server.question.answer.dto.AnswerPostDto;
import ynzmz.server.question.answer.dto.AnswerResponseDto;
import ynzmz.server.question.answer.entity.Answer;
import ynzmz.server.question.question.dto.QuestionDto;
import ynzmz.server.question.question.entity.Question;
import ynzmz.server.tag.dto.SubjectTagDto;
import ynzmz.server.tag.mappingtable.question.QuestionSubjectTag;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-17T11:53:59+0900",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.1.jar, environment: Java 11.0.17 (Azul Systems, Inc.)"
)
@Component
public class AnswerMapperImpl implements AnswerMapper {

    @Override
    public Answer answerPostDtoToAnswer(AnswerPostDto answerPostDto) {
        if ( answerPostDto == null ) {
            return null;
        }

        Answer answer = new Answer();

        answer.setContent( answerPostDto.getContent() );

        return answer;
    }

    @Override
    public Answer answerPatchDtoToAnswer(AnswerPatchDto answerPatchDto) {
        if ( answerPatchDto == null ) {
            return null;
        }

        Answer answer = new Answer();

        answer.setAnswerId( answerPatchDto.getAnswerId() );
        answer.setContent( answerPatchDto.getContent() );

        return answer;
    }

    @Override
    public AnswerInfoResponseDto answerToAnswerInfoResponse(Answer answer) {
        if ( answer == null ) {
            return null;
        }

        AnswerInfoResponseDto.AnswerInfoResponseDtoBuilder answerInfoResponseDto = AnswerInfoResponseDto.builder();

        answerInfoResponseDto.answerId( answer.getAnswerId() );
        answerInfoResponseDto.member( answer.getMember() );
        answerInfoResponseDto.content( answer.getContent() );
        answerInfoResponseDto.voteCount( (int) answer.getVoteCount() );
        answerInfoResponseDto.createdAt( answer.getCreatedAt() );
        answerInfoResponseDto.modifiedAt( answer.getModifiedAt() );
        answerInfoResponseDto.adoptStatus( answer.getAdoptStatus() );

        return answerInfoResponseDto.build();
    }

    @Override
    public List<AnswerResponseDto> answersToAnswerResponses(List<Answer> answers) {
        if ( answers == null ) {
            return null;
        }

        List<AnswerResponseDto> list = new ArrayList<AnswerResponseDto>( answers.size() );
        for ( Answer answer : answers ) {
            list.add( answerToAnswerResponseDto( answer ) );
        }

        return list;
    }

    @Override
    public List<AnswerInfoResponseDto> answerToAnswerResponses(List<Answer> answers) {
        if ( answers == null ) {
            return null;
        }

        List<AnswerInfoResponseDto> list = new ArrayList<AnswerInfoResponseDto>( answers.size() );
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

    protected AnswerResponseDto answerToAnswerResponseDto(Answer answer) {
        if ( answer == null ) {
            return null;
        }

        AnswerResponseDto answerResponseDto = new AnswerResponseDto();

        answerResponseDto.setAnswerId( answer.getAnswerId() );
        answerResponseDto.setContent( answer.getContent() );
        answerResponseDto.setVoteCount( (int) answer.getVoteCount() );
        answerResponseDto.setCreatedAt( answer.getCreatedAt() );
        answerResponseDto.setModifiedAt( answer.getModifiedAt() );
        answerResponseDto.setAdoptStatus( answer.getAdoptStatus() );
        answerResponseDto.setMember( answer.getMember() );
        answerResponseDto.setQuestion( questionToListPageResponse( answer.getQuestion() ) );

        return answerResponseDto;
    }
}

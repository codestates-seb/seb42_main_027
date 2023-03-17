package ynzmz.server.board.qna.answer.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ynzmz.server.board.qna.answer.dto.AnswerDto;
import ynzmz.server.board.qna.answer.entity.Answer;
import ynzmz.server.board.qna.question.dto.QuestionDto;
import ynzmz.server.board.qna.question.entity.Question;
import ynzmz.server.member.dto.MemberDto;
import ynzmz.server.member.entity.Member;
import ynzmz.server.tag.dto.SubjectTagDto;
import ynzmz.server.tag.mappingtable.question.QuestionSubjectTag;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-17T18:24:25+0900",
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

        return answer;
    }

    @Override
    public AnswerDto.SimpleInfoResponse answerToAnswerInfoResponse(Answer answer) {
        if ( answer == null ) {
            return null;
        }

        AnswerDto.SimpleInfoResponse simpleInfoResponse = new AnswerDto.SimpleInfoResponse();

        simpleInfoResponse.setAnswerId( answer.getAnswerId() );
        simpleInfoResponse.setMember( answer.getMember() );
        simpleInfoResponse.setContent( answer.getContent() );
        simpleInfoResponse.setVoteCount( (int) answer.getVoteCount() );
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
        response.setMember( memberToSimpleInfoResponse( answer.getMember() ) );
        response.setQuestion( questionToListPageResponse( answer.getQuestion() ) );

        return response;
    }
}

package ynzmz.server.board.qna.answer.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import ynzmz.server.board.qna.question.entity.Question;
import ynzmz.server.board.qna.answer.dto.AnswerDto;
import ynzmz.server.board.qna.answer.entity.Answer;
import ynzmz.server.tag.dto.SubjectTagDto;
import ynzmz.server.tag.entity.SubjectTag;
import ynzmz.server.tag.mappingtable.teacher.TeacherSubjectTag;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AnswerMapper {
    Answer answerPostDtoToAnswer(AnswerDto.Post answerPostDto);
    Answer answerPatchDtoToAnswer(AnswerDto.Patch answerPatchDto);
//    @Mapping(source = "question", target = "questionId", qualifiedByName = "setQuestionId")
//    @Mapping(source = "member.questions", target = "member.myQuestionCount", qualifiedByName = "countQuestions")
//    @Mapping(source = "member.answers", target = "member.myAnswerCount", qualifiedByName = "countAnswers")
    AnswerDto.SimpleInfoResponse answerToAnswerInfoResponse(Answer answer);
    @Named("countQuestions")
    default long countQuestions(List<Question> questions) { return questions.size(); }
    @Named("countAnswers")
    default long countAnswers(List<Answer> answers) { return answers.size(); }
    @Named("setQuestionId")
    default long setQuestionId(Question question) { return question.getQuestionId(); }

    List<AnswerDto.Response> answersToAnswerResponses(List<Answer> answers);
    List<AnswerDto.SimpleInfoResponse> answersToAnswerInfoResponses(List<Answer> answers);

    default SubjectTag.Subject map(SubjectTag subjectTag) {
        return subjectTag.getSubject();
    }

    default SubjectTagDto.Response teacherSubjectTagToSubjectTagResponse(TeacherSubjectTag teacherSubjectTag) {
        if ( teacherSubjectTag == null ) return null;

        SubjectTagDto.Response response = new SubjectTagDto.Response();

        response.setSubjectTag( teacherSubjectTag.getSubjectTag().getSubject() );

        return response;
    }
}

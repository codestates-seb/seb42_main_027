package ynzmz.server.question.answer.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import ynzmz.server.question.answer.dto.AnswerInfoResponseDto;
import ynzmz.server.question.answer.dto.AnswerPatchDto;
import ynzmz.server.question.answer.dto.AnswerPostDto;
import ynzmz.server.question.answer.dto.AnswerResponseDto;
import ynzmz.server.question.answer.entity.Answer;
import ynzmz.server.question.question.entity.Question;
import ynzmz.server.tag.dto.SubjectTagDto;
import ynzmz.server.tag.entity.SubjectTag;
import ynzmz.server.tag.mappingtable.teacher.TeacherSubjectTag;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AnswerMapper {
    Answer answerPostDtoToAnswer(AnswerPostDto answerPostDto);
    Answer answerPatchDtoToAnswer(AnswerPatchDto answerPatchDto);
//    @Mapping(source = "question", target = "questionId", qualifiedByName = "setQuestionId")
//    @Mapping(source = "member.questions", target = "member.myQuestionCount", qualifiedByName = "countQuestions")
//    @Mapping(source = "member.answers", target = "member.myAnswerCount", qualifiedByName = "countAnswers")
    AnswerInfoResponseDto answerToAnswerInfoResponse(Answer answer);
    @Named("countQuestions")
    default long countQuestions(List<Question> questions) { return questions.size(); }
    @Named("countAnswers")
    default long countAnswers(List<Answer> answers) { return answers.size(); }
    @Named("setQuestionId")
    default long setQuestionId(Question question) { return question.getQuestionId(); }

    List<AnswerResponseDto> answersToAnswerResponses(List<Answer> answers);
    List<AnswerInfoResponseDto> answerToAnswerResponses(List<Answer> answers);

    default SubjectTag.Subject map(SubjectTag subjectTag) {
        return subjectTag.getSubject();
    }

    default SubjectTagDto.Response subjectTagResponseToTeacherSubjectTag(TeacherSubjectTag teacherSubjectTag) {
        if ( teacherSubjectTag == null ) return null;

        SubjectTagDto.Response response = new SubjectTagDto.Response();

        response.setSubjectTag( teacherSubjectTag.getSubjectTag().getSubject() );

        return response;
    }
}

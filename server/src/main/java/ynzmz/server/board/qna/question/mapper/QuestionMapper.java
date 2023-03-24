package ynzmz.server.board.qna.question.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import ynzmz.server.board.qna.answer.entity.Answer;
import ynzmz.server.board.qna.question.dto.QuestionDto;
import ynzmz.server.board.qna.question.entity.Question;
import ynzmz.server.tag.dto.SubjectTagDto;

import java.util.List;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface QuestionMapper {
    Question questionPostToQuestion(QuestionDto.Post questionPostDto);
    Question questionPatcToQuestion(QuestionDto.Patch questionPatchDto);
    QuestionDto.InfoResponse questionToQuestionInfoResponse(Question question);
    @Mapping(source = "answers", target = "answerCount", qualifiedByName = "countAnswers") // 수동구현 대신하는 신기술
    QuestionDto.DetailPageResponse questionToQuestionDetailPageResponse(Question question);

    // source = 주입받는곳의 변수명 , target = 변환될곳의 변수명, qualifiedByName = 등록할 로직 메서드명
    @Mapping(source = "answers", target = "answerCount", qualifiedByName = "countAnswers")
//    @Mapping(source = "member.questions", target = "member.myQuestionCount", qualifiedByName = "countQuestions")// 수동구현 대신하는 신기술
//    @Mapping(source = "member.answers", target = "member.myAnswerCount", qualifiedByName = "countAnswers")// 수동구현 대신하는 신기술
    QuestionDto.ListPageResponse questionToQuestionListPageResponse(Question question);
    @Named("countQuestions")
    default long countQuestions(List<Question> questions) { return questions.size(); }
    @Named("countAnswers")
    default long countAnswers(List<Answer> answers) { return answers.size(); }
    List<QuestionDto.ListPageResponse> questionToQuestionListPageResponses(List<Question> questions);

}

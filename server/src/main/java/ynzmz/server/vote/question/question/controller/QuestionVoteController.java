package ynzmz.server.vote.question.question.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ynzmz.server.dto.SingleResponseDto;
import ynzmz.server.question.question.entity.Question;
import ynzmz.server.question.question.service.QuestionService;
import ynzmz.server.vote.question.question.dto.QuestionVoteResponseDto;
import ynzmz.server.vote.question.question.entity.QuestionVote;
import ynzmz.server.vote.question.question.mapper.QuestionVoteMapper;
import ynzmz.server.vote.question.question.service.QuestionVoteService;

@RestController
@RequestMapping("/question-vote")
@RequiredArgsConstructor
@Validated
public class QuestionVoteController {
    private final QuestionVoteService questionVoteService;
    private final QuestionService questionService;
    private final QuestionVoteMapper questionVoteMapper;

    @PostMapping("/{question-id}/up")
    public ResponseEntity<?> questionVoteUpPost(@PathVariable("question-id") long questionId) {
        QuestionVote questionVote = questionVoteService.questionVoteUp(questionId);
        Question question = questionService.findQuestion(questionVote.getQuestion().getQuestionId());
        QuestionVoteResponseDto response = questionVoteMapper.questionVoteToQuestionResponseDto(questionVote, question);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    @PostMapping("/{question-id}/down")
    public ResponseEntity<?> questionVoteDownPost(@PathVariable("question-id") long questionId) {
        QuestionVote questionVote = questionVoteService.questionVoteDown(questionId);
        Question question = questionService.findQuestion(questionVote.getQuestion().getQuestionId());
        QuestionVoteResponseDto response = questionVoteMapper.questionVoteToQuestionResponseDto(questionVote, question);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }


}

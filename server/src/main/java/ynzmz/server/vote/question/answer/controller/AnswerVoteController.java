package ynzmz.server.vote.question.answer.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ynzmz.server.dto.SingleResponseDto;
import ynzmz.server.question.answer.entity.Answer;
import ynzmz.server.question.answer.service.AnswerService;
import ynzmz.server.vote.question.answer.dto.AnswerVoteResponseDto;
import ynzmz.server.vote.question.answer.entity.AnswerVote;
import ynzmz.server.vote.question.answer.mapper.AnswerVoteMapper;
import ynzmz.server.vote.question.answer.service.AnswerVoteService;

@RestController
@RequestMapping("/answer-vote")
@RequiredArgsConstructor
public class AnswerVoteController {
    private final AnswerVoteService answerVoteService;
    private final AnswerService answerService;
    private final AnswerVoteMapper answerVoteMapper;
    //추천수
    @PostMapping("/{answer-id}/up")
    public ResponseEntity<?> answerVoteUpPost(@PathVariable("answer-id") long answerId) {
        AnswerVote answerVote = answerVoteService.answerVoteUp(answerId);
        Answer answer = answerService.findAnswer(answerVote.getAnswer().getAnswerId());
        AnswerVoteResponseDto response = answerVoteMapper.answerVoteToAnswerResponseDto(answerVote, answer);
        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    @PostMapping("/{answer-id}/down")
    public ResponseEntity<?> answerVoteDownPost(@PathVariable("answer-id") long answerId) {
        AnswerVote answerVote = answerVoteService.answerVoteDown(answerId);
        Answer answer = answerService.findAnswer(answerVote.getAnswer().getAnswerId());
        AnswerVoteResponseDto response = answerVoteMapper.answerVoteToAnswerResponseDto(answerVote, answer);
        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }
}

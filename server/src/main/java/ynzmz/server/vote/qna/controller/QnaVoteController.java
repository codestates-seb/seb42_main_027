package ynzmz.server.vote.qna.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ynzmz.server.board.qna.answer.entity.Answer;
import ynzmz.server.board.qna.answer.service.AnswerService;
import ynzmz.server.comment.qna.entity.QnaComment;
import ynzmz.server.comment.qna.service.QnaCommentService;
import ynzmz.server.global.dto.SingleResponseDto;
import ynzmz.server.board.qna.question.entity.Question;
import ynzmz.server.board.qna.question.service.QuestionService;
import ynzmz.server.member.entity.Member;
import ynzmz.server.member.service.MemberService;
import ynzmz.server.recomment.qna.entity.QnaReComment;
import ynzmz.server.recomment.qna.service.QnaReCommentService;
import ynzmz.server.vote.qna.dto.QnaVoteDto;
import ynzmz.server.vote.qna.entity.QnaVote;
import ynzmz.server.vote.qna.mapper.QnaVoteMapper;
import ynzmz.server.vote.qna.service.QnaVoteService;

@RestController
@RequestMapping("/votes/qnas")
@RequiredArgsConstructor
@Validated
public class QnaVoteController {
    private final QnaVoteService qnaVoteService;
    private final QuestionService questionService;
    public final AnswerService answerService;
    private final QnaCommentService qnaCommentService;
    private final QnaReCommentService qnaReCommentService;
    private final QnaVoteMapper qnaVoteMapper;
    private final MemberService memberService;

    @PostMapping("questions/{question-id}/up")
    public ResponseEntity<?> questionVoteUpPost(@PathVariable("question-id") long questionId) {

        Question question = questionService.findQuestionById(questionId);
        Member member = loginMemberFindByToken();
        QnaVote qnaVote = qnaVoteService.findQnaVoteTargetQuestion(question, member); // 현재 상태값 불러오기

        QnaVote voteUp = qnaVoteService.qnaVoteUp(question, qnaVote);

        QnaVoteDto.QuestionResponse response = qnaVoteMapper.qnaVoteToQuestionResponse(voteUp);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    @PostMapping("questions/{question-id}/down")
    public ResponseEntity<?> questionVoteDownPost(@PathVariable("question-id") long questionId) {

        Question question = questionService.findQuestionById(questionId);
        Member member = loginMemberFindByToken();

        QnaVote qnaVote = qnaVoteService.findQnaVoteTargetQuestion(question, member); // 현재 상태값 불러오기
        QnaVote voteDown = qnaVoteService.qnaVoteDown(question, qnaVote);

        QnaVoteDto.QuestionResponse response = qnaVoteMapper.qnaVoteToQuestionResponse(voteDown);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }
    @PostMapping("answers/{answer-id}/up")
    public ResponseEntity<?> answerVoteUpPost(@PathVariable("answer-id") long answerId) {

        Answer answer = answerService.findAnswerById(answerId);
        Member member = loginMemberFindByToken();

        QnaVote qnaVote = qnaVoteService.findQnaVoteTargetAnswer(answer, member); // 현재 상태값 불러오기
        QnaVote voteUp = qnaVoteService.qnaVoteUp(answer, qnaVote);

        QnaVoteDto.AnswerResponse response = qnaVoteMapper.qnaVoteToAnswerResponse(voteUp);
        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    @PostMapping("answers/{answer-id}/down")
    public ResponseEntity<?> answerVoteDownPost(@PathVariable("answer-id") long answerId) {
        Answer answer = answerService.findAnswerById(answerId);
        Member member = loginMemberFindByToken();

        QnaVote qnaVote = qnaVoteService.findQnaVoteTargetAnswer(answer, member); // 현재 상태값 불러오기
        QnaVote voteDown = qnaVoteService.qnaVoteDown(answer, qnaVote);

        QnaVoteDto.AnswerResponse response = qnaVoteMapper.qnaVoteToAnswerResponse(voteDown);
        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    @PostMapping("comments/{qna-comment-id}/up")
    public ResponseEntity<?> qnaCommentVoteUpPost(@PathVariable("qna-comment-id") long qnaCommentId) {

        QnaComment qnaComment = qnaCommentService.findQnaCommentById(qnaCommentId);
        Member member = loginMemberFindByToken();

        QnaVote qnaVote = qnaVoteService.findQnaVoteTargetComment(qnaComment, member); // 현재 상태값 불러오기
        QnaVote voteUp = qnaVoteService.qnaVoteUp(qnaComment, qnaVote);

        QnaVoteDto.CommentResponse response = qnaVoteMapper.qnaVoteToCommentResponse(voteUp);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    @PostMapping("comments/{qna-comment-id}/down")
    public ResponseEntity<?> qnaCommentVoteDownPost(@PathVariable("qna-comment-id") long qnaCommentId) {

        QnaComment qnaComment = qnaCommentService.findQnaCommentById(qnaCommentId);
        Member member = loginMemberFindByToken();

        QnaVote qnaVote = qnaVoteService.findQnaVoteTargetComment(qnaComment, member); // 현재 상태값 불러오기
        QnaVote voteDown = qnaVoteService.qnaVoteDown(qnaComment, qnaVote);

        QnaVoteDto.CommentResponse response = qnaVoteMapper.qnaVoteToCommentResponse(voteDown);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    @PostMapping("recomments/{qna-recomment-id}/up")
    public ResponseEntity<?> qnaReCommentVoteUpPost(@PathVariable("qna-recomment-id") long qnaReCommentId) {

        QnaReComment qnaReComment = qnaReCommentService.findQnaReCommentById(qnaReCommentId);
        Member member = loginMemberFindByToken();

        QnaVote qnaVote = qnaVoteService.findQnaVoteTargetReComment(qnaReComment, member); // 현재 상태값 불러오기
        QnaVote voteUp = qnaVoteService.qnaVoteUp(qnaReComment, qnaVote);

        QnaVoteDto.ReCommentResponse response = qnaVoteMapper.qnaVoteToReCommentResponse(voteUp);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    @PostMapping("recomments/{qna-recomment-id}/down")
    public ResponseEntity<?> qnaReCommentVoteDownPost(@PathVariable("qna-recomment-id") long qnaReCommentId) {

        QnaReComment qnaReComment = qnaReCommentService.findQnaReCommentById(qnaReCommentId);
        Member member = loginMemberFindByToken();

        QnaVote qnaVote = qnaVoteService.findQnaVoteTargetReComment(qnaReComment, member); // 현재 상태값 불러오기
        QnaVote voteDown = qnaVoteService.qnaVoteDown(qnaReComment, qnaVote);

        QnaVoteDto.ReCommentResponse response = qnaVoteMapper.qnaVoteToReCommentResponse(voteDown);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    private Member loginMemberFindByToken(){
        String loginEmail = SecurityContextHolder.getContext().getAuthentication().getName(); // 토큰에서 유저 email 확인
        return memberService.findMemberByEmail(loginEmail);
    }
}

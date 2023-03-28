package ynzmz.server.comment.qna.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ynzmz.server.board.qna.answer.entity.Answer;
import ynzmz.server.board.qna.answer.service.AnswerService;
import ynzmz.server.board.qna.question.entity.Question;
import ynzmz.server.board.qna.question.service.QuestionService;
import ynzmz.server.comment.qna.dto.QnaCommentDto;
import ynzmz.server.comment.qna.entity.QnaComment;
import ynzmz.server.comment.qna.mapper.QnaCommentMapper;
import ynzmz.server.comment.qna.service.QnaCommentService;
import ynzmz.server.global.dto.SingleResponseDto;
import ynzmz.server.member.entity.Member;
import ynzmz.server.member.service.MemberService;

import java.util.Optional;

@RestController
@RequestMapping("/comments/qnas")
@RequiredArgsConstructor
public class QnaCommentController {
    private final MemberService memberService;
    private final QnaCommentMapper qnaCommentMapper;
    private final QnaCommentService qnaCommentService;
    private final QuestionService questionService;
    private final AnswerService answerService;
    @PostMapping("/questions/{question-id}")
    public ResponseEntity<?> createQuestionComment(@RequestBody QnaCommentDto.Post postDto,
                                                   @PathVariable("question-id") long questionId) {
        QnaComment qnaComment = qnaCommentMapper.qnaCommentPostToQnaComment(postDto);
        Question question = questionService.findQuestionById(questionId);
        //토큰에서 memberId 확인
        qnaComment.setMember(loginMemberFindByToken());
        qnaComment.setQuestion(question);
        qnaComment.setTarget(QnaComment.Target.Question);
        question.setCommentCount(question.getComments().size() + 1);

        QnaComment createComment = qnaCommentService.createQnaComment(qnaComment);
        QnaCommentDto.Response response = qnaCommentMapper.qnaCommentToQnaCommentResponse(createComment);
        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.CREATED);
    }

    @PostMapping("/answers/{answer-id}")
    public ResponseEntity<?> createAnswerComment(@RequestBody QnaCommentDto.Post postDto,
                                                   @PathVariable("answer-id") long answerId) {
        QnaComment qnaComment = qnaCommentMapper.qnaCommentPostToQnaComment(postDto);
        Answer answer = answerService.findAnswerById(answerId);
        //토큰에서 memberId 확인
        qnaComment.setMember(loginMemberFindByToken());
        qnaComment.setAnswer(answer);
        qnaComment.setTarget(QnaComment.Target.Answer);
        answer.setCommentCount(answer.getComments().size() + 1);

        QnaComment createComment = qnaCommentService.createQnaComment(qnaComment);
        QnaCommentDto.Response response = qnaCommentMapper.qnaCommentToQnaCommentResponse(createComment);
        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.CREATED);
    }

    @PatchMapping("/{qna-comment-id}")
    public ResponseEntity<?> updateQnaComment(@RequestBody QnaCommentDto.Patch patchDto,
                                              @PathVariable("qna-comment-id") long qnaCommentId) {
        //본인확인
        memberService.memberValidation(loginMemberFindByToken(), qnaCommentService.findQnaCommentById(qnaCommentId).getMember().getMemberId());

        QnaComment qnaComment = qnaCommentMapper.qnaCommentPatchToQnaComment(patchDto);
        qnaComment.setQnaCommentId(qnaCommentId);

        QnaComment updateComment = qnaCommentService.updateQnaComment(qnaComment);
        QnaCommentDto.Response response = qnaCommentMapper.qnaCommentToQnaCommentResponse(updateComment);
        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    @DeleteMapping("/{qna-comment-id}")
    public ResponseEntity<?> deleteQnaComment(@PathVariable("qna-comment-id") long qnaCommentId) {
        memberService.memberValidation(loginMemberFindByToken(), qnaCommentService.findQnaCommentById(qnaCommentId).getMember().getMemberId());

        QnaComment qnaComment = qnaCommentService.findQnaCommentById(qnaCommentId);
        if(qnaComment.getTarget() == QnaComment.Target.Question) {
            Question question = qnaComment.getQuestion();
            question.setCommentCount(question.getComments().size() - 1);
        } else {
            Answer answer = qnaComment.getAnswer();
            answer.setCommentCount(answer.getComments().size() -1 );
        }

        qnaCommentService.deleteQnaComment(qnaCommentId);

        Optional<QnaComment> deletedQnaComment = qnaCommentService.findOptionalQnaCommentById(qnaCommentId);
        return deletedQnaComment.isEmpty() ? new ResponseEntity<>("삭제완료",HttpStatus.OK) : new ResponseEntity<>("삭제실패",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private Member loginMemberFindByToken(){
        String loginEmail = SecurityContextHolder.getContext().getAuthentication().getName(); // 토큰에서 유저 email 확인
        return memberService.findMemberByEmail(loginEmail);
    }
}

package ynzmz.server.comment.qna.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ynzmz.server.board.qna.answer.service.AnswerService;
import ynzmz.server.board.qna.question.service.QuestionService;
import ynzmz.server.comment.qna.dto.QnaCommentDto;
import ynzmz.server.comment.qna.entity.QnaComment;
import ynzmz.server.comment.qna.mapper.QnaCommentMapper;
import ynzmz.server.comment.qna.service.QnaCommentService;
import ynzmz.server.dto.SingleResponseDto;
import ynzmz.server.member.entity.Member;
import ynzmz.server.member.service.MemberService;

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
        //토큰에서 memberId 확인
        qnaComment.setMember(loginMemberFindByToken());
        qnaComment.setQuestion(questionService.findQuestionById(questionId));
        qnaComment.setTarget(QnaComment.Target.Question);

        QnaComment createComment = qnaCommentService.createQnaComment(qnaComment);
        QnaCommentDto.Response response = qnaCommentMapper.qnaCommentToQnaCommentResponse(createComment);
        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.CREATED);
    }

    @PostMapping("/answers/{answer-id}")
    public ResponseEntity<?> createAnswerComment(@RequestBody QnaCommentDto.Post postDto,
                                                   @PathVariable("answer-id") long answerId) {
        QnaComment qnaComment = qnaCommentMapper.qnaCommentPostToQnaComment(postDto);
        //토큰에서 memberId 확인
        qnaComment.setMember(loginMemberFindByToken());
        qnaComment.setAnswer(answerService.findAnswerById(answerId));
        qnaComment.setTarget(QnaComment.Target.Answer);

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
    public void deleteQnaComment(@PathVariable("qna-comment-id") long qnaCommentId) {
        memberService.memberValidation(loginMemberFindByToken(), qnaCommentService.findQnaCommentById(qnaCommentId).getMember().getMemberId());
        qnaCommentService.deleteQnaComment(qnaCommentId);
    }

    private Member loginMemberFindByToken(){
        String loginEmail = SecurityContextHolder.getContext().getAuthentication().getName(); // 토큰에서 유저 email 확인
        return memberService.findMemberByEmail(loginEmail);
    }
}

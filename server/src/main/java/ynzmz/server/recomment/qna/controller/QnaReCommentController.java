package ynzmz.server.recomment.qna.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ynzmz.server.comment.qna.service.QnaCommentService;
import ynzmz.server.global.dto.SingleResponseDto;
import ynzmz.server.member.entity.Member;
import ynzmz.server.member.service.MemberService;
import ynzmz.server.recomment.qna.dto.QnaReCommentDto;
import ynzmz.server.recomment.qna.entity.QnaReComment;
import ynzmz.server.recomment.qna.mapper.QnaReCommentMapper;
import ynzmz.server.recomment.qna.service.QnaReCommentService;

import java.util.Optional;

@RestController
@RequestMapping("/recomments/qnas")
@RequiredArgsConstructor
public class QnaReCommentController {
    private final MemberService memberService;
    private final QnaReCommentMapper qnaReCommentMapper;
    private final QnaReCommentService qnaReCommentService;
    private final QnaCommentService qnaCommentService;
    @PostMapping("/{qna-comment-id}")
    public ResponseEntity<?> createQuestionComment(@RequestBody QnaReCommentDto.Post postDto,
                                                   @PathVariable("qna-comment-id") long qnaCommentId) {
        QnaReComment qnaReComment = qnaReCommentMapper.qnaReCommentPostToQnaReComment(postDto);
        //토큰에서 memberId 확인
        qnaReComment.setMember(loginMemberFindByToken());
        qnaReComment.setQnaComment(qnaCommentService.findQnaCommentById(qnaCommentId));

        QnaReComment createComment = qnaReCommentService.createQnaReComment(qnaReComment);
        QnaReCommentDto.Response response = qnaReCommentMapper.qnaReCommentToQnaReCommentResponse(createComment);
        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.CREATED);
    }


    @PatchMapping("/{qna-recomment-id}")
    public ResponseEntity<?> updateQnaComment(@RequestBody QnaReCommentDto.Patch patchDto,
                                              @PathVariable("qna-recomment-id") long qnaReCommentId) {
        //본인확인
        memberService.memberValidation(loginMemberFindByToken(), qnaReCommentService.findQnaReCommentById(qnaReCommentId).getMember().getMemberId());

        QnaReComment qnaReComment = qnaReCommentMapper.qnaReCommentPatchToQnaReComment(patchDto);
        qnaReComment.setQnaReCommentId(qnaReCommentId);

        QnaReComment updateComment = qnaReCommentService.updateQnaReComment(qnaReComment);
        QnaReCommentDto.Response response = qnaReCommentMapper.qnaReCommentToQnaReCommentResponse(updateComment);
        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    @DeleteMapping("/{qna-recomment-id}")
    public ResponseEntity<?> deleteQnaComment(@PathVariable("qna-recomment-id") long qnaReCommentId) {
        //본인확인
        memberService.memberValidation(loginMemberFindByToken(), qnaReCommentService.findQnaReCommentById(qnaReCommentId).getMember().getMemberId());

        qnaReCommentService.deleteQnaReComment(qnaReCommentId);
        Optional<QnaReComment> deletedQnaReComment = qnaReCommentService.findOptionalQnaReCommentById(qnaReCommentId);
        return deletedQnaReComment.isEmpty() ? new ResponseEntity<>("삭제완료",HttpStatus.OK) : new ResponseEntity<>("삭제실패",HttpStatus.INTERNAL_SERVER_ERROR);

    }

    private Member loginMemberFindByToken(){
        String loginEmail = SecurityContextHolder.getContext().getAuthentication().getName(); // 토큰에서 유저 email 확인
        return memberService.findMemberByEmail(loginEmail);
    }
}

package ynzmz.server.comment.free.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ynzmz.server.comment.free.dto.FreeCommentDto;
import ynzmz.server.comment.free.entity.FreeComment;
import ynzmz.server.comment.free.mapper.FreePostCommentMapper;
import ynzmz.server.comment.free.service.FreeCommentService;
import ynzmz.server.dto.MultiResponseDto;
import ynzmz.server.dto.SingleResponseDto;
import ynzmz.server.member.entity.Member;
import ynzmz.server.member.service.MemberService;

import java.util.List;

@RestController
@RequestMapping("/lectures/reviews/comments")
@RequiredArgsConstructor
public class FreeCommentController {
    private final MemberService memberService;
    private final FreePostCommentMapper freePostCommentMapper;
    private final FreeCommentService freeCommentService;
    @PostMapping
    public ResponseEntity<?> createFreeReviewComment(@RequestBody FreeCommentDto.Post postDto) {
        FreeComment freeComment = freePostCommentMapper.lectureReviewCommentToLectureReviewCommentPost(postDto);
        Member member = memberService.findMemberById(postDto.getMemberId());
        freeComment.setMember(member);

        FreeComment createFreeComment = freeCommentService.creatFreeReviewComment(freeComment);
        FreeCommentDto.Response response = freePostCommentMapper.lectureReviewCommentResponseToLectureReviewComment(createFreeComment);
        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.CREATED);
    }

    @PatchMapping("/{lecture-review-comment-id}")
    public ResponseEntity<?> updateFreeReviewComment(@RequestBody FreeCommentDto.Patch patchDto,
                                                        @PathVariable("lecture-review-comment-id") long lectureReviewPostCommentId) {
        FreeComment freeComment = freePostCommentMapper.lectureReviewCommentToLectureReviewCommentPatch(patchDto);
        freeComment.setLectureReviewCommentId(lectureReviewPostCommentId);

        FreeComment updateFreeComment = freeCommentService.updateFreeReviewComment(freeComment);
        FreeCommentDto.Response response = freePostCommentMapper.lectureReviewCommentResponseToLectureReviewComment(updateFreeComment);
        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    //강의별 댓글 조회 (필터기능)
    @GetMapping
    public ResponseEntity<?> getFreeReviewComments(@RequestParam(value = "filter", required = false) String filter,
                                                      @RequestParam long lectureReviewId,
                                                      @RequestParam int page,
                                                      @RequestParam int size) {

        if (filter == null) filter = "lectureReviewCommentId";
        Page<FreeComment> pageLectureReviewPostComments = freeCommentService.getFreeReviewComments(lectureReviewId, filter, page - 1, size);
        List<FreeComment> freeComments = pageLectureReviewPostComments.getContent();

        List<FreeCommentDto.Response> responses = freePostCommentMapper.lectureReviewCommentResponsesToLectureReviewComments(freeComments);

        return new ResponseEntity<>(new MultiResponseDto<>(responses, pageLectureReviewPostComments), HttpStatus.OK);
    }

    @DeleteMapping("/{lecture-review-comment-id}")
    public void deleteFreeReviewComment(@PathVariable("lecture-review-comment-id") long lectureReviewCommentId) {
        freeCommentService.deleteFreeReviewComment(lectureReviewCommentId);
    }
}

package ynzmz.server.comment.review.lecture.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ynzmz.server.board.review.lecture.entity.LectureReview;
import ynzmz.server.board.review.lecture.sevice.LectureReviewService;
import ynzmz.server.comment.review.lecture.dto.LectureReviewCommentDto;
import ynzmz.server.comment.review.lecture.entity.LectureReviewComment;
import ynzmz.server.comment.review.lecture.mapper.LectureReviewCommentMapper;
import ynzmz.server.comment.review.lecture.service.LectureReviewCommentService;
import ynzmz.server.dto.MultiResponseDto;
import ynzmz.server.dto.SingleResponseDto;
import ynzmz.server.member.entity.Member;
import ynzmz.server.member.service.MemberService;


import java.util.List;

@RestController
@RequestMapping("/comments/reviews/lectures")
@RequiredArgsConstructor
public class LectureReviewCommentController {
    private final MemberService memberService;
    private final LectureReviewService lectureReviewService;
    private final LectureReviewCommentMapper lectureReviewCommentMapper;
    private final LectureReviewCommentService lectureReviewCommentService;
    @PostMapping
    public ResponseEntity<?> createLectureReviewComment(@RequestBody LectureReviewCommentDto.Post postDto) {
        LectureReviewComment lectureReviewComment = lectureReviewCommentMapper.lectureReviewCommentPostToLectureReviewComment(postDto);
        LectureReview lectureReview = lectureReviewService.findLectureReviewById(postDto.getLectureReviewId());
        lectureReview.setTotalCommentCount(lectureReview.getComments().size() + 1);

        lectureReviewComment.setLectureReview(lectureReview);
        lectureReviewComment.setMember(loginMemberFindByToken());

        LectureReviewComment createLectureReviewComment = lectureReviewCommentService.createLectureReviewComment(lectureReviewComment);
        LectureReviewCommentDto.Response response = lectureReviewCommentMapper.lectureReviewCommentToLectureReviewCommentResponse(createLectureReviewComment);
        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.CREATED);
    }

    @PatchMapping("/{lecture-review-comment-id}")
    public ResponseEntity<?> updateLectureReviewComment(@RequestBody LectureReviewCommentDto.Patch patchDto,
                                                        @PathVariable("lecture-review-comment-id") long lectureReviewPostCommentId) {
        LectureReviewComment lectureReviewComment = lectureReviewCommentMapper.lectureReviewCommentPatchToLectureReviewComment(patchDto);
        lectureReviewComment.setLectureReviewCommentId(lectureReviewPostCommentId);

        LectureReviewComment updateLectureReviewComment = lectureReviewCommentService.updateLectureReviewComment(lectureReviewComment);
        LectureReviewCommentDto.Response response = lectureReviewCommentMapper.lectureReviewCommentToLectureReviewCommentResponse(updateLectureReviewComment);
        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    //강의별 댓글 조회 (필터기능)
    @GetMapping
    public ResponseEntity<?> getLectureReviewComments(@RequestParam(value = "filter", required = false) String filter,
                                                      @RequestParam long lectureReviewId,
                                                      @RequestParam int page,
                                                      @RequestParam int size) {

        if (filter == null) filter = "lectureReviewCommentId";
        Page<LectureReviewComment> pageLectureReviewPostComments = lectureReviewCommentService.getLectureReviewComments(lectureReviewId, filter, page - 1, size);
        List<LectureReviewComment> lectureReviewComments = pageLectureReviewPostComments.getContent();

        List<LectureReviewCommentDto.Response> responses = lectureReviewCommentMapper.lectureReviewCommentsToLectureReviewCommentResponses(lectureReviewComments);

        return new ResponseEntity<>(new MultiResponseDto<>(responses, pageLectureReviewPostComments), HttpStatus.OK);
    }

    @DeleteMapping("/{lecture-review-comment-id}")
    public void deleteLectureReviewComment(@PathVariable("lecture-review-comment-id") long lectureReviewCommentId) {
        LectureReviewComment lectureReviewComment = lectureReviewCommentService.findLectureReviewCommentById(lectureReviewCommentId);
        lectureReviewComment.getLectureReview().setTotalCommentCount(lectureReviewComment.getLectureReview().getComments().size() -1);
        lectureReviewCommentService.deleteLectureReviewComment(lectureReviewCommentId);
    }

    private Member loginMemberFindByToken(){
        String loginEmail = SecurityContextHolder.getContext().getAuthentication().getName(); // 토큰에서 유저 email 확인
        return memberService.findMemberByEmail(loginEmail);
    }
}

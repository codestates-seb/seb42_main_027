package ynzmz.server.comment.lecturereview.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ynzmz.server.comment.lecturereview.dto.LectureReviewCommentDto;
import ynzmz.server.comment.lecturereview.entity.LectureReviewComment;
import ynzmz.server.comment.lecturereview.mapper.LectureReviewPostCommentMapper;
import ynzmz.server.comment.lecturereview.service.LectureReviewCommentService;
import ynzmz.server.dto.MultiResponseDto;
import ynzmz.server.dto.SingleResponseDto;
import ynzmz.server.member.entity.Member;


import java.util.List;

@RestController
@RequestMapping("lecture-review-comment")
@RequiredArgsConstructor
public class LectureReviewCommentController {
    private final ynzmz.server.member.Service.MemberService memberService;
    private final LectureReviewPostCommentMapper lectureReviewPostCommentMapper;
    private final LectureReviewCommentService lectureReviewCommentService;
    @PostMapping
    public ResponseEntity<?> createLectureReviewComment(@RequestBody LectureReviewCommentDto.Post postDto) {
        LectureReviewComment lectureReviewComment = lectureReviewPostCommentMapper.lectureReviewCommentToLectureReviewCommentPost(postDto);
        Member member = memberService.findMemberById(postDto.getMemberId());
        lectureReviewComment.setMember(member);

        LectureReviewComment createLectureReviewComment = lectureReviewCommentService.createLectureReviewComment(lectureReviewComment);
        LectureReviewCommentDto.Response response = lectureReviewPostCommentMapper.lectureReviewCommentResponseToLectureReviewComment(createLectureReviewComment);
        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.CREATED);
    }

    @PatchMapping("/{lecture-review-comment-id}")
    public ResponseEntity<?> updateLectureReviewComment(@RequestBody LectureReviewCommentDto.Patch patchDto,
                                                        @PathVariable("lecture-review-comment-id") long lectureReviewPostCommentId) {
        LectureReviewComment lectureReviewComment = lectureReviewPostCommentMapper.lectureReviewCommentToLectureReviewCommentPatch(patchDto);
        lectureReviewComment.setLectureReviewCommentId(lectureReviewPostCommentId);

        LectureReviewComment updateLectureReviewComment = lectureReviewCommentService.updateLectureReviewComment(lectureReviewComment);
        LectureReviewCommentDto.Response response = lectureReviewPostCommentMapper.lectureReviewCommentResponseToLectureReviewComment(updateLectureReviewComment);
        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    //강의별 댓글 조회 (필터기능)
    @GetMapping
    public ResponseEntity<?> getLectureReviewComments(@RequestParam(value = "filter", required = false) String filter,
                                                      @RequestParam long lectureReviewPostId,
                                                      @RequestParam int page,
                                                      @RequestParam int size) {

        if (filter == null) filter = "lectureReviewCommentId";
        Page<LectureReviewComment> pageLectureReviewPostComments = lectureReviewCommentService.getLectureReviewComments(lectureReviewPostId, filter, page - 1, size);
        List<LectureReviewComment> lectureReviewComments = pageLectureReviewPostComments.getContent();

        List<LectureReviewCommentDto.Response> responses = lectureReviewPostCommentMapper.lectureReviewCommentResponsesToLectureReviewComments(lectureReviewComments);

        return new ResponseEntity<>(new MultiResponseDto<>(responses, pageLectureReviewPostComments), HttpStatus.OK);
    }

    @DeleteMapping("/{lecture-review-comment-id}")
    public void deleteLectureReviewComment(@PathVariable("lecture-review-comment-id") long lectureReviewCommentId) {
        lectureReviewCommentService.deleteLectureReviewComment(lectureReviewCommentId);
    }
}

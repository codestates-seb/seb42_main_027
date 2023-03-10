package ynzmz.server.comment.lecturereviewpost.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ynzmz.server.comment.lecturereviewpost.dto.LectureReviewPostCommentDto;
import ynzmz.server.comment.lecturereviewpost.entity.LectureReviewPostComment;
import ynzmz.server.comment.lecturereviewpost.mapper.LectureReviewPostCommentMapper;
import ynzmz.server.comment.lecturereviewpost.service.LectureReviewPostCommentService;
import ynzmz.server.dto.MultiResponseDto;
import ynzmz.server.dto.SingleResponseDto;
import ynzmz.server.member.entity.Member;
import ynzmz.server.member.service.MemberService;

import java.util.List;

@RestController
@RequestMapping("lecture-review-post-comment")
@RequiredArgsConstructor
public class LectureReviewPostCommentController {
    private final MemberService memberService;
    private final LectureReviewPostCommentMapper lectureReviewPostCommentMapper;
    private final LectureReviewPostCommentService lectureReviewPostCommentService;
    @PostMapping
    public ResponseEntity<?> createLectureReviewPostComment(@RequestBody LectureReviewPostCommentDto.Post postDto) {
        LectureReviewPostComment lectureReviewPostComment = lectureReviewPostCommentMapper.lectureReviewPostCommentToLectureReviewPostCommentPost(postDto);
        Member member = memberService.findMemberById(postDto.getMemberId());
        lectureReviewPostComment.setMember(member);

        LectureReviewPostComment createLectureReviewPostComment = lectureReviewPostCommentService.createLectureReviewPostComment(lectureReviewPostComment);
        LectureReviewPostCommentDto.Response response = lectureReviewPostCommentMapper.lectureReviewPostCommentResponseToLectureReviewPostComment(createLectureReviewPostComment);
        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.CREATED);
    }

    @PatchMapping("/{lecture-review-post-comment-id}")
    public ResponseEntity<?> updateLectureReviewPostComment(@RequestBody LectureReviewPostCommentDto.Patch patchDto,
                                                            @PathVariable("lecture-review-post-comment-id") long lectureReviewPostCommentId) {
        LectureReviewPostComment lectureReviewPostComment = lectureReviewPostCommentMapper.lectureReviewPostCommentToLectureReviewPostCommentPatch(patchDto);
        lectureReviewPostComment.setLectureReviewPostCommentId(lectureReviewPostCommentId);

        LectureReviewPostComment updateLectureReviewPostComment = lectureReviewPostCommentService.updateLectureReviewPostComment(lectureReviewPostComment);
        LectureReviewPostCommentDto.Response response = lectureReviewPostCommentMapper.lectureReviewPostCommentResponseToLectureReviewPostComment(updateLectureReviewPostComment);
        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    //강의별 댓글 조회 (필터기능)
    @GetMapping
    public ResponseEntity<?> getLectureReviewPostComments(@RequestParam(value = "filter", required = false) String filter,
                                                          @RequestParam long lectureReviewPostId,
                                                          @RequestParam int page,
                                                          @RequestParam int size) {

        if (filter == null) filter = "lectureReviewPostCommentId";
        Page<LectureReviewPostComment> pageLectureReviewPostComments = lectureReviewPostCommentService.getLectureReviewPostComments(lectureReviewPostId, filter, page - 1, size);
        List<LectureReviewPostComment> lectureReviewPostComments = pageLectureReviewPostComments.getContent();

        List<LectureReviewPostCommentDto.Response> responses = lectureReviewPostCommentMapper.lectureReviewPostCommentResponsesToLectureReviewPostComments(lectureReviewPostComments);

        return new ResponseEntity<>(new MultiResponseDto<>(responses, pageLectureReviewPostComments), HttpStatus.OK);
    }

    @DeleteMapping("/{lecture-review-post-comment-id}")
    public void deleteLectureReviewPostComment(@PathVariable("lecture-review-post-comment-id") long lectureReviewPostCommentId) {
        lectureReviewPostCommentService.deleteLectureReviewPostComment(lectureReviewPostCommentId);
    }
}

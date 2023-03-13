package ynzmz.server.vote.lecturereview.comment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ynzmz.server.comment.lecturereview.entity.LectureReviewComment;
import ynzmz.server.comment.lecturereview.service.LectureReviewCommentService;
import ynzmz.server.dto.SingleResponseDto;
import ynzmz.server.member.entity.Member;
import ynzmz.server.member.service.MemberService;
import ynzmz.server.vote.lecturereview.comment.dto.LectureReviewCommentVoteDto;
import ynzmz.server.vote.lecturereview.comment.entity.LectureReviewCommentVote;
import ynzmz.server.vote.lecturereview.comment.mapper.LectureReviewCommentVoteMapper;
import ynzmz.server.vote.lecturereview.comment.service.LectureReviewCommentVoteService;

@RestController
@RequestMapping("/lecture-review-comment-vote")
@RequiredArgsConstructor
public class LectureReviewCommentVoteController {

    private final LectureReviewCommentVoteService lectureReviewCommentVoteService;
    private final LectureReviewCommentService lectureReviewCommentService;
    private final LectureReviewCommentVoteMapper lectureReviewCommentVoteMapper;
    private final MemberService memberService;

    @PostMapping("/{lecture-review-comment-id}/up/{member-id}")
    public ResponseEntity<?> lectureReviewCommentVoteUp(@PathVariable("lecture-review-comment-id") long lectureReviewCommentId,
                                                        @PathVariable("member-id") long memberId) {

        LectureReviewComment lectureReviewComment = lectureReviewCommentService.findLectureReviewCommentById(lectureReviewCommentId);
        Member member = memberService.findMemberById(memberId);

        LectureReviewCommentVote lectureReviewCommentVote = lectureReviewCommentVoteService.lectureReviewCommentVoteUp(lectureReviewComment, member);
        LectureReviewComment voteUpLectureReviewComment = lectureReviewCommentService.findLectureReviewCommentById(lectureReviewCommentVote.getLectureReviewComment().getLectureReviewCommentId());

        LectureReviewCommentVoteDto.Response response = lectureReviewCommentVoteMapper.lectureReviewCommentVoteToLectureReviewCommentVoteResponse(lectureReviewCommentVote, voteUpLectureReviewComment);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    @PostMapping("/{lecture-review-comment-id}/down/{member-id}")
    public ResponseEntity<?> lectureReviewCommentVoteDown(@PathVariable("lecture-review-comment-id") long lectureReviewCommentId,
                                                          @PathVariable("member-id") long memberId) {
        LectureReviewComment lectureReviewComment = lectureReviewCommentService.findLectureReviewCommentById(lectureReviewCommentId);
        Member member = memberService.findMemberById(memberId);

        LectureReviewCommentVote lectureReviewCommentVote = lectureReviewCommentVoteService.lectureReviewCommentVoteDown(lectureReviewComment, member);
        LectureReviewComment voteDownLectureReviewComment = lectureReviewCommentService.findLectureReviewCommentById(lectureReviewCommentVote.getLectureReviewComment().getLectureReviewCommentId());

        LectureReviewCommentVoteDto.Response response = lectureReviewCommentVoteMapper.lectureReviewCommentVoteToLectureReviewCommentVoteResponse(lectureReviewCommentVote, voteDownLectureReviewComment);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }
}

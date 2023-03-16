package ynzmz.server.vote.review.lecture.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ynzmz.server.comment.review.lecture.entity.LectureReviewComment;
import ynzmz.server.comment.review.lecture.service.LectureReviewCommentService;
import ynzmz.server.dto.SingleResponseDto;
import ynzmz.server.review.lecture.entity.LectureReview;
import ynzmz.server.review.lecture.sevice.LectureReviewService;
import ynzmz.server.member.entity.Member;
import ynzmz.server.member.service.MemberService;
import ynzmz.server.vote.review.lecture.dto.LectureReviewVoteDto;
import ynzmz.server.vote.review.lecture.entity.LectureReviewVote;
import ynzmz.server.vote.review.lecture.mapper.LectureReviewVoteMapper;
import ynzmz.server.vote.review.lecture.service.LectureReviewVoteService;

@RestController
@RequestMapping("/vote/lecture/review")
@RequiredArgsConstructor
public class LectureReviewVoteController {
    private final LectureReviewVoteService lectureReviewVoteService;
    private final LectureReviewService lectureReviewService;
    private final LectureReviewCommentService lectureReviewCommentService;
    private final LectureReviewVoteMapper lectureReviewVoteMapper;
    private final  MemberService memberService;

    @PostMapping("/{lecture-review-id}/up/{member-id}")
    public ResponseEntity<?> lectureReviewVoteUp(@PathVariable("lecture-review-id") long lectureReviewPostId,
                                                 @PathVariable("member-id") long memberId) {

        LectureReview lectureReview = lectureReviewService.findLectureReviewById(lectureReviewPostId);
        Member member = memberService.findMemberById(memberId);
        LectureReviewVote lectureReviewVote = lectureReviewVoteService.findLectureReviewVoteTargetReview(lectureReview, member);// 현재 상태값 불러오기

        LectureReviewVote voteUplectureReviewVote = lectureReviewVoteService.lectureReviewVoteUp(lectureReview, lectureReviewVote);

        LectureReviewVoteDto.Response response = lectureReviewVoteMapper.lectureReviewVoteToLectureReviewResponse(voteUplectureReviewVote);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    @PostMapping("/{lecture-review-id}/down/{member-id}")
    public ResponseEntity<?> lectureReviewVoteDown(@PathVariable("lecture-review-id") long lectureReviewPostId,
                                                   @PathVariable("member-id") long memberId) {
        LectureReview lectureReview = lectureReviewService.findLectureReviewById(lectureReviewPostId);
        Member member = memberService.findMemberById(memberId);
        LectureReviewVote lectureReviewVote = lectureReviewVoteService.findLectureReviewVoteTargetReview(lectureReview, member);// 현재 상태값 불러오기

        LectureReviewVote voteDownlectureReviewVote = lectureReviewVoteService.lectureReviewVoteDown(lectureReview, lectureReviewVote);

        LectureReviewVoteDto.Response response = lectureReviewVoteMapper.lectureReviewVoteToLectureReviewResponse(voteDownlectureReviewVote);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    @PostMapping("/comment/{lecture-review-comment-id}/up/{member-id}")
    public ResponseEntity<?> lectureReviewCommentVoteUp(@PathVariable("lecture-review-comment-id") long lectureReviewCommentId,
                                                        @PathVariable("member-id") long memberId) {

        LectureReviewComment lectureReviewComment = lectureReviewCommentService.findLectureReviewCommentById(lectureReviewCommentId);
        Member member = memberService.findMemberById(memberId);
        LectureReviewVote lectureReviewVote = lectureReviewVoteService.findLectureReviewVoteTargetComment(lectureReviewComment, member);// 현재 상태값 불러오기

        LectureReviewVote voteUplectureReviewVote = lectureReviewVoteService.lectureReviewVoteUp(lectureReviewComment, lectureReviewVote);

        LectureReviewVoteDto.Response response = lectureReviewVoteMapper.lectureReviewVoteToLectureReviewResponse(voteUplectureReviewVote);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    @PostMapping("/comment/{lecture-review-comment-id}/down/{member-id}")
    public ResponseEntity<?> lectureReviewCommentVoteDown(@PathVariable("lecture-review-comment-id") long lectureReviewCommentId,
                                                          @PathVariable("member-id") long memberId) {
        LectureReviewComment lectureReviewComment = lectureReviewCommentService.findLectureReviewCommentById(lectureReviewCommentId);
        Member member = memberService.findMemberById(memberId);
        LectureReviewVote lectureReviewVote = lectureReviewVoteService.findLectureReviewVoteTargetComment(lectureReviewComment, member);// 현재 상태값 불러오기

        LectureReviewVote voteDownlectureReviewVote = lectureReviewVoteService.lectureReviewVoteDown(lectureReviewComment, lectureReviewVote);

        LectureReviewVoteDto.Response response = lectureReviewVoteMapper.lectureReviewVoteToLectureReviewResponse(voteDownlectureReviewVote);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }
}

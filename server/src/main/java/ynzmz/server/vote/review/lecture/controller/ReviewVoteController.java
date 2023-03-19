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
import ynzmz.server.board.review.lecture.entity.LectureReview;
import ynzmz.server.board.review.lecture.sevice.LectureReviewService;
import ynzmz.server.member.entity.Member;
import ynzmz.server.member.service.MemberService;
import ynzmz.server.vote.review.lecture.dto.ReviewVoteDto;
import ynzmz.server.vote.review.lecture.entity.ReviewVote;
import ynzmz.server.vote.review.lecture.mapper.ReviewVoteMapper;
import ynzmz.server.vote.review.lecture.service.ReviewVoteService;

@RestController
@RequestMapping("/votes/reviews/lectures")
@RequiredArgsConstructor
public class ReviewVoteController {
    private final ReviewVoteService reviewVoteService;
    private final LectureReviewService lectureReviewService;
    private final LectureReviewCommentService lectureReviewCommentService;
    private final ReviewVoteMapper reviewVoteMapper;
    private final  MemberService memberService;

    @PostMapping("/{lecture-review-id}/up/{member-id}")
    public ResponseEntity<?> lectureReviewVoteUp(@PathVariable("lecture-review-id") long lectureReviewPostId,
                                                 @PathVariable("member-id") long memberId) {

        LectureReview lectureReview = lectureReviewService.findLectureReviewById(lectureReviewPostId);
        Member member = memberService.findMemberById(memberId);
        ReviewVote reviewVote = reviewVoteService.findLectureReviewVoteTargetReview(lectureReview, member);// 현재 상태값 불러오기

        ReviewVote voteUplectureReviewVote = reviewVoteService.lectureReviewVoteUp(lectureReview, reviewVote);

        ReviewVoteDto.Response response = reviewVoteMapper.lectureReviewVoteToLectureReviewResponse(voteUplectureReviewVote);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    @PostMapping("/{lecture-review-id}/down/{member-id}")
    public ResponseEntity<?> lectureReviewVoteDown(@PathVariable("lecture-review-id") long lectureReviewPostId,
                                                   @PathVariable("member-id") long memberId) {
        LectureReview lectureReview = lectureReviewService.findLectureReviewById(lectureReviewPostId);
        Member member = memberService.findMemberById(memberId);
        ReviewVote reviewVote = reviewVoteService.findLectureReviewVoteTargetReview(lectureReview, member);// 현재 상태값 불러오기

        ReviewVote voteDownlectureReviewVote = reviewVoteService.lectureReviewVoteDown(lectureReview, reviewVote);

        ReviewVoteDto.Response response = reviewVoteMapper.lectureReviewVoteToLectureReviewResponse(voteDownlectureReviewVote);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    @PostMapping("/comments/{lecture-review-comment-id}/up/{member-id}")
    public ResponseEntity<?> lectureReviewCommentVoteUp(@PathVariable("lecture-review-comment-id") long lectureReviewCommentId,
                                                        @PathVariable("member-id") long memberId) {

        LectureReviewComment lectureReviewComment = lectureReviewCommentService.findLectureReviewCommentById(lectureReviewCommentId);
        Member member = memberService.findMemberById(memberId);
        ReviewVote reviewVote = reviewVoteService.findLectureReviewVoteTargetComment(lectureReviewComment, member);// 현재 상태값 불러오기

        ReviewVote voteUplectureReviewVote = reviewVoteService.lectureReviewVoteUp(lectureReviewComment, reviewVote);

        ReviewVoteDto.Response response = reviewVoteMapper.lectureReviewVoteToLectureReviewResponse(voteUplectureReviewVote);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    @PostMapping("/comments/{lecture-review-comment-id}/down/{member-id}")
    public ResponseEntity<?> lectureReviewCommentVoteDown(@PathVariable("lecture-review-comment-id") long lectureReviewCommentId,
                                                          @PathVariable("member-id") long memberId) {
        LectureReviewComment lectureReviewComment = lectureReviewCommentService.findLectureReviewCommentById(lectureReviewCommentId);
        Member member = memberService.findMemberById(memberId);
        ReviewVote reviewVote = reviewVoteService.findLectureReviewVoteTargetComment(lectureReviewComment, member);// 현재 상태값 불러오기

        ReviewVote voteDownlectureReviewVote = reviewVoteService.lectureReviewVoteDown(lectureReviewComment, reviewVote);

        ReviewVoteDto.Response response = reviewVoteMapper.lectureReviewVoteToLectureReviewResponse(voteDownlectureReviewVote);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }
}

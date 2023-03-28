package ynzmz.server.vote.review.lecture.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ynzmz.server.comment.review.lecture.entity.LectureReviewComment;
import ynzmz.server.comment.review.lecture.service.LectureReviewCommentService;
import ynzmz.server.global.dto.SingleResponseDto;
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

    @PostMapping("/{lecture-review-id}/up")
    public ResponseEntity<?> lectureReviewVoteUp(@PathVariable("lecture-review-id") long lectureReviewPostId) {

        LectureReview lectureReview = lectureReviewService.findLectureReviewById(lectureReviewPostId);
        Member member = loginMemberFindByToken();
        ReviewVote reviewVote = reviewVoteService.findLectureReviewVoteTargetReview(lectureReview, member);// 현재 상태값 불러오기

        ReviewVote voteUplectureReviewVote = reviewVoteService.lectureReviewVoteUp(lectureReview, reviewVote);

        ReviewVoteDto.ReviewResponse response = reviewVoteMapper.lectureReviewVoteToReviewVoteReviewResponse(voteUplectureReviewVote);
        response.setLectureReviewId(voteUplectureReviewVote.getLectureReview().getLectureReviewId());
        response.setLectureReviewVoteTotalCount(voteUplectureReviewVote.getLectureReview().getVoteCount());

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    @PostMapping("/{lecture-review-id}/down")
    public ResponseEntity<?> lectureReviewVoteDown(@PathVariable("lecture-review-id") long lectureReviewPostId) {
        LectureReview lectureReview = lectureReviewService.findLectureReviewById(lectureReviewPostId);
        Member member = loginMemberFindByToken();
        ReviewVote reviewVote = reviewVoteService.findLectureReviewVoteTargetReview(lectureReview, member);// 현재 상태값 불러오기

        ReviewVote voteDownlectureReviewVote = reviewVoteService.lectureReviewVoteDown(lectureReview, reviewVote);

        ReviewVoteDto.ReviewResponse response = reviewVoteMapper.lectureReviewVoteToReviewVoteReviewResponse(voteDownlectureReviewVote);
        response.setLectureReviewId(voteDownlectureReviewVote.getLectureReview().getLectureReviewId());
        response.setLectureReviewVoteTotalCount(voteDownlectureReviewVote.getLectureReview().getVoteCount());

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    @PostMapping("/comments/{lecture-review-comment-id}/up")
    public ResponseEntity<?> lectureReviewCommentVoteUp(@PathVariable("lecture-review-comment-id") long lectureReviewCommentId) {

        LectureReviewComment lectureReviewComment = lectureReviewCommentService.findLectureReviewCommentById(lectureReviewCommentId);
        Member member = loginMemberFindByToken();
        ReviewVote reviewVote = reviewVoteService.findLectureReviewVoteTargetComment(lectureReviewComment, member);// 현재 상태값 불러오기

        ReviewVote voteUplectureReviewVote = reviewVoteService.lectureReviewVoteUp(lectureReviewComment, reviewVote);

        ReviewVoteDto.CommentResponse response = reviewVoteMapper.lectureReviewVoteToReviewVoteCommentResponse(voteUplectureReviewVote);
        response.setLectureReviewCommentId(voteUplectureReviewVote.getLectureReviewComment().getLectureReviewCommentId());
        response.setLectureReviewCommentVoteTotalCount(voteUplectureReviewVote.getLectureReviewComment().getVoteCount());

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    @PostMapping("/comments/{lecture-review-comment-id}/down")
    public ResponseEntity<?> lectureReviewCommentVoteDown(@PathVariable("lecture-review-comment-id") long lectureReviewCommentId) {
        LectureReviewComment lectureReviewComment = lectureReviewCommentService.findLectureReviewCommentById(lectureReviewCommentId);
        Member member = loginMemberFindByToken();
        ReviewVote reviewVote = reviewVoteService.findLectureReviewVoteTargetComment(lectureReviewComment, member);// 현재 상태값 불러오기

        ReviewVote voteDownlectureReviewVote = reviewVoteService.lectureReviewVoteDown(lectureReviewComment, reviewVote);

        ReviewVoteDto.CommentResponse response = reviewVoteMapper.lectureReviewVoteToReviewVoteCommentResponse(voteDownlectureReviewVote);
        response.setLectureReviewCommentId(voteDownlectureReviewVote.getLectureReviewComment().getLectureReviewCommentId());
        response.setLectureReviewCommentVoteTotalCount(voteDownlectureReviewVote.getLectureReviewComment().getVoteCount());

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }
    private Member loginMemberFindByToken(){
        String loginEmail = SecurityContextHolder.getContext().getAuthentication().getName(); // 토큰에서 유저 email 확인
        return memberService.findMemberByEmail(loginEmail);
    }
}

package ynzmz.server.vote.lecturereview.lecturereview.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ynzmz.server.dto.SingleResponseDto;
import ynzmz.server.lecturereview.entity.LectureReview;
import ynzmz.server.lecturereview.sevice.LectureReviewService;
import ynzmz.server.member.entity.Member;
import ynzmz.server.member.service.MemberService;
import ynzmz.server.vote.lecturereview.lecturereview.dto.LectureReviewVoteDto;
import ynzmz.server.vote.lecturereview.lecturereview.entity.LectureReviewVote;
import ynzmz.server.vote.lecturereview.lecturereview.mapper.LectureReviewVoteMapper;
import ynzmz.server.vote.lecturereview.lecturereview.service.LectureReviewVoteService;

@RestController
@RequestMapping("/lecture-review-vote")
@RequiredArgsConstructor
public class LectureReviewVoteController {

    private final LectureReviewVoteService lectureReviewVoteService;
    private final LectureReviewService lectureReviewService;
    private final LectureReviewVoteMapper lectureReviewVoteMapper;
    private final MemberService memberService;

    @PostMapping("/{lecture-review-id}/up/{member-id}")
    public ResponseEntity<?> lectureReviewVoteUp(@PathVariable("lecture-review-id") long lectureReviewPostId,
                                                 @PathVariable("member-id") long memberId) {

        LectureReview lectureReview = lectureReviewService.findLectureReviewById(lectureReviewPostId);
        Member member = memberService.findMemberById(memberId);

        LectureReviewVote lectureReviewVote = lectureReviewVoteService.lectureReviewPostVoteUp(lectureReview, member);
        LectureReview voteUpLectureReview = lectureReviewService.findLectureReviewById(lectureReviewVote.getLectureReview().getLectureReviewId());

        LectureReviewVoteDto.Response response = lectureReviewVoteMapper.lectureReviewPostVoteToLectureReviewPostResponse(lectureReviewVote, voteUpLectureReview);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    @PostMapping("/{lecture-review-id}/down/{member-id}")
    public ResponseEntity<?> lectureReviewVoteDown(@PathVariable("lecture-review-id") long lectureReviewPostId,
                                                   @PathVariable("member-id") long memberId) {
        LectureReview lectureReview = lectureReviewService.findLectureReviewById(lectureReviewPostId);
        Member member = memberService.findMemberById(memberId);

        LectureReviewVote lectureReviewVote = lectureReviewVoteService.lectureReviewPostVoteDown(lectureReview, member);
        LectureReview voteDownLectureReview = lectureReviewService.findLectureReviewById(lectureReviewVote.getLectureReview().getLectureReviewId());

        LectureReviewVoteDto.Response response = lectureReviewVoteMapper.lectureReviewPostVoteToLectureReviewPostResponse(lectureReviewVote, voteDownLectureReview);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }
}

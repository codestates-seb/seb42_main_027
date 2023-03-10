package ynzmz.server.vote.lecturereviewpost.lecturereviewpost.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ynzmz.server.dto.SingleResponseDto;
import ynzmz.server.lecturereviewpost.entity.LectureReviewPost;
import ynzmz.server.lecturereviewpost.sevice.LectureReviewPostService;
import ynzmz.server.member.entity.Member;
import ynzmz.server.member.service.MemberService;
import ynzmz.server.vote.lecturereviewpost.lecturereviewpost.dto.LectureReviewPostVoteDto;
import ynzmz.server.vote.lecturereviewpost.lecturereviewpost.entity.LectureReviewPostVote;
import ynzmz.server.vote.lecturereviewpost.lecturereviewpost.mapper.LectureReviewPostVoteMapper;
import ynzmz.server.vote.lecturereviewpost.lecturereviewpost.service.LectureReviewPostVoteService;

@RestController
@RequestMapping("/lecture-review-post-vote")
@RequiredArgsConstructor
public class LectureReviewPostVoteController {

    private final LectureReviewPostVoteService lectureReviewPostVoteService;
    private final LectureReviewPostService lectureReviewPostService;
    private final LectureReviewPostVoteMapper lectureReviewPostVoteMapper;
    private final MemberService memberService;

    @PostMapping("/{lecture-review-post-id}/up/{member-id}")
    public ResponseEntity<?> questionVoteUpPost(@PathVariable("lecture-review-post-id") long lectureReviewPostId,
                                                @PathVariable("member-id") long memberId) {

        LectureReviewPost lectureReviewPost = lectureReviewPostService.findLectureReviewPostById(lectureReviewPostId);
        Member member = memberService.findMemberById(memberId);

        LectureReviewPostVote lectureReviewPostVote = lectureReviewPostVoteService.lectureReviewPostVoteUp(lectureReviewPost, member);
        LectureReviewPost voteUpLectureReviewPost = lectureReviewPostService.findLectureReviewPostById(lectureReviewPostVote.getLectureReviewPost().getLectureReviewPostId());

        LectureReviewPostVoteDto.Response response = lectureReviewPostVoteMapper.lectureReviewPostVoteToLectureReviewPostResponse(lectureReviewPostVote, voteUpLectureReviewPost);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    @PostMapping("/{lecture-review-post-id}/down/{member-id}")
    public ResponseEntity<?> questionVoteDownPost(@PathVariable("lecture-review-post-id") long lectureReviewPostId,
                                                  @PathVariable("member-id") long memberId) {
        LectureReviewPost lectureReviewPost = lectureReviewPostService.findLectureReviewPostById(lectureReviewPostId);
        Member member = memberService.findMemberById(memberId);

        LectureReviewPostVote lectureReviewPostVote = lectureReviewPostVoteService.lectureReviewPostVoteDown(lectureReviewPost, member);
        LectureReviewPost voteDownLectureReviewPost = lectureReviewPostService.findLectureReviewPostById(lectureReviewPostVote.getLectureReviewPost().getLectureReviewPostId());

        LectureReviewPostVoteDto.Response response = lectureReviewPostVoteMapper.lectureReviewPostVoteToLectureReviewPostResponse(lectureReviewPostVote, voteDownLectureReviewPost);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }
}

package ynzmz.server.vote.lecturereviewpost.comment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ynzmz.server.comment.lecturereviewpost.entity.LectureReviewPostComment;
import ynzmz.server.comment.lecturereviewpost.service.LectureReviewPostCommentService;
import ynzmz.server.dto.SingleResponseDto;
import ynzmz.server.lecturereviewpost.entity.LectureReviewPost;
import ynzmz.server.member.entity.Member;
import ynzmz.server.member.service.MemberService;
import ynzmz.server.vote.lecturereviewpost.comment.dto.LectureReviewPostCommentVoteDto;
import ynzmz.server.vote.lecturereviewpost.comment.entity.LectureReviewPostCommentVote;
import ynzmz.server.vote.lecturereviewpost.comment.mapper.LectureReviewPostCommentVoteMapper;
import ynzmz.server.vote.lecturereviewpost.comment.service.LectureReviewPostCommentVoteService;
import ynzmz.server.vote.lecturereviewpost.lecturereviewpost.dto.LectureReviewPostVoteDto;
import ynzmz.server.vote.lecturereviewpost.lecturereviewpost.entity.LectureReviewPostVote;

@RestController
@RequestMapping("/lecture-review-post-comment-vote")
@RequiredArgsConstructor
public class LectureReviewPostCommentVoteController {

    private final LectureReviewPostCommentVoteService lectureReviewPostCommentVoteService;
    private final LectureReviewPostCommentService lectureReviewPostCommentService;
    private final LectureReviewPostCommentVoteMapper lectureReviewPostCommentVoteMapper;
    private final MemberService memberService;

    @PostMapping("/{lecture-review-post-comment-id}/up/{member-id}")
    public ResponseEntity<?> questionVoteUpPost(@PathVariable("lecture-review-post-comment-id") long lectureReviewPostCommentId,
                                                @PathVariable("member-id") long memberId) {

        LectureReviewPostComment lectureReviewPostComment = lectureReviewPostCommentService.findLectureReviewPostCommentById(lectureReviewPostCommentId);
        Member member = memberService.findMemberById(memberId);

        LectureReviewPostCommentVote lectureReviewPostCommentVote = lectureReviewPostCommentVoteService.lectureReviewPostVoteUp(lectureReviewPostComment, member);
        LectureReviewPostComment voteUpLectureReviewPostComment = lectureReviewPostCommentService.findLectureReviewPostCommentById(lectureReviewPostCommentVote.getLectureReviewPostComment().getLectureReviewPostCommentId());

        LectureReviewPostCommentVoteDto.Response response = lectureReviewPostCommentVoteMapper.lectureReviewPostVoteToLectureReviewPostResponse(lectureReviewPostCommentVote, voteUpLectureReviewPostComment);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    @PostMapping("/{lecture-review-post-comment-id}/down/{member-id}")
    public ResponseEntity<?> questionVoteDownPost(@PathVariable("lecture-review-post-comment-id") long lectureReviewPostCommentId,
                                                  @PathVariable("member-id") long memberId) {
        LectureReviewPostComment lectureReviewPostComment = lectureReviewPostCommentService.findLectureReviewPostCommentById(lectureReviewPostCommentId);
        Member member = memberService.findMemberById(memberId);

        LectureReviewPostCommentVote lectureReviewPostCommentVote = lectureReviewPostCommentVoteService.lectureReviewPostVoteDown(lectureReviewPostComment, member);
        LectureReviewPostComment voteDownLectureReviewPostComment = lectureReviewPostCommentService.findLectureReviewPostCommentById(lectureReviewPostCommentVote.getLectureReviewPostComment().getLectureReviewPostCommentId());

        LectureReviewPostCommentVoteDto.Response response = lectureReviewPostCommentVoteMapper.lectureReviewPostVoteToLectureReviewPostResponse(lectureReviewPostCommentVote, voteDownLectureReviewPostComment);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }
}

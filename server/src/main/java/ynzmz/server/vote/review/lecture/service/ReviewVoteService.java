package ynzmz.server.vote.review.lecture.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ynzmz.server.comment.review.lecture.entity.LectureReviewComment;
import ynzmz.server.board.review.lecture.entity.LectureReview;
import ynzmz.server.member.entity.Member;
import ynzmz.server.vote.Vote;
import ynzmz.server.vote.review.lecture.entity.ReviewVote;
import ynzmz.server.vote.review.lecture.repository.ReviewVoteRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewVoteService {

    private final ReviewVoteRepository reviewVoteRepository;

    public ReviewVote lectureReviewVoteUp(Vote countTarget, ReviewVote reviewVote) {

        if(reviewVote.getStatus().equals(Vote.Status.UP)){ //만약에 UP 이면
            reviewVote.setStatus(Vote.Status.NONE); // UP 취소로 동작 (NONE 으로 변경)
            countTarget.setVoteCount(countTarget.getVoteCount() -1); // 게시글의 카운트수 -1
        } else if (reviewVote.getStatus().equals(Vote.Status.NONE)) { // NONE 상태면
            reviewVote.setStatus(Vote.Status.UP); // UP 으로 변경
            countTarget.setVoteCount(countTarget.getVoteCount() +1); // 게시글 카운트 수 +1
        } else { //down 상태면
            reviewVote.setStatus(Vote.Status.UP); // DOWN -> UP 으로 변경
            countTarget.setVoteCount(countTarget.getVoteCount() +2); // 게시글 카운트 수 + 2 (DOWN -> UP)이라서
        }
        return reviewVoteRepository.save(reviewVote);
    }

    public ReviewVote lectureReviewVoteDown(Vote countTarget, ReviewVote reviewVote) {

        if(reviewVote.getStatus().equals(Vote.Status.DOWN)){ //만약에 DOWN 이면
            reviewVote.setStatus(Vote.Status.NONE); // DOWN 취소로 동작 (NONE 으로 변경)
            countTarget.setVoteCount(countTarget.getVoteCount() +1); // 게시글의 카운트수 +1
        } else if (reviewVote.getStatus().equals(Vote.Status.NONE)) { // NONE 상태면
            reviewVote.setStatus(Vote.Status.DOWN); // DOWN 으로 변경
            countTarget.setVoteCount(countTarget.getVoteCount() -1); // 게시글 카운트 수 +1
        } else { //down 상태면
            reviewVote.setStatus(Vote.Status.DOWN); // UP -> DOWN 으로 변경
            countTarget.setVoteCount(countTarget.getVoteCount() -2); // 게시글 카운트 수 + 2 (DOWN -> UP)이라서
        }
        return reviewVoteRepository.save(reviewVote);
    }

    public ReviewVote findLectureReviewVoteTargetReview(LectureReview lectureReview, Member member){
        Optional<ReviewVote> findLectureReviewPostVote = reviewVoteRepository.findByLectureReviewAndMemberAndTarget(lectureReview, member, Vote.Target.REVIEW);
        return findLectureReviewPostVote.orElseGet(()-> new ReviewVote(lectureReview, member, Vote.Status.NONE, Vote.Target.REVIEW));
    }

    public ReviewVote findLectureReviewVoteTargetComment(LectureReviewComment lectureReviewComment, Member member){
        Optional<ReviewVote> findLectureReviewPostVote = reviewVoteRepository.findByLectureReviewCommentAndMemberAndTarget(lectureReviewComment, member, Vote.Target.COMMENT);
        return findLectureReviewPostVote.orElseGet(()-> new ReviewVote(lectureReviewComment, member, Vote.Status.NONE, Vote.Target.COMMENT));
    }
}

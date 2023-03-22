package ynzmz.server.vote.review.lecture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ynzmz.server.comment.review.lecture.entity.LectureReviewComment;
import ynzmz.server.board.review.lecture.entity.LectureReview;
import ynzmz.server.member.entity.Member;
import ynzmz.server.vote.Vote;
import ynzmz.server.vote.review.lecture.entity.ReviewVote;

import java.util.Optional;

@Repository
public interface ReviewVoteRepository extends JpaRepository<ReviewVote, Long> {
    Optional<ReviewVote> findByLectureReviewAndMemberAndTarget(LectureReview lectureReview, Member member, Vote.Target target);
    Optional<ReviewVote> findByLectureReviewCommentAndMemberAndTarget(LectureReviewComment lectureReviewComment, Member member, Vote.Target target);
}


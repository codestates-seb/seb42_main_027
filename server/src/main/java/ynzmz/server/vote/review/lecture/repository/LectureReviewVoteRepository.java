package ynzmz.server.vote.review.lecture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ynzmz.server.comment.review.lecture.entity.LectureReviewComment;
import ynzmz.server.review.lecture.entity.LectureReview;
import ynzmz.server.member.entity.Member;
import ynzmz.server.vote.review.lecture.entity.LectureReviewVote;

import java.util.Optional;

@Repository
public interface LectureReviewVoteRepository extends JpaRepository<LectureReviewVote, Long> {
    Optional<LectureReviewVote> findByLectureReviewAndMemberAndTarget(LectureReview lectureReview, Member member, LectureReviewVote.Target target);
    Optional<LectureReviewVote> findByLectureReviewCommentAndMemberAndTarget(LectureReviewComment lectureReviewComment, Member member, LectureReviewVote.Target target);
}


package ynzmz.server.vote.lecturereview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ynzmz.server.comment.lecturereview.entity.LectureReviewComment;
import ynzmz.server.lecturereview.entity.LectureReview;
import ynzmz.server.member.entity.Member;
import ynzmz.server.vote.lecturereview.entity.LectureReviewVote;

import java.util.Optional;

@Repository
public interface LectureReviewVoteRepository extends JpaRepository<LectureReviewVote, Long> {
    Optional<LectureReviewVote> findByLectureReviewAndMemberAndTarget(LectureReview lectureReview, Member member, LectureReviewVote.Target target);
    Optional<LectureReviewVote> findByLectureReviewCommentAndMemberAndTarget(LectureReviewComment lectureReviewComment, Member member, LectureReviewVote.Target target);
}


package ynzmz.server.vote.lecturereview.lecturereview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ynzmz.server.lecturereview.entity.LectureReview;
import ynzmz.server.member.entity.Member;
import ynzmz.server.vote.lecturereview.lecturereview.entity.LectureReviewVote;

import java.util.Optional;

@Repository
public interface LectureReviewVoteRepository extends JpaRepository<LectureReviewVote, Long> {

    Optional<LectureReviewVote> findByLectureReviewAndMember(LectureReview lectureReview, Member member);
}


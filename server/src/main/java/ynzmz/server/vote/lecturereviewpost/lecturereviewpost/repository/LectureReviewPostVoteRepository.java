package ynzmz.server.vote.lecturereviewpost.lecturereviewpost.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ynzmz.server.lecturereviewpost.entity.LectureReviewPost;
import ynzmz.server.member.entity.Member;
import ynzmz.server.vote.lecturereviewpost.lecturereviewpost.entity.LectureReviewPostVote;

import java.util.Optional;

@Repository
public interface LectureReviewPostVoteRepository extends JpaRepository<LectureReviewPostVote, Long> {

    Optional<LectureReviewPostVote> findByLectureReviewPostAndMember(LectureReviewPost lectureReviewPost, Member member);
}


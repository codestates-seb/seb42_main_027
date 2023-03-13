package ynzmz.server.vote.lecturereview.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ynzmz.server.comment.lecturereview.entity.LectureReviewComment;
import ynzmz.server.member.entity.Member;
import ynzmz.server.vote.lecturereview.comment.entity.LectureReviewCommentVote;

import java.util.Optional;

@Repository
public interface LectureReviewCommentVoteRepository extends JpaRepository<LectureReviewCommentVote, Long> {

    Optional<LectureReviewCommentVote> findByLectureReviewCommentAndMember(LectureReviewComment lectureReviewComment, Member member);
}


package ynzmz.server.vote.lecturereviewpost.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ynzmz.server.comment.lecturereviewpost.entity.LectureReviewPostComment;
import ynzmz.server.member.entity.Member;
import ynzmz.server.vote.lecturereviewpost.comment.entity.LectureReviewPostCommentVote;
import ynzmz.server.vote.lecturereviewpost.lecturereviewpost.entity.LectureReviewPostVote;

import java.util.Optional;

@Repository
public interface LectureReviewPostCommentVoteRepository extends JpaRepository<LectureReviewPostCommentVote, Long> {

    Optional<LectureReviewPostCommentVote> findByLectureReviewPostCommentAndMember(LectureReviewPostComment lectureReviewPostComment, Member member);
}


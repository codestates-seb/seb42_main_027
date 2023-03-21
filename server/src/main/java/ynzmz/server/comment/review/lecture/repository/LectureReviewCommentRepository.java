package ynzmz.server.comment.review.lecture.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ynzmz.server.comment.free.entity.FreeComment;
import ynzmz.server.comment.review.lecture.entity.LectureReviewComment;

import java.util.List;

@Repository
public interface LectureReviewCommentRepository extends JpaRepository<LectureReviewComment,Long> {
    Page<LectureReviewComment> findLectureReviewCommentsByLectureReviewLectureReviewId(long lectureReviewId, Pageable pageable);

    @Query(value = "SELECT lr FROM LectureReviewComment lr WHERE lr.member.memberId = :memberId")
    List<LectureReviewComment> findByMemberId(long memberId);

}

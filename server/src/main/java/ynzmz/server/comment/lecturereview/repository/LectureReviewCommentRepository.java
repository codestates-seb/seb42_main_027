package ynzmz.server.comment.lecturereview.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ynzmz.server.comment.lecturereview.entity.LectureReviewComment;

@Repository
public interface LectureReviewCommentRepository extends JpaRepository<LectureReviewComment,Long> {
    Page<LectureReviewComment> findLectureReviewCommentsByLectureReviewLectureReviewId(long lectureReviewId, Pageable pageable);

}

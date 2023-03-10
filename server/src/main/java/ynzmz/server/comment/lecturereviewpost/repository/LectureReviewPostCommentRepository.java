package ynzmz.server.comment.lecturereviewpost.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ynzmz.server.comment.lecturereviewpost.entity.LectureReviewPostComment;
@Repository
public interface LectureReviewPostCommentRepository extends JpaRepository<LectureReviewPostComment,Long> {
    Page<LectureReviewPostComment> findLectureReviewPostCommentsByLectureReviewPostLectureReviewPostId(long lectureReviewPostId, Pageable pageable);

}

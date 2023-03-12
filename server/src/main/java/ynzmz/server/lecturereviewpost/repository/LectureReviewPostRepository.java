package ynzmz.server.lecturereviewpost.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ynzmz.server.lecturereviewpost.entity.LectureReviewPost;

import java.util.List;

@Repository
public interface LectureReviewPostRepository extends JpaRepository<LectureReviewPost,Long> {

    @Query("SELECT lr FROM LectureReviewPost lr WHERE lr.lecture.lectureId = :lectureId")
    List<LectureReviewPost> findAllLecturesByLectureReviewPost(long lectureId);

//    @Query("SELECT lr FROM LectureReviewPost lr JOIN lr.lecture l where l.lectureId = :lectureId")
    Page<LectureReviewPost> findLectureReviewPostByLectureLectureId(long lectureId, Pageable pageable);

    Page<LectureReviewPost> findLectureReviewPostByLectureTeacherTeacherId(long teacherId, Pageable pageable);
}

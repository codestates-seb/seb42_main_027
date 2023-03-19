package ynzmz.server.board.review.lecture.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ynzmz.server.board.review.lecture.entity.LectureReview;

import java.util.List;

@Repository
public interface LectureReviewRepository extends JpaRepository<LectureReview,Long> {

    @Query("SELECT lr FROM LectureReview  lr WHERE lr.member.memberId = :memberId")
    Page<LectureReview> findByMemberId(long memberId,Pageable pageable); //다빈 추가

    @Query("SELECT lr FROM LectureReview lr JOIN lr.lecture lrl WHERE lrl.lectureId = :lectureId")
    List<LectureReview> findAllByLectureId(long lectureId);

    @Query("SELECT lr FROM LectureReview lr JOIN lr.lecture lrl JOIN lrl.teacher lrlt WHERE lrlt.teacherId = :teacherId")
    List<LectureReview> findAllByTeacherId(long teacherId);

//    @Query("SELECT lr FROM LectureReviewPost lr JOIN lr.lecture l where l.lectureId = :lectureId")
    Page<LectureReview> findLectureReviewByLectureLectureId(long lectureId, Pageable pageable);

    Page<LectureReview> findLectureReviewByLectureTeacherTeacherId(long teacherId, Pageable pageable);
}

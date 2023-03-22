package ynzmz.server.lecture.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ynzmz.server.lecture.entity.Lecture;
import ynzmz.server.tag.entity.GradeTag;
import ynzmz.server.tag.entity.PlatformTag;
import ynzmz.server.tag.entity.SubjectTag;
import ynzmz.server.teacher.entity.Teacher;

@Repository
public interface LectureRepository extends JpaRepository<Lecture,Long> {
//    @Query("SELECT l FROM Lecture l JOIN l.lectureReviews lt JOIN lt.tag tg where tg.type = :tag")
//    Page<Lecture> findByTagType(String tag, Pageable pageable);

    @Query("SELECT l FROM Lecture l JOIN l.teacher lt where lt.teacherId = :teacherId")
    Page<Lecture> findByTeacherId(long teacherId, Pageable pageable);

    @Query("SELECT DISTINCT l FROM Lecture l " +
            "JOIN l.gradeTags lg " +
            "JOIN l.platformTags lp " +
            "JOIN  l.subjectTags ls " +
            "WHERE (:grade IS NULL OR lg.gradeTag.grade = :grade) " +
            "AND (:platform IS NULL OR lp.platformTag.platform = :platform) " +
            "AND (:subject IS NULL OR ls.subjectTag.subject = :subject) " +
            "AND (:title IS NULL OR l.title LIKE CONCAT('%', :title, '%'))")
    Page<Lecture> findAllByGradeAndPlatformAndSubjectAndTitle(GradeTag.Grade grade, PlatformTag.Platform platform, SubjectTag.Subject subject, String title, Pageable pageable);

}

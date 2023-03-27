package ynzmz.server.board.teacher.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ynzmz.server.tag.entity.GradeTag;
import ynzmz.server.tag.entity.PlatformTag;
import ynzmz.server.tag.entity.SubjectTag;
import ynzmz.server.board.teacher.entity.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Long> {
    @Query("SELECT t FROM Teacher t JOIN t.gradeTags tg JOIN tg.gradeTag tgg where tgg.grade = :grade")
    Page<Teacher> findByGrade(String grade, Pageable pageable);


    @Query("SELECT DISTINCT t FROM Teacher t " +
            "JOIN t.gradeTags tg " +
            "JOIN t.platformTags tp " +
            "JOIN t.subjectTags ts " +
            "WHERE (:grade IS NULL OR tg.gradeTag.grade = :grade) " +
            "AND (:platform IS NULL OR tp.platformTag.platform = :platform) " +
            "AND (:subject IS NULL OR ts.subjectTag.subject = :subject) " +
            "AND (:name IS NULL OR t.name LIKE CONCAT('%', :name, '%'))")
    Page<Teacher> findAllByGradeAndPlatformAndSubjectAndName(GradeTag.Grade grade, PlatformTag.Platform platform, SubjectTag.Subject subject, String name, Pageable pageable);
//    @Query("SELECT DISTINCT t FROM Teacher t " +
//            "JOIN t.gradeTags tg " +
//            "JOIN t.platformTags tp " +
//            "JOIN  t.subjectTags ts " +
//            "WHERE (:grade IS NULL OR tg.gradeTag.grade = :grade) " +
//            "AND (:platform IS NULL OR tp.platformTag.platform = :platform) " +
//            "AND (:subject IS NULL OR ts.subjectTag.subject = :subject) " +
//            "AND (:name IS NULL OR t.name LIKE CONCAT('%', :name, '%')) " +
//            "ORDER BY RAND() ")
//    Page<Teacher> findAllByGradeAndPlatformAndSubjectAndNameByRandom(GradeTag.Grade grade, PlatformTag.Platform platform, SubjectTag.Subject subject, String name, Pageable pageable);
}

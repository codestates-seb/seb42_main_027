package ynzmz.server.board.free.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ynzmz.server.board.free.entity.Free;
import ynzmz.server.lecture.entity.Lecture;
import ynzmz.server.tag.entity.GradeTag;
import ynzmz.server.tag.entity.PlatformTag;
import ynzmz.server.tag.entity.SubjectTag;

import java.util.List;

@Repository
public interface FreeRepository extends JpaRepository<Free,Long> {
    @Query(value = "SELECT f FROM Free f WHERE f.member.memberId = :memberId")
    List<Free> findByMemberId(long memberId); //다빈 추가

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

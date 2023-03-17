package ynzmz.server.question.question.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ynzmz.server.question.question.entity.Question;
import ynzmz.server.tag.entity.GradeTag;
import ynzmz.server.tag.entity.PlatformTag;
import ynzmz.server.tag.entity.SubjectTag;
import ynzmz.server.teacher.entity.Teacher;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query(value = "SELECT q FROM Question q WHERE q.member.memberId = :memberId")
    Page<Question> findByMemberId(long memberId, Pageable pageable);

    Page<Question> findByTitleContainingIgnoreCase(String title, Pageable pageable);

    @Query("SELECT DISTINCT q FROM Question q " +
            "JOIN q.subjectTags ts " +
            "WHERE (:subject IS NULL OR ts.subjectTag.subject = :subject) " +
            "AND (LOWER(:title) IS NULL OR q.title LIKE CONCAT('%', LOWER(:title), '%'))")
    Page<Question> findAllByGradeAndPlatformAndSubjectAndName(SubjectTag.Subject subject, String title, Pageable pageable);


}

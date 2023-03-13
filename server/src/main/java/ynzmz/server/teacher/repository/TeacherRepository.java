package ynzmz.server.teacher.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ynzmz.server.teacher.entity.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Long> {
    @Query("SELECT t FROM Teacher t JOIN t.gradeTags tg JOIN tg.gradeTag tgg where tgg.grade = :grade")
    Page<Teacher> findByGrade(String grade, Pageable pageable);

    Page<Teacher> findAllByGradeAndPlatformAndSubjectAndName(@Param("grade")String grade, String platform, String subject, String name, Pageable pageable);
    Page<Teacher> findAllByGradeTagsGradeAndPlatformTagsPlatformAndSubjectTagsSubjectAndName(String grade, String platform, String subject, String name, Pageable pageable);

}

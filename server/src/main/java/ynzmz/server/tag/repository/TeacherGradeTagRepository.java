package ynzmz.server.tag.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ynzmz.server.tag.entity.teacher.TeacherGradeTag;
import ynzmz.server.board.teacher.entity.Teacher;

@Repository
public interface TeacherGradeTagRepository extends JpaRepository<TeacherGradeTag,Long> {
    void deleteAllTeacherGradeTagByTeacher(Teacher teacher);
}

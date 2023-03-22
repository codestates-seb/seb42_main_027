package ynzmz.server.tag.repository.teacher;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ynzmz.server.tag.mappingtable.teacher.TeacherGradeTag;
import ynzmz.server.teacher.entity.Teacher;

@Repository
public interface TeacherGradeTagRepository extends JpaRepository<TeacherGradeTag,Long> {
    void deleteAllTeacherGradeTagByTeacher(Teacher teacher);
}

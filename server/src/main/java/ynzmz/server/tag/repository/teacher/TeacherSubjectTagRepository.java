package ynzmz.server.tag.repository.teacher;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ynzmz.server.tag.mappingtable.teacher.TeacherSubjectTag;
import ynzmz.server.teacher.entity.Teacher;

@Repository
public interface TeacherSubjectTagRepository extends JpaRepository<TeacherSubjectTag,Long> {
    void deleteAllTeacherSubjectTagByTeacher(Teacher teacher);
}

package ynzmz.server.tag.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ynzmz.server.tag.mappingtable.teacher.TeacherTag;
import ynzmz.server.teacher.entity.Teacher;

@Repository
public interface TeacherTagRepository extends JpaRepository<TeacherTag,Long> {
    void deleteAllTeacherTagByTeacher(Teacher teacher);
}

package ynzmz.server.tag.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ynzmz.server.tag.entity.teacher.TeacherSubjectTag;
import ynzmz.server.board.teacher.entity.Teacher;

@Repository
public interface TeacherSubjectTagRepository extends JpaRepository<TeacherSubjectTag,Long> {
    void deleteAllTeacherSubjectTagByTeacher(Teacher teacher);
}

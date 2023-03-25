package ynzmz.server.tag.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ynzmz.server.tag.entity.teacher.TeacherPlatformTag;
import ynzmz.server.board.teacher.entity.Teacher;

@Repository
public interface TeacherPlatformTagRepository extends JpaRepository<TeacherPlatformTag,Long> {
    void deleteAllTeacherPlatformTagByTeacher(Teacher teacher);
}

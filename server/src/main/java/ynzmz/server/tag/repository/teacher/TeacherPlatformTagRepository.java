package ynzmz.server.tag.repository.teacher;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ynzmz.server.tag.mappingtable.teacher.TeacherPlatformTag;
import ynzmz.server.teacher.entity.Teacher;

@Repository
public interface TeacherPlatformTagRepository extends JpaRepository<TeacherPlatformTag,Long> {
    void deleteAllTeacherPlatformTagByTeacher(Teacher teacher);
}

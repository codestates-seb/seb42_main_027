package ynzmz.server.tag.manager;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ynzmz.server.board.teacher.entity.Teacher;
import ynzmz.server.tag.entity.teacher.TeacherGradeTag;
import ynzmz.server.tag.entity.teacher.TeacherPlatformTag;
import ynzmz.server.tag.entity.teacher.TeacherSubjectTag;
import ynzmz.server.tag.repository.TeacherGradeTagRepository;
import ynzmz.server.tag.repository.TeacherPlatformTagRepository;
import ynzmz.server.tag.repository.TeacherSubjectTagRepository;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Component
public class TeacherTagManager {
    private final TeacherGradeTagRepository teacherGradeTagRepository;
    private final TeacherPlatformTagRepository teacherPlatformTagRepository;
    private final TeacherSubjectTagRepository teacherSubjectTagRepository;

    public void saveTeacherGradeTag(TeacherGradeTag teacherGradeTag) {
        teacherGradeTagRepository.save(teacherGradeTag);
    }

    public void saveTeacherPlatformTag(TeacherPlatformTag teacherPlatformTag) {
        teacherPlatformTagRepository.save(teacherPlatformTag);
    }
    public void saveTeacherSubjectTag(TeacherSubjectTag teacherSubjectTag) {
        teacherSubjectTagRepository.save(teacherSubjectTag);
    }
    @Transactional
    public void deleteAllTeacherTagByTeacher(Teacher teacher) {
        teacherGradeTagRepository.deleteAllTeacherGradeTagByTeacher(teacher);
        teacherPlatformTagRepository.deleteAllTeacherPlatformTagByTeacher(teacher);
        teacherSubjectTagRepository.deleteAllTeacherSubjectTagByTeacher(teacher);
    }
}

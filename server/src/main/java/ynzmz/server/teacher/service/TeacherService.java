package ynzmz.server.teacher.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ynzmz.server.error.exception.BusinessLogicException;
import ynzmz.server.error.exception.ExceptionCode;
import ynzmz.server.tag.repository.TeacherTagRepository;
import ynzmz.server.teacher.dto.TeacherDto;
import ynzmz.server.teacher.entity.Teacher;
import ynzmz.server.teacher.repository.TeacherRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final TeacherTagRepository teacherTagRepository;

    public Teacher createTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }
    public Teacher getTeacher(long teacherId){
        Optional<Teacher> teacher = teacherRepository.findById(teacherId);
        return teacher.orElseThrow(() -> new BusinessLogicException(ExceptionCode.TEACHER_NOT_FOUND));
    }
}

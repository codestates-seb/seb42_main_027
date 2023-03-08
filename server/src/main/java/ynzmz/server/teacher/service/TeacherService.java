package ynzmz.server.teacher.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ynzmz.server.error.exception.BusinessLogicException;
import ynzmz.server.error.exception.ExceptionCode;
import ynzmz.server.teacher.entity.Teacher;
import ynzmz.server.teacher.repository.TeacherRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeacherService {
    private final TeacherRepository teacherRepository;

    public Teacher createTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    public Teacher updateTeacher(Teacher teacher) {
        Teacher findTeacher = findTeacherById(teacher.getTeacherId());
        Optional.ofNullable(teacher.getName()).ifPresent(findTeacher::setName);
        Optional.ofNullable(teacher.getIntroduction()).ifPresent(findTeacher::setIntroduction);
        return teacherRepository.save(findTeacher);
    }

    public Page<Teacher> findTeachers(int page, int size) {
        return teacherRepository.findAll(PageRequest.of(page, size, Sort.by("teacherId").descending()));
    }
    public Teacher findTeacherById(long teacherId){
        Optional<Teacher> teacher = teacherRepository.findById(teacherId);
        return teacher.orElseThrow(() -> new BusinessLogicException(ExceptionCode.TEACHER_NOT_FOUND));
    }
}

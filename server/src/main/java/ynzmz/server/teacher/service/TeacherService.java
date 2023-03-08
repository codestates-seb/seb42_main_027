package ynzmz.server.teacher.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ynzmz.server.teacher.entity.Teacher;
import ynzmz.server.teacher.repository.TeacherRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeacherService {
    private final TeacherRepository teacherRepository;

    public Teacher createTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }
    public Teacher getTeacher(long teacherId) throws Exception {
        Optional<Teacher> teacher = teacherRepository.findById(teacherId);
        return teacher.orElseThrow(() -> new Exception("강사가없습니다"));
    }
}

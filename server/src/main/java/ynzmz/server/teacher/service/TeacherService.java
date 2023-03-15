package ynzmz.server.teacher.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ynzmz.server.error.exception.BusinessLogicException;
import ynzmz.server.error.exception.ExceptionCode;
import ynzmz.server.lecture.entity.Lecture;
import ynzmz.server.tag.entity.GradeTag;
import ynzmz.server.tag.entity.PlatformTag;
import ynzmz.server.tag.entity.SubjectTag;
import ynzmz.server.teacher.entity.Teacher;
import ynzmz.server.teacher.repository.TeacherRepository;

import javax.transaction.Transactional;
import java.util.List;
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

    public Page<Teacher> findTeachers(GradeTag.Grade grade, PlatformTag.Platform platform, SubjectTag.Subject subject, String name, String sort, int page, int size) {
        return teacherRepository.findAllByGradeAndPlatformAndSubjectAndName(grade, platform, subject, name, PageRequest.of(page, size, Sort.by(sort)));
    }

    public Page<Teacher> findTeachers(GradeTag.Grade grade, PlatformTag.Platform platform, SubjectTag.Subject subject, String name, String sort,String reverse, int page, int size) {
        return teacherRepository.findAllByGradeAndPlatformAndSubjectAndName(grade, platform, subject, name, PageRequest.of(page, size, Sort.by(sort).descending()));
    }

    public void deleteTeacher(long teacherId) {
        teacherRepository.deleteById(teacherId);
    }

    @Transactional
    public void setTeacherStarPointAverageAndTotalReviewCount(Teacher teacher) {
        List<Lecture> lectures = teacher.getLectures();
        double starPoint = 0;
        double starPointAverage;
        for(Lecture lecture : lectures) {
            starPoint += lecture.getStarPointAverage();
        }
        starPointAverage = starPoint / lectures.size();
        long totalReviewCount = lectures.size();
        teacher.setStarPointAverage(starPointAverage);
        teacher.setTotalReviewCount(totalReviewCount);
        teacherRepository.save(teacher);
    }

    public void setTotalReviewCount(Teacher teacher) {
        List<Lecture> lectures = teacher.getLectures();
        long totalReviewCount = lectures.size();
    }

    public Teacher findTeacherById(long teacherId){
        Optional<Teacher> teacher = teacherRepository.findById(teacherId);
        return teacher.orElseThrow(() -> new BusinessLogicException(ExceptionCode.TEACHER_NOT_FOUND));
    }
}

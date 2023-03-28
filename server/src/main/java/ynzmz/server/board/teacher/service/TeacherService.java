package ynzmz.server.board.teacher.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.stereotype.Service;
import ynzmz.server.board.review.lecture.entity.LectureReview;
import ynzmz.server.global.error.exception.BusinessLogicException;
import ynzmz.server.global.error.exception.ExceptionCode;
import ynzmz.server.board.lecture.entity.Lecture;
import ynzmz.server.tag.entity.GradeTag;
import ynzmz.server.tag.entity.PlatformTag;
import ynzmz.server.tag.entity.SubjectTag;
import ynzmz.server.board.teacher.entity.Teacher;
import ynzmz.server.board.teacher.repository.TeacherRepository;

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
        Optional.ofNullable(teacher.getProfileImageUrl()).ifPresent(findTeacher::setProfileImageUrl);
        Optional.ofNullable(teacher.getRealImageUrl()).ifPresent(findTeacher::setRealImageUrl);
        Optional.ofNullable(teacher.getProfile()).ifPresent(findTeacher::setProfile);
        Optional.ofNullable(teacher.getAnalects()).ifPresent(findTeacher::setAnalects);
        return teacherRepository.save(findTeacher);
    }

    public Page<Teacher> findTeachers(GradeTag.Grade grade, PlatformTag.Platform platform, SubjectTag.Subject subject, String name, String sort, int page, int size) {
        return teacherRepository.findAllByGradeAndPlatformAndSubjectAndName(grade, platform, subject, name, PageRequest.of(page, size, Sort.by(sort)));
    }

    public Page<Teacher> findTeachers(GradeTag.Grade grade, PlatformTag.Platform platform, SubjectTag.Subject subject, String name, String sort,String reverse, int page, int size) {
        return teacherRepository.findAllByGradeAndPlatformAndSubjectAndName(grade, platform, subject, name, PageRequest.of(page, size, Sort.by(sort).descending()));
    }
    public Page<Teacher> findTeachersByRandom(GradeTag.Grade grade, PlatformTag.Platform platform, SubjectTag.Subject subject, String name, String sort, int page, int size) {
        return teacherRepository.findAllByGradeAndPlatformAndSubjectAndName(grade, platform, subject, name, PageRequest.of(page, size, JpaSort.unsafe("RAND()")));
    }

    @Transactional
    public void deleteTeacher(long teacherId) {
        teacherRepository.deleteById(teacherId);
    }

    @Transactional
    public void setTeacherStarPointAverageAndTotalReviewCount(Teacher teacher) {
        List<Lecture> lectures = teacher.getLectures();
        double starPoint = 0;
        double starPointAverage;
        long totalReviewCount = 0;
        for(Lecture lecture : lectures) {
            for(LectureReview lectureReview : lecture.getLectureReviews()) {
                starPoint += lectureReview.getStarPoint();
                totalReviewCount ++;
            }
        }
        starPointAverage = starPoint / totalReviewCount;
        teacher.setStarPointAverage(starPointAverage);
        teacher.setTotalReviewCount(totalReviewCount);
        teacherRepository.save(teacher);
    }

    public Teacher findTeacherById(long teacherId){
        Optional<Teacher> teacher = teacherRepository.findById(teacherId);
        return teacher.orElseThrow(() -> new BusinessLogicException(ExceptionCode.TEACHER_NOT_FOUND));
    }
    public Optional<Teacher> findOptionalTeacherById(long teacherId){
        return teacherRepository.findById(teacherId);
    }
}

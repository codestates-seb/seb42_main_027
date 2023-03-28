package ynzmz.server.tag.manager;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ynzmz.server.board.lecture.entity.Lecture;
import ynzmz.server.board.teacher.entity.Teacher;
import ynzmz.server.tag.entity.lecture.LectureGradeTag;
import ynzmz.server.tag.entity.lecture.LecturePlatformTag;
import ynzmz.server.tag.entity.lecture.LectureSubjectTag;
import ynzmz.server.tag.entity.teacher.TeacherGradeTag;
import ynzmz.server.tag.entity.teacher.TeacherPlatformTag;
import ynzmz.server.tag.entity.teacher.TeacherSubjectTag;
import ynzmz.server.tag.repository.LectureGradeTagRepository;
import ynzmz.server.tag.repository.LecturePlatformTagRepository;
import ynzmz.server.tag.repository.LectureSubjectTagRepository;

import javax.transaction.Transactional;

@Component
@RequiredArgsConstructor
public class LectureTagManager {
    private final LectureGradeTagRepository lectureGradeTagRepository;
    private final LecturePlatformTagRepository lecturePlatformTagRepository;
    private final LectureSubjectTagRepository lectureSubjectTagRepository;
    public void saveLectureGradeTag(LectureGradeTag lectureGradeTag) {
        lectureGradeTagRepository.save(lectureGradeTag);
    }

    public void saveLecturePlatformTag(LecturePlatformTag lecturePlatformTag) {
        lecturePlatformTagRepository.save(lecturePlatformTag);
    }
    public void saveLectureSubjectTag(LectureSubjectTag lectureSubjectTag) {
        lectureSubjectTagRepository.save(lectureSubjectTag);
    }
    @Transactional
    public void deleteAllLectureTagByLecture(Lecture lecture) {
        lectureGradeTagRepository.deleteAllTeacherGradeTagByLecture(lecture);
        lecturePlatformTagRepository.deleteAllLecturePlatformTagByLecture(lecture);
        lectureSubjectTagRepository.deleteAllLectureSubjectTagByLecture(lecture);
    }
}

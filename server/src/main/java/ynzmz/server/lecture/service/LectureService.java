package ynzmz.server.lecture.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ynzmz.server.error.exception.BusinessLogicException;
import ynzmz.server.error.exception.ExceptionCode;
import ynzmz.server.lecture.entity.Lecture;
import ynzmz.server.lecture.repository.LectureRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LectureService {
    private final LectureRepository lectureRepository;

    public Lecture createdLecture(Lecture lecture) {
        return lectureRepository.save(lecture);
    }

    public Lecture updateLecture(Lecture lecture) {
        Lecture findLecture = findLectureById(lecture.getLectureId());

        Optional.ofNullable(lecture.getName()).ifPresent(findLecture::setName);
        Optional.ofNullable(lecture.getIntroduction()).ifPresent(findLecture::setIntroduction);
        Optional.ofNullable(lecture.getTeacher()).ifPresent(findLecture::setTeacher);

        return lectureRepository.save(findLecture);
    }

    public Lecture findLectureById(long lectureId){
        Optional<Lecture> lecture = lectureRepository.findById(lectureId);
        return lecture.orElseThrow(() -> new BusinessLogicException(ExceptionCode.TEACHER_NOT_FOUND));
    }
}

package ynzmz.server.lecture.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ynzmz.server.error.exception.BusinessLogicException;
import ynzmz.server.error.exception.ExceptionCode;
import ynzmz.server.lecture.entity.Lecture;
import ynzmz.server.lecture.repository.LectureRepository;
import ynzmz.server.review.lecture.entity.LectureReview;

import javax.transaction.Transactional;
import java.util.List;
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

        Optional.ofNullable(lecture.getTitle()).ifPresent(findLecture::setTitle);
        Optional.ofNullable(lecture.getIntroduction()).ifPresent(findLecture::setIntroduction);
        Optional.ofNullable(lecture.getTeacher()).ifPresent(findLecture::setTeacher);

        return lectureRepository.save(findLecture);
    }

    public Page<Lecture> findLectures(int page, int size) {
        return lectureRepository.findAll(PageRequest.of(page, size, Sort.by("lectureId").descending()));
    }

//    public Page<Lecture> findLectures(String tag, int page, int size) {
//        return lectureRepository.findByTagType(tag, PageRequest.of(page, size, Sort.by("lectureId").descending()));
//    }

    public Page<Lecture> findLecturesByTeacher(long teacherId, int page, int size) {
        return lectureRepository.findByTeacherId(teacherId, PageRequest.of(page, size, Sort.by("lectureId").descending()));
    }

    public void deleteLecture(long lectureId) {
        Lecture lecture = findLectureById(lectureId);
        lectureRepository.delete(lecture);
    }

    public Lecture findLectureById(long lectureId){
        Optional<Lecture> lecture = lectureRepository.findById(lectureId);
        return lecture.orElseThrow(() -> new BusinessLogicException(ExceptionCode.TEACHER_NOT_FOUND));
    }

    //강의 평균 별점 저장
    @Transactional
    public void setLectureStaPointAverageAndTotalReviewCount(Lecture lecture){
        List<LectureReview> lectureReviews = lecture.getLectureReviews();
        double starPoint = 0;
        double starPointAverage;
        for(LectureReview lectureReview : lectureReviews) {
            starPoint += lectureReview.getStarPoint();
        }
        starPointAverage = starPoint / lectureReviews.size();
        long totalReviewCount = lectureReviews.size();
        lecture.setStarPointAverage(starPointAverage);
        lecture.setTotalReviewCount(totalReviewCount);
        lectureRepository.save(lecture);
    }

}

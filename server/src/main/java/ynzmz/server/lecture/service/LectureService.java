package ynzmz.server.lecture.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.stereotype.Service;
import ynzmz.server.error.exception.BusinessLogicException;
import ynzmz.server.error.exception.ExceptionCode;
import ynzmz.server.lecture.entity.Lecture;
import ynzmz.server.lecture.repository.LectureRepository;
import ynzmz.server.board.review.lecture.entity.LectureReview;
import ynzmz.server.tag.entity.GradeTag;
import ynzmz.server.tag.entity.PlatformTag;
import ynzmz.server.tag.entity.SubjectTag;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LectureService {
    private final LectureRepository lectureRepository;
    @Transactional
    public Lecture createdLecture(Lecture lecture) {
        return lectureRepository.save(lecture);
    }

    public Lecture updateLecture(Lecture lecture) {
        Lecture findLecture = findLectureById(lecture.getLectureId());

        Optional.ofNullable(lecture.getTitle()).ifPresent(findLecture::setTitle);
        Optional.ofNullable(lecture.getIntroduction()).ifPresent(findLecture::setIntroduction);
        Optional.ofNullable(lecture.getStatus()).ifPresent(findLecture::setStatus);

        return lectureRepository.save(findLecture);
    }


    public Page<Lecture> findLectures(GradeTag.Grade grade, PlatformTag.Platform platform, SubjectTag.Subject subject, String title, String sort, int page, int size) {
        return lectureRepository.findAllByGradeAndPlatformAndSubjectAndTitle(grade, platform, subject, title, PageRequest.of(page, size, Sort.by(sort)));
    }

    public Page<Lecture> findLectures(GradeTag.Grade grade, PlatformTag.Platform platform, SubjectTag.Subject subject, String title, String sort,String reverse, int page, int size) {
        return lectureRepository.findAllByGradeAndPlatformAndSubjectAndTitle(grade, platform, subject, title, PageRequest.of(page, size, Sort.by(sort).descending()));
    }

    public Page<Lecture> findLecturesByRandom(GradeTag.Grade grade, PlatformTag.Platform platform, SubjectTag.Subject subject, String title, String sort, int page, int size) {
        return lectureRepository.findAllByGradeAndPlatformAndSubjectAndTitle(grade, platform, subject, title, PageRequest.of(page, size, JpaSort.unsafe("RAND()")));
    }

//    public Page<Lecture> findLecturesN(GradeTag.Grade grade, PlatformTag.Platform platform, SubjectTag.Subject subject, String title, String sort, int page, int size) {
//        List<Long> lectureIds = lectureRepository.findLectureIdsByGradeAndPlatformAndSubjectAndTitle(grade, platform, subject, title, PageRequest.of(page, size, Sort.by(sort)));
//        List<Lecture> lectures = lectureRepository.findLecturesByIds(lectureIds);
//
//        int i = lectureRepository.countByGradeAndPlatformAndSubjectAndTitle(grade, platform, subject, title);
//
//
//        return new PageImpl<>(lectures, PageRequest.of(page, size), i);
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
    public void setLectureStarPointAverageAndTotalReviewCount(Lecture lecture){
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

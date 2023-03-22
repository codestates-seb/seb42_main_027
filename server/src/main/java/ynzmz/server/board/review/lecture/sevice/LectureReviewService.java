package ynzmz.server.board.review.lecture.sevice;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ynzmz.server.board.review.lecture.entity.LectureReview;
import ynzmz.server.board.review.lecture.repository.LectureReviewRepository;
import ynzmz.server.error.exception.BusinessLogicException;
import ynzmz.server.error.exception.ExceptionCode;
import ynzmz.server.lecture.entity.Lecture;
import ynzmz.server.teacher.entity.Teacher;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LectureReviewService {
    private final LectureReviewRepository lectureReviewRepository;

    public LectureReview createLectureReview(LectureReview lectureReview) {
        return lectureReviewRepository.save(lectureReview);
    }

    public LectureReview updateLectureReview(LectureReview lectureReview) {
        LectureReview findLectureReview = findLectureReviewById(lectureReview.getLectureReviewId());

        Optional.ofNullable(lectureReview.getTitle()).ifPresent(findLectureReview::setTitle);
        Optional.ofNullable(lectureReview.getContent()).ifPresent(findLectureReview::setContent);
        Optional.of(lectureReview.getStarPoint()).ifPresent(findLectureReview::setStarPoint);
        Optional.ofNullable(lectureReview.getModifiedAt()).ifPresent(findLectureReview::setModifiedAt);

        return lectureReviewRepository.save(findLectureReview);
    }

    public Page<LectureReview> findAllLectureReviews(int page, int size) {
        return lectureReviewRepository.findAll(PageRequest.of(page, size, Sort.by("lectureReviewId").descending()));
    }

    public Page<LectureReview> findLectureReviewsByLecture(long lectureId, int page, int size) {
        return lectureReviewRepository.findLectureReviewByLectureLectureId(lectureId, PageRequest.of(page, size, Sort.by("lectureReviewId").descending()));
    }
    public Page<LectureReview> findLectureReviewsByTeacher(long teacherId, int page, int size) {
        return lectureReviewRepository.findLectureReviewByLectureTeacherTeacherId(teacherId, PageRequest.of(page, size, Sort.by("teacherId").descending()));
    }

    public List<LectureReview> findLectureReviewsByMemberId(long memberId) {
        return lectureReviewRepository.findByMemberId(memberId);
    }

    public void deleteLectureReview(long lectureReviewId) {
        lectureReviewRepository.deleteById(lectureReviewId);
    }


    public LectureReview findLectureReviewById(long lectureReviewId) {
        Optional<LectureReview> lectureReviewPost = lectureReviewRepository.findById(lectureReviewId);
        return lectureReviewPost.orElseThrow(() -> new BusinessLogicException(ExceptionCode.LECTURE_REVIEW_NOT_FOUND));
    }

    public Map<String,Long> findStarPointCountByTeacher(Teacher teacher) {
        return getStarPointCount(lectureReviewRepository.findAllByTeacherId(teacher.getTeacherId()));
    }

    public Map<String,Long> findStarPointCountByLecture(Lecture lecture) {
        return getStarPointCount(lectureReviewRepository.findAllByLectureId(lecture.getLectureId()));
    }

    private static Map<String, Long> getStarPointCount(List<LectureReview> lectureReviews) {
        Map<String, Long> starPointCount = new HashMap<>();
        for(int i = 1; i <= 5; i++) starPointCount.put(i + "점갯수",0L);

        for( LectureReview lectureReview : lectureReviews) {
            int lectureReviewStarPoint = lectureReview.getStarPoint();
            String key = lectureReviewStarPoint + "점갯수";
            starPointCount.put(key, starPointCount.get(key) + 1);
        }
        return starPointCount;
    }

}

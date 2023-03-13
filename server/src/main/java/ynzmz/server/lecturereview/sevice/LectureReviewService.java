package ynzmz.server.lecturereview.sevice;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ynzmz.server.error.exception.BusinessLogicException;
import ynzmz.server.error.exception.ExceptionCode;
import ynzmz.server.lecturereview.entity.LectureReview;
import ynzmz.server.lecturereview.repository.LectureReviewRepository;

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

    public void deleteLectureReview(long lectureReviewId) {
        lectureReviewRepository.deleteById(lectureReviewId);
    }


    public LectureReview findLectureReviewById(long lectureReviewId) {
        Optional<LectureReview> lectureReviewPost = lectureReviewRepository.findById(lectureReviewId);
        return lectureReviewPost.orElseThrow(() -> new BusinessLogicException(ExceptionCode.LECTURE_REVIEW_NOT_FOUND));
    }

}

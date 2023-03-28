package ynzmz.server.comment.review.lecture.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ynzmz.server.comment.review.lecture.entity.LectureReviewComment;
import ynzmz.server.comment.review.lecture.repository.LectureReviewCommentRepository;
import ynzmz.server.global.error.exception.BusinessLogicException;
import ynzmz.server.global.error.exception.ExceptionCode;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LectureReviewCommentService {

    private final LectureReviewCommentRepository lectureReviewCommentRepository;

    public LectureReviewComment createLectureReviewComment(LectureReviewComment lectureReviewComment){
        return lectureReviewCommentRepository.save(lectureReviewComment);
    }

    public LectureReviewComment updateLectureReviewComment(LectureReviewComment lectureReviewComment){
        LectureReviewComment findLectureReviewComment = findLectureReviewCommentById(lectureReviewComment.getLectureReviewCommentId());

        Optional.ofNullable(lectureReviewComment.getContent()).ifPresent(findLectureReviewComment::setContent);
        Optional.ofNullable(lectureReviewComment.getModifiedAt()).ifPresent(findLectureReviewComment::setModifiedAt);

        return lectureReviewCommentRepository.save(findLectureReviewComment);
    }

    public Page<LectureReviewComment> getLectureReviewComments(long lectureReviewId, String filter, int page, int size) {
        return lectureReviewCommentRepository.findLectureReviewCommentsByLectureReviewLectureReviewId(lectureReviewId, PageRequest.of(page, size, Sort.by(filter).descending()));
    }

    public void deleteLectureReviewComment(long lectureReviewCommentId) {
        lectureReviewCommentRepository.deleteById(lectureReviewCommentId);
    }

    public LectureReviewComment findLectureReviewCommentById(long lectureReviewCommentId) {
        Optional<LectureReviewComment> lectureReviewPostComment = lectureReviewCommentRepository.findById(lectureReviewCommentId);
        return lectureReviewPostComment.orElseThrow(() -> new BusinessLogicException(ExceptionCode.LECTURE_REVIEW_COMMENT_NOT_FOUND));
    }

    public List<LectureReviewComment> findLectureReviewCommentsByMemberId(long memberId) {
        return lectureReviewCommentRepository.findByMemberId(memberId);
    }
}

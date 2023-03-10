package ynzmz.server.comment.lecturereviewpost.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ynzmz.server.comment.lecturereviewpost.entity.LectureReviewPostComment;
import ynzmz.server.comment.lecturereviewpost.repository.LectureReviewPostCommentRepository;
import ynzmz.server.error.exception.BusinessLogicException;
import ynzmz.server.error.exception.ExceptionCode;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LectureReviewPostCommentService{

    private final LectureReviewPostCommentRepository lectureReviewPostCommentRepository;

    public LectureReviewPostComment createLectureReviewPostComment(LectureReviewPostComment lectureReviewPostComment){
        return lectureReviewPostCommentRepository.save(lectureReviewPostComment);
    }

    public LectureReviewPostComment updateLectureReviewPostComment(LectureReviewPostComment lectureReviewPostComment){
        LectureReviewPostComment findLectureReviewPostComment = findLectureReviewPostCommentById(lectureReviewPostComment.getLectureReviewPostCommentId());

        Optional.ofNullable(lectureReviewPostComment.getContent()).ifPresent(findLectureReviewPostComment::setContent);
        Optional.ofNullable(lectureReviewPostComment.getModifiedAt()).ifPresent(findLectureReviewPostComment::setModifiedAt);

        return lectureReviewPostCommentRepository.save(findLectureReviewPostComment);
    }

    public Page<LectureReviewPostComment> getLectureReviewPostComments(long lectureReviewPostId, String filter, int page, int size) {
        return lectureReviewPostCommentRepository.findLectureReviewPostCommentsByLectureReviewPostLectureReviewPostId(lectureReviewPostId, PageRequest.of(page, size, Sort.by(filter).descending()));
    }

    public void deleteLectureReviewPostComment(long lectureReviewPostCommentId) {
        lectureReviewPostCommentRepository.deleteById(lectureReviewPostCommentId);
    }

    public LectureReviewPostComment findLectureReviewPostCommentById(long lectureReviewPostCommentId) {
        Optional<LectureReviewPostComment> lectureReviewPostComment = lectureReviewPostCommentRepository.findById(lectureReviewPostCommentId);
        return lectureReviewPostComment.orElseThrow(() -> new BusinessLogicException(ExceptionCode.LECTURE_REVIEW_POST_COMMENT_NOT_FOUND));
    }
}

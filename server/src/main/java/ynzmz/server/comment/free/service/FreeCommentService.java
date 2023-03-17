package ynzmz.server.comment.free.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ynzmz.server.comment.free.entity.FreeComment;
import ynzmz.server.comment.free.repository.FreeCommentRepository;
import ynzmz.server.error.exception.BusinessLogicException;
import ynzmz.server.error.exception.ExceptionCode;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FreeCommentService {

    private final FreeCommentRepository freeCommentRepository;

    public FreeComment creatFreeReviewComment(FreeComment freeComment){
        return freeCommentRepository.save(freeComment);
    }

    public FreeComment updateFreeReviewComment(FreeComment freeComment){
        FreeComment findFreeComment = findFreeReviewCommentById(freeComment.getLectureReviewCommentId());

        Optional.ofNullable(freeComment.getContent()).ifPresent(findFreeComment::setContent);
        Optional.ofNullable(freeComment.getModifiedAt()).ifPresent(findFreeComment::setModifiedAt);

        return freeCommentRepository.save(findFreeComment);
    }

    public Page<FreeComment> getFreeReviewComments(long lectureReviewId, String filter, int page, int size) {
        return freeCommentRepository.findLectureReviewCommentsByLectureReviewLectureReviewId(lectureReviewId, PageRequest.of(page, size, Sort.by(filter).descending()));
    }

    public void deleteFreeReviewComment(long lectureReviewCommentId) {
        freeCommentRepository.deleteById(lectureReviewCommentId);
    }

    public FreeComment findFreeReviewCommentById(long lectureReviewCommentId) {
        Optional<FreeComment> lectureReviewPostComment = freeCommentRepository.findById(lectureReviewCommentId);
        return lectureReviewPostComment.orElseThrow(() -> new BusinessLogicException(ExceptionCode.LECTURE_REVIEW_COMMENT_NOT_FOUND));
    }
}

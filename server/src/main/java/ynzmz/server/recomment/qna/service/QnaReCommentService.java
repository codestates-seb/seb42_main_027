package ynzmz.server.recomment.qna.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ynzmz.server.error.exception.BusinessLogicException;
import ynzmz.server.error.exception.ExceptionCode;
import ynzmz.server.recomment.qna.entity.QnaReComment;
import ynzmz.server.recomment.qna.repository.QnaReCommentRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QnaReCommentService {

    private final QnaReCommentRepository qnaReCommentRepository;

    public QnaReComment createQnaReComment(QnaReComment qnaReComment){
        return qnaReCommentRepository.save(qnaReComment);
    }

    public QnaReComment updateQnaReComment(QnaReComment qnaReComment){
        QnaReComment findQnaReComment = findQnaReCommentById(qnaReComment.getQnaReCommentId());

        Optional.ofNullable(qnaReComment.getContent()).ifPresent(findQnaReComment::setContent);
        Optional.ofNullable(qnaReComment.getModifiedAt()).ifPresent(findQnaReComment::setModifiedAt);

        return qnaReCommentRepository.save(findQnaReComment);
    }

    public void deleteQnaReComment(long qnaReCommentId) {
        qnaReCommentRepository.deleteById(qnaReCommentId);
    }

    public QnaReComment findQnaReCommentById(long qnaReCommentId) {
        Optional<QnaReComment> qnaComment = qnaReCommentRepository.findById(qnaReCommentId);
        return qnaComment.orElseThrow(() -> new BusinessLogicException(ExceptionCode.QNA_RE_COMMENT_NOT_FOUND));
    }
}

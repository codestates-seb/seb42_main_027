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

    public FreeComment creatFreeComment(FreeComment freeComment){
        return freeCommentRepository.save(freeComment);
    }

    public FreeComment updateFreeComment(FreeComment freeComment){
        FreeComment findFreeComment = findFreeCommentById(freeComment.getFreeCommentId());

        Optional.ofNullable(freeComment.getContent()).ifPresent(findFreeComment::setContent);
        Optional.ofNullable(freeComment.getModifiedAt()).ifPresent(findFreeComment::setModifiedAt);

        return freeCommentRepository.save(findFreeComment);
    }
//
//    public Page<FreeComment> getFrees(long freeId, String filter, int page, int size) {
//        return freeCommentRepository.findFreeCommentsByFreeId(freeId, PageRequest.of(page, size, Sort.by(filter).descending()));
//    }

    public void deleteFreeComment(long freeId) {
        freeCommentRepository.deleteById(freeId);
    }

    public FreeComment findFreeCommentById(long freeId) {
        Optional<FreeComment> FreeComment = freeCommentRepository.findById(freeId);
        return FreeComment.orElseThrow(() -> new BusinessLogicException(ExceptionCode.FREE_NOT_FOUND));
    }

    public void getSimilarityMember(FreeComment freeComment){
        if (freeComment.getMember() == freeComment.getFree().getMember())
        {
            freeComment.setMemberSim(true);
        }
        else
        {
            freeComment.setMemberSim(false);
        }
    }
}

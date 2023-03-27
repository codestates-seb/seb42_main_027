package ynzmz.server.recomment.free.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ynzmz.server.recomment.free.entity.FreeReComment;
import ynzmz.server.recomment.free.repository.ReCommentRepository;
import ynzmz.server.global.error.exception.BusinessLogicException;
import ynzmz.server.global.error.exception.ExceptionCode;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReCommentService {

    private final ReCommentRepository recommentRepository;

    public FreeReComment creatRecomment(FreeReComment recomment){
        return recommentRepository.save(recomment);
    }

    public FreeReComment updateRecomment(FreeReComment recomment){
        FreeReComment findFreeReComment = findReCommentById(recomment.getFreeReCommentId());

        Optional.ofNullable(recomment.getContent()).ifPresent(findFreeReComment::setContent);
        Optional.ofNullable(recomment.getModifiedAt()).ifPresent(findFreeReComment::setModifiedAt);

        return recommentRepository.save(findFreeReComment);
    }
//
//    public Page<Recomment> getRecomments(long freeId, String filter, int page, int size) {
//        return freeCommentRepository.findrecommentsByrecommandId(freeId, PageRequest.of(page, size, Sort.by(filter).descending()));
//    }

    public void deleteReComment(long freeId) {
        recommentRepository.deleteById(freeId);
    }

    public FreeReComment findReCommentById(long freeId) {
        Optional<FreeReComment> Recomment = recommentRepository.findById(freeId);
        return Recomment.orElseThrow(() -> new BusinessLogicException(ExceptionCode.FREE_NOT_FOUND));
    }

    public void getSimilarityMember(FreeReComment recomment){
        if (recomment.getMember().getMemberId() == recomment.getComment().getMember().getMemberId())
        {
            recomment.setMemberSim(true);
        }
        else
        {
            recomment.setMemberSim(false);
        }
    }
}

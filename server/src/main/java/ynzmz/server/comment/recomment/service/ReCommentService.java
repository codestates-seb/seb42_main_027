package ynzmz.server.comment.recomment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ynzmz.server.comment.recomment.entity.ReComment;
import ynzmz.server.comment.recomment.repository.ReCommentRepository;
import ynzmz.server.error.exception.BusinessLogicException;
import ynzmz.server.error.exception.ExceptionCode;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReCommentService {

    private final ReCommentRepository recommentRepository;

    public ReComment creatRecomment(ReComment recomment){
        return recommentRepository.save(recomment);
    }

    public ReComment updateRecomment(ReComment recomment){
        ReComment findReComment = findReCommentById(recomment.getReCommentId());

        Optional.ofNullable(recomment.getContent()).ifPresent(findReComment::setContent);
        Optional.ofNullable(recomment.getModifiedAt()).ifPresent(findReComment::setModifiedAt);

        return recommentRepository.save(findReComment);
    }
//
//    public Page<Recomment> getRecomments(long freeId, String filter, int page, int size) {
//        return freeCommentRepository.findrecommentsByrecommandId(freeId, PageRequest.of(page, size, Sort.by(filter).descending()));
//    }

    public void deleteReComment(long freeId) {
        recommentRepository.deleteById(freeId);
    }

    public ReComment findReCommentById(long freeId) {
        Optional<ReComment> Recomment = recommentRepository.findById(freeId);
        return Recomment.orElseThrow(() -> new BusinessLogicException(ExceptionCode.FREE_NOT_FOUND));
    }

    public void getSimilarityMember(ReComment recomment){
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

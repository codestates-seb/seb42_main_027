package ynzmz.server.board.free.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ynzmz.server.board.free.repository.FreeRepository;
import ynzmz.server.board.review.lecture.entity.LectureReview;
import ynzmz.server.error.exception.BusinessLogicException;
import ynzmz.server.error.exception.ExceptionCode;
import ynzmz.server.board.free.entity.Free;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class FreeService {

    private final FreeRepository repository;
    //--------------------------------------------CREATE--------------------------------------------------------
    public Free createFree(Free free){
        return repository.save(free);
    }
    //--------------------------------------------READ----------------------------------------------------------
    public Free findFreeById(long freeId){
        return repository.findById(freeId).orElseThrow(()-> new BusinessLogicException(ExceptionCode.FREE_NOT_FOUND));

    }

    public List<Free> findFreesByMemberId(long memberId) {
        return repository.findByMemberId(memberId);
    }

    public Page<Free> findAllFree(int page){
        return repository.findAll(PageRequest.of(page,15, Sort.by("freeId").descending()));
    }
    //-------------------------------------------UPDATE---------------------------------------------------------
    public Free updateFree(Free free){
        Free findFree = findFreeById(free.getFreeId());
        Optional.ofNullable(free.getTitle()).ifPresent(findFree::setTitle);
        Optional.ofNullable(free.getContent()).ifPresent(findFree::setContent);
        Optional.ofNullable(free.getViewCount()).ifPresent(findFree::setViewCount);
        Optional.ofNullable(free.getModifiedAt()).ifPresent(findFree::setModifiedAt);
        Optional.ofNullable(free.getTitle()).ifPresent(findFree::setTitle);

        return repository.save(findFree);

    }
    //-------------------------------------------DELETE---------------------------------------------------------

    public void deleteFree(long freeId){
        repository.deleteById(freeId);
    }

}

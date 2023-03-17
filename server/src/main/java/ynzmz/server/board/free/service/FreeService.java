package ynzmz.server.board.free.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ynzmz.server.board.free.repository.FreeRepository;
import ynzmz.server.error.exception.BusinessLogicException;
import ynzmz.server.error.exception.ExceptionCode;
import ynzmz.server.board.free.entity.Free;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FreeService {

    FreeRepository repository;
    //--------------------------------------------CREATE--------------------------------------------------------
    public Free createFree(Free free){
        return repository.save(free);
    }
    //--------------------------------------------READ----------------------------------------------------------
    public Free findFreeById(long id){
        return repository.findById(id).orElseThrow(()-> new BusinessLogicException(ExceptionCode.FREE_NOT_FOUND));

    }

    public Page<Free> findAllFree(int page){
        return repository.findAll(PageRequest.of(page,15, Sort.by("freeId")));
    }
    //-------------------------------------------UPDATE---------------------------------------------------------
    public Free updateFree(Free free){
        Free findFree = findFreeById(free.getId());
        Optional.ofNullable(findFree.getTitle()).ifPresent(findFree::setTitle);
        Optional.ofNullable(findFree.getContent()).ifPresent(findFree::setContent);
        Optional.ofNullable(findFree.getViewCount()).ifPresent(findFree::setViewCount);
        Optional.ofNullable(findFree.getModifiedAt()).ifPresent(findFree::setModifiedAt);
        Optional.ofNullable(findFree.getTitle()).ifPresent(findFree::setTitle);

        return repository.save(findFree);

    }
    //-------------------------------------------DELETE---------------------------------------------------------

    public void deleteFree(long id){
        repository.deleteById(id);
    }

}

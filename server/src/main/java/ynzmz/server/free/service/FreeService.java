package ynzmz.server.free.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ynzmz.server.error.exception.BusinessLogicException;
import ynzmz.server.error.exception.ExceptionCode;
import ynzmz.server.free.entity.Free;
import ynzmz.server.free.repository.FreeRepository;

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
}
package ynzmz.server.board.free.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ynzmz.server.board.free.repository.FreeRepository;

import ynzmz.server.global.error.exception.BusinessLogicException;
import ynzmz.server.global.error.exception.ExceptionCode;
import ynzmz.server.board.free.entity.Free;

import javax.transaction.Transactional;
import java.util.ArrayList;
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
    @Transactional
    public void deleteFree(long freeId){
        repository.deleteById(freeId);
    }


    //---------------------------------추가기능--------------------------------------------------------
    public void plusViewCount(Free free){
        free.setViewCount(free.getViewCount()+1);
        repository.save(free);
    }

    //-------------------------------------------검색기능----------------------------------------------------------
    public Page<Free> findAllFrees(String title, String sort, int page, int size) {
        List<Free> noticeFrees = repository.findNoticeListFree(PageRequest.of(0, 3, Sort.by("freeId").descending()));
        Page<Free> allFrees = repository.findFreesOutNotice(title, PageRequest.of(page, size, Sort.by(sort).descending()));
        List<Free> mergedList = new ArrayList<>(noticeFrees);
        allFrees.forEach(free -> {
            if(!noticeFrees.contains(mergedList)) {
                mergedList.add(free);
            }
        });

        int start = (int) allFrees.getPageable().getOffset();
        int end = Math.min((start + allFrees.getPageable().getPageSize()), mergedList.size());
        return new PageImpl<>(mergedList.subList(start, end), allFrees.getPageable(), mergedList.size());
    }
public Page<Free> findFreesByCategoryAndSortAndTitle(int page, String category,String sort,String title) {
    return repository.findFreesByCategory(category, PageRequest.of(page, 15, Sort.by(sort).descending()), title);
}
    public Page<Free> findFreesByCategoryAndSortAndTitle(int page, String category,String title) {
        return repository.findFreesByCategory(category, PageRequest.of(page, 15), title);
    }

    public Page<Free> findEventsByNotice(int page, String sort) {
        return repository.findNoticePageFree(PageRequest.of(page,15 ,Sort.by(sort).descending()));
    }



    public Page<Free> findFreesByCategory(int page, String title,String category,String sort){
        List<Free> noticeList = repository.findNoticeListFree(PageRequest.of(0, 3, Sort.by("freeId").descending()));
        Page<Free> freePage = repository.findFreesByCategory(category,PageRequest.of(page,15,Sort.by(sort).descending()),title);
        List<Free> mergedList = new ArrayList<>(noticeList);
        freePage.forEach(free -> {if (!noticeList.contains(mergedList)) {
            mergedList.add(free);
        }
        });

        int start = (int) freePage.getPageable().getOffset();
        int end = Math.min((start + freePage.getPageable().getPageSize()), mergedList.size());
        return new PageImpl<>(mergedList.subList(start, end), freePage.getPageable(), mergedList.size());
    }


    public Page<Free> findFreesWithSort(int page, String sort){
        return repository.findAll(PageRequest.of(page,15,Sort.by(sort).descending()));
    }

    public Page<Free> findFreesWithSort(int page, String sort,String title){
        return repository.findFreesBySort(PageRequest.of(page,15,Sort.by(sort).descending()), title);
    }
//    public Page<Free> findFreesByCategoryAndSort(int page, String category){
//        return repository.findFreesByCategory(category, PageRequest.of(page,15));
//    }

    public void getCommentNum(Free free){
        free.setCommentsListNum(free.getComments().size());
        repository.save(free);
    }


}

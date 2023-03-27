package ynzmz.server.board.event.our.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ynzmz.server.board.event.our.entity.Our;
import ynzmz.server.board.event.our.repository.OurRepository;
import ynzmz.server.global.error.exception.BusinessLogicException;
import ynzmz.server.global.error.exception.ExceptionCode;


import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OurService {
     private final OurRepository ourRepository;

    //--------------------------------------------CREATE--------------------------------------------------------
    public Our createEvent(Our event){
        return ourRepository.save(event);
    }


    //--------------------------------------------READ----------------------------------------------------------
    public Our findEvent(long eventId){
        Optional<Our> event = ourRepository.findById(eventId);

        return event.orElseThrow(()->new BusinessLogicException(ExceptionCode.EVENT_NOT_FOUND));
    }

    public Page<Our> findAllEvents(int page){
        return ourRepository.findAll(PageRequest.of(page-1,15, Sort.by("eventId")));
    }
    //-------------------------------------------UPDATE---------------------------------------------------------
    public Our updateEvent(Our event){//추후 고유 이벤트 내용이 확정되면 이야기 하는 것으로
        Our findEvent = findEvent(event.getEventId());
        Optional.ofNullable(event.getTitle()).ifPresent(findEvent::setTitle);
        Optional.ofNullable(event.getDate()).ifPresent(findEvent::setDate);
        Optional.ofNullable(event.getDate()).ifPresent(findEvent::setDate);
        Optional.ofNullable(event.getContent()).ifPresent(findEvent::setContent);
    return ourRepository.save(findEvent);

    }





    // -------------------------------------------DELETE---------------------------------------------------------
    public void deleteAll(){
        ourRepository.deleteAll();
    }
    public void deleteEvent(Our event){
        ourRepository.delete(event);
    }



}



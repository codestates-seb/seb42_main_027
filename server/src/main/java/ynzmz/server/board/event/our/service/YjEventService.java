package ynzmz.server.board.event.our.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ynzmz.server.board.event.our.entity.YjEvent;
import ynzmz.server.board.event.our.repository.YjEventRepository;
import ynzmz.server.error.exception.BusinessLogicException;
import ynzmz.server.error.exception.ExceptionCode;


import java.util.Optional;

@Service
public class YjEventService {
    YjEventRepository yjEventRepository;

    //--------------------------------------------CREATE--------------------------------------------------------
    public YjEvent createEvent(YjEvent event){
        return yjEventRepository.save(event);
    }


    //--------------------------------------------READ----------------------------------------------------------
    public YjEvent findEvent(long eventId){
        Optional<YjEvent> event = yjEventRepository.findById(eventId);

        return event.orElseThrow(()->new BusinessLogicException(ExceptionCode.EVENT_NOT_FOUND));
    }

    public Page<YjEvent> findAllEvents(int page, int size){
        return yjEventRepository.findAll(PageRequest.of(page,size, Sort.by("eventId")));
    }
    //-------------------------------------------UPDATE---------------------------------------------------------
    public YjEvent updateEvent(YjEvent yjevent){//추후 고유 이벤트 내용이 확정되면 이야기 하는 것으로
        YjEvent findEvent = findEvent(yjevent.getEventId());
        Optional.ofNullable(yjevent.getTitle()).ifPresent(findEvent::setTitle);
        Optional.ofNullable(yjevent.getDate()).ifPresent(findEvent::setDate);
        Optional.ofNullable(yjevent.getDate()).ifPresent(findEvent::setDate);
        Optional.ofNullable(yjevent.getContent()).ifPresent(findEvent::setContent);
    return yjEventRepository.save(findEvent);

    }





    // -------------------------------------------DELETE---------------------------------------------------------
    public void deleteAll(){
        yjEventRepository.deleteAll();
    }
    public void deleteEvent(YjEvent event){
        yjEventRepository.delete(event);
    }
}



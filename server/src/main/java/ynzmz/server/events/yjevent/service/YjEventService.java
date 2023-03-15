package ynzmz.server.events.yjevent.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ynzmz.server.error.exception.BusinessLogicException;
import ynzmz.server.error.exception.ExceptionCode;
import ynzmz.server.events.yjevent.entity.YjEvent;
import ynzmz.server.events.yjevent.repository.YjEventRepository;


import java.util.Optional;

@Service
public class YjEventService {
    YjEventRepository yjEventRepository;

    //--------------------------------------------CREATE--------------------------------------------------------
    public void creatEvent(YjEvent event){
        yjEventRepository.save(event);
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
//    public void updateEvent(Event event){//추후 고유 이벤트 내용이 확정되면 이야기 하는 것으로
//        Optional<String> yzevent = Optional.of(event.getSource().getSourceType()).orElseThrow(()-> new BusinessLogicException())
//       Event foundEvent = findEvent(event.getEventId());
//
//       Optional.ofNullable(event.getDate()).ifPresent(foundEvent::setDate);
//       Optional.ofNullable(event.getTitle()).
//
//    }
//




    //-------------------------------------------DELETE---------------------------------------------------------
//    public void deleteAll(){
//        eventRepository.deleteAll();
//    }
//    public void deleteEvent(Event event){
//        eventRepository.delete(event);
//    }
//}

}

package ynzmz.server.events.eventboard.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ynzmz.server.error.exception.BusinessLogicException;
import ynzmz.server.error.exception.ExceptionCode;
import ynzmz.server.events.eventboard.entity.Event;
import ynzmz.server.events.eventboard.repository.EventRepository;

import java.util.Optional;

@Service
public class EventService {

    //Create와 Delete 만 구현 --> 더 필요할까? --> 야놀지말자 고유 이벤트도 포함시켜야 함
EventRepository eventRepository;

//--------------------------------------------CREATE--------------------------------------------------------
    public void creatEvent(Event event){
eventRepository.save(event);
    }


//--------------------------------------------READ----------------------------------------------------------
    public Event findEvent(long eventId){
        Optional<Event> event = eventRepository.findById(eventId);

        return event.orElseThrow(()->new BusinessLogicException(ExceptionCode.EVENT_NOT_FOUND));
    }

    public Page<Event> findAllEvents(int page, int size){
        return eventRepository.findAll(PageRequest.of(page,size, Sort.by("eventId")));
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
    public void deleteAll(){
        eventRepository.deleteAll();
    }
    public void deleteEvent(Event event){
        eventRepository.delete(event);
    }
}

package ynzmz.server.eventBoard.service;

import org.springframework.stereotype.Service;
import ynzmz.server.eventBoard.entity.Event;
import ynzmz.server.eventBoard.repository.EventRepository;

@Service
public class EventService {

    //Create와 Delete 만 구현 --> 더 필요할까?
EventRepository eventRepository;
    public void updateEvent(Event event){
eventRepository.save(event);
    }

    public void deleteEvent(Event event){
        eventRepository.delete(event);
    }

    public void deleteAll(){
        eventRepository.deleteAll();
    }
}

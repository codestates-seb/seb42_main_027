package ynzmz.server.event.outer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ynzmz.server.dto.MultiResponseDto;
import ynzmz.server.event.outer.dto.EventDto;
import ynzmz.server.event.outer.entity.Event;
import ynzmz.server.event.outer.mapper.EventMapper;
import ynzmz.server.event.outer.repository.EventRepository;
import ynzmz.server.event.outer.service.EventService;

import java.util.List;


@RestController
@RequestMapping("/outer-event")
@RequiredArgsConstructor
public class EventController {
    private final EventRepository eventRepository;
    private final EventService eventService;
    private final EventMapper eventMapper;

    @GetMapping
    public ResponseEntity<?> getAllEvents(@RequestParam int page,
                                        @RequestParam int size){
        Page<Event> pagedEvent = eventService.findAllEvents(page,size);
        List<Event> events = pagedEvent.getContent();
        List<EventDto.Response>response = eventMapper.eventToEventResponses(events);

        return new ResponseEntity<>(new MultiResponseDto<>(response,pagedEvent), HttpStatus.OK);
    } // patch,post, 개인 이벤트창은 프런트랑 논의해야함
}

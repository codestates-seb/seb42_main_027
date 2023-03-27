package ynzmz.server.board.event.their.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import ynzmz.server.board.event.their.service.EventService;
import ynzmz.server.global.dto.MultiResponseDto;
import ynzmz.server.board.event.their.dto.EventDto;
import ynzmz.server.board.event.their.entity.Event;
import ynzmz.server.board.event.their.mapper.EventMapper;
import ynzmz.server.board.event.their.repository.EventRepository;
import ynzmz.server.global.dto.SingleResponseDto;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/boards/events/theirs")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;
    private final EventMapper eventMapper;

    @PostMapping()
    public ResponseEntity<?> postEvent(@RequestBody EventDto.Post p){
       Event post = eventMapper.eventPostToevent(p);
       eventService.createEvent(post);
       EventDto.Response response = eventMapper.eventToEventResponse(post);
       return new ResponseEntity<>(new SingleResponseDto<>(response),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllEvents(@RequestParam int page,
                                        @RequestParam int size){
        Page<Event> pagedEvent = eventService.findAllEvents(page,size);
        List<Event> events = pagedEvent.getContent();
        List<EventDto.Response>response = eventMapper.eventToEventResponses(events);

        return new ResponseEntity<>(new MultiResponseDto<>(response,pagedEvent), HttpStatus.OK);
    }

}

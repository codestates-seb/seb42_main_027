package ynzmz.server.eventBoard.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ynzmz.server.eventBoard.entity.Event;
import ynzmz.server.eventBoard.mapper.EventMapper;
import ynzmz.server.eventBoard.repository.EventRepository;
import ynzmz.server.eventBoard.service.EventService;


@RestController
@RequestMapping("/event")
@RequiredArgsConstructor
public class EventController {
    private final EventRepository eventRepository;
    private final EventService eventService;
    private final EventMapper eventMapper;

    @GetMapping
    public ResponseEntity<?> getAllEvents(@RequestParam int page,
                                        @RequestParam int size){
        Page<Event>
    }
}

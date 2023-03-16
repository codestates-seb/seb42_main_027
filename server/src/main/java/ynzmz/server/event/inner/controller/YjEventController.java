package ynzmz.server.event.inner.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ynzmz.server.dto.SingleResponseDto;
import ynzmz.server.event.inner.dto.YjEventDto;
import ynzmz.server.event.inner.entity.YjEvent;
import ynzmz.server.event.inner.mapper.YjEventMapper;
import ynzmz.server.event.inner.repository.YjEventRepository;
import ynzmz.server.event.inner.service.YjEventService;

@RestController
@RequestMapping("/inner-event")
@RequiredArgsConstructor
public class YjEventController {

    YjEventService service;
    YjEventRepository repository;
    YjEventMapper mapper;


@PostMapping("/{event-id}")
    public ResponseEntity<?> postYj(@RequestBody YjEventDto.Post post){
    YjEvent event = mapper.yjEventPostToYjEvent(post);
    YjEvent Created = service.createEvent(event);
    return new ResponseEntity<>(new SingleResponseDto<>(Created), HttpStatus.CREATED );
    }
}

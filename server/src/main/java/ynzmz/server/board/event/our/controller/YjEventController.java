package ynzmz.server.board.event.our.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ynzmz.server.board.event.our.dto.YjEventDto;
import ynzmz.server.board.event.our.entity.YjEvent;
import ynzmz.server.board.event.our.mapper.YjEventMapper;
import ynzmz.server.board.event.our.repository.YjEventRepository;
import ynzmz.server.board.event.our.service.YjEventService;
import ynzmz.server.dto.SingleResponseDto;

@RestController
@RequestMapping("/boards/events/ours")
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

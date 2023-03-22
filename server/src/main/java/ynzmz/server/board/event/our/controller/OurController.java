package ynzmz.server.board.event.our.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ynzmz.server.board.event.our.dto.OurDto;
import ynzmz.server.board.event.our.entity.Our;
import ynzmz.server.board.event.our.mapper.OurMapper;
import ynzmz.server.board.event.our.repository.OurRepository;
import ynzmz.server.board.event.our.service.OurService;
import ynzmz.server.dto.SingleResponseDto;

@RestController
@RequestMapping("/boards/events/ours")
@RequiredArgsConstructor
public class OurController {

    OurService service;
    OurRepository repository;
    OurMapper mapper;


@PostMapping()
    public ResponseEntity<?> postOur( @RequestBody OurDto.Post post){
    Our event = mapper.ourPostToOur(post);
    Our Created = service.createEvent(event);
    return new ResponseEntity<>(new SingleResponseDto<>(Created), HttpStatus.CREATED);
    }

    @PatchMapping("/{event-id}")
    public ResponseEntity<?> patchOur(@PathVariable("event-id") int id,OurDto.Patch patch){
    Our event= mapper.ourPatchToOur(patch);
    event.setEventId(id);
    service.updateEvent(event);
    OurDto.Response response = mapper.ourToOurResponse(event);
    return new ResponseEntity<>(new SingleResponseDto<>(response),HttpStatus.OK);

    }
}

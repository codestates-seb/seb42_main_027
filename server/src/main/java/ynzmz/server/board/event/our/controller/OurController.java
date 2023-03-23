package ynzmz.server.board.event.our.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ynzmz.server.board.event.our.dto.OurDto;
import ynzmz.server.board.event.our.entity.Our;
import ynzmz.server.board.event.our.mapper.OurMapper;
import ynzmz.server.board.event.our.repository.OurRepository;
import ynzmz.server.board.event.our.service.OurService;
import ynzmz.server.dto.MultiResponseDto;
import ynzmz.server.dto.SingleResponseDto;
import ynzmz.server.member.entity.Member;
import ynzmz.server.member.service.MemberService;

import java.util.List;

@RestController
@RequestMapping("/boards/events/ours")
@RequiredArgsConstructor
public class OurController {

    private final OurService service;
    private final OurMapper mapper;
    private final MemberService memberService;


@PostMapping()
    public ResponseEntity<?> postOur( @RequestBody OurDto.Post post){
    Our event = mapper.ourPostToOur(post);
    Our Created = service.createEvent(event);
    return new ResponseEntity<>(new SingleResponseDto<>(Created), HttpStatus.CREATED);
    }

    @PatchMapping("/{event-id}")
    public ResponseEntity<?> patchOur(@PathVariable("event-id") int id,@RequestBody OurDto.Patch patch){
    Our event= mapper.ourPatchToOur(patch);
    event.setEventId(id);
    service.updateEvent(event);
    OurDto.Response response = mapper.ourToOurResponse(event);
    return new ResponseEntity<>(new SingleResponseDto<>(response),HttpStatus.OK);

    }

    @DeleteMapping("/{event-id}")
    public void deleteOur(@PathVariable("event-id") int id){
    Our delete = service.findEvent(id);
    service.deleteEvent(delete);
    }


    @GetMapping
    public ResponseEntity<?> GetAllOurs(@RequestParam int page){
        Page<Our> ourPage = service.findAllEvents(page);
        List<Our> ourList = ourPage.getContent();
        List<OurDto.ListResponse> responses = mapper.ourToOurListResponse(ourList);
        return new ResponseEntity<>(new MultiResponseDto(responses,ourPage),HttpStatus.OK);
    }

    @GetMapping("/{event-id}")
    public ResponseEntity<?> GetOur(@PathVariable("event-id") int id){
        Our Our = service.findEvent(id);
        OurDto.Response response = mapper.ourToOurResponse(Our);
        return new ResponseEntity<>(new SingleResponseDto<>(response),HttpStatus.OK);
    }



    private Member loginMemberFindByToken(){
        String loginEmail = SecurityContextHolder.getContext().getAuthentication().getName(); // 토큰에서 유저 email 확인
        return memberService.findMemberByEmail(loginEmail);
    }

}

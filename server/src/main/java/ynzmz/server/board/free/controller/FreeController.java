package ynzmz.server.board.free.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ynzmz.server.board.free.dto.FreeDto;
import ynzmz.server.board.free.entity.Free;
import ynzmz.server.board.free.mapper.FreeMapper;
import ynzmz.server.board.free.repository.FreeRepository;
import ynzmz.server.board.free.service.FreeService;
import ynzmz.server.dto.MultiResponseDto;
import ynzmz.server.dto.SingleResponseDto;
import ynzmz.server.member.entity.Member;
import ynzmz.server.member.service.MemberService;

import java.util.List;

@RestController
@RequestMapping("/boards/frees")
@RequiredArgsConstructor
@Slf4j
public class FreeController {
    private final FreeService freeService;
    private final MemberService memberService;
    private final FreeMapper freeMapper;


    @PostMapping(produces =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> postFree(@RequestBody FreeDto.post post){
        Free free = freeMapper.freePostToFree(post);
        free.setMember(loginMemberFindByToken());

        freeService.createFree(free);
        FreeDto.DetailResponse Response = freeMapper.freeToFreeDetailResponse(free);
        return new ResponseEntity<>(new SingleResponseDto<>(Response),HttpStatus.CREATED);
    }


    @GetMapping("/{free-id}")
    public ResponseEntity<?> getDetailFree(@PathVariable("free-id") long Id) {
        Free foundFree = freeService.findFreeById(Id);
        FreeDto.DetailResponse response = freeMapper.freeToFreeDetailResponse(foundFree);

    return new ResponseEntity<>(new SingleResponseDto<>(response),HttpStatus.OK);
    }
    @GetMapping()
    public ResponseEntity<?> getListedFree(@RequestParam int page) {
        Page<Free> foundFreePage = freeService.findAllFree(page-1);
        List<Free> listFoundFree = foundFreePage.getContent();
        List<FreeDto.ListResponse> responses = freeMapper.freesToFreeListResponses(listFoundFree);
        return new ResponseEntity<>(new MultiResponseDto<>(responses,foundFreePage),HttpStatus.OK);
    }

    @PatchMapping("/{free-id}")
    public ResponseEntity<?> patchFree(@PathVariable("free-id")long id, @RequestBody FreeDto.patch freepatch) {
        memberService.memberValidation(loginMemberFindByToken(), freeService.findFreeById(id).getMember().getMemberId());

        Free free = freeMapper.freePatchToFree(freepatch);
        free.setFreeId(id);

        Free newFree = freeService.updateFree(free);
        FreeDto.DetailResponse freeDetailResponse = freeMapper.freeToFreeDetailResponse(newFree);


        return new ResponseEntity<>(new SingleResponseDto<>(freeDetailResponse),HttpStatus.OK);
    }

    @DeleteMapping("/{free-id}")
    public ResponseEntity<?> deleteFree(@PathVariable("free-id") long id){
        freeService.deleteFree(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private Member loginMemberFindByToken(){
        String loginEmail = SecurityContextHolder.getContext().getAuthentication().getName(); // 토큰에서 유저 email 확인
        return memberService.findMemberByEmail(loginEmail);
    }


}

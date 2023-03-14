package ynzmz.server.member.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Parameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ynzmz.server.member.Service.MemberService;
import ynzmz.server.member.dto.MemberDto;
import ynzmz.server.member.dto.MemberPatchDto;
import ynzmz.server.member.dto.MemberPostDto;
import ynzmz.server.member.entity.Member;
import ynzmz.server.member.mapper.MemberMapper;
import ynzmz.server.member.repository.MemberRepository;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
@Slf4j
@RequiredArgsConstructor
public class MemberController {

    private final MemberMapper memberMapper;
    private final MemberService memberService;

    @PostMapping
    public ResponseEntity postMember(@RequestBody @Valid MemberPostDto requestBody){
        Member member = memberMapper.memberPostDtoToMember(requestBody);
        Member createdMember = memberService.createMember(member);
        MemberDto response=  memberMapper.memberToMemberResponse(createdMember);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PatchMapping("/{member-id}")
    public ResponseEntity patchMember(@PathVariable("member-id") @Positive long memberId, @RequestBody @Valid MemberPatchDto requestBody) {
        requestBody.setMemberId(memberId);
        Member member = memberService.updateMember(memberMapper.memberPatchDtoToMember(requestBody));
        Member updatedMember = memberService.updateMember(member);
        MemberDto response=  memberMapper.memberToMemberResponse(updatedMember);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}

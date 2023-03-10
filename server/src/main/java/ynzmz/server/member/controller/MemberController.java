package ynzmz.server.member.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ynzmz.server.member.Service.MemberService;
import ynzmz.server.member.dto.MemberDto;
import ynzmz.server.member.dto.MemberPostDto;
import ynzmz.server.member.entity.Member;
import ynzmz.server.member.mapper.MemberMapper;
import ynzmz.server.member.repository.MemberRepository;

import javax.validation.Valid;

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
        return member;
    }


}

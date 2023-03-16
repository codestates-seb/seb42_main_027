package ynzmz.server.member.controller;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Parameter;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import ynzmz.server.dto.MultiResponseDto;
import ynzmz.server.member.service.MemberService;
import ynzmz.server.member.dto.MemberDto;
import ynzmz.server.member.dto.MemberPatchDto;
import ynzmz.server.member.dto.MemberPostDto;
import ynzmz.server.member.entity.Member;
import ynzmz.server.member.mapper.MemberMapper;
import ynzmz.server.member.repository.MemberRepository;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/members")
@Setter
public class MemberController {
    private final MemberMapper memberMapper;
    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<?> postMember(@RequestBody @Valid MemberPostDto requestBody){
        Member member = memberMapper.memberPostDtoToMember(requestBody);
        member.setMemberState(Member.MemberState.STUDENT);
        Member createdMember = memberService.createMember(member);
        MemberDto response=  memberMapper.memberToMemberResponse(createdMember);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PatchMapping("/{member-id}")
    public ResponseEntity<?> patchMember(@PathVariable("member-id") @Positive long memberId, @RequestBody @Valid MemberPatchDto requestBody) {
        requestBody.setMemberId(memberId);
        Member member = memberService.updateMember(memberMapper.memberPatchDtoToMember(requestBody));
        Member updatedMember = memberService.updateMember(member);
        MemberDto response=  memberMapper.memberToMemberResponse(updatedMember);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/{member-id}")
    public ResponseEntity<?> getMember(@PathVariable("member-id") @Positive long memberId){
        Member findMember = memberService.findMemberById(memberId);
        MemberDto response = memberMapper.memberToMemberResponse(findMember);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getMembers(@Positive @RequestParam int page,
                                        @Positive @RequestParam int size) {
        Page<Member> pageMembers = memberService.findMembers(page-1, size);
        List<Member> members = pageMembers.getContent();
        return new ResponseEntity<>(new MultiResponseDto<>(memberMapper.memberToMemberResponses(members),pageMembers),HttpStatus.OK);
    }

    @DeleteMapping("/{member-id}")
    public ResponseEntity<?> deleteMember(@PathVariable("member-id") @Positive long memberId){
//        boolean deleteStatus = memberService.deleteMember(memberId);
//        return deleteStatus ? new ResponseEntity<>("삭제완료", HttpStatus.OK) : new ResponseEntity<>("삭제실패", HttpStatus.INTERNAL_SERVER_ERROR);
        memberService.deleteMember(memberId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}

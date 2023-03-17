package ynzmz.server.member.controller;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ynzmz.server.dto.MultiResponseDto;
import ynzmz.server.member.service.MemberService;
import ynzmz.server.member.dto.MemberDto;
import ynzmz.server.member.entity.Member;
import ynzmz.server.member.mapper.MemberMapper;

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
    public ResponseEntity<?> postMember(@RequestBody @Valid MemberDto.Post requestBody){
        if (!requestBody.getPassword().equals(requestBody.getConfirmPassword())) {
            return ResponseEntity.badRequest().body("패스워드와 패스워드 확인이 일치하지 않습니다.");
        }
        Member member = memberMapper.memberPostToMember(requestBody);
        member.setState(Enum.valueOf( Member.State.class, requestBody.getState() ));

        Member createdMember = memberService.createMember(member);
        MemberDto.Response response=  memberMapper.memberToMemberResponse(createdMember);
        member.setCreatedAt(requestBody.getCreatedAt());

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PatchMapping("/{member-id}")
    public ResponseEntity<?> patchMember(@PathVariable("member-id") @Positive long memberId, @RequestBody @Valid MemberDto.Patch requestBody) {
        Member member = memberMapper.memberPatchToMember(requestBody);
        member.setMemberId(memberId);

        Member updatedMember = memberService.updateMember(member);
        MemberDto.Response response=  memberMapper.memberToMemberResponse(updatedMember);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/{email}")
    public ResponseEntity<?> getMember(@PathVariable("email") @Positive String email){
        Member findMember = memberService.findMemberByEmail(email);
        MemberDto.Response response = memberMapper.memberToMemberResponse(findMember);

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

    //내가쓴 강의리뷰조회
    @GetMapping("/{member-id}/review")
    public ResponseEntity<?> getMemberReview(@PathVariable("member-id") @Positive long memberId){
        Member findMember = memberService.findMemberById(memberId);
        MemberDto.MyLectureReview response = memberMapper.memberToMemberMyLectureReview(findMember);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    //내가쓴 질문조회
//    @GetMapping("/{member-id}/question")
//    public ResponseEntity<?> getMemberQuestion(@PathVariable("member-id") @Positive long memberId){
//        Member findMember = memberService.findMemberById(memberId);
//        MemberQuestionResponseDto response = memberMapper.memberToMemberQuestionResponse(findMember);
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }


}

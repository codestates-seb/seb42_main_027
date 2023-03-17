package ynzmz.server.member.mapper;


import org.mapstruct.Mapper;
import ynzmz.server.member.dto.*;
import ynzmz.server.member.entity.Member;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    Member memberPostToMember(MemberDto.Post memberPostDto);
    Member memberPatchToMember(MemberDto.Patch memberPatchDto);
    MemberDto.Response memberToMemberResponse (Member member);
    MemberDto.SimpleInfoResponse memberToMemberSimpleInfoResponse(MemberDto.SimpleInfoResponse simpleInfoResponse);
    List<MemberDto.Response> memberToMemberResponses(List<Member> members);
    MemberDto.MyLectureReview memberToMemberMyLectureReview(Member member);
//    MemberQuestionResponseDto memberToMemberQuestionResponse(Member member);

}

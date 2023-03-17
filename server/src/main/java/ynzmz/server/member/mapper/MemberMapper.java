package ynzmz.server.member.mapper;


import org.mapstruct.Mapper;
import ynzmz.server.member.dto.*;
import ynzmz.server.member.dto.response.MemberReviewResponseDto;
import ynzmz.server.member.entity.Member;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    Member memberPostDtoToMember(MemberPostDto memberPostDto);
    Member memberPatchDtoToMember(MemberPatchDto memberPatchDto);
    MemberDto memberToMemberResponse (Member member);
    MemberReviewResponseDto memberToMemberReviewResponse(Member member);
//    MemberQuestionResponseDto memberToMemberQuestionResponse(Member member);
    List<MemberDto> memberToMemberResponses(List<Member> members);

}

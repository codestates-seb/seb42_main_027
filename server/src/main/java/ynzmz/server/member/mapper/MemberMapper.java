package ynzmz.server.member.mapper;


import org.mapstruct.Mapper;
import ynzmz.server.member.dto.*;
import ynzmz.server.member.entity.Member;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    Member memberPostDtoToMember(MemberPostDto memberPostDto);
    Member memberPatchDtoToMember(MemberPatchDto memberPatchDto);
    MemberDto memberToMemberResponse (Member member);

}

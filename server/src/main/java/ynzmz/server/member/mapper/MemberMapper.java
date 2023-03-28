package ynzmz.server.member.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ynzmz.server.member.dto.*;
import ynzmz.server.member.entity.Member;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MemberMapper {

    Member memberPostToMember(MemberDto.Post memberPostDto);
    Member memberPatchToMember(MemberDto.Patch memberPatchDto);
    MemberDto.Response memberToMemberResponse (Member member);
    MemberDto.SimpleInfoResponse memberToMemberSimpleInfoResponse(Member member);
    List<MemberDto.Response> memberToMemberResponses(List<Member> members);
    MemberDto.ChangePassword memberToMemberChangePassword(MemberDto.ChangePassword changePassword);


//    MemberDto.MyLectureReview memberToMemberMyLectureReview(Member member);
//    MemberQuestionResponseDto memberToMemberQuestionResponse(Member member);
//    List<QuestionDto.SimpleInfoResponse> memberToMemberMyQuestions(List<Question> questions);

}

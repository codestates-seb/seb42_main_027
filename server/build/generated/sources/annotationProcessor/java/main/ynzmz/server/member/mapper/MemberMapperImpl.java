package ynzmz.server.member.mapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ynzmz.server.member.dto.MemberDto;
import ynzmz.server.member.dto.MemberPatchDto;
import ynzmz.server.member.dto.MemberPostDto;
import ynzmz.server.member.dto.response.MemberReviewResponseDto;
import ynzmz.server.member.entity.Member;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-17T12:03:23+0900",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.1.jar, environment: Java 11.0.17 (Azul Systems, Inc.)"
)
@Component
public class MemberMapperImpl implements MemberMapper {

    @Override
    public Member memberPostDtoToMember(MemberPostDto memberPostDto) {
        if ( memberPostDto == null ) {
            return null;
        }

        Member member = new Member();

        member.setUsername( memberPostDto.getUsername() );
        member.setPhoneNumber( memberPostDto.getPhoneNumber() );
        member.setEmail( memberPostDto.getEmail() );
        member.setDisplayName( memberPostDto.getDisplayName() );
        member.setPassword( memberPostDto.getPassword() );
        if ( memberPostDto.getMemberState() != null ) {
            member.setMemberState( Enum.valueOf( Member.MemberState.class, memberPostDto.getMemberState() ) );
        }

        return member;
    }

    @Override
    public Member memberPatchDtoToMember(MemberPatchDto memberPatchDto) {
        if ( memberPatchDto == null ) {
            return null;
        }

        Member member = new Member();

        member.setPhoneNumber( memberPatchDto.getPhoneNumber() );
        member.setMemberId( memberPatchDto.getMemberId() );
        member.setDisplayName( memberPatchDto.getDisplayName() );
        member.setPassword( memberPatchDto.getPassword() );
        member.setIconImageUrl( memberPatchDto.getIconImageUrl() );

        return member;
    }

    @Override
    public MemberDto memberToMemberResponse(Member member) {
        if ( member == null ) {
            return null;
        }

        String username = null;
        String phoneNumber = null;
        Long memberId = null;
        String email = null;
        String password = null;
        String displayName = null;
        String memberState = null;
        LocalDateTime createdAt = null;

        username = member.getUsername();
        phoneNumber = member.getPhoneNumber();
        memberId = member.getMemberId();
        email = member.getEmail();
        password = member.getPassword();
        displayName = member.getDisplayName();
        if ( member.getMemberState() != null ) {
            memberState = member.getMemberState().name();
        }
        if ( member.getCreatedAt() != null ) {
            createdAt = LocalDateTime.parse( member.getCreatedAt() );
        }

        String iconImageUrl = null;

        MemberDto memberDto = new MemberDto( username, phoneNumber, memberId, email, password, displayName, iconImageUrl, memberState, createdAt );

        memberDto.setIconImageUrl( member.getIconImageUrl() );

        return memberDto;
    }

    @Override
    public MemberReviewResponseDto memberToMemberReviewResponse(Member member) {
        if ( member == null ) {
            return null;
        }

        MemberReviewResponseDto memberReviewResponseDto = new MemberReviewResponseDto();

        return memberReviewResponseDto;
    }

    @Override
    public List<MemberDto> memberToMemberResponses(List<Member> members) {
        if ( members == null ) {
            return null;
        }

        List<MemberDto> list = new ArrayList<MemberDto>( members.size() );
        for ( Member member : members ) {
            list.add( memberToMemberResponse( member ) );
        }

        return list;
    }
}

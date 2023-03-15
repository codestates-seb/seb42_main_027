package ynzmz.server.member.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ynzmz.server.member.dto.MemberDto;
import ynzmz.server.member.dto.MemberPatchDto;
import ynzmz.server.member.dto.MemberPostDto;
import ynzmz.server.member.entity.Member;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-16T02:00:37+0900",
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

        member.setEmail( memberPostDto.getEmail() );
        member.setDisplayName( memberPostDto.getDisplayName() );
        member.setPassword( memberPostDto.getPassword() );

        return member;
    }

    @Override
    public Member memberPatchDtoToMember(MemberPatchDto memberPatchDto) {
        if ( memberPatchDto == null ) {
            return null;
        }

        Member member = new Member();

        member.setMemberId( memberPatchDto.getMemberId() );
        member.setDisplayName( memberPatchDto.getDisplayName() );
        member.setPassword( memberPatchDto.getPassword() );

        return member;
    }

    @Override
    public MemberDto memberToMemberResponse(Member member) {
        if ( member == null ) {
            return null;
        }

        Long memberId = null;
        String email = null;
        String password = null;
        String displayName = null;
        String createdAt = null;

        memberId = member.getMemberId();
        email = member.getEmail();
        password = member.getPassword();
        displayName = member.getDisplayName();
        createdAt = member.getCreatedAt();

        String iconImageUrl = null;

        MemberDto memberDto = new MemberDto( memberId, email, password, displayName, iconImageUrl, createdAt );

        memberDto.setIconImageUrl( member.getIconImageUrl() );

        return memberDto;
    }
}

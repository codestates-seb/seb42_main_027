package ynzmz.server.member.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ynzmz.server.member.dto.MemberDto;
import ynzmz.server.member.entity.Member;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-19T01:16:27+0900",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.1.jar, environment: Java 11.0.17 (Azul Systems, Inc.)"
)
@Component
public class MemberMapperImpl implements MemberMapper {

    @Override
    public Member memberPostToMember(MemberDto.Post memberPostDto) {
        if ( memberPostDto == null ) {
            return null;
        }

        Member member = new Member();

        member.setUsername( memberPostDto.getUsername() );
        member.setPhoneNumber( memberPostDto.getPhoneNumber() );
        member.setEmail( memberPostDto.getEmail() );
        member.setDisplayName( memberPostDto.getDisplayName() );
        member.setPassword( memberPostDto.getPassword() );
        member.setCreatedAt( memberPostDto.getCreatedAt() );
        if ( memberPostDto.getState() != null ) {
            member.setState( Enum.valueOf( Member.State.class, memberPostDto.getState() ) );
        }

        return member;
    }

    @Override
    public Member memberPatchToMember(MemberDto.Patch memberPatchDto) {
        if ( memberPatchDto == null ) {
            return null;
        }

        Member member = new Member();

        member.setPhoneNumber( memberPatchDto.getPhoneNumber() );
        member.setDisplayName( memberPatchDto.getDisplayName() );
        member.setPassword( memberPatchDto.getPassword() );
        member.setIconImageUrl( memberPatchDto.getIconImageUrl() );

        return member;
    }

    @Override
    public MemberDto.Response memberToMemberResponse(Member member) {
        if ( member == null ) {
            return null;
        }

        String username = null;
        String phoneNumber = null;
        Long memberId = null;
        String email = null;
        String displayName = null;
        String state = null;
        String createdAt = null;

        username = member.getUsername();
        phoneNumber = member.getPhoneNumber();
        memberId = member.getMemberId();
        email = member.getEmail();
        displayName = member.getDisplayName();
        if ( member.getState() != null ) {
            state = member.getState().name();
        }
        createdAt = member.getCreatedAt();

        String iconImageUrl = null;

        MemberDto.Response response = new MemberDto.Response( username, phoneNumber, memberId, email, displayName, iconImageUrl, state, createdAt );

        response.setIconImageUrl( member.getIconImageUrl() );

        return response;
    }

    @Override
    public MemberDto.SimpleInfoResponse memberToMemberSimpleInfoResponse(MemberDto.SimpleInfoResponse simpleInfoResponse) {
        if ( simpleInfoResponse == null ) {
            return null;
        }

        Long memberId = null;
        String displayName = null;
        String state = null;

        memberId = simpleInfoResponse.getMemberId();
        displayName = simpleInfoResponse.getDisplayName();
        state = simpleInfoResponse.getState();

        String iconImageUrl = null;

        MemberDto.SimpleInfoResponse simpleInfoResponse1 = new MemberDto.SimpleInfoResponse( memberId, displayName, iconImageUrl, state );

        simpleInfoResponse1.setIconImageUrl( simpleInfoResponse.getIconImageUrl() );

        return simpleInfoResponse1;
    }

    @Override
    public List<MemberDto.Response> memberToMemberResponses(List<Member> members) {
        if ( members == null ) {
            return null;
        }

        List<MemberDto.Response> list = new ArrayList<MemberDto.Response>( members.size() );
        for ( Member member : members ) {
            list.add( memberToMemberResponse( member ) );
        }

        return list;
    }

    @Override
    public MemberDto.MyLectureReview memberToMemberMyLectureReview(Member member) {
        if ( member == null ) {
            return null;
        }

        MemberDto.MyLectureReview myLectureReview = new MemberDto.MyLectureReview();

        return myLectureReview;
    }
}

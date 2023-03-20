package ynzmz.server.board.free.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ynzmz.server.board.free.dto.FreeDto;
import ynzmz.server.board.free.entity.Free;
import ynzmz.server.member.dto.MemberDto;
import ynzmz.server.member.entity.Member;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-20T14:07:11+0900",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.1.jar, environment: Java 11.0.17 (Azul Systems, Inc.)"
)
@Component
public class FreeMapperImpl implements FreeMapper {

    @Override
    public Free freePostToFree(FreeDto.post p) {
        if ( p == null ) {
            return null;
        }

        Free free = new Free();

        free.setTitle( p.getTitle() );
        free.setContent( p.getContent() );
        free.setCategory( p.getCategory() );
        free.setCreatedAt( p.getCreatedAt() );

        return free;
    }

    @Override
    public Free freePatchToFree(FreeDto.patch p) {
        if ( p == null ) {
            return null;
        }

        Free free = new Free();

        free.setTitle( p.getTitle() );
        free.setContent( p.getContent() );
        free.setCategory( p.getCategory() );
        free.setModifiedAt( p.getModifiedAt() );

        return free;
    }

    @Override
    public FreeDto.DetailResponse freeToFreeDetailResponse(Free free) {
        if ( free == null ) {
            return null;
        }

        FreeDto.DetailResponse.DetailResponseBuilder detailResponse = FreeDto.DetailResponse.builder();

        detailResponse.freeId( free.getFreeId() );
        detailResponse.title( free.getTitle() );
        detailResponse.content( free.getContent() );
        detailResponse.category( free.getCategory() );
        detailResponse.viewCount( free.getViewCount() );
        detailResponse.voteCount( free.getVoteCount() );
        detailResponse.createdAt( free.getCreatedAt() );
        detailResponse.modifiedAt( free.getModifiedAt() );
        detailResponse.member( memberToSimpleInfoResponse( free.getMember() ) );

        return detailResponse.build();
    }

    @Override
    public FreeDto.ListResponse freeToFreeListResponse(Free free) {
        if ( free == null ) {
            return null;
        }

        FreeDto.ListResponse.ListResponseBuilder listResponse = FreeDto.ListResponse.builder();

        listResponse.freeId( free.getFreeId() );
        listResponse.title( free.getTitle() );
        listResponse.content( free.getContent() );
        listResponse.category( free.getCategory() );
        listResponse.viewCount( free.getViewCount() );
        listResponse.voteCount( free.getVoteCount() );
        listResponse.createdAt( free.getCreatedAt() );
        listResponse.modifiedAt( free.getModifiedAt() );
        listResponse.member( memberToSimpleInfoResponse( free.getMember() ) );
        listResponse.commentsListNum( free.getCommentsListNum() );

        return listResponse.build();
    }

    @Override
    public List<FreeDto.ListResponse> freesToFreeListResponses(List<Free> frees) {
        if ( frees == null ) {
            return null;
        }

        List<FreeDto.ListResponse> list = new ArrayList<FreeDto.ListResponse>( frees.size() );
        for ( Free free : frees ) {
            list.add( freeToFreeListResponse( free ) );
        }

        return list;
    }

    protected MemberDto.SimpleInfoResponse memberToSimpleInfoResponse(Member member) {
        if ( member == null ) {
            return null;
        }

        Long memberId = null;
        String displayName = null;
        String iconImageUrl = null;
        String state = null;

        memberId = member.getMemberId();
        displayName = member.getDisplayName();
        iconImageUrl = member.getIconImageUrl();
        if ( member.getState() != null ) {
            state = member.getState().name();
        }

        MemberDto.SimpleInfoResponse simpleInfoResponse = new MemberDto.SimpleInfoResponse( memberId, displayName, iconImageUrl, state );

        return simpleInfoResponse;
    }
}
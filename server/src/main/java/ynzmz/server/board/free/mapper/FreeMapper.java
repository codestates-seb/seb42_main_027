package ynzmz.server.board.free.mapper;


import java.util.ArrayList;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ynzmz.server.board.free.entity.Free;
import ynzmz.server.board.free.dto.FreeDto;
import ynzmz.server.comment.free.dto.FreeCommentDto;
import ynzmz.server.comment.free.entity.FreeComment;
import ynzmz.server.member.dto.MemberDto;
import ynzmz.server.member.entity.Member;
import ynzmz.server.member.mapper.MemberMapper;


import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FreeMapper {
    Free freePostToFree(FreeDto.post p);
    Free freePatchToFree(FreeDto.patch p);
//---------------------------Response-----------------------
//    FreeDto.DetailResponse freeToFreeDetailResponse(Free free);
    default FreeDto.DetailResponse freeToFreeDetailResponse(Free free){
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
        detailResponse.comments( freeCommentListToResponseForFreeDetailList( free.getComments() ) );
        detailResponse.commentsListNum( free.getComments().size() );

        return detailResponse.build();
    }

//    FreeDto.ListResponse freeToFreeListResponse(Free free);

    default FreeDto.ListResponse freeToFreeListResponse(Free free) {
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
        listResponse.commentsListNum( free.getComments().size() );

        return listResponse.build();
    }

    List<FreeDto.ListResponse> freesToFreeListResponses(List<Free> frees);



    default MemberDto.SimpleInfoResponse memberToSimpleInfoResponse(Member member) {
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

    default FreeCommentDto.Response freeCommentToResponse(FreeComment freeComment) {
        if ( freeComment == null ) {
            return null;
        }

        long freeCommentId = 0L;
        Free free = null;
        String content = null;
        String createdAt = null;
        String modifiedAt = null;
        long voteCount = 0L;
        MemberDto.SimpleInfoResponse member = null;
        boolean memberSim = false;

        if ( freeComment.getFreeCommentId() != null ) {
            freeCommentId = freeComment.getFreeCommentId();
        }
        free = freeComment.getFree();
        content = freeComment.getContent();
        createdAt = freeComment.getCreatedAt();
        modifiedAt = freeComment.getModifiedAt();
        voteCount = freeComment.getVoteCount();
        member = memberToSimpleInfoResponse( freeComment.getMember() );
        memberSim = freeComment.isMemberSim();

        FreeCommentDto.Response response = new FreeCommentDto.Response( freeCommentId, content, createdAt, modifiedAt, voteCount, member, memberSim );

        return response;
    }
        default FreeCommentDto.ResponseForFreeDetail freeCommentToResponseForFreeDetail(FreeComment freeComment) {
        if ( freeComment == null ) {
            return null;
        }

        long freeCommentId = 0L;

        String content = null;
        String createdAt = null;
        String modifiedAt = null;
        long voteCount = 0L;
        MemberDto.SimpleInfoResponse member = null;
        boolean memberSim = false;

        if ( freeComment.getFreeCommentId() != null ) {
            freeCommentId = freeComment.getFreeCommentId();
        }
        content = freeComment.getContent();
        createdAt = freeComment.getCreatedAt();
        modifiedAt = freeComment.getModifiedAt();
        voteCount = freeComment.getVoteCount();
        member = memberToSimpleInfoResponse( freeComment.getMember() );
        memberSim = freeComment.isMemberSim();

        FreeCommentDto.ResponseForFreeDetail response = new FreeCommentDto.ResponseForFreeDetail( freeCommentId, content, createdAt, modifiedAt, voteCount, member, memberSim );

        return response;
    }

    default List<FreeCommentDto.Response> freeCommentListToResponseList(List<FreeComment> list) {
        if ( list == null ) {
            return null;
        }

        List<FreeCommentDto.Response> list1 = new ArrayList<FreeCommentDto.Response>( list.size() );
        for ( FreeComment freeComment : list ) {
            list1.add( freeCommentToResponse( freeComment ) );
        }

        return list1;
    }

    default List<FreeCommentDto.ResponseForFreeDetail> freeCommentListToResponseForFreeDetailList(List<FreeComment> list) {
        if ( list == null ) {
            return null;
        }

        List<FreeCommentDto.ResponseForFreeDetail> list1 = new ArrayList<FreeCommentDto.ResponseForFreeDetail>( list.size() );
        for ( FreeComment freeComment : list ) {
            list1.add( freeCommentToResponseForFreeDetail( freeComment ) );
        }

        return list1;
    }
}

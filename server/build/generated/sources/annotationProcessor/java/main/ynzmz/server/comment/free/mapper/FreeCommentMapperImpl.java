package ynzmz.server.comment.free.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ynzmz.server.comment.free.dto.FreeCommentDto;
import ynzmz.server.comment.free.entity.FreeComment;
import ynzmz.server.member.dto.MemberDto;
import ynzmz.server.member.entity.Member;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-23T03:22:24+0900",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.1.jar, environment: Java 11.0.17 (Azul Systems, Inc.)"
)
@Component
public class FreeCommentMapperImpl implements FreeCommentMapper {

    @Override
    public FreeComment freeCommentPostToFreeComment(FreeCommentDto.Post FreeCommentPostDto) {
        if ( FreeCommentPostDto == null ) {
            return null;
        }

        FreeComment freeComment = new FreeComment();

        freeComment.setContent( FreeCommentPostDto.getContent() );
        freeComment.setCreatedAt( FreeCommentPostDto.getCreatedAt() );

        return freeComment;
    }

    @Override
    public FreeComment freeCommentPatchToFreeComment(FreeCommentDto.Patch FreeCommentPatchDto) {
        if ( FreeCommentPatchDto == null ) {
            return null;
        }

        FreeComment freeComment = new FreeComment();

        freeComment.setContent( FreeCommentPatchDto.getContent() );
        freeComment.setModifiedAt( FreeCommentPatchDto.getModifiedAt() );

        return freeComment;
    }

    @Override
    public FreeCommentDto.Response freeCommentToFreeCommentResponse(FreeComment freeComment) {
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

        FreeCommentDto.Response response = new FreeCommentDto.Response( freeCommentId, content, createdAt, modifiedAt, voteCount, member, memberSim );

        return response;
    }

    @Override
    public List<FreeCommentDto.SimpleResponse> freeCommentToFreeCommentsResponses(List<FreeComment> freeComments) {
        if ( freeComments == null ) {
            return null;
        }

        List<FreeCommentDto.SimpleResponse> list = new ArrayList<FreeCommentDto.SimpleResponse>( freeComments.size() );
        for ( FreeComment freeComment : freeComments ) {
            list.add( freeCommentToSimpleResponse( freeComment ) );
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

    protected FreeCommentDto.SimpleResponse freeCommentToSimpleResponse(FreeComment freeComment) {
        if ( freeComment == null ) {
            return null;
        }

        long freeCommentId = 0L;
        String content = null;
        String createdAt = null;
        String modifiedAt = null;
        long voteCount = 0L;

        if ( freeComment.getFreeCommentId() != null ) {
            freeCommentId = freeComment.getFreeCommentId();
        }
        content = freeComment.getContent();
        createdAt = freeComment.getCreatedAt();
        modifiedAt = freeComment.getModifiedAt();
        voteCount = freeComment.getVoteCount();

        FreeCommentDto.SimpleResponse simpleResponse = new FreeCommentDto.SimpleResponse( freeCommentId, content, createdAt, modifiedAt, voteCount );

        return simpleResponse;
    }
}
package ynzmz.server.comment.free.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ynzmz.server.board.free.entity.Free;
import ynzmz.server.comment.free.dto.FreeCommentDto;
import ynzmz.server.comment.free.entity.FreeComment;
import ynzmz.server.member.entity.Member;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-19T19:13:27+0900",
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
        Free free = null;
        String content = null;
        String createdAt = null;
        String modifiedAt = null;
        long voteCount = 0L;
        Member member = null;
        boolean memberSim = false;

        if ( freeComment.getFreeCommentId() != null ) {
            freeCommentId = freeComment.getFreeCommentId();
        }
        free = freeComment.getFree();
        content = freeComment.getContent();
        createdAt = freeComment.getCreatedAt();
        modifiedAt = freeComment.getModifiedAt();
        voteCount = freeComment.getVoteCount();
        member = freeComment.getMember();
        memberSim = freeComment.isMemberSim();

        FreeCommentDto.Response response = new FreeCommentDto.Response( freeCommentId, free, content, createdAt, modifiedAt, voteCount, member, memberSim );

        return response;
    }

    @Override
    public List<FreeCommentDto.Response> freeCommentToFreeCommentsResponses(List<FreeComment> freeComments) {
        if ( freeComments == null ) {
            return null;
        }

        List<FreeCommentDto.Response> list = new ArrayList<FreeCommentDto.Response>( freeComments.size() );
        for ( FreeComment freeComment : freeComments ) {
            list.add( freeCommentToFreeCommentResponse( freeComment ) );
        }

        return list;
    }
}
